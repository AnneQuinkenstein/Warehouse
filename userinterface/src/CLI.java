import admin.*;
import cargo.Hazard;
import eventInfrastrukture.GenericEventHandler;
import eventInfrastrukture.InsertCargoEvent;
import eventInfrastrukture.NumberEvent;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.Scanner;

public class CLI {
    protected final Warehouse model;
    private GenericEventHandler<InsertCargoEvent> insertCargoHandler;
    private GenericEventHandler<NumberEvent> deleteCargoHandler;
    private GenericEventHandler<NumberEvent> updateCargoHandler;
    private GenericEventHandler readHandler;



    public void setInsertCargoHandler(GenericEventHandler <InsertCargoEvent> handler) { this.insertCargoHandler = handler; }
    public void setDeleteCargoHandler(GenericEventHandler <NumberEvent> handler){ this.deleteCargoHandler = handler; }
    public void setUpdateCargoHandler(GenericEventHandler <NumberEvent> handler){ this.updateCargoHandler = handler; }
    public void setReadHandler(GenericEventHandler handler){ this.readHandler = handler; }


    public CLI(Warehouse model){
        this.model = model;
     }

    public void execute() {
        Command input;
        try (Scanner s = new Scanner(System.in)) {
            do {
                String message = "enter command: ";
                System.out.println(message);

                input = new Command(s.nextLine());

                switch (input.operator) {
                    case CREATE -> {

                        if (input.input.length < 7) {
                            System.out.println("You have to add all necessary arguments: [Operator] [Fracht-Typ] [K-Name] [Wert] [kommaseparierte Gefahrenstoffe] [[fragile(true/false)]] or [[pressurized(true/false)]] [grainSize]!");
                            break;
                        }

                        Cargotype cargotype;
                        try {
                           cargotype = Cargotype.valueOf(input.input[1]);
                        } catch (IllegalArgumentException e){
                            System.out.println("Unsupported Cargotype: try DRYBULKCARGO, LIQUIDBULDCARGO or UNITISEDCARGO");
                            break;
                        }

                        String customer = input.input[2];
                        BigDecimal wert = new BigDecimal(input.input[3]);
                        List <Hazard> hazards = splitHazards(input.input[4]);
                        boolean fragileOrPressurized = Boolean.parseBoolean(input.input[5]);
                        int grainSize = Integer.parseInt(input.input[6]);

                        InsertCargoEvent event = new InsertCargoEvent(this, cargotype, customer, wert, hazards, fragileOrPressurized, grainSize);
                        if (null != this.insertCargoHandler) insertCargoHandler.handle(event);

                    }

                    case READ -> {

                        if (null != this.readHandler) readHandler.handle(new EventObject(this));
                    }

                    case UPDATE -> {

                        if (input.input.length < 2) {
                            System.out.println("update requires the location! ");
                            break;
                        }

                        int location = Integer.parseInt(input.input[1]);
                        NumberEvent event = new NumberEvent(this, location);
                        if (null != this.updateCargoHandler) updateCargoHandler.handle(event);
                    }

                    case DELETE -> {

                        if (input.input.length < 2) {
                            System.out.println("delete requires the location! ");
                            break;
                        }

                        int location = Integer.parseInt(input.input[1]);
                        NumberEvent event = new NumberEvent(this, location);
                        if (null != this.deleteCargoHandler) deleteCargoHandler.handle(event);
                    }

                    case ERROR -> System.out.println("error");
                }
            } while (true);
        }

    }




    private List<Hazard> splitHazards(String input){
        String[] hazardStrings = input.split(",");
        return  Arrays.stream(hazardStrings).map(name -> Hazard.valueOf(name)).toList();
    }



}
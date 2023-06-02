import Simulation1.Consumer;
import Simulation1.Producer;
import admin.Warehouse;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
       Warehouse model = null; 
        
            int input;
            boolean notANumber = true;
            String message = "How big is the capacity of the warehouse?";
            try (Scanner s = new Scanner(System.in)) {
                do {
                    System.out.println(message);
                    try
                    {
                        input = Integer.parseInt(s.nextLine());
                        model = new Warehouse(input); 
                        notANumber = false;
                    }
                    catch (NumberFormatException e)
                    {
                        message = "This was not a Number. Please try again to insert the capacity: ";
                    }
                }while (notANumber);
            }
        

        new Producer(model).start();
        new Consumer(model).start();


        //  Warehouse model = new Warehouse();
        // CLI console = new CLI(model);
        
        
      /*  GenericEventHandler <InsertCargoEvent> insertCargoHandler = new GenericEventHandler <>();
        GenericEventListener <InsertCargoEvent> insertListener = new InsertCargoListener(model);
        insertCargoHandler.add(insertListener);
        console.setInsertCargoHandler(insertCargoHandler);
        //TODO soll InsertCargoEvent aufgelöst werden in ein GenericEvent, das ein Objekt entgegen nimmt

        GenericEventHandler <NumberEvent> deleteCargoHandler = new GenericEventHandler<>();
        GenericEventListener <NumberEvent>  deleteListener = new DeleteCargoListener(model);
        deleteCargoHandler.add(deleteListener);
        console.setDeleteCargoHandler(deleteCargoHandler);

        GenericEventHandler <NumberEvent> updateCargoHandler = new GenericEventHandler<>();
        GenericEventListener <NumberEvent>  updateListener = new DeleteCargoListener(model);
        updateCargoHandler.add(updateListener);
        console.setUpdateCargoHandler(updateCargoHandler);

        GenericEventHandler<EventObject> readEventHandler = new GenericEventHandler<>();
        GenericEventListener<EventObject> readCargoListener = new ReadCargoListener(model);
        readEventHandler.add(readCargoListener);
        console.setReadHandler(readEventHandler);
        //TODO bei ReadHandler einfach EventObject reinschreiben?

        GenericEventHandler <StringEvent> outputHandler = new GenericEventHandler<>();
        GenericEventListener <StringEvent> infoListener = new InfoListener();
        outputHandler.add(infoListener);
        ((ReadCargoListener) readCargoListener).setOutputHandler(outputHandler);
        //TODO Problem, dass readCargoListener GenericEventListener ist und dort keine Methode setOutputHandler
        //doch ReadEventListener benutzen?
        */


     /*   CapacityObserver o1=new CapacityObserver(model);
        model.addObserver(o1);
        Observer.HazardsObserver o2=new Observer.HazardsObserver(model);
        model.addObserver(o2); */


       // console.execute();

    }

}
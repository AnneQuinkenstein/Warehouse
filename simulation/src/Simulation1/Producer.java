package Simulation1;

import admin.Cargotype;
import admin.Warehouse;
import cargo.Hazard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {
    private final Warehouse warehouse;
    private Random rand = new Random();
    private EnumSet<Cargotype> cargos = EnumSet.allOf(Cargotype.class);
    private Cargotype type;
    private String customer;
    private BigDecimal value;
    private List<Hazard> hazards;
    private boolean characteristics;
    private int grainSize;
    public Producer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void run() {
        while (true) {
            synchronized (this.warehouse){
                if (!this.warehouse.isFull()){
                    this.createMockCargoData();
                    System.out.printf("Ready to insert: Cargo %s from Customer %s priced %s with boolean %s and grainsize %s. %n" ,type.toString(), customer, value.toString(), characteristics, grainSize);
                    int location = this.warehouse.insert(type, customer, value, hazards, characteristics, grainSize);
                    System.out.println("Cargo safe in storage-location: " + location);
                }
            }
        }
    }


    private void createMockCargoData(){
        type = cargos.stream().skip(rand.nextInt(cargos.size())).findFirst().orElse(null);
        //TODO generics
        customer = "mockCustomer";
        value = BigDecimal.valueOf(Math.random());
        //   EnumSet<Hazard> hazardEnums = EnumSet.allOf(Hazard.class);
        hazards = new ArrayList<>();
        characteristics = Math.random() < 0.5;
        grainSize = rand.nextInt(50);
    }


}
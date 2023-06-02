package Simulation1;

import admin.CargoAbstr;
import admin.Warehouse;

import java.util.TreeMap;

public class Consumer extends Thread {
    private final Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void run() {
        while (true) {
            synchronized (this.warehouse) {
                TreeMap<Integer, CargoAbstr> storage = this.warehouse.read();
                if (!storage.isEmpty()) {
                    for (int location = 1; location <= warehouse.getCapacity(); location++) {
                        if (storage.containsKey(location)) {
                            warehouse.delete(location);
                          System.out.println("Cargo an Stelle " + location + " gelöscht.");
                          break;
                        }
                }

                }
            }
        }
    }
}
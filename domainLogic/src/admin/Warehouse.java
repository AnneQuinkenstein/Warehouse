package admin;


import administration.Customer;
import cargo.Cargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse extends Observable {

    private TreeMap<Integer, CargoAbstr> storage;
    private List<CustomerImpl> customers;

    final int capacity;

    public Warehouse(int capacity) {
        this.storage = new TreeMap<>();
        this.capacity = Math.abs(capacity);
        this.customers = new ArrayList<>();
    }

    public Warehouse() {
        this(10);
    }


    public boolean addCustomer(String name) {
        if (!customerExists(name)) {
            CustomerImpl customer = new CustomerImpl(name);
            customers.add(customer);
            return true;
        } else return false;
    }

    private boolean customerExists(String name) {
        //alternativ:
        // CustomerImpl customer = new CustomerImpl(name);
        // return customers.contains(customer);
        return customers.stream().map(customer -> customer.getName()).anyMatch(customerName -> customerName == name);
    }

    public int insert(Cargotype type, String customer, BigDecimal value, List<Hazard> hazards, boolean characteristics, int grainSize) {
        // characteristics = [[fragile(true/false)]] [[pressurized(true/false)]]
        if (this.isFull()) return -1;
//        if (!customerExists(customer)) return false;

        CargoAbstr cargo;
        switch (type) {
            case DRYBULKCARGO -> cargo = new DryBulkCargoImpl(customer, value, hazards, grainSize);
            case LIQUIDBULDCARGO -> cargo = new LiquidBulkCargoImpl(customer, value, hazards, characteristics);
            default -> {
                return -1;
            }
        }

        for (int location = 1; location <= capacity; location++) {
            if (!storage.containsKey(location)) {
                Date dateNow = new Date();
                cargo.setInputDate(dateNow);
                storage.put(location, cargo);
                cargo.setStorageLocation(location);
                this.setChanged();
                this.notifyObservers();
                return location;
            }
        }

        //viele unterschiedliche return false:  throw new IllegalStateException("no such ....");
        //oder Fehlercode?
        return -1;
    }

    public boolean isFull() {
        return storage.size() >= capacity;
    }

    //TODO als Collection evt. wegen Darstellung auf der Oberfläche GUI/CLI
    public TreeMap<Integer, CargoAbstr> read() {
        return new TreeMap<Integer, CargoAbstr>(this.storage);
    }

    public Cargo delete(int location) {
        return storage.remove(location);
    }

    public boolean inspect(int location) {
        if (this.storage.containsKey(location)) {
            this.storage.get(location).setInspectionDate(new Date());
            return true;
        } else {
            return false;
        }
        //oder mit Fehlercode falls Cargo nicht vorhanden
    }

    public Cargo remove(int location) {
        return this.storage.remove(location);
    }

    public boolean deleteCustomer(String name) {
        if (customers.isEmpty()) return false;

        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                customers.remove(customer);
                return true;
            }
        }
        return false;
    }

    public int getCapacity() {
        return this.capacity;
    }

}

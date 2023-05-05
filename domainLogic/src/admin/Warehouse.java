package admin;

import administration.Customer;
import cargo.Cargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse extends Observable {

    private TreeMap<Integer, CargoAbstr> warehouse;
    List<CustomerImpl> customers;

    final int capacity;

    public Warehouse(int capacity) {
        this.warehouse = new TreeMap<>();
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

    public boolean insert(Cargotype type, String customer, BigDecimal value, List<Hazard> hazards, boolean characteristics, int grainSize) {
        // characteristics = [[fragile(true/false)]] [[pressurized(true/false)]]
        if (this.isFull()) return false;
//        if (!customerExists(customer)) return false;

        CargoAbstr cargo;
        switch (type) {
            case DRYBULKCARGO -> cargo = new DryBulkCargoImpl(customer, value, hazards, grainSize);
            case LIQUIDBULDCARGO -> cargo = new LiquidBulkCargoImpl(customer, value, hazards, characteristics);
            default -> {
                return false;
            }
        }

        for (int location = 1; location <= capacity; location++) {
            if (!warehouse.containsKey(location)) {
                Date dateNow = new Date();
                cargo.setInputDate(dateNow);
                warehouse.put(location, cargo);
                this.setChanged();
                this.notifyObservers();
                return true;
            }
        }

        //viele unterschiedliche return false:  throw new IllegalStateException("no such ....");
        //oder Fehlercode?
        return false;
    }

    private boolean isFull() {
        return warehouse.size() >= capacity;
    }

    public TreeMap<Integer, CargoAbstr> read() {
        return new TreeMap<Integer, CargoAbstr>(this.warehouse);
    }

    public Cargo delete(int location) {
        return warehouse.remove(location);
    }

    public boolean inspect(int location) {
        if (this.warehouse.containsKey(location)) {
            this.warehouse.get(location).setInspectionDate(new Date());
            return true;
        } else {
            return false;
        }
        //oder mit Fehlercode falls Cargo nicht vorhanden
    }

    public Cargo remove(int location) {
        return this.warehouse.remove(location);
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

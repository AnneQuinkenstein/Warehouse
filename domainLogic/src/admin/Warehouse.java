package admin;


import administration.Customer;
import cargo.Cargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse extends Observable {

    private TreeMap<Integer, CargoAbstr> storage;
    /*
    Map         | Get | ContainsKey | Next | Data Structure
--------------|----------|-------------|----------|-------------------------
    HashMap     | O(1) | O(1) | O(h) | Hash Table
    LinkedHashMap | O(1) | O(1) | O(1) | Hash Table + Linked List
    TreeMap     | O(log n) | O(log n) | O(log n) | Red-black tr
     */
    //TODO: warum TreeMap und nicht HashMap? log n doch ab n =10 mehr als 1? - //
    // -weil Reihenfolge durch Ordnung der Schlüssel und besser zum finden des 1. freien Platzes?
    // aber eigentlich egal welcher freie Platz zuerst gefunden wird?
    private HashSet <CustomerImpl> customers;
    //customer nur 1* drin

    final int capacity;

    public Warehouse(int capacity) {
        this.storage = new TreeMap<>();
        this.capacity = Math.abs(capacity);
        this.customers = new HashSet<>();
    }

    public Warehouse() {
        this(10);
    }


    public boolean addCustomer(String name) {
       return customers.add(new CustomerImpl(name));
    }

    //Abruf aller Kund*innen mit der Anzahl der ihrer Frachtstücke
    public HashMap<Customer, Integer> getCargoNrPerCustomer(){
        HashMap<Customer, Integer> customerCargos = new HashMap<>();
      for(CargoAbstr cargo : storage.values()){
         if(customerCargos.containsKey(cargo.getOwner())){
             int i = customerCargos.get(cargo.getOwner()) + 1;
             customerCargos.put(cargo.getOwner(), i);
          }
          else {
             customerCargos.put(cargo.getOwner(), 1);
          }
      }
      return customerCargos;
    }


    //TODO: Abruf eingelagerter Frachtstücke, wird ein Typ (Klassenname) angegeben
    // werden nur Frachtstücke von diesem Typ aufgelistet
    //TODO: Abruf aller vorhandenen bzw. nicht vorhandenen Gefahrenstoffe (hazards) im Lager
    public int insert(Cargotype type, String customer, BigDecimal value, List<Hazard> hazards, boolean characteristics, int grainSize) {
        // characteristics = [[fragile(true/false)]] [[pressurized(true/false)]]
        //TODO: toask - wie mach ich das mit den Attributen? alle? dann könnte ich aber Typ Cargo als Attribut abspeichern + dann als Attribut den Typ haben, alle Methoden (geben null zurück, wenn nicht der richtige Typ)
        Customer owner = new CustomerImpl(customer);
        if (this.isFull()) return -1;
        if (!customers.contains(owner)) return -1;

        //TODO: toask: ok so oder blöd, dass neues ownerObjekt geschaffen wird, das ggf. gar nicht gebraucht wird?
         /* private boolean customerExists(String name) {
        //alternativ:
        // CustomerImpl customer = new CustomerImpl(name);
        // return customers.contains(customer);
        return customers.stream().map(customer -> customer.getName()).anyMatch(customerName -> customerName == name);
    }*/

        CargoAbstr cargo;

        switch (type) {
            case DRYBULKCARGO -> cargo = new DryBulkCargoImpl(owner, value, hazards, grainSize);
            case LIQUIDBULDCARGO -> cargo = new LiquidBulkCargoImpl(owner, value, hazards, characteristics);
           // case UNITISEDCARGO -> cargo = new UnitisedCargo();
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
                cargo.setOwner(owner);
                //TODO: toask hier ok?
                this.setChanged();
                this.notifyObservers();
                return location;
            }
        }

        //viele unterschiedliche return false:  throw new IllegalStateException("no such ....");
        //oder Fehlercode?
        //TODO: was hier am besten zurückgeben?
        return -1;
    }// ca

    public boolean isFull() {
        return storage.size() >= capacity;
    }

    //TODO als Collection evt. wegen Darstellung auf der Oberfläche GUI/CLI
    public TreeMap<Integer, CargoAbstr> read() {
        return new TreeMap<Integer, CargoAbstr>(this.storage);
        //return new ArrayList<CargoAbstr>(this.storage.values());
    }

    public Cargo delete(int location) {
        return storage.remove(location);
    }

    public boolean inspect(int location) {
        if (this.storage.containsKey(location)) {// ca
            this.storage.get(location).setInspectionDate(new Date());
            return true;
        } else {
            return false;
        }
        //oder mit Fehlercode falls Cargo nicht vorhanden
    }

    public Cargo remove(int location) {// add, remove, contains (remove in linkedList billiger als ArrayList)
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

package observer;

import admin.Warehouse;

import java.util.Observable;
import java.util.Observer;

public class HazardsObserver implements Observer {
    private final Warehouse model;
    private int lastHazards;

    public HazardsObserver(Warehouse model){
        this.model = model; 
        //this.lastHazards
    }

    @Override
    public void update(Observable o, Object arg) {

       //Check ob Hazards dazugekommen sind 
    }
}

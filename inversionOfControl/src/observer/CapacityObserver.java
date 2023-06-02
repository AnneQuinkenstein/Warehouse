package observer;

import admin.Warehouse;

import java.util.Observable;
import java.util.Observer;

public class CapacityObserver implements Observer {
    private final Warehouse model;
    private int lastCapicity;

    public CapacityObserver(Warehouse model){
        this.model = model;
        this.lastCapicity=this.model.read().size();
    }

    @Override
    public void update(Observable o, Object arg) {

        double ninetyPercent = this.model.getCapacity() * 0.9;
        if(this.lastCapicity != this.model.read().size()) {
            if (this.model.read().size() >= ninetyPercent) System.out.println(" The Capacity has reached 90%");
            this.lastCapicity = this.model.read().size();
        }
    }
}

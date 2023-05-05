package listener;

import admin.Warehouse;
import infrastrukture.InsertCargoEvent;
import infrastrukture.InsertCargoEventListener;

public class InsertCargoListener implements InsertCargoEventListener {
    private Warehouse model;
    public InsertCargoListener(Warehouse model) { this.model=model; }
    @Override public void onInsertCargoEvent(InsertCargoEvent event) {
        this.model.insert(event.getCargotype(), event.getCustomer(),event.getWert(), event.getHazards(), event.isFragileOrPressurized(), event.getGrainSize()); }
}


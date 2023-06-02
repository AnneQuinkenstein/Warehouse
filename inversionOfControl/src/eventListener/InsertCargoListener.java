package eventListener;

import admin.Warehouse;
import eventInfrastrukture.GenericEventListener;
import eventInfrastrukture.InsertCargoEvent;
import eventInfrastrukture.InsertCargoEventListener;

public class InsertCargoListener implements GenericEventListener<InsertCargoEvent> {
    private Warehouse model;
    public InsertCargoListener(Warehouse model) { this.model=model; }
    @Override public void onGenericEvent(InsertCargoEvent event) {
        this.model.insert(event.getCargotype(), event.getCustomer(),event.getWert(), event.getHazards(), event.isFragileOrPressurized(), event.getGrainSize()); }
}


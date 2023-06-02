package eventListener;

import admin.Warehouse;
import eventInfrastrukture.GenericEventListener;
import eventInfrastrukture.NumberEvent;
import eventInfrastrukture.NumberEventListener;

public class InspectCargoListener implements GenericEventListener<NumberEvent> {

    private final Warehouse model;

    public InspectCargoListener(Warehouse model){
        this.model = model;
    }
    @Override
    public void onGenericEvent(NumberEvent event) {
      this.model.inspect(event.getNumber());
    }
}

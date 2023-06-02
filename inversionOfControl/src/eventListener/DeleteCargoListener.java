package eventListener;

import admin.Warehouse;
import eventInfrastrukture.GenericEventListener;
import eventInfrastrukture.NumberEvent;

public class DeleteCargoListener implements GenericEventListener<NumberEvent> {
    private final Warehouse model;

    public DeleteCargoListener(Warehouse model){
        this.model = model;
    }
    @Override
    public void onGenericEvent(NumberEvent event) {
        this.model.inspect(event.getNumber());
    }
}

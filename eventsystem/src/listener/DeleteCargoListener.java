package listener;

import admin.Warehouse;
import infrastrukture.NumberEvent;
import infrastrukture.NumberEventListener;

public class DeleteCargoListener implements NumberEventListener {
    private final Warehouse model;

    public DeleteCargoListener(Warehouse model){
        this.model = model;
    }
    @Override
    public void onNumberEvent(NumberEvent event) {
        this.model.inspect(event.getNumber());
    }
}

package listener;

import admin.Warehouse;
import infrastrukture.ReadEventListener;

import java.util.EventListener;
import java.util.EventObject;

public class ReadCargoListener implements ReadEventListener {
    private Warehouse model;

    public ReadCargoListener(Warehouse model){
        this.model = model;
    }

    @Override
    public void onReadEvent(EventObject event) {
        model.read();
    }
}

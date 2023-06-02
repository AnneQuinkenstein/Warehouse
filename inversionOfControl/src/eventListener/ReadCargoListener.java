package eventListener;

import admin.CargoAbstr;
import admin.Warehouse;
import eventInfrastrukture.*;

import java.util.EventObject;
import java.util.TreeMap;

public class ReadCargoListener implements GenericEventListener<EventObject> {
    private Warehouse model;
    private StringEvent stringEvent;
    private GenericEventHandler <StringEvent> outputHandler;

    public ReadCargoListener(Warehouse model){
        this.model = model;
    }
    public void setOutputHandler(GenericEventHandler <StringEvent> handler) {
    this.outputHandler = handler; }
    private TreeMap<Integer, CargoAbstr> cargos;
    @Override
    public void onGenericEvent(EventObject event) {
        cargos =  model.read();
        stringEvent = new StringEvent(this, cargosToString());
        if (null!=this.outputHandler) outputHandler.handle(stringEvent);
    }

    private String cargosToString(){
        String s = "Enthaltene Cargos:  ";
        for(int i = 0; i < cargos.size(); i++){
            if(cargos.get(i)!= null){
                s += cargos.get(i).toString() + " -- ";
            }
        }
        return s;
    }
}

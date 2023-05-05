package infrastrukture;

import java.util.LinkedList;
import java.util.List;

public class InsertCargoEventHandler {
    private List<InsertCargoEventListener> listenerList = new LinkedList<>();
    public void add(InsertCargoEventListener listener) {
        this.listenerList.add(listener);
    }
    public void remove(InsertCargoEventListener listener) {
        this.listenerList.remove(listener);
    }
    public void handle(InsertCargoEvent event){
        for (InsertCargoEventListener listener : listenerList) listener.onInsertCargoEvent(event);

    }
}

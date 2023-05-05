package infrastrukture;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

public class ReadEventHandler {
    private List<ReadEventListener> listenerList = new LinkedList<>();
    public void add(ReadEventListener listener) {
        this.listenerList.add(listener);
    }
    public void remove(ReadEventListener listener) {
        this.listenerList.remove(listener);
    }
    public void handle(EventObject event){for (ReadEventListener listener : listenerList) listener.onReadEvent(event);}

}

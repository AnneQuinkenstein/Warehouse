package eventInfrastrukture;

import java.util.LinkedList;
import java.util.List;

public class NumberEventHandler {
    private List<NumberEventListener> listenerList = new LinkedList<>();
    public void add(NumberEventListener listener) {
        this.listenerList.add(listener);
    }
    public void remove(NumberEventListener listener) {
        this.listenerList.remove(listener);
    }
    public void handle(NumberEvent event){for (NumberEventListener listener : listenerList) listener.onNumberEvent(event);}
}

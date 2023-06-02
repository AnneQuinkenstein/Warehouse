package eventInfrastrukture;

import java.util.LinkedList;
import java.util.List;

public class OutputEventHandler {
    private List<OutputEventListener> listenerList = new LinkedList<>();
    public void add(OutputEventListener listener) {  this.listenerList.add(listener); }
    public void remove(OutputEventListener listener) {
        this.listenerList.remove(listener);
    }
    public void handle(StringEvent event){for (OutputEventListener listener : listenerList) listener.onOutputEvent(event);}
}

package eventInfrastrukture;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

public class GenericEventHandler <E extends EventObject>  {

        private List<GenericEventListener <E>> listenerList = new LinkedList<>();
        public void add(GenericEventListener <E> listener) {
            this.listenerList.add(listener);
        }
        public void remove(GenericEventListener <E> listener) {
            this.listenerList.remove(listener);
        }
        public void handle(E event) {
            for (GenericEventListener <E> listener : listenerList)
                listener.onGenericEvent(event);
        }
}

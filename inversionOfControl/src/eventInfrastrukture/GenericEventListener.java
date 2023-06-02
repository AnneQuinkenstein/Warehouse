package eventInfrastrukture;

import java.util.EventObject;

public interface GenericEventListener<E extends EventObject> {
    void onGenericEvent(E genericEvent);
}

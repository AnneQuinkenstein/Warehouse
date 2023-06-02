package eventInfrastrukture;

import java.util.EventListener;
import java.util.EventObject;

public interface ReadEventListener extends EventListener {
    void onReadEvent(EventObject event);
}

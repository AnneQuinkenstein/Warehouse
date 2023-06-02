package eventInfrastrukture;

import java.util.EventListener;

public interface InsertCargoEventListener extends EventListener {
    void onInsertCargoEvent(InsertCargoEvent insertCargoEvent);
}

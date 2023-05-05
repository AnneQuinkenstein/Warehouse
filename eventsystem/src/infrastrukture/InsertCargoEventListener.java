package infrastrukture;

import java.util.EventListener;

public interface InsertCargoEventListener extends EventListener {
    void onInsertCargoEvent(InsertCargoEvent insertCargoEvent);
}

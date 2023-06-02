package eventInfrastrukture;

import java.util.EventListener;

public interface OutputEventListener extends EventListener {
    default void onOutputEvent(StringEvent event) {

    }
}

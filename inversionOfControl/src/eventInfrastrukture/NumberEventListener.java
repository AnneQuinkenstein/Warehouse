package eventInfrastrukture;

import java.util.EventListener;

public interface NumberEventListener extends EventListener {
    void onNumberEvent(NumberEvent event);
}

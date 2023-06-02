package eventListener;


import eventInfrastrukture.GenericEventListener;
import eventInfrastrukture.StringEvent;
import eventInfrastrukture.OutputEventListener;

public class InfoListener implements GenericEventListener<StringEvent> {

    @Override public void onGenericEvent(StringEvent event) {

        System.out.println(event.getInput());
    }
}

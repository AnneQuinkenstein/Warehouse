package eventInfrastrukture;

import java.util.EventObject;

public class GenericEvent <T extends Object> extends EventObject {

    private final T sourceData;

    public GenericEvent(Object source, T sourceData ){
            super(source);
        this.sourceData =sourceData;
}

    public T getSourceData() {
        return sourceData;
    }

}

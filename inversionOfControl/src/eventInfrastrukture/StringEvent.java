package eventInfrastrukture;

import java.util.EventObject;

public class StringEvent extends EventObject {
    private String output;

    public StringEvent(Object source, String output) {
        super(source);
        this.output = output;

    }
    public String getInput() { return output; }
}

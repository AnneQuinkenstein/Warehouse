package infrastrukture;

import java.util.EventObject;

public class NumberEvent  extends EventObject {
    private int number;

    public NumberEvent(Object source, int number) {
        super(source);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}

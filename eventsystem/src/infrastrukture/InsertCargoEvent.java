package infrastrukture;

import admin.Cargotype;
import cargo.Hazard;

import java.math.BigDecimal;
import java.util.EventObject;
import java.util.List;

public class InsertCargoEvent extends EventObject {

    Cargotype cargotype;
    String customer;
    BigDecimal wert;
    List<Hazard> hazards;
    boolean fragileOrPressurized;
    int grainSize;

    public InsertCargoEvent(Object source, Cargotype type, String customer, BigDecimal value, List<Hazard> hazards, boolean characteristics, int grainSize) {
        super(source);
        this.cargotype = type;
        this.customer = customer;
        this.wert = value;
        this.hazards = hazards;
        this.fragileOrPressurized = fragileOrPressurized;
        this.grainSize = grainSize;
    }

    public Cargotype getCargotype() {
        return cargotype;
    }

    public String getCustomer() {
        return customer;
    }

    public BigDecimal getWert() {
        return wert;
    }

    public List<Hazard> getHazards() {
        return hazards;
    }

    public boolean isFragileOrPressurized() {
        return fragileOrPressurized;
    }

    public int getGrainSize() {
        return grainSize;
    }


}

package admin;

import admin.CargoAbstr;
import cargo.Hazard;
import cargo.LiquidBulkCargo;

import java.math.BigDecimal;
import java.util.List;

public class LiquidBulkCargoImpl extends CargoAbstr implements LiquidBulkCargo {
    private boolean pressurized;

    public LiquidBulkCargoImpl(String customer, BigDecimal value, List<Hazard> hazards, boolean characteristics){
        super(customer, value, hazards);
        this.pressurized = characteristics;
    }
    @Override
    public boolean isPressurized() {
        return this.pressurized;
    }
}

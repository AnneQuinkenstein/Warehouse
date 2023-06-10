package admin;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidBulkCargo;

import java.math.BigDecimal;
import java.util.List;

public class LiquidBulkCargoImpl extends CargoAbstr implements LiquidBulkCargo {
    private boolean pressurized;

    public LiquidBulkCargoImpl(Customer owner, BigDecimal value, List<Hazard> hazards, boolean characteristics){
        super(owner, value, hazards);
        this.pressurized = characteristics;
    }
    @Override
    public boolean isPressurized() {
        return this.pressurized;
    }
}

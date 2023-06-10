package admin;

import administration.Customer;
import cargo.DryBulkCargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.util.List;


public class DryBulkCargoImpl extends CargoAbstr implements DryBulkCargo {
 private int grainSize;

 public DryBulkCargoImpl(Customer owner, BigDecimal value, List<Hazard> hazards, int grainSize){
    super(owner, value, hazards);
    this.grainSize = grainSize;
 }
    @Override
    public int getGrainSize() {
        return this.grainSize;
    }
}

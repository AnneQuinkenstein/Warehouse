package admin;

import cargo.DryBulkCargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.util.List;


public class DryBulkCargoImpl extends CargoAbstr implements DryBulkCargo {
 private int grainSize;

 public DryBulkCargoImpl(String customer, BigDecimal value, List<Hazard> hazards, int grainSize){
    super(customer, value, hazards);
    this.grainSize = grainSize;
 }
    @Override
    public int getGrainSize() {
        return this.grainSize;
    }
}

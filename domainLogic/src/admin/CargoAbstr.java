package admin;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public abstract class CargoAbstr implements Storable, Cargo {
    protected Date inspectionDate = null;
    protected BigDecimal value;
    protected Collection<Hazard> hazards;

    protected Date inputDate;
    protected String ownerName;
    protected int storageLocation;

    public CargoAbstr(String customer, BigDecimal value, List<Hazard> hazards){
         this.value = value;
         this.hazards = hazards;
         this.ownerName = customer;
         this.inputDate = new Date();
    }

    @Override
    public BigDecimal getValue() {
        return this.value;
    }
    @Override
    public Collection<Hazard> getHazards(){
        return this.hazards;
    }

    @Override
    public Customer getOwner() {
        throw new UnsupportedOperationException("not yet implemented");
        //aus Liste Owner raussuchen, wenn nicht drin
    }

    @Override
    public Duration getDurationOfStorage() {
        Instant dateNow = Instant.now();
        return Duration.between(dateNow, this.inputDate.toInstant());
    }

    protected void setInspectionDate(Date inspectionDate){
        this.inspectionDate = inspectionDate;
    }
    @Override
    public Date getLastInspectionDate() {
        return this.inspectionDate;
    }

    protected void setStorageLocation(int location){
        this.storageLocation = location;
    }
    @Override
    public int getStorageLocation() {
        return this.storageLocation;
    }

    protected void setInputDate(Date inputDate){
        this.inputDate = inputDate;
    }
    public Date getInputDate() {
        return inputDate;
    }

    @Override
    public String toString(){
        String s = String.format("Cargo: StorageLocation: ; Owner: , Value: %s, Hazards: ", this.getValue(), this.hazards);
        return s;
    }
}

package admin;

import cargo.Cargo;
import cargo.Hazard;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class WarehouseTest {


    @Test
    void insert_DryBulkCargo_InEmptyStorage(){
        Warehouse warehouseUnderTest = new Warehouse();
        Cargotype type = Cargotype.DRYBULKCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;

        boolean bo = warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);
        assertTrue(bo);
    }

    @Test
    void insert_TwoCargo_InEmptyStorage_Location(){
        Warehouse warehouseUnderTest = new Warehouse(2);
        Cargotype type = Cargotype.LIQUIDBULDCARGO;
        Cargotype type2 = Cargotype.DRYBULKCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;

        warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);
        boolean result = warehouseUnderTest.insert(type2, customer, value, hazards, characteristics, grainSize);

        assertEquals(2, warehouseUnderTest.read().size());
    }

    @Test
    void insert_InFullStorage_Negative(){
        Warehouse warehouseUnderTest = new Warehouse(0);
        Cargotype type = Cargotype.DRYBULKCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;

        boolean result = warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);
        assertFalse(result);
    }

    @Test
    void read_NeverNull_Negativ() {
        Warehouse warehouseUnderTest = new Warehouse();

        TreeMap<Integer, CargoAbstr> result = warehouseUnderTest.read();

        assertNotNull(result);
    }

    @Test
    void read_EmptyStorage() {
        Warehouse warehouseUnderTest = new Warehouse();

        TreeMap<Integer, CargoAbstr> result = warehouseUnderTest.read();

        assertEquals(0, result.size());
    }

    @Test
    void read_filledStorage(){
        Warehouse warehouseUnderTest = new Warehouse(2);
        Cargotype type = Cargotype.DRYBULKCARGO;
        Cargotype type2 = Cargotype.LIQUIDBULDCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;

        warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);
        warehouseUnderTest.insert(type2, customer, value, hazards, characteristics, grainSize);

        assertEquals(2, warehouseUnderTest.read().size());
    }

    @Test
    void read_filledStoragePlusInsertOne_Negativ(){
        Warehouse warehouseUnderTest = new Warehouse(2);
        Cargotype type = Cargotype.DRYBULKCARGO;
        Cargotype type2 = Cargotype.LIQUIDBULDCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;

        warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);
        warehouseUnderTest.insert(type2, customer, value, hazards, characteristics, grainSize);
        warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);

        assertEquals(2, warehouseUnderTest.read().size());
    }

    @Test
    void remove_FilledStorage_Boolean(){
        Warehouse warehouseUnderTest = new Warehouse(1);

        Cargotype type = Cargotype.DRYBULKCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;

        warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);


        Cargo result = warehouseUnderTest.remove(1);

        assertNotNull(result);
        //assertTrue(result instanceof Cargo);
    }

    @Test
    void remove_FilledStorage_ListSize(){
        Warehouse warehouseUnderTest = new Warehouse(1);
        Cargotype type = Cargotype.DRYBULKCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;

        warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);

        warehouseUnderTest.remove(1);

        assertEquals(0, warehouseUnderTest.read().size());
    }

    @Test
    void remove_EmptyStorage_Negative(){
        Warehouse warehouseUnderTest = new Warehouse(0);

        Cargo result = warehouseUnderTest.remove(1);

        assertNull(result);
    }

    @Test
     void inspect_DateCorrect(){
        Warehouse warehouseUnderTest = new Warehouse(1);
        Cargotype type = Cargotype.DRYBULKCARGO;
        String customer = "Petersen";
        BigDecimal value = BigDecimal.valueOf(20);
        List<Hazard> hazards = mock(List.class);
        boolean characteristics = true;
        int grainSize = 2;
        warehouseUnderTest.insert(type, customer, value, hazards, characteristics, grainSize);

        Date dateNow = new Date();

        warehouseUnderTest.inspect(1);

        TreeMap<Integer, CargoAbstr>cargos = warehouseUnderTest.read();
        Date cargoDate = cargos.get(1).getLastInspectionDate();
      //  assertTrue(cargoDate.getTime() - dateNow.getTime() >=0);
        assertTrue((dateNow.equals(cargoDate)) || cargoDate.after(dateNow));
    }


}
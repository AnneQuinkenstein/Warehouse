import Observer.CapacityObserver;
import admin.Warehouse;
import infrastrukture.*;
import listener.DeleteCargoListener;
import listener.InsertCargoListener;
import listener.ReadCargoListener;

public class Main {
    public static void main(String[] args){

        Warehouse model = new Warehouse();
        CLI console = new CLI(model);

        InsertCargoEventHandler insertCargoHandler = new InsertCargoEventHandler();
        InsertCargoListener insertListener = new InsertCargoListener(model);
        insertCargoHandler.add(insertListener);
        console.setInsertCargoHandler(insertCargoHandler);

        NumberEventHandler deleteCargoHandler = new NumberEventHandler();
        DeleteCargoListener deleteListener = new DeleteCargoListener(model);
        deleteCargoHandler.add(deleteListener);
        console.setDeleteCargoHandler(deleteCargoHandler);

        NumberEventHandler updateCargoHandler = new NumberEventHandler();
        DeleteCargoListener updateListener = new DeleteCargoListener(model);
        updateCargoHandler.add(updateListener);
        console.setUpdateCargoHandler(updateCargoHandler);

        ReadEventHandler readEventHandler = new ReadEventHandler();
        ReadCargoListener readCargoListener = new ReadCargoListener(model);
        readEventHandler.add(readCargoListener);

     /*   CapacityObserver o1=new CapacityObserver(model);
        model.addObserver(o1);
        Observer.HazardsObserver o2=new Observer.HazardsObserver(model);
        model.addObserver(o2); */


        console.execute();

    }
}
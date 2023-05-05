package Observer;

import admin.Warehouse;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Observable;

import static org.mockito.Mockito.*;

class CapacityObserverTest {

    /*
   benutzt ausschließlich getValue()
    */
    /*
    @Test
    void properNotificationReaction() {
        Warehouse observableCounter=mock(Warehouse.class);
        CapacityObserver observer=new CapacityObserver(observableCounter,"");
        Observable o = mock(Observable.class);
        Object arg = mock(Object.class);

        observer.update(o, arg);

        verify(observableCounter,times(1)).addObserver(any());
        verify(observableCounter,atLeastOnce()).read();
        verifyNoMoreInteractions(observableCounter);
    }
    /*
    registriert sich bei der Initialisierung
     */
    @Test void properInit() {
        Warehouse observableCounter=mock(Warehouse.class);

        CapacityObserver observer=new CapacityObserver(observableCounter,"");

        verify(observableCounter,times(1)).addObserver(observer);
    }
    /*
    initialisiert sich richtig bei der Initialisierung
     */
    @Test void properConnecting() {
        Warehouse observableCounter=mock(Warehouse.class);

        new CapacityObserver(observableCounter,"");

        verify(observableCounter,atLeastOnce()).getValue();
    }
    /*
    produziert richtige Ausgabe
     */
    @Test void properOutput() {
        PrintStream originalOut=System.out;
        try {
            PrintStream out=mock(PrintStream.class);
            System.setOut(out);
            Warehouse observableCounter=mock(Warehouse.class);
            when(observableCounter.getValue()).thenReturn(2).thenReturn(4);
            CapacityObserver observer=new CapacityObserver(observableCounter,"T");

            observer.update();

            verify(out).println("T: 2->4");
        }finally {
            System.setOut(originalOut);
        }
    }
    /*
    keine Änderung => keine Ausgabe
     */
    @Test void properStateManagementOnlyChanges() {
        PrintStream originalOut=System.out;
        try {
            PrintStream out=mock(PrintStream.class);
            System.setOut(out);
            Warehouse observableCounter=mock(Warehouse.class);
            when(observableCounter.getValue()).thenReturn(5);
            CapacityObserver observer=new CapacityObserver(observableCounter,"T");

            observer.update();

            verifyZeroInteractions(out);
        }finally {
            System.setOut(originalOut);
        }
    }
    void properNotificationReaction() {
        Warehouse modle=mock(Warehouse.class);
        CapacityObserver observer=new CapacityObserver(modle,"");

        observer.update();

        verify(modle,times(1)).addObserver(any());
        verify(modle,atLeastOnce()).getValue();
        verifyNoMoreInteractions(modle);
    }
    /*
    registriert sich bei der Initialisierung
     */
    @Test void properInit() {
        Warehouse modle=mock(Warehouse.class);

        CapacityObserver observer=new CapacityObserver(modle,"");

        verify(modle,times(1)).addObserver(observer);
    }
    /*
    initialisiert sich richtig bei der Initialisierung
     */
    @Test void properConnecting() {
        Warehouse modle=mock(Warehouse.class);

        new CapacityObserver(modle,"");

        verify(modle,atLeastOnce()).getValue();
    }
    /*
    produziert richtige Ausgabe
     */
    @Test void properOutput() {
        PrintStream originalOut=System.out;
        try {
            PrintStream out=mock(PrintStream.class);
            System.setOut(out);
            Warehouse modle=mock(Warehouse.class);
            when(modle.getValue()).thenReturn(2).thenReturn(4);
            CapacityObserver observer=new CapacityObserver(modle,"T");

            observer.update();

            verify(out).println("T: 2->4");
        }finally {
            System.setOut(originalOut);
        }
    }
    /*
    keine Änderung => keine Ausgabe
     */
    @Test void properStateManagementOnlyChanges() {modle
        PrintStream originalOut=System.out;
        try {
            PrintStream out=mock(PrintStream.class);
            System.setOut(out);
            Warehouse modle=mock(Warehouse.class);
            when(modle.getValue()).thenReturn(5);
            CapacityObserver observer=new CapacityObserver(modle,"T");

            observer.update();

            verifyZeroInteractions(out);
        }finally {
            System.setOut(originalOut);
        }

    }
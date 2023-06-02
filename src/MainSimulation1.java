import Simulation1.Consumer;
import Simulation1.Producer;
import admin.Warehouse;

import java.util.Scanner;


public class MainSimulation1 {
    public static void main(String[] args){
       Warehouse model = null; 
        
            int input;
            boolean notANumber = true;
            String message = "How big is the capacity of the warehouse?";
            try (Scanner s = new Scanner(System.in)) {
                do {
                    System.out.println(message);
                    try
                    {
                        input = Integer.parseInt(s.nextLine());
                        model = new Warehouse(input); 
                        notANumber = false;
                    }
                    catch (NumberFormatException e)
                    {
                        message = "This was not a Number. Please try again to insert the capacity: ";
                    }
                }while (notANumber);
            }
        

        new Producer(model).start();
        new Consumer(model).start();

    }

}
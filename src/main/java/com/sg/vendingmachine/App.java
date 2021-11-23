package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vendingmachine");
        appContext.refresh();

        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
        controller.run();
    }
}

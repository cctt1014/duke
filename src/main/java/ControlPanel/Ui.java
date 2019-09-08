package ControlPanel;

import java.io.IOException;
import java.util.Scanner;

/**
 * the class generates the user interface
 */
public class Ui {

    private Scanner scanner;

    /**
     * the constructor initialize the object and the scanner
     */
    public Ui (){
        scanner = new Scanner(System. in);
    }

    /**
     * print the greeting info
     */
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    /**
     * read in the command line typed in the interface
     * @return return the command read from the user interface
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * check whether there is a input string or not
     * @return return a status of the input data
     */
    public boolean inputStatus(){
        return scanner.hasNextLine();
    }

    /**
     * print a line shown in user interface
     */
    public void showLine(){
        System.out.println("____________________________________________________________\n");
    }

    /**
     * show users there is a loading error
     */
    public void showLoadingError(){
        System.out.println("This is not a valid input from the file!!!");
    }

    /**
     * print any error messages
     * @param message the error message sent by other classes
     */
    public void showError(String message){
        System.out.println(message);
    }


}

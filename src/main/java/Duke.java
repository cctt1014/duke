/**
 * From Chen Tong.
 * @return greeting info.
 */

import java.util.Scanner;

public class Duke{
    /**
     * Main method.
     */
    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System. in);
        while(scanner.hasNextLine()){
            String inputString = scanner.nextLine();
            if (inputString.equals("bye")){
                System.out.println("____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                break;
            }
            System.out.println(inputString);
        }

    }//main

}//duke class


/**
 * From Chen Tong.
 * @return greeting info.
 */

import java.util.Scanner;

public class Duke{
    /**
     * Main method.
     */

    private static String[] checkList = new String[100];
    private static int taskNo = 0;

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
            if (inputString.equals("bye")) {
                System.out.println("____________________________________________________________\n");
                System.out.println("     Bye. Hope to see you again soon!\n");
                System.out.println("____________________________________________________________\n");
                break;
            }
            else if (inputString.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 1; checkList[i-1] != null; i++){
                    System.out.println(" " + i + ". " + checkList[i-1] + "\n");
                }
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("____________________________________________________________\n");
                System.out.println("     added: " + inputString + "\n");
                System.out.println("____________________________________________________________\n");
                checkList[taskNo++] = inputString;
            }
        }

    }//main

}//duke class


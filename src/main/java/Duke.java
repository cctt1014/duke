/**
 * From Chen Tong.
 * @return greeting info.
 */

import java.util.Scanner;

public class Duke{

    private static Task[] checkList = new Task[100];
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
                    System.out.println(" " + i + "." + checkList[i-1].getStatusIcon() + " "
                                        + checkList[i-1].description + "\n");
                }
                System.out.println("____________________________________________________________\n");
            }
            else if(inputString.contains("done")){
                String temp = inputString.replaceAll("[^0-9]", "");
                int serialNo = Integer.parseInt(temp);
                checkList[serialNo-1].markAsDone();
                System.out.println("____________________________________________________________\n");
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("   [✓] " + checkList[serialNo-1].description + "\n");
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("____________________________________________________________\n");
                System.out.println("     added: " + inputString + "\n");
                System.out.println("____________________________________________________________\n");
                Task t = new Task(inputString);
                checkList[taskNo++] = t;
            }
        }

    }//main

}//duke class


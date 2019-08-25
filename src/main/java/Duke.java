/**
 * From Chen Tong.
 * @return greeting info.
 */

import java.io.*;
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

        FileControl fileControl = new FileControl();
        checkList = fileControl.requestTheData();
        taskNo = fileControl.requestTheTotalNumberofTask();

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
                    System.out.println(" " + i + "." + checkList[i-1].toString() + "\n");
                }
                System.out.println("____________________________________________________________\n");
            }
            else if(inputString.contains("done")){
                try {
                    String temp = inputString.replaceAll("[^0-9]", "");
                    int serialNo = Integer.parseInt(temp);
                    if (serialNo > taskNo){
                        throw new DukeException("The serial number of the task is Out Of Bounds!");
                    }
                    checkList[serialNo - 1].markAsDone();
                    fileControl.requestToWriteTheFile(checkList);
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.println("   [✓] " + checkList[serialNo - 1].description + "\n");
                    System.out.println("____________________________________________________________\n");
                } catch (DukeException e){
                    e.printStackTrace();
                }
            } else {
                try {
                    String[] keyword = inputString.split(" ");
                    if (!(keyword[0].equals("deadline") || keyword[0].equals("event") || keyword[0].equals("todo"))){
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    switch (keyword[0]) {
                        case "deadline": {
                            if (inputString.equals("deadline")){
                                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                            }
                            String[] getDate = inputString.split("/by ");
                            Task t = new Deadline(getDate[0].replaceFirst("deadline ", ""),
                                    getDate[getDate.length - 1]);
                            checkList[taskNo++] = t;
                            break;
                        }
                        case "event": {
                            if (inputString.equals("event")){
                                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                            }
                            String[] getDate = inputString.split("/at ");
                            Task t = new Events(getDate[0].replaceFirst("event ", ""),
                                    getDate[getDate.length - 1]);
                            checkList[taskNo++] = t;
                            break;
                        }
                        case "todo": {
                            if (inputString.equals("todo")){
                                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            Task t = new ToDos(inputString.replaceFirst("todo ", ""));
                            checkList[taskNo++] = t;
                            break;
                        }
                    }
                    fileControl.requestToWriteTheFile(checkList);
                    System.out.println("____________________________________________________________\n");
                    System.out.println(" Got it. I've added this task: \n");
                    System.out.println("     " + checkList[taskNo - 1].toString() + "\n");
                    System.out.println(" Now you have " + taskNo + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                } catch (DukeException e){
                    e.printStackTrace();
                }

            } //else
        }//while loop

    }//main

}//duke class


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke{

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
        ArrayList<Task> checkList = fileControl.requestTheData();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy HHmm");

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
                int i = 1;
                for (Task t : checkList){
                    System.out.println(" " + i++ + "." + t.toString() + "\n");
                }
                System.out.println("____________________________________________________________\n");
            }
            else if(inputString.contains("done")){
                try {
                    String temp = inputString.replaceAll("[^0-9]", "");
                    int serialNo = Integer.parseInt(temp);
                    if (serialNo > checkList.size()){
                        throw new DukeException("The serial number of the task is Out Of Bounds!");
                    }
                    checkList.get(serialNo-1).markAsDone();
                    fileControl.requestToWriteTheFile(checkList);
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.println("   [✓] " + checkList.get(serialNo-1).description + "\n");
                    System.out.println("____________________________________________________________\n");
                } catch (DukeException e){
                    e.printStackTrace();
                }
            }
            else if (inputString.contains("delete")){
                try {
                    String temp = inputString.replaceAll("[^0-9]", "");
                    int serialNo = Integer.parseInt(temp);
                    if (serialNo > checkList.size()){
                        throw new DukeException("The serial number of the task is Out Of Bounds!");
                    }
                    fileControl.requestToWriteTheFile(checkList);
                    System.out.println("____________________________________________________________\n");
                    System.out.println(" Noted. I've removed this task:\n");
                    System.out.println("  " + checkList.get(serialNo-1).toString() + "\n");
                    System.out.println(" Now you have " + (checkList.size()-1) + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                    checkList.remove(serialNo-1);
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
                            Date date = simpleDateFormat.parse(getDate[getDate.length-1]);
                            String formattedDate = simpleDateFormat.format(date);
                            Task t = new Deadline(getDate[0].replaceFirst("deadline ", ""),
                                    formattedDate);
                            checkList.add(t);
                            break;
                        }
                        case "event": {
                            if (inputString.equals("event")){
                                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                            }
                            String[] getDate = inputString.split("/at ");
                            Date date = simpleDateFormat.parse(getDate[getDate.length-1]);
                            String formattedDate = simpleDateFormat.format(date);
                            Task t = new Events(getDate[0].replaceFirst("event ", ""),
                                    formattedDate);
                            checkList.add(t);
                            break;
                        }
                        case "todo": {
                            if (inputString.equals("todo")){
                                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            Task t = new ToDos(inputString.replaceFirst("todo ", ""));
                            checkList.add(t);
                            break;
                        }
                    }
                    fileControl.requestToWriteTheFile(checkList);
                    System.out.println("____________________________________________________________\n");
                    System.out.println(" Got it. I've added this task: \n");
                    System.out.println("     " + checkList.get(checkList.size()-1).toString() + "\n");
                    System.out.println(" Now you have " + checkList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                } catch (DukeException | ParseException e){
                    e.printStackTrace();
                }

            } //else
        }//while loop

    }//main

}//duke class


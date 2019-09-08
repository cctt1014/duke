package Commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Tasks.*;
import ControlPanel.*;

/**
 * The command which aims to add a new task to the list
 */

public class AddCommand extends Command {

    private String type;
    private String inputString;
    private SimpleDateFormat simpleDateFormat;

    /**
     * The constructor to initialize a add command object
     * @param string the type of the command
     * @param cmd the content of the original text in input command
     */
    public AddCommand(String string, String cmd){
        type = string;
        inputString = cmd;
        simpleDateFormat  = new SimpleDateFormat("d/M/yyyy HHmm");
    }

    /**
     * method to label whether this command means ceasing the overall program
     * @return whether this command means ceasing the overall program
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * The methods to execute the command based on its type
     * @param tasks the tasklist object to interact with the checklist
     * @param ui to print something needed in user interface
     * @param storage to re-save the data in local disk if necessary
     * @throws ParseException when there is something wrong with the parsing
     * @throws DukeException when the command line is not qualified
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParseException, DukeException {
        switch (type) {
            case "deadline": {
                String[] getDate = inputString.split("/by ");
                Date date = simpleDateFormat.parse(getDate[getDate.length-1]);
                String formattedDate = simpleDateFormat.format(date);
                Task t = new Deadline(getDate[0].replaceFirst("deadline ", ""),
                        formattedDate);
                tasks.addTask(t);
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
                tasks.addTask(t);
                break;
            }
            case "todo": {
                if (inputString.equals("todo")){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task t = new ToDos(inputString.replaceFirst("todo ", ""));
                tasks.addTask(t);
                break;
            }
        }
        storage.writeTheFile(tasks.getCheckList());
        System.out.println(" Got it. I've added this task: \n");
        System.out.println("     " + tasks.getTask(tasks.lengthOfList()-1).toString() + "\n");
        System.out.println(" Now you have " + tasks.lengthOfList() + " tasks in the list.");

    }


}

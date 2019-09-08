package Commands;

import Tasks.*;
import ControlPanel.*;

/**
 * The command which aims to delete a specific task from the checklist
 */
public class DeleteCommand extends Command {

    private int serialNo;

    /**
     * the constructor
     * @param index the index number of the task which will be deleted
     */
    public DeleteCommand(int index){
        serialNo = index;
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
     * @throws DukeException when the command line is not qualified
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (serialNo > tasks.lengthOfList()){
            throw new DukeException("The serial number of the task is Out Of Bounds!");
        }
        System.out.println(" Noted. I've removed this task:\n");
        System.out.println("  " + tasks.getTask(serialNo-1).toString() + "\n");
        System.out.println(" Now you have " + (tasks.lengthOfList()-1) + " tasks in the list.");
        tasks.removeTask(serialNo-1);
        storage.writeTheFile(tasks.getCheckList());
    }
}

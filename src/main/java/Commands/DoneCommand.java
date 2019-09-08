package Commands;

import Tasks.*;
import ControlPanel.*;

/**
 * The command aims to mark a specific task as done
 */
public class DoneCommand extends Command {

    private int serialNo;

    /**
     * the constructor to initialize the done command
     * @param index the index number of the task which will be marked as done
     */
    public DoneCommand(int index){
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
        if (serialNo > tasks.lengthOfList()) {
            throw new DukeException("The serial number of the task is Out Of Bounds!");
        }
        tasks.markDoneATask(serialNo-1);
        storage.writeTheFile(tasks.getCheckList());
        System.out.println(" Nice! I've marked this task as done:\n");
        System.out.println("   [✓] " + tasks.getTask(serialNo-1).getDescription() + "\n");
    }
}

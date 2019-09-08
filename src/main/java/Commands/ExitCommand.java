package Commands;

import Tasks.*;
import ControlPanel.*;

/**
 * the exit command aims to trigger the greeting and cease the overall program
 */
public class ExitCommand extends Command {

    /**
     * a default constructor
     */
    public ExitCommand(){
    }

    /**
     * method to label whether this command means ceasing the overall program
     * @return whether this command means ceasing the overall program
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * The methods to execute the command based on its type
     * @param tasks the tasklist object to interact with the checklist
     * @param ui to print something needed in user interface
     * @param storage to re-save the data in local disk if necessary
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Bye. Hope to see you again soon!\n");
    }


}

package Commands;

import Tasks.*;
import ControlPanel.*;

/**
 * the command aims to view the current checklist i.e. print out all the tasks
 * in the list
 */
public class ViewCommand extends Command{

    /**
     * a default construtor
     */
    public ViewCommand(){
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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.lengthOfList();i++) {
            System.out.println(" " + i + "." + tasks.getTask(i-1).toString() + "\n");
        }
    }
}

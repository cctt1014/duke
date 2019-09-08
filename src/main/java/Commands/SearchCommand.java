package Commands;

import Tasks.*;
import ControlPanel.*;

/**
 * the command aims to search the tasks with the keyword
 */
public class SearchCommand extends Command {

    private String keyword;

    /**
     * the constructor which initialize the search command object
     * @param string the keyword
     */
    public SearchCommand(String string){
        keyword = string;
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
        int count = 1;
        for (int i = 1;i <= tasks.lengthOfList();i++) {
            if (tasks.getTask(i-1).getDescription().contains(keyword)) {
                System.out.println(" " + count++ + "." + tasks.getTask(i - 1).toString() + "\n");

            }
        };
    }
}

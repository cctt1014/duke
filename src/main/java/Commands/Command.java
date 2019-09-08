package Commands;

import ControlPanel.*;
import Tasks.*;

import java.text.ParseException;

/**
 * the abstract class to represent all types of the commands
 */
public abstract class Command {
    public Command(){

    }

    /**
     * method to label whether this command means ceasing the overall program
     * @return whether this command means ceasing the overall program
     */
    public abstract boolean isExit();

    /**
     * The methods to execute the command based on its type
     * @param tasks the tasklist object to interact with the checklist
     * @param ui to print something needed in user interface
     * @param storage to re-save the data in local disk if necessary
     * @throws ParseException when there is something wrong with the parsing
     * @throws DukeException when the command line is not qualified
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException;
}

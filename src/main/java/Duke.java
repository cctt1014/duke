import java.text.ParseException;
import Commands.*;
import ControlPanel.*;
import Tasks.*;

/**
 * The overall control of the duke software. Initialize all the setup and handle the
 * overall process
 */

public class Duke{

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * The constructor to initialize a duke object
     *
     * @param filePath input the path of the file in which the data of the list are saved
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the overall program
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit && ui.inputStatus()){
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ParseException | DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }

    /**
     * This method executes the overall things by initializing a duke object and run it
     *
     * @param args
     * @throws DukeException some exceptions defined in other classes
     */
    public static void main(String[] args) throws DukeException {
        new Duke("D:/duke/data/tasks.txt").run();
    }

}//duke class




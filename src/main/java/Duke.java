import java.text.ParseException;

public class Duke{

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

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

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit){
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | ParseException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("D:/duke/data/tasks.txt").run();
    }

}//duke class




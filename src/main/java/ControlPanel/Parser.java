package ControlPanel;

import Commands.*;

/**
 * the class to classify the type of the command
 */
public class Parser {
    public Parser(){}

    /**
     * the method to analyze the input command and classify the type of the command
     * @param cmd the original text typed in
     * @return return a command object which has been categorized based on the input command
     * @throws DukeException if the input command is not valid
     */
    public static Command parse(String cmd) throws DukeException {
        Command command = null;
        if (cmd.equals("bye")){
            command = new ExitCommand();
        }
        else if (cmd.equals("list")){
            command = new ViewCommand();
        }
        else if (cmd.contains("find")){
            if (cmd.equals("find"))
                throw new DukeException("OOPS!!! The description of a find cannot be empty.");
            String keyword = cmd.split(" ")[1];
            command = new SearchCommand(keyword);
        }
        else if(cmd.contains("done")) {
            String temp = cmd.replaceAll("[^0-9]", "");
            int serialNo = Integer.parseInt(temp);
            command = new DoneCommand(serialNo);
        }
        else if (cmd.contains("delete")){
                String temp = cmd.replaceAll("[^0-9]", "");
                int serialNo = Integer.parseInt(temp);
                command = new DeleteCommand(serialNo);

        } else {
            String keyword = cmd.split(" ")[0];
            if (!(keyword.equals("deadline") || keyword.equals("event") || keyword.equals("todo"))){
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
            }
            command = new AddCommand(keyword, cmd);
        }
        return command;
    }
}

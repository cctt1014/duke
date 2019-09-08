package ControlPanel;

/**
 * a self defined exception class
 */
public class DukeException extends Exception{
    DukeException(){}

    /**
     * the constructor to throw the errors
     * @param errorMsg
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }

}

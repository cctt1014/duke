import java.io.*;
import java.util.ArrayList;

public class FileControl {

    private static ArrayList<Task> checkList = new ArrayList<>();
    private static int taskNo = 0;

    public FileControl() {
        try {
            FileReader fileReader = new FileReader("data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace('|', '@');
                String[] info = line.split(" @ ");
                if (!(info[0].equals("T") || info[0].equals("D") || info[0].equals("E"))) {
                    throw new DukeException("This is not a valid input from the file!!!");
                }
                Task t = new Task("default");
                switch (info[0]) {
                    case "T":
                        t = new ToDos(info[2]);
                        break;
                    case "D":
                        t = new Deadline(info[2], info[3]);
                        break;
                    case "E":
                        t = new Events(info[2], info[3]);
                        break;
                }
                if (t.description.equals("default")) {
                    throw new DukeException("This task is not refreshed.");
                }
                if (info[1].equals("1")) {
                    t.markAsDone();
                }
                checkList.add(t);
                taskNo++;
            }
            bufferedReader.close();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> requestTheData() {
        return checkList;
    }

    public int requestTheTotalNumberofTask() {
        return taskNo;
    }

    public void requestToWriteTheFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            for (Task t : taskList) {
                if (t instanceof ToDos) {
                    if (t.isDone)
                        bufferedWriter.write(t.type + " | 1 | " + t.description + "\n");
                    else
                        bufferedWriter.write(t.type + " | 0 | " + t.description + "\n");
                } else if (t instanceof Events) {
                    if (t.isDone)
                        bufferedWriter.write(t.type + " | 1 | " + t.description + " | "
                                + ((Events) t).at + "\n");
                    else
                        bufferedWriter.write(t.type + " | 0 | " + t.description + " | "
                                + ((Events) t).at + "\n");
                } else if (t instanceof Deadline) {
                    if (t.isDone)
                        bufferedWriter.write(t.type + " | 1 | " + t.description + " | "
                                + ((Deadline) t).by + "\n");
                    else
                        bufferedWriter.write(t.type + " | 0 | " + t.description + " | "
                                + ((Deadline) t).by + "\n");

                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


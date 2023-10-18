//imports for the project
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//class declaration
public class TaskModel {

    //dclares an FXCollection named tasks of ObservableList<task> type
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    //getter that provides access to ObservableList<task>
    public ObservableList<Task> getTasks() {
        return tasks;
    }

    //modify ObservableList<task> by adding tasks
    public void addTask(Task task) {
        tasks.add(task);
    }

    //remove tasks from ObservableList<task>
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // Save tasks to a .dat file
    public void saveTasksToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            List<Task> taskList = new ArrayList<>(tasks);
            outputStream.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load tasks from a .dat file
    public void loadTasksFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Task> taskList = (List<Task>) inputStream.readObject();
            tasks.clear();
            tasks.addAll(taskList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//imports for the project
import java.io.File;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

//controller class declaration
public class TaskController {

    //fxml annotations used to inject GUI components to controller class
    @FXML
    private ListView<Task> taskListView;

    @FXML
    private TextField taskNameTextField;

    @FXML
    private TextArea taskDescriptionTextArea;

    @FXML
    private ChoiceBox<Priority> priorityChoiceBox;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    //reffers to TaskModel instance called model
    private TaskModel model;

    //method initializes the model object that is used by the controller
    public void initModel(TaskModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.model = model;
        taskListView.setItems(model.getTasks());                                            //taskListView is populated with entered items

        priorityChoiceBox.setItems(FXCollections.observableArrayList(Priority.values()));   //priorityChoiceBox is populated by priority values from enum class
        priorityChoiceBox.setValue(Priority.MEDIUM);                                        //value of priority is set to medium by default
    }

    @FXML                                                                                   //signifies that method is loaded when fxml class is being loaded
    private void initialize() {
        addButton.setOnAction(event -> {                                                    //addbutton onclick exenthandler
            String name = taskNameTextField.getText();
            String description = taskDescriptionTextArea.getText();
            Priority priority = priorityChoiceBox.getValue();
            if (!name.isEmpty()) {
                Task newTask = new Task(name, description, priority);
                model.addTask(newTask);
                taskNameTextField.clear();
                taskDescriptionTextArea.clear();
            }
        });

        removeButton.setOnAction(event -> {                                                 //removebutton onclick eventhandler
            Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                model.removeTask(selectedTask);
            }
        });
    }

    //the method to handle click event on the save button, it hence generates a file with the contents of the taskList in .dat format
    @FXML
    private void handleSaveButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("tasks.dat");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            model.saveTasksToFile(file.getAbsolutePath());
    }
}

    //the method is used to retrieve data from a presaved file on the device to be accessed when the load file button is clicked
    @FXML
    private void handleLoadButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data Files", "*.dat"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            model.loadTasksFromFile(file.getAbsolutePath());
    }
}
}

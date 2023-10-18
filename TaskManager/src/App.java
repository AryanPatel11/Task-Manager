//imports for the project
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//class declaration
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader is used to load the task_manager.fxml class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("task_manager.fxml"));
        //load() method loads the fxml content while parent node represents root user interface
        Parent root = loader.load();

        //TaskController instance is called using getController method
        TaskController taskController = loader.getController();

        //method is used to createa a new TaskModel object and then passes it to the controller
        taskController.initModel(new TaskModel());


        primaryStage.setTitle("Task Manager");                  //sets title
        primaryStage.setScene(new Scene(root, 400, 600));       //sets Scene with Parent root with 400x600 px of dimensions
        primaryStage.show();                                    //displays application window
    }

    public static void main(String[] args) {                    //entry point of Java Application
        launch(args);
    }
}

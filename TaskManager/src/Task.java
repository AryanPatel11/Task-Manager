//class declaration

import java.io.Serializable;

public class Task implements Serializable {

    //declaring class variables
    private String name;
    private String description;
    private Priority priority;

    //task constructor method
    public Task(String name, String description, Priority priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    //get name of the task
    public String getName() {
        return name;
    }

    //get description of the task
    public String getDescription() {
        return description;
    }

    //get priority
    public Priority getPriority() {
        return priority;
    }

    //toString method for Task class
    @Override
    public String toString() {
        return name + "(" + priority + ")" + " - " + description;
    }
}

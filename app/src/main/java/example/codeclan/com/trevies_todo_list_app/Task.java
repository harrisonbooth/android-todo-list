package example.codeclan.com.trevies_todo_list_app;

/**
 * Created by user on 21/01/2017.
 */

public class Task {

    private String headline;
    private String description;
    private boolean complete;
    private boolean priority;

    // Takes in headline, description, and if task is priority then creates
    // Task is false by default
    public Task(String headline, String description, Boolean priority){
        this.headline = headline;
        this.description = description;
        complete = false;
        this.priority = priority;
    }

    // Returns headline
    public String getHeadline() {
        return headline;
    }

    // Returns description
    public String getDescription() {
        return description;
    }

    // Returns completion status
    public boolean getComplete() {
        return complete;
    }

    // Returns priority
    public boolean getPriority(){
        return priority;
    }

    // Switches completion state
    public void toggleComplete() {
        complete = !complete;
    }
}

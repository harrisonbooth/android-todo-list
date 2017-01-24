package example.codeclan.com.trevies_todo_list_app;

/**
 * Created by user on 21/01/2017.
 */

public class Task {

    private String headline;
    private String description;
    private boolean complete;
    private boolean priority;

    public Task(String headline, String description, Boolean priority){
        this.headline = headline;
        this.description = description;
        complete = false;
        this.priority = priority;
    }


    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public boolean getComplete() {
        return complete;
    }

    public boolean getPriority(){
        return priority;
    }

    public void toggleComplete() {
        complete = !complete;
    }
}

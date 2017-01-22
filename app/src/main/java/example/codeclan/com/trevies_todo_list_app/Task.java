package example.codeclan.com.trevies_todo_list_app;

/**
 * Created by user on 21/01/2017.
 */

public class Task implements Listable {

    private String headline;
    private String description;
    private boolean complete;

    public Task(String headline, String description){
        this.headline = headline;
        this.description = description;
        complete = false;
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

    public void toggleComplete() {
        complete = !complete;
    }
}

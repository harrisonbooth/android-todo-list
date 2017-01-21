package example.codeclan.com.trevies_todo_list_app;

/**
 * Created by user on 21/01/2017.
 */
public interface Listable {
    String getHeadline();

    String getDescription();

    boolean getComplete();

    void toggleComplete();
}

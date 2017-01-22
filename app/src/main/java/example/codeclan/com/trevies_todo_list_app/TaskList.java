package example.codeclan.com.trevies_todo_list_app;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by user on 21/01/2017.
 */

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    public int taskCount(){
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void removeTaskByHeadline(String headline) {
        ArrayList<Task> tasksToRemove = new ArrayList<Task>();
        for(Task task : tasks){
            if(task.getHeadline() == headline){
                tasksToRemove.add(task);
            }
        }
        tasks.removeAll(tasksToRemove);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setup(){
        tasks.add(new Task("Example Task", "This is an example task, you can toggle completion in the options menu."));
    }

}

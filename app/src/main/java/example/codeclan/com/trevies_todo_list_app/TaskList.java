package example.codeclan.com.trevies_todo_list_app;

import java.util.ArrayList;

/**
 * Created by user on 21/01/2017.
 */

public class TaskList {

    ArrayList<Listable> tasks;

    public TaskList(){
        tasks = new ArrayList<Listable>();
    }

    public int taskCount(){
        return tasks.size();
    }

    public void addTask(Listable task) {
        tasks.add(task);
    }

    public void removeTask(Listable task) {
        tasks.remove(task);
    }

    public void removeTaskByHeadline(String headline) {
        ArrayList<Listable> tasksToRemove = new ArrayList<Listable>();
        for(Listable task : tasks){
            if(task.getHeadline() == headline){
                tasksToRemove.add(task);
            }
        }
        tasks.removeAll(tasksToRemove);
    }

    public ArrayList<Listable> getTasks() {
        return tasks;
    }
}

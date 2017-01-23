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
        tasks.add(new Task("Housework", "Clean house before kids get home."));
        tasks.add(new Task("Shopping", "Do weekly shop and get new school uniform for Shaun"));
        tasks.add(new Task("Cooking", "Cook dinner for kids and make packed lunches for tomorrow"));
        tasks.add(new Task("Reply to emails", "Check all work emaisl and reply to all if appropriate"));
        tasks.add(new Task("Pick up kids", "Get Shaun from school and Macy from nursery"));
        tasks.add(new Task("Get MOT", "Current MOT due to be renewed on Tuesday"));
    }

}

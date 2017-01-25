package example.codeclan.com.trevies_todo_list_app;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by user on 21/01/2017.
 */

public class TaskList {

    private ArrayList<Task> tasks;

    // Creates tasklist with empty arraylist of tasks
    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    // Returns the number of tasks in the tasklist
    public int taskCount(){
        return tasks.size();
    }

    // Takes in task and adds it to tasklist
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Takes in task and removes it from tasklist
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // Takes in a headline and searches the arraylist for tasks with that headline,
    // then removes them from tasklist
    public void removeTaskByHeadline(String headline) {
        ArrayList<Task> tasksToRemove = new ArrayList<Task>();
        for(Task task : tasks){
            if(task.getHeadline() == headline){
                tasksToRemove.add(task);
            }
        }
        tasks.removeAll(tasksToRemove);
    }

    // Returns tasklist
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Sets up tasklist with sample data
    public void setup(){
        tasks.add(new Task("Example Task", "This is an example task, you can toggle completion in the options menu.", true));
        tasks.add(new Task("Housework", "Clean house before kids get home.", false));
        tasks.add(new Task("Shopping", "Do weekly shop and get new school uniform for Shaun", false));
        tasks.add(new Task("Cooking", "Cook dinner for kids and make packed lunches for tomorrow", false));
        tasks.add(new Task("Reply to emails", "Check all work emaisl and reply to all if appropriate", true));
        tasks.add(new Task("Pick up kids", "Get Shaun from school and Macy from nursery", true));
        tasks.add(new Task("Get MOT", "Current MOT due to be renewed on Tuesday", false));
    }

}

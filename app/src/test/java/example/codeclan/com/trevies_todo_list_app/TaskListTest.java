package example.codeclan.com.trevies_todo_list_app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by user on 21/01/2017.
 */
public class TaskListTest {

    TaskList taskList;
    Task task1;

    @Before
    public void before(){
        taskList = new TaskList();
        task1 = new Task("Headline 1", "Description 1", true);
    }

    @Test
    public void startsWithNoTasks(){
        assertEquals(0, taskList.taskCount());
    }

    @Test
    public void canAddTask(){
        taskList.addTask(task1);
        assertEquals(1, taskList.taskCount());
    }

    @Test
    public void canRemoveTask(){
        taskList.addTask(task1);
        taskList.removeTask(task1);
        assertEquals(0, taskList.taskCount());
    }

    @Test
    public void canRemoveTaskByHeadline(){
        taskList.addTask(task1);
        taskList.removeTaskByHeadline("Headline 1");
        assertEquals(0, taskList.taskCount());
    }

    @Test
    public void canGetTasks(){
        ArrayList<Task> gottenTasks;
        Task gottenTask;
        String gottenTaskHeadline;

        taskList.addTask(task1);
        gottenTasks = taskList.getTasks();
        gottenTask = (Task) gottenTasks.get(0);
        gottenTaskHeadline = gottenTask.getHeadline();

        assertEquals("Headline 1", gottenTaskHeadline);
    }

}
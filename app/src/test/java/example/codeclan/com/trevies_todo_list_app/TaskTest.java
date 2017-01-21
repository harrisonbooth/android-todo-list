package example.codeclan.com.trevies_todo_list_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 21/01/2017.
 */
public class TaskTest {

    Listable task;

    @Before
    public void before(){
        task = new Task("Weekly shop", "Get weekly shop from Asda.");
    }

    @Test
    public void canGetHeadline(){
        assertEquals("Weekly shop", task.getHeadline());
    }

    @Test
    public void canGetDescription(){
        assertEquals("Get weekly shop from Asda.", task.getDescription());
    }

    @Test
    public void startsIncomplete(){
        assertFalse(task.getComplete());
    }

    @Test
    public void canToggleComplete(){
        task.toggleComplete();
        assertTrue(task.getComplete());
    }

}
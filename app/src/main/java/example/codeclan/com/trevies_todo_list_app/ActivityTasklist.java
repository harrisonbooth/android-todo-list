package example.codeclan.com.trevies_todo_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 22/01/2017.
 */

public class ActivityTasklist extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Gets saved theme settings and applies them, if there are none, default is applied
        String themeName = SavedThemePreferences.getStoredTheme(this);
        if(themeName == null || themeName.equals("Default")){
            setTheme(R.style.AppTheme);
        } else if(themeName.equals("Blackboard")){
            setTheme(R.style.AppTheme_BlackBoard);
        } else if(themeName.equals("Lab")){
            setTheme(R.style.AppTheme_Lab);
        }

        // Sets layout for activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        // Sets taskListView variable to the ListView in the layout
        taskListView = (ListView) findViewById(R.id.activity_tasklist);

        // Sets taskList variable to saved tasklist, if there is no saved tasklist it will inject example tasks
        TaskList taskList;
        if(SavedTaskListPreferences.getStoredTaskList(this) != null) {
            taskList = SavedTaskListPreferences.getStoredTaskList(this);
        } else {
            taskList = new TaskList();
            taskList.setup();
            SavedTaskListPreferences.setStoredTaskList(this, taskList);
        }

        // Creates taskArrayList from the tasks in the taskList
        ArrayList<Task> taskArrayList = taskList.getTasks();
        ArrayList<String> taskHeadlineList = new ArrayList<>();

        // Creates taskHeadlineList from the headlines of completed tasks
        for(int i = 0; i < (taskArrayList.size()); i++){
            Task task = taskArrayList.get(i);
            if(!task.getComplete() && task.getHeadline() != null) {
                taskHeadlineList.add(task.getHeadline());
            }
        }

        // Adapts headlines into ListView if there are any
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.custom_list_items, taskHeadlineList);
        taskListView.setAdapter(adapter);

        // Sets up listener for items in ListView
        taskListView.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        // Sets taskList variable to saved taskList
        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);

        // Creates taskArrayList from the tasks in the taskList
        ArrayList<Task> taskArrayList = taskList.getTasks();
        ArrayList<Task> nonCompletedTaskArrayList = new ArrayList<>();

        // Creates completeTaskArrayList of incomplete tasks from taskList
        for(Task task : taskArrayList){
            if(!task.getComplete()){
                nonCompletedTaskArrayList.add(task);
            }
        }

        // Selects task with position in ListView that was clicked
        Task task = nonCompletedTaskArrayList.get(position);

        // Gets headline, description, and completion status from task
        String headline = task.getHeadline();
        String description = task.getDescription();
        boolean complete = task.getComplete();

        // Creates intent to send user to task details activity
        Intent intent = new Intent();
        intent.setClass(this, ActivityTaskDetail.class);

        // Bundles the source, taskIndex, headline, descpription, and completion in intent
        intent.putExtra("source", "tasklist");
        intent.putExtra("taskIndex", position);
        intent.putExtra("headline", headline);
        intent.putExtra("description", description);
        intent.putExtra("complete", complete);

        // Sends user to task details activity with intent and extras
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflates menu when option button is pressed
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_mainmenu, menu);
        return true;
    }

    @Override
    public void onRestart() {
        // Ensures that when activity restarts or is moved back to, onCreate will run like it is
        // being opened for the first time
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();

        // Decides what option item was selected
        if(item.getItemId() == R.id.action_new_task) {
            // If New Task is selected, sends user to new task activity
            intent.setClass(this, ActivityNewTask.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.action_archives) {
            // If view completed tasks is selected, sends user to archive activity
            intent.setClass(this, ActivityArchive.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.action_theme_menu) {
            // If themes is selected, sends user to theme menu activity
            intent.setClass(this, ActivityThemeMenu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }


}

package example.codeclan.com.trevies_todo_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 23/01/2017.
 */
public class ActivityArchive extends AppCompatActivity implements AdapterView.OnItemClickListener {

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

        // Sets taskList variable to saved tasklist
        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);

        // Creates taskArrayList from the tasks in the taskList
        ArrayList<Task> taskArrayList = taskList.getTasks();
        ArrayList<String> taskHeadlineList = new ArrayList<>();

        // Creates taskHeadlineList from the headlines of completed tasks
        for(int i = 0; i < (taskArrayList.size()); i++){
            Task task = taskArrayList.get(i);
            if(task.getComplete()) {
                taskHeadlineList.add(task.getHeadline());
            }
        }

        // Adapts headlines into ListView if there are any
        if(taskHeadlineList.get(0) != null) {
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.custom_list_items, taskHeadlineList);
            taskListView.setAdapter(adapter);
        }

        // Sets up listener for items in ListView
        taskListView.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        // Sets taskList variable to saved taskList
        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);

        // Creates taskArrayList from the tasks in the taskList
        ArrayList<Task> taskArrayList = taskList.getTasks();
        ArrayList<Task> completedTaskArrayList = new ArrayList<>();

        // Creates completeTaskArrayList of completed tasks from taskList
        for(Task task : taskArrayList){
            if(task.getComplete()){
                completedTaskArrayList.add(task);
            }
        }

        // Selects task with position in ListView that was clicked
        Task task = completedTaskArrayList.get(position);

        // Gets headline, description, and completion status from task
        String headline = task.getHeadline();
        String description = task.getDescription();
        boolean complete = task.getComplete();

        // Creates intent to send user to task details activity
        Intent intent = new Intent();
        intent.setClass(this, ActivityTaskDetail.class);

        // Bundles the source, taskIndex, headline, descpription, and completion in intent
        intent.putExtra("source", "archive");
        intent.putExtra("taskIndex", position);
        intent.putExtra("headline", headline);
        intent.putExtra("description", description);
        intent.putExtra("complete", complete);

        // Sends user to task details activity with intent and extras
        startActivity(intent);
    }

    @Override
    public void onRestart() {
        // Ensures that when activity restarts or is moved back to, onCreate will run like it is
        // being opened for the first time
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}

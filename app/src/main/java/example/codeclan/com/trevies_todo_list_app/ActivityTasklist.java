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
        String themeName = SavedThemePreferences.getStoredTheme(this);
        if(themeName == null || themeName.equals("Default")){
            setTheme(R.style.AppTheme);
        } else if(themeName.equals("Blackboard")){
            setTheme(R.style.AppTheme_BlackBoard);
        } else if(themeName.equals("Lab")){
            setTheme(R.style.AppTheme_Lab);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        taskListView = (ListView) findViewById(R.id.activity_tasklist);

        TaskList taskList;

        if(SavedTaskListPreferences.getStoredTaskList(this) != null) {
            taskList = SavedTaskListPreferences.getStoredTaskList(this);
//            Log.d(getClass().toString(), taskList.getTasks().toString());
        } else {
            taskList = new TaskList();
            taskList.setup();
            SavedTaskListPreferences.setStoredTaskList(this, taskList);
        }

        ArrayList<Task> taskArrayList = taskList.getTasks();
        ArrayList<String> taskHeadlineList = new ArrayList<>();

        for(int i = 0; i < (taskArrayList.size()); i++){
            Task task = taskArrayList.get(i);
//            Log.d(getClass().toString(), String.valueOf(task.getComplete()));
            if(!task.getComplete() && task.getHeadline() != null) {
                taskHeadlineList.add(task.getHeadline());
//                Log.d(getClass().toString(), task.getHeadline());
//                Log.d(getClass().toString(), taskHeadlineList[i]);
            }
        }

            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.custom_list_items, taskHeadlineList);
            taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);

        ArrayList<Task> taskArrayList = taskList.getTasks();
        ArrayList<Task> nonCompletedTaskArrayList = new ArrayList<>();
        for(Task task : taskArrayList){
            if(!task.getComplete()){
                nonCompletedTaskArrayList.add(task);
            }
        }

        Task task = nonCompletedTaskArrayList.get(position);

        String headline = task.getHeadline();
        String description = task.getDescription();
        boolean complete = task.getComplete();

        Log.d(getClass().toString(), headline + description + complete);

        Intent intent = new Intent();
        intent.setClass(this, ActivityTaskDetail.class);

        intent.putExtra("source", "tasklist");
        intent.putExtra("taskIndex", position);
        intent.putExtra("headline", headline);
        intent.putExtra("description", description);
        intent.putExtra("complete", complete);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_mainmenu, menu);
        return true;
    }

    @Override
    public void onRestart() {  //when restart the page
        super.onRestart(); //call normal restart stuff from android
        finish();
        startActivity(getIntent()); //re-gets implicit intent, so acts like it was just launched
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();

        if(item.getItemId() == R.id.action_new_task) {
            intent.setClass(this, ActivityNewTask.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.action_archives) {
            intent.setClass(this, ActivityArchive.class);
            startActivity(intent);
        } else if(item.getItemId() == R.id.action_theme_menu) {
            intent.setClass(this, ActivityThemeMenu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}

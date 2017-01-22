package example.codeclan.com.trevies_todo_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
    private ArrayList<String> taskHeadlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        taskListView = (ListView) findViewById(R.id.activity_tasklist);

        TaskList taskList;

        if(SavedTaskListPreferences.getStoredTaskList(this) != null) {
            taskList = SavedTaskListPreferences.getStoredTaskList(this);
        } else {
            taskList = new TaskList();
            taskList.setup();
            SavedTaskListPreferences.setStoredTaskList(this, taskList);
        }


        ArrayList<Task> taskArrayList = taskList.getTasks();
        String[] taskHeadlineList = new String[taskArrayList.size()];

        for(int i = 0; i < taskArrayList.size(); i++){
            Task task = taskArrayList.get(i);
            if(task.getComplete()) {
                taskHeadlineList[i] = task.getHeadline() + " - complete";
            } else {
                taskHeadlineList[i] = task.getHeadline();
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.custom_list_items, taskHeadlineList);
        taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);


        ArrayList<Task> taskArrayList = taskList.getTasks();
        Task task = taskArrayList.get(position);

        String headline = task.getHeadline();
        String description = task.getDescription();
        boolean complete = task.getComplete();

        Log.d(getClass().toString(), headline + description + complete);

        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, ActivityTaskDetail.class);

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


}

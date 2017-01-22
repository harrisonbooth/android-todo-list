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
        }


        ArrayList<Listable> taskArrayList = taskList.getTasks();
        String[] taskHeadlineList = new String[taskArrayList.size()];

        for(int i = 0; i < taskArrayList.size(); i++){
            Listable task = taskArrayList.get(i);
            taskHeadlineList[i] = task.getHeadline();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskHeadlineList);
        taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        TaskList taskList;

        if(SavedTaskListPreferences.getStoredTaskList(this) != null) {
            taskList = SavedTaskListPreferences.getStoredTaskList(this);
        } else {
            taskList = new TaskList();
            taskList.setup();
        }

        ArrayList<Listable> taskArrayList = taskList.getTasks();
        Listable task = taskArrayList.get(position);

        String headline = task.getHeadline();
        String description = task.getDescription();
        boolean complete = task.getComplete();

        Log.d(getClass().toString(), headline + description + complete);

        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, ActivityTaskDetail.class);

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


}

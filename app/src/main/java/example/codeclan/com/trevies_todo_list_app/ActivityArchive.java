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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        taskListView = (ListView) findViewById(R.id.activity_tasklist);

        TaskList taskList;

        taskList = SavedTaskListPreferences.getStoredTaskList(this);

        ArrayList<Task> taskArrayList = taskList.getTasks();
        String[] taskHeadlineList = new String[taskArrayList.size()];

        for(int i = 0; i < taskArrayList.size(); i++){
            Task task = taskArrayList.get(i);
            if(task.getComplete()) {
                taskHeadlineList[i] = task.getHeadline();
            }
        }

        if(taskHeadlineList[0] != null) {
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.custom_list_items, taskHeadlineList);
            taskListView.setAdapter(adapter);
        }

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

}

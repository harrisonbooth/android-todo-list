package example.codeclan.com.trevies_todo_list_app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 22/01/2017.
 */
public class ActivityTaskDetail extends AppCompatActivity {

    private TextView headlineTextView;
    private TextView descriptionTextView;
    private TextView completeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String headline = extras.getString("headline");
        String description = extras.getString("description");
        boolean complete = extras.getBoolean("complete");

        headlineTextView = (TextView) findViewById(R.id.headline_text_view);
        descriptionTextView = (TextView) findViewById(R.id.description_text_view);
        completeTextView = (TextView) findViewById(R.id.complete_text_view);

        headlineTextView.setText(headline);
        descriptionTextView.setText(description);

        if(complete){
            completeTextView.setText("Complete");
        } else {
            completeTextView.setText("Incomplete");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_taskmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);
        ArrayList<Task> taskArrayList = taskList.getTasks();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int taskIndex = extras.getInt("taskIndex");

        Task task = taskArrayList.get(taskIndex);
        task.toggleComplete();
        SavedTaskListPreferences.setStoredTaskList(this, taskList);

        return super.onOptionsItemSelected(item);
    }
}

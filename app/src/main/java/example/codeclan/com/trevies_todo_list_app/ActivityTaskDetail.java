package example.codeclan.com.trevies_todo_list_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
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
        final TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);
        final ArrayList<Task> taskArrayList = taskList.getTasks();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int taskIndex = extras.getInt("taskIndex");

        if(item.getItemId() == R.id.action_toggle_complete) {
            Task task = taskArrayList.get(taskIndex);
            task.toggleComplete();
            boolean complete = task.getComplete();

            String completeText = complete ? "Complete" : "Incomplete";
            completeTextView.setText(completeText);

            SavedTaskListPreferences.setStoredTaskList(this, taskList);
        } else if(item.getItemId() == R.id.action_delete_task) {
            final Intent parentIntent = new Intent();
            parentIntent.setClass(this, ActivityTasklist.class);
            final Context currentContext = this;

            new AlertDialog.Builder(this)
                    .setTitle("Delete task")
                    .setMessage("Do you really want to send this task back to from whence it came?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Task task = taskArrayList.get(taskIndex);
                            taskList.removeTask(task);
                            SavedTaskListPreferences.setStoredTaskList(currentContext, taskList);
                            startActivity(parentIntent);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        }
        return super.onOptionsItemSelected(item);
    }

}


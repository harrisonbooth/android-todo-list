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
        setContentView(R.layout.activity_task_details);

        // Retrieves intent and extras from previous activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // Assigns headline, description and completion of task to view to variables
        String headline = extras.getString("headline");
        String description = extras.getString("description");
        boolean complete = extras.getBoolean("complete");

        // Sets view variables to the views in the layout
        headlineTextView = (TextView) findViewById(R.id.headline_text_view);
        descriptionTextView = (TextView) findViewById(R.id.description_text_view);
        completeTextView = (TextView) findViewById(R.id.complete_text_view);

        // Sets text in TextViews to task information from extras
        headlineTextView.setText(headline);
        descriptionTextView.setText(description);

        // Decides if task is active or complete, and displays this in TextView
        if(complete){
            completeTextView.setText("Complete");
        } else {
            completeTextView.setText("Incomplete");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflates menu when option button is pressed
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_taskmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Retrieves intent and extras from previous activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // Assigns source and task index from extras to variables
        String source = extras.getString("source");
        final int taskIndex = extras.getInt("taskIndex");
        final TaskList taskList;

        // Sets taskList variable to saved tasklist
        taskList = SavedTaskListPreferences.getStoredTaskList(this);
        ArrayList<Task> fullTaskArrayList = taskList.getTasks();

        // Creates an ArrayList of either complete or incomplete tasks depending on the source
        // This is used to ensure that the taskIndex selects the correct task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        if(source.equals("archive")) {
            for (int i = 0; i < (fullTaskArrayList.size()); i++) {
                Task task = fullTaskArrayList.get(i);
                if (task.getComplete()) {
                    taskArrayList.add(task);
                }
            }
        } else if(source.equals("tasklist")) {
            for (int i = 0; i < (fullTaskArrayList.size()); i++) {
                Task task = fullTaskArrayList.get(i);
                if (!task.getComplete()) {
                    taskArrayList.add(task);
                }
            }
        }

        // Sets final ArrayList, must be final for dialog box
        final ArrayList<Task> finalTaskArrayList = taskArrayList;

        // Decides what option item was selected
        if(item.getItemId() == R.id.action_toggle_complete) {

            // If toggle complete is selected, selects task using taskIndex
            Task task = finalTaskArrayList.get(taskIndex);

            // Toggles completion on selected task
            task.toggleComplete();
            boolean complete = task.getComplete();

            // Manually updates completion TextView without refreshing entire activity
            String completeText = complete ? "Complete" : "Incomplete";
            completeTextView.setText(completeText);

            // Saves task with toggled completion
            SavedTaskListPreferences.setStoredTaskList(this, taskList);

        } else if(item.getItemId() == R.id.action_delete_task) {

            // If delete task is selected, creates intent to bring send user back to previous activity
            final Intent parentIntent = new Intent();

            if(source.equals("archive")) {
                parentIntent.setClass(this, ActivityArchive.class);
            } else if(source.equals("tasklist")) {
                parentIntent.setClass(this, ActivityTasklist.class);
            }

            final Context currentContext = this;

            // Creates dialog box to check user want to delete task, if yes, deletes task
            new AlertDialog.Builder(this)
                    .setTitle("Delete task")
                    .setMessage("Do you really want to send this task back to from whence it came?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Task task = finalTaskArrayList.get(taskIndex);
                            taskList.removeTask(task);
                            SavedTaskListPreferences.setStoredTaskList(currentContext, taskList);
                            startActivity(parentIntent);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        } else if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        // Ensure back button goes back to previous activity, not parent activity
        finish();
    }


}


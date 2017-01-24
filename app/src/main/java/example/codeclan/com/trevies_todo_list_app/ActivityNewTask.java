package example.codeclan.com.trevies_todo_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by user on 23/01/2017.
 */
public class ActivityNewTask extends AppCompatActivity {

    EditText headlineEditText;
    EditText descriptionEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {

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
        setContentView(R.layout.activity_new_task);

        // Sets view variables to the views in the layout
        headlineEditText = (EditText) findViewById(R.id.headline_edit_text);
        descriptionEditText = (EditText) findViewById(R.id.description_edit_text);

    }

    public void onSaveNewTaskButtonPressed(View button) {

        // Creates intent to send user to the active tasklist activity
        Intent parentIntent = new Intent();
        parentIntent.setClass(this, ActivityTasklist.class);

        // Sets newTaskHeadline and newTaskDescription to what the user has types in the EditTexts
        String newTaskHeadline = headlineEditText.getText().toString();
        String newTaskDescription = descriptionEditText.getText().toString();

        // Creates new task with the information in the newTask string variables
        Task newTask = new Task(newTaskHeadline, newTaskDescription);

        // Sets taskList variable to saved taskList
        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);

        // Adds new task to taskList, then saves it again
        taskList.addTask(newTask);
        SavedTaskListPreferences.setStoredTaskList(this, taskList);

        // Sends user to active tasklist activity with intent
        startActivity(parentIntent);

    }
}

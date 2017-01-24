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
        String themeName = SavedThemePreferences.getStoredTheme(this);
        if(themeName == null || themeName.equals("Default")){
            setTheme(R.style.AppTheme);
        } else if(themeName.equals("Blackboard")){
            setTheme(R.style.AppTheme_BlackBoard);
        } else if(themeName.equals("Lab")){
            setTheme(R.style.AppTheme_Lab);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        headlineEditText = (EditText) findViewById(R.id.headline_edit_text);
        descriptionEditText = (EditText) findViewById(R.id.description_edit_text);
    }

    public void onSaveNewTaskButtonPressed(View button) {
        Intent parentIntent = new Intent();
        parentIntent.setClass(this, ActivityTasklist.class);

        String newTaskHeadline = headlineEditText.getText().toString();
        String newTaskDescription = descriptionEditText.getText().toString();

        Task newTask = new Task(newTaskHeadline, newTaskDescription);

        TaskList taskList;
        taskList = SavedTaskListPreferences.getStoredTaskList(this);


        taskList.addTask(newTask);
        SavedTaskListPreferences.setStoredTaskList(this, taskList);

        startActivity(parentIntent);
    }
}

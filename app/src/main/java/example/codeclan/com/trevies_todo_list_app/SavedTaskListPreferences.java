package example.codeclan.com.trevies_todo_list_app;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;


/**
 * Created by user on 22/01/2017.
 */
public class SavedTaskListPreferences {

    // Sets final string as key to retrieve saved tasklist
    private static final String PREF_SAVEDTASKLIST = "savedTaskList";

    public static void setStoredTaskList(Context context, TaskList taskList) {

        // Coverts tasklist into JSON to allow storage in saved preferences
        Gson gson = new Gson();
        String taskListJSON = gson.toJson(taskList);

        // Saves JSON tasklist to saved preferences
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SAVEDTASKLIST, taskListJSON)
                .apply();

    }

    public static TaskList getStoredTaskList(Context context) {

        // Retrieves JSON tasklist from saved preferences
        String taskListJSON = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SAVEDTASKLIST, null);

        // Converts JSON tasklist back to java object tasklist, then returns it
        Gson gson = new Gson();
        TaskList taskList = gson.fromJson(taskListJSON, TaskList.class);
        return taskList;

    }

}

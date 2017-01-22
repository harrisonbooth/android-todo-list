package example.codeclan.com.trevies_todo_list_app;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;


/**
 * Created by user on 22/01/2017.
 */
public class SavedTaskListPreferences {

    private static final String PREF_SAVEDTASKLIST = "savedTaskList";

    public static void setStoredTaskList(Context context, TaskList taskList) {

        Gson gson = new Gson();
        String taskListJSON = gson.toJson(taskList);

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SAVEDTASKLIST, taskListJSON)
                .apply();
    }

    public static TaskList getStoredTaskList(Context context) {
        String taskListJSON = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SAVEDTASKLIST, null);

        Gson gson = new Gson();
        TaskList taskList = gson.fromJson(taskListJSON, TaskList.class);
        return taskList;
    }

}

package example.codeclan.com.trevies_todo_list_app;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by user on 24/01/2017.
 */

public class SavedThemePreferences {

    // Sest final string as key to retrieve saved selected theme
    private static final String PREF_SAVED_THEME = "savedTheme";

    public static void setStoredTheme(Context context, String themeName) {

        // Saves selected theme name to saved preferences
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SAVED_THEME, themeName)
                .apply();

    }

    public static String getStoredTheme(Context context) {

        // Retrieves saved selected theme name from saved preferences then returns it
        String themeName = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SAVED_THEME, null);
        return themeName;

    }

}

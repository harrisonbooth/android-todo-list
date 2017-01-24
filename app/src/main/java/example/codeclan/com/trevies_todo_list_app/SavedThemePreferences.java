package example.codeclan.com.trevies_todo_list_app;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by user on 24/01/2017.
 */

public class SavedThemePreferences {

    private static final String PREF_SAVED_THEME = "savedTheme";

    public static void setStoredTheme(Context context, String themeName) {

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SAVED_THEME, themeName)
                .apply();
    }

    public static String getStoredTheme(Context context) {
        String themeName = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SAVED_THEME, null);

        return themeName;
    }

}

package example.codeclan.com.trevies_todo_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 24/01/2017.
 */
public class ActivityThemeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView themeMenu;
    ArrayList<String> themes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

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
        setContentView(R.layout.activity_theme_menu);

        // Sets themeMenu variable to the ListView in the layout
        themeMenu = (ListView) findViewById(R.id.activity_theme_menu_listview);

        // Populates themes ArrayList with hard coded options
        themes.add("Blackboard");
        themes.add("Lab");
        themes.add("Default");

        // Adapts theme options into ListView
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.custom_list_items, themes);
        themeMenu.setAdapter(adapter);

        // Sets up listener for items in ListView
        themeMenu.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        // Selects task with position in ListView that was clicked
        String selectedTheme = themes.get(position);

        // Saves selected theme
        SavedThemePreferences.setStoredTheme(this, selectedTheme);

        // Restarts page, thus applying new selected theme
        finish();
        startActivity(getIntent());

        // Makes a toast to the new theme
        Toast.makeText(this, "Theme selected: " + selectedTheme, Toast.LENGTH_SHORT).show();

    }

}

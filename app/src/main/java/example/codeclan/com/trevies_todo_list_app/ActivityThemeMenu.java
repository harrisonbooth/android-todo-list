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

import java.util.ArrayList;

/**
 * Created by user on 24/01/2017.
 */
public class ActivityThemeMenu extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView themeMenu;
    ArrayList<String> themes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String themeName = SavedThemePreferences.getStoredTheme(this);
        if(themeName == null || themeName.equals("Default")){
            setTheme(R.style.AppTheme);
        } else if(themeName.equals("Blackboard")){
            setTheme(R.style.AppTheme_BlackBoard);
        } else if(themeName.equals("Lab")){
            setTheme(R.style.AppTheme_Lab);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_menu);

        themeMenu = (ListView) findViewById(R.id.activity_theme_menu_listview);

        themes.add("Blackboard");
        themes.add("Lab");
        themes.add("Default");

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.custom_list_items, themes);
        themeMenu.setAdapter(adapter);

        themeMenu.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        String selectedTheme = themes.get(position);

        SavedThemePreferences.setStoredTheme(this, selectedTheme);

        finish();
        startActivity(getIntent());
    }

}

package example.codeclan.com.trevies_todo_list_app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 22/01/2017.
 */

public class ActivityTasklist extends AppCompatActivity {

    private ListView taskListView;
    private ArrayList<String> taskHeadlines;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);

        taskListView = (ListView) findViewById(R.id.activity_tasklist);
        
        TaskList taskList = SavedTaskListPreferences.getStoredTaskList(this);
        ArrayList<Listable> taskArrayList = taskList.getTasks();
        String[] taskHeadlineList = new String[taskArrayList.size()];

        for(int i = 0; i < taskArrayList.size(); i++){
            Listable task = taskArrayList.get(i);
            taskHeadlineList[i] = task.getHeadline();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskHeadlineList);
        taskListView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_mainmenu, menu);
        return true;
    }


}

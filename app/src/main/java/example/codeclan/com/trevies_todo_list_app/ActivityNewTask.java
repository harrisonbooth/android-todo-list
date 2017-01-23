package example.codeclan.com.trevies_todo_list_app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by user on 23/01/2017.
 */
public class ActivityNewTask extends AppCompatActivity {

    EditText headlineEditText;
    EditText descriptionEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
    }
}

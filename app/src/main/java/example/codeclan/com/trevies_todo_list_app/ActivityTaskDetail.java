package example.codeclan.com.trevies_todo_list_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 22/01/2017.
 */
public class ActivityTaskDetail extends AppCompatActivity {

    private TextView headlineTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String headline = extras.getString("headline");
        String description = extras.getString("description");

        headlineTextView = (TextView) findViewById(R.id.headline_text_view);
        descriptionTextView = (TextView) findViewById(R.id.description_text_view);

        headlineTextView.setText(headline);
        descriptionTextView.setText(description);
    }
}

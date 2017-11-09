package dev.uublabs.hw14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView tv = findViewById(R.id.list);
        String list = "";

        ArrayList<Person> people = getIntent().getParcelableArrayListExtra(Constants.KEY.EXTRA_PERSON);
        for(Person p : people)
        {
            list += p.toString();
        }
        Log.d(TAG, "onCreate: "+list);
        tv.setText(list);
    }
}

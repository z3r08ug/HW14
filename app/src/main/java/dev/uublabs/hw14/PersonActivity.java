package dev.uublabs.hw14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity
{
    private EditText etFn;
    private EditText etLn;
    private ArrayList<Person> people = new ArrayList<Person>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        etFn = findViewById(R.id.etFn);
        etLn = findViewById(R.id.etLn);
    }

    public void createPerson(View view)
    {
        if(etFn.getText().toString()!= "" && etLn.getText().toString() != "") {
            people.add(new Person(etFn.getText().toString(), etLn.getText().toString()));
            Toast.makeText(getApplicationContext(), "Created person.",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No first name or last name was entered.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void goToList(View view)
    {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putParcelableArrayListExtra(Constants.KEY.EXTRA_PERSON, people);
        startActivity(intent);
    }
}

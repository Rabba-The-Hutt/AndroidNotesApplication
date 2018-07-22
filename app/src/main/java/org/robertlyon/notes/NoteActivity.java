package org.robertlyon.notes;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {


    Button saveButton;
    Button discardButton;
    TextView textView;

    public void save(View v) {

        //Check to see if it is a new note or editing an existing one
        if (getIntent().getExtras() == null)
        {
            MainActivity.noteList.add(textView.getText().toString());
        }
        else
        {
            MainActivity.noteList.set(getIntent().getIntExtra("id", 0), textView.getText().toString());
            Log.i("textTest", "added infor to text view id: " + getIntent().getIntExtra("id", 99));
        }
        Intent anIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(anIntent);
    }

    //Discards whatever the user is working on
    public void discard(View v)
    {
        Intent anIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(anIntent);
    }

    //checks to see if the user wants to save their work
    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle("Save Note")
                .setMessage("Do you wish to save your changes?")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        save(saveButton);
                    }
                })
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        discard(discardButton);
                    }
                })
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        saveButton = findViewById(R.id.saveButton);
        discardButton = findViewById(R.id.discardButton);
        textView = findViewById(R.id.editText);

        if(getIntent().getExtras() != null) {
            String extra = getIntent().getStringExtra("note");

            //Trick to make the cursor appear at the end of the text
            textView.setText("");
            textView.append(extra);
        }
    }

}

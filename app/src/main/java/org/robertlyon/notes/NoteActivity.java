package org.robertlyon.notes;

import android.Manifest;
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
    TextView textView;

    public void save(View v)
    {
        MainActivity.noteList.add(textView.getText().toString());
    }

    @Override
    public void onBackPressed()
    {
        Log.i("backTest", "back pressed");


        new AlertDialog.Builder(this)
                .setTitle("Save Note")
                .setMessage("Do you wish to save your changes?")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent anIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(anIntent);

                        //Add saved info to main activity list
                    }
                })
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent anIntent = new Intent(NoteActivity.this, MainActivity.class);
                        startActivity(anIntent);
                    }
                })
                .show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        saveButton = findViewById(R.id.saveButton);
        textView = findViewById(R.id.editText);
        String extra = getIntent().getStringExtra("note");

        //Trick to make the cursor appear at the end of the text
        textView.setText("");
        textView.append(extra);
    }

}

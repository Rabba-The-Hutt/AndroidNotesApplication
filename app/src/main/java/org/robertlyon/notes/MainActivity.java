package org.robertlyon.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //TODO
    //Add menu for adding new note
    //Remove test note
    //Save notes info to recycle view
    //program save and discard buttons
    //Save card info to shared preferences
    //retrieve card info from shared preferences
    //add option to delete note

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;

    public static List<String> noteList = new ArrayList<>();

    public void cardSelected(View v)
    {
        CardView c = (CardView) v;
        RelativeLayout r = (RelativeLayout) c.getChildAt(0);
        TextView t = (TextView) r.getChildAt(0);
        Log.i("testText", String.valueOf(t.getId()));

        Intent anIntent = new Intent(getApplicationContext(), NoteActivity.class);
        anIntent.putExtra("note", t.getText().toString());
        startActivity(anIntent);
    }


    //Calls on create when a new intent is created
    //Updates the recycle view to the new state
    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        mAdapter = new Adapter(this, noteList);
        mRecyclerView.setAdapter(mAdapter);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Need to be taken away
        noteList.add("Hello my name is Robert it is very nice to meet you :)");

        mAdapter = new Adapter(this, noteList);
        mRecyclerView.setAdapter(mAdapter);


    }
}

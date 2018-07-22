package org.robertlyon.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    SharedPreferences sharedPreferences;

    public static ArrayList<String> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = this.getSharedPreferences("org.robertlyon.notes", Context.MODE_PRIVATE);

        //Checks to see if there is anything to add to shared preferences.
        //Must be a better way to achieve this but spent too long trying to figure it out.
        if(noteList.size() > 0) {
            try {
                sharedPreferences.edit().putString("notes", ObjectSerializer.serialize(noteList)).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Makes sure that an empty list is not assigned to notList
        if(noteList != null) {
            try {
                noteList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("notes", ObjectSerializer.serialize(new ArrayList<String>())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mAdapter = new Adapter(this, noteList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);

        //Switch statement was added to allow for potential future features
        switch (item.getItemId())
        {
            case R.id.newNote:
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    public void cardSelected(View v)
    {
        //Three statements to get to the TextView
        //Didn't know a better way to do this
        CardView c = (CardView) v;
        RelativeLayout r = (RelativeLayout) c.getChildAt(0);
        TextView t = (TextView) r.getChildAt(0);

        Intent anIntent = new Intent(this, NoteActivity.class);
        //Extra passes the note to the NoteActivity so that it can be edited
        anIntent.putExtra("note", t.getText().toString());
        //passes Id which is used in the NoteActivity, which is used to identify which text view is
        //being used in the next activity.
        anIntent.putExtra("id", t.getId());
        startActivity(anIntent);
    }

    //Calls on create when a new intent is created
    //Updates the recycle view to the new state
    @Override
    protected void onNewIntent(Intent intent) {
        //Updates the recycler view when an item is deleted.
        mAdapter.notifyDataSetChanged();
    }
}

package org.robertlyon.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PlaceViewHolder> {

    private Context mCtx;
    private List<String> notes;

    Adapter(Context mCtx, List<String> notes)
    {
        this.mCtx = mCtx;
        this.notes = notes;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_layout, null);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, final int position) {

        String note = notes.get(position);
        holder.textView.setText(note);
        holder.textView.setId(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    class PlaceViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        CardView cardView;

        PlaceViewHolder(final View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.textBox);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(mCtx)
                            .setTitle("Delete Note")
                            .setMessage("Are you sure you wish to delete this note")
                            .setPositiveButton("Cancel", null)
                            .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.noteList.remove(textView.getId());
                                    Intent anIntent = new Intent(mCtx, MainActivity.class);
                                    mCtx.startActivity(anIntent);
                                }
                            })
                            .show();
                    return false;
                }
            });
        }
    }



}

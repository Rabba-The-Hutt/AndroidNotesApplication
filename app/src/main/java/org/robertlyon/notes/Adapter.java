package org.robertlyon.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.Placeholder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PlaceViewHolder> {

    Context mCtx;
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
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        String note = notes.get(position);
        holder.textView.setText(note);
        holder.textView.setId(notes.size());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    class PlaceViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;

        PlaceViewHolder(View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.textBox);
        }
    }



}

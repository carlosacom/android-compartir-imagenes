package com.example.helloword;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {
    private Context context;
    private ArrayList<Plublication> publications;
    private int resource;

    public PublicationAdapter( ArrayList<Plublication> publications, int resource, Context context) {
        this.context = context;
        this.publications = publications;
        this.resource = resource;
    }

    @NonNull
    @Override
    public PublicationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card, viewGroup, false);
        return new PublicationAdapter.PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationViewHolder publicationViewHolder, int i) {
        publicationViewHolder.cardTitle.setText(publications.get(i).getTitle());
        Picasso.get().load(publications.get(i).getImage()).into(publicationViewHolder.cardImage);
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    static class PublicationViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        ImageView cardImage;
        // CheckBox cardLike;
        public PublicationViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardImage = itemView.findViewById(R.id.cardImage);
            // cardLike = itemView.findViewById(R.id.cardImage);
        }
    }
}

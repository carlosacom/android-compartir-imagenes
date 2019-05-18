package com.example.helloword;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder> {
    private Context context;
    private ArrayList<Plublication> images;
    private int resource;

    public ImageGalleryAdapter( ArrayList<Plublication> images, int resource, Context context) {
        this.context = context;
        this.images = images;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_gallery, viewGroup, false);
        return new ImageGalleryAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Picasso.get().load(images.get(i).getImage()).into(imageViewHolder.ImageGallery);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView ImageGallery;
        // CheckBox cardLike;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageGallery = itemView.findViewById(R.id.imageGallery);
        }
    }
}

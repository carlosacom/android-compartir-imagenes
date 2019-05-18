package com.example.helloword;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        Picasso.get().load(images.get(i).getImage()).into(imageViewHolder.imageGallery);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageGallery;
        // CheckBox cardLike;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageGallery = itemView.findViewById(R.id.imageGallery);
            imageGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Plublication item = images.get(getLayoutPosition());
                    Intent intent = new Intent(context, ViewPublication.class);
                    intent.putExtra("publication", item);
                    context.startActivity(intent);
                }
            });
        }
    }

}




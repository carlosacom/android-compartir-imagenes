package com.example.helloword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ViewPublication extends AppCompatActivity {
    public TextView title;
    public TextView likes;
    public ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_publication);
        this.title = findViewById(R.id.viewTitle);
        this.likes = findViewById(R.id.viewLike);
        this.image = findViewById(R.id.viewImage);
        Plublication publication = (Plublication) getIntent().getSerializableExtra("publication");
        System.out.println(publication.getTitle());
        this.title.setText(publication.getTitle());
        String likeData = String.valueOf(publication.getLike());
        this.likes.setText("Likes: " + likeData);
        Picasso.get().load(publication.getImage()).into(this.image);
    }
}

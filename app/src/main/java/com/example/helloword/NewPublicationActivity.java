package com.example.helloword;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class NewPublicationActivity extends AppCompatActivity {
    public TextView newPublicationText;
    public Button newPublicationSubmit;
    public ImageView newPublicationImage;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_publication);
        this.newPublicationSubmit = findViewById(R.id.newPublicationSubmit);
        this.newPublicationImage = findViewById(R.id.newPublicationImage);
        this.newPublicationText = findViewById(R.id.newPublicationTitle);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        comprobarImagen();

        this.newPublicationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });
    }
    public void comprobarImagen(){
        if (getIntent().getExtras() != null){
            photoPath=getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            verFoto();

        }
    }

    private void verFoto(){
        Picasso.get().load(photoPath).into(this.newPublicationImage);
    }
    public void uploadPhoto () {
        this.newPublicationImage.setDrawingCacheEnabled(true);
        this.newPublicationImage.buildDrawingCache();
        Bitmap bitmap = this.newPublicationImage.getDrawingCache();
        bitmap = redimensionarImagenMaximo(bitmap, 1000, 1000);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] photoByte=baos.toByteArray();

        String photoName = photoPath.substring(photoPath.lastIndexOf("/")+1,photoPath.length());
        final StorageReference photoReference = storageReference.child("reporteImagenes/"+photoName);
        UploadTask uploadTask = photoReference.putBytes(photoByte);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return photoReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    crear(downloadUri.toString());
                    deleteCache(getApplicationContext());
                    finish();
                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }
    public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth){
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    public void crear(String direccionPhoto){
        Plublication reporte= new Plublication(databaseReference.push().getKey(),this.newPublicationText.getText().toString(),direccionPhoto,0);
        databaseReference.child("reportes").child(reporte.getId()).setValue(reporte);
    }
}

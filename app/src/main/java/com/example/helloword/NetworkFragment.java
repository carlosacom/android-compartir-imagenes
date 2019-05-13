package com.example.helloword;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment {
    private static final String TAG = "NetworkFragment" ;
    private static final int REQUEST_CAMERA = 1;
    PublicationAdapter publicationAdapter;
    FirebaseDatabase firebaseDatabase;
    RecyclerView publicationRecycler;
    FloatingActionButton addPublication ;
    private String photoPathTemp;

    public NetworkFragment() {
        // Required empty public constructor
        this.firebaseDatabase = FirebaseDatabase.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        this.publicationRecycler = (RecyclerView)view.findViewById(R.id.recyclerContainer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.publicationRecycler.setLayoutManager(linearLayoutManager);
        this.publicationAdapter = new PublicationAdapter(this.getNetworkCloud() ,R.layout.card,getActivity());
        this.publicationRecycler.setAdapter(publicationAdapter);
        this.addPublication = view.findViewById(R.id.addPublication);
        this.addPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagenCamara();
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK) {
            Log.d("HomeFragment", "CAMERA OK!! :)");
            Intent i = new Intent(getActivity(), NewPublicationActivity.class);
            i.putExtra("PHOTO_PATH_TEMP",photoPathTemp);
            startActivity(i);
        }

    }
    public ArrayList getNetwork() {
        ArrayList<Plublication> publications = new ArrayList();
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        publications.add(new Plublication("1","paisaje","https://algo.tv/includes/Images/Algo2018_logo_large.png", 4));
        return publications;
    }
    public ArrayList getNetworkCloud() {
        DatabaseReference myRef = this.firebaseDatabase.getReference();
        final ArrayList<Plublication> publications = new ArrayList();
        // Read from the database
        myRef.child("reportes").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                publications.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data:dataSnapshot.getChildren()) {
                        Plublication publication = data.getValue(Plublication.class);
                        publications.add(publication);
                        Log.d(TAG, "Value is: ");
                        publicationAdapter.notifyDataSetChanged();
                    }
                }
                // publications.add(dataSnapshot.getValue(Plublication.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return publications;
    }
    private void cargarImagenCamara(){
        Intent intentTakePicture= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentTakePicture.resolveActivity(getActivity().getPackageManager())!=null){
            File photoFile=null;
            try{
                photoFile=createImegeFile();

            } catch(Exception e){
                e.printStackTrace();
            }
            if (photoFile!= null) {
                Uri photoUri = FileProvider.getUriForFile(getActivity(),"com.example.helloword", photoFile);
                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intentTakePicture, REQUEST_CAMERA);
            }
        }
    }

    private File createImegeFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName = "JPEG" + timeStamp + "_";
        File storageDir =getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photo = File.createTempFile(imageFileName,".jpg", storageDir);
        photoPathTemp = "file:" + photo.getAbsolutePath();
        return photo;
    }

}

package com.example.helloword;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment {
    private static final String TAG = "NetworkFragment" ;
    PublicationAdapter publicationAdapter;
    FirebaseDatabase firebaseDatabase;
    RecyclerView publicationRecycler;
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
        return view;
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
}

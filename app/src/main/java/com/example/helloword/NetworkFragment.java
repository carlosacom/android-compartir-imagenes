package com.example.helloword;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment {
    PublicationAdapter publicationAdapter;

    public NetworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        RecyclerView publication = (RecyclerView)view.findViewById(R.id.recyclerContainer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        publication.setLayoutManager(linearLayoutManager);
        publicationAdapter = new PublicationAdapter(getNetwork() ,R.layout.card,getActivity());
        publication.setAdapter(publicationAdapter);
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
}

package com.example.stefan.newapp3;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Recents> recentsList;
    Context context;

    public RecentsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_shows, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        recyclerView = view.findViewById(R.id.recycler);
        AppDatabase db = Room.databaseBuilder(recyclerView.getContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        recentsList = db.recentsDao().getRecents();

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new HistoryAdapter(recentsList, recyclerView.getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}

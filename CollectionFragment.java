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
public class CollectionFragment extends Fragment implements SearchView.OnQueryTextListener,MenuItem.OnActionExpandListener{

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<PersonalList> myList;
    Context context;
    public CollectionFragment() {
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
        myList = db.personalListDao().getAllShows();

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new ListAdapter(myList, recyclerView.getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

        inflater.inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu,inflater);

    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {

        final AppDatabase db = Room.databaseBuilder(recyclerView.getContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        myList = db.personalListDao().getByName("%" + newText + "%");

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new ListAdapter(myList, recyclerView.getContext());
        recyclerView.setAdapter(adapter);



        return false;

    }
    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }

    public interface OnItem1SelectedListener {
        void OnItem1SelectedListener(String item);
    }
}
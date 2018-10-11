package com.example.stefan.newapp3;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Shows> showList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        String expression;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT shows.* FROM genres inner join sgjoin on genres.genres_genre_name=sgjoin.join_genre inner join shows on sgjoin.join_show=shows.shows_show_name where ");
        int index = 0;

        if (getIntent().getBooleanExtra("Genres", false)) {
            index++;
            sb.append("sgjoin.join_genre like \'" + getIntent().getStringExtra("0") + "\'");
        }
        if (getIntent().getBooleanExtra("Released By", false)) {
            if (index == 0) {
                index++;
                sb.append(" shows.show_releaser like \'" + getIntent().getStringExtra("1") + "\'");
            } else {
                sb.append(" AND shows.show_releaser like '" + getIntent().getStringExtra("1") + "\'");
            }
        }
        if (getIntent().getBooleanExtra("Years", false)) {
            if (index == 0) {
                index++;
                if (getIntent().getStringExtra("2").equals("90s")) {
                    sb.append("shows.show_year < 2000 AND shows.show_year >1990");
                }
                if (getIntent().getStringExtra("2").equals("2000s")) {
                    sb.append("shows.show_year < 2010 AND shows.show_year > 2000");
                }
                if (getIntent().getStringExtra("2").equals("2010s")) {
                    sb.append("shows.show_year > 2010");
                }
            } else {
                if (getIntent().getStringExtra("2").equals("90s")) {
                    sb.append(" AND shows.show_year < 2000 AND shows.show_year >1990");
                }
                if (getIntent().getStringExtra("2").equals("2000s")) {
                    sb.append(" AND shows.show_year < 2010 AND shows.show_year > 2000");
                }
                if (getIntent().getStringExtra("2").equals("2010s")) {
                    sb.append(" AND shows.show_year > 2010");
                }
            }
        }
        if (getIntent().getBooleanExtra("Completed", false)) {
            if (index == 0) {
                index++;
                if (getIntent().getStringExtra("4").equals("Ongoing")) {
                    sb.append("shows.show_completed like 0");
                }
                if (getIntent().getStringExtra("4").equals("Completed")) {
                    sb.append("shows.show_completed like 1");
                }
            } else {
                if (getIntent().getStringExtra("4").equals("Ongoing")) {
                    sb.append(" AND shows.show_completed like 0");
                }
                if (getIntent().getStringExtra("4").equals("Completed")) {
                    sb.append(" AND shows.show_completed like 1");
                }
            }
        }
        if (getIntent().getBooleanExtra("Rating", false)) {
            if (index == 0)
                Toast.makeText(getApplicationContext(), "Can't order an empty list", Toast.LENGTH_SHORT).show();
            else {
                if (getIntent().getStringExtra("3").equals("Ascending by Rating")) {
                    sb.append(" ORDER BY shows.shows_show_name, shows.imdb_rating DESC");
                }
                if (getIntent().getStringExtra("3").equals("Descending by Rating")) {
                    sb.append("ORDER BY shows.shows_show_name, shows.imdb_rating ASC");
                }
            }
        }
        expression = sb.toString();
        setSupportActionBar(toolbar);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        showList = db.rawDao().getAdvancedSearchList(new SimpleSQLiteQuery(expression));
        Map<String, Shows> uniqueMap = new HashMap<String, Shows>();
        List<Shows> uniqueList;

        if (showList != null && !showList.isEmpty()) {
            for (Shows show : showList) {
                uniqueMap.put(String.valueOf(show.getShowName()), show);
            }
        }
        uniqueList = new ArrayList<>(uniqueMap.values());
        showList.clear();
        showList = uniqueList;

        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ShowsAdapter(showList, recyclerView.getContext());
        recyclerView.setAdapter(adapter);
    }
}

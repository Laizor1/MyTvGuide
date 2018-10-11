package com.example.stefan.newapp3;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class GenreSearch extends AppCompatActivity {
    Button btnAction,btnComedy,btnThriller,btnMystery,btnDrama,btnNoGenre;
    RecyclerView genreRecycler;
    RecyclerView.Adapter adapter,actionAdapter,comedyAdapter,thrillerAdapter,mysteryAdapter,dramaAdapter;
    List<Shows> genreList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_search);

        btnAction=findViewById(R.id.btnAction);
        btnComedy=findViewById(R.id.btnComedy);
        btnThriller=findViewById(R.id.btnThriller);
        btnMystery=findViewById(R.id.btnMystery);
        btnDrama=findViewById(R.id.btnDrama);
        btnNoGenre = findViewById(R.id.btnNoGenre);
        genreRecycler=findViewById(R.id.genre_recycler);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        /*db.showsDao().nukeTable();
        db.showsDao().insertAll(Shows.populateData());*/
        genreList = db.showsDao().getAllShows();

        btnAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genreList=db.sgJoinDao().getByGenre("Action");
                actionAdapter = new GenreListAdapter(genreList);
                genreRecycler.setAdapter(actionAdapter);
            }
        });
        btnComedy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genreList=db.sgJoinDao().getByGenre("Comedy");
                comedyAdapter = new GenreListAdapter(genreList);
                genreRecycler.setAdapter(comedyAdapter);
            }
        });
        btnThriller.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genreList=db.sgJoinDao().getByGenre("Thriller");
                thrillerAdapter = new GenreListAdapter(genreList);
                genreRecycler.setAdapter(thrillerAdapter);
            }
        });
        btnDrama.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genreList=db.sgJoinDao().getByGenre("Drama");
                dramaAdapter = new GenreListAdapter(genreList);
                genreRecycler.setAdapter(dramaAdapter);
            }
        });
        btnMystery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genreList=db.sgJoinDao().getByGenre("Mystery");
                mysteryAdapter = new GenreListAdapter(genreList);
                genreRecycler.setAdapter(mysteryAdapter);
            }
        });
        btnNoGenre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                genreList=db.showsDao().getAllShows();
                adapter = new GenreListAdapter(genreList);
                genreRecycler.setAdapter(adapter);
            }
        });
        genreRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GenreListAdapter(genreList);
        genreRecycler.setAdapter(adapter);
    }
}
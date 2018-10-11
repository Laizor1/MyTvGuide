package com.example.stefan.newapp3;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShowCardViewInfo extends AppCompatActivity implements View.OnClickListener {
    TextView showName, showYears, showDesc, showEp, showGenre, showRating, showCountry,searchReleaser;
    ImageView img;
    Button btnAdd;
    Shows show;
    List<String> genreList;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    List<Actors> actorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String years, episode, rating,genres=" ",releaser = "Released by: ";
        StringBuilder sb=new StringBuilder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_view_info);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        show=db.showsDao().findShow(getIntent().getStringExtra("name"));
        if(db.recentsDao().countShowExists(show.getX())==0) {
            if (db.recentsDao().countRecents() >= 20) {
                Date date = db.recentsDao().getOldestDate();
                db.recentsDao().deleteOldest(date);

            } else {
                db.recentsDao().insertAll(new Recents(show.getX(), Calendar.getInstance().getTime()));
            }
        }else{
            db.recentsDao().updateDate(show.getX(),Calendar.getInstance().getTime());

        }
        showName = findViewById(R.id.search_name);
        showYears = findViewById(R.id.search_years);
        showDesc = findViewById(R.id.search_description);
        showEp = findViewById(R.id.search_episode);
        showGenre = findViewById(R.id.search_genre);
        showRating = findViewById(R.id.search_rating);
        showCountry = findViewById(R.id.search_country);
        searchReleaser = findViewById(R.id.search_releaser);
        img = findViewById(R.id.show_image);
        btnAdd = findViewById(R.id.add_to_plist);
        genreList=db.sgJoinDao().getGenresByName(getIntent().getStringExtra("name"));
        try {
            for (String genre : genreList) {
                sb.append(genre);
                sb.append(", ");
            }
            genres = sb.toString();
            genres = genres.substring(0, genres.length() - 2);
        }catch(Exception e){}
        years = show.getShowYear() + " - " + show.getShowEnd();
        episode = "Episode length:" + show.getDuration();
        rating = "IMDB Note:" + show.getImdbRating();
        releaser +=show.getShowReleaser();
        searchReleaser.setText(releaser);
        showName.setText(show.getShowName());
        showYears.setText(years);
        showDesc.setText(show.getShowDesc());
        showEp.setText(episode);
        showGenre.setText(genres);
        showRating.setText(rating);
        showCountry.setText(show.getCountry());
        Picasso.with(getApplicationContext()).load(show.getImgSrc()).into(img);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        img.setOnClickListener(this);
        actorList=db.saJoinDao().getActorsByShow(getIntent().getStringExtra("name"));
        recycler=findViewById(R.id.horizontal_shows_recycler);
        recycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(layoutManager);
        adapter = new ShowInfoAdapter(actorList,this);
        recycler.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<String> genreList;
                StringBuilder sb=new StringBuilder();
                show = db.showsDao().findShow(getIntent().getStringExtra("name"));
                genreList=db.sgJoinDao().getGenresByName(getIntent().getStringExtra("name"));
                if(db.personalListDao().showExists(getIntent().getStringExtra("name"))!=0){
                    Toast.makeText(ShowCardViewInfo.this,"Show already added!",Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        for (String genre : genreList) {
                            sb.append(genre);
                            sb.append(", ");
                        }
                        String genres2 = sb.toString();
                        genres2 = genres2.substring(0, genres2.length() - 2);
                       db.personalListDao().insertAll(new PersonalList(show.getShowName(), show.getShowYear(), genres2, show.getShowDesc(),show.getImdbRating(),show.getImgSrc(),show.getCountry(),show.getDuration(),show.getShowEnd(),show.getShowReleaser(),false, Calendar.getInstance().getTime()));

                        Toast.makeText(ShowCardViewInfo.this, "Succesfully added!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {}
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,ImageFullScreen.class);
        intent.putExtra("img",show.getImgSrc());
        this.startActivity(intent);
    }
}

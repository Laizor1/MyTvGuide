package com.example.stefan.newapp3;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs = null;
    BottomNavigationView mainNav;
    FrameLayout frameLayout;
    private ShowsFragment showsFragment;
    private ActorsFragment actorsFragment;
    private FiltersFragment filtersFragment;
    private RecentsFragment recentsFragment;
    private CollectionFragment collectionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("com.example.stefan.newapp3", MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 101);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        showsFragment = new ShowsFragment();
        actorsFragment = new ActorsFragment();
        filtersFragment = new FiltersFragment();
        recentsFragment = new RecentsFragment();
        collectionFragment = new CollectionFragment();

        mainNav = findViewById(R.id.navigation);
        frameLayout = findViewById(R.id.main_frame);
        setFragment(showsFragment);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_shows:
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(showsFragment);
                        return true;
                    case R.id.nav_actors:
                        mainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(actorsFragment);
                        return true;
                    case R.id.nav_filters:
                        mainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(filtersFragment);
                        return true;
                    case R.id.nav_collection:
                        mainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(collectionFragment);
                        return true;
                    case R.id.nav_recent:
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(recentsFragment);
                        return true;
                }
                return false;
            }
        });


    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();


            try {
                JSONObject json = new JSONObject(loadJSONFromAsset());

                JSONArray releasers = json.getJSONArray("releasers");
                for (int i = 0; i < releasers.length(); i++) {
                    JSONObject releasersJSON = releasers.getJSONObject(i);
                    Releasers releaser = new Releasers(releasersJSON.getString("releaserName"));
                    db.releasersDao().insertAll(releaser);
                }

                JSONArray countries = json.getJSONArray("countries");
                for (int i = 0; i < countries.length(); i++) {
                    JSONObject countriesJSON = countries.getJSONObject(i);
                    Countries country = new Countries(countriesJSON.getString("country"));
                    db.countriesDao().insertAll(country);
                }

                JSONArray shows = json.getJSONArray("shows");
                for (int i = 0; i < shows.length(); i++) {
                    JSONObject showJSON = shows.getJSONObject(i);
                    Shows show = new Shows(showJSON.getString("showName"), showJSON.getString("showYear"), showJSON.getString("showEnd"), showJSON.getString("showGenre"), showJSON.getString("showDesc"), showJSON.getInt("genreRating"), showJSON.getDouble("imdbRating"), showJSON.getString("imgSrc"), showJSON.getString("country"), showJSON.getString("duration"), showJSON.getString("releaserName"),showJSON.getBoolean("showCompleted"));
                    db.showsDao().insertAll(show);

                }

                JSONArray genres = json.getJSONArray("genres");
                for (int i = 0; i < genres.length(); i++) {
                    JSONObject genreJson = genres.getJSONObject(i);
                    Genres genre = new Genres(genreJson.getString("genreName"));
                    db.genresDao().insertAll(genre);
                }

                JSONArray actors = json.getJSONArray("actors");
                for (int i = 0; i < actors.length(); i++) {
                    JSONObject actorJSON = actors.getJSONObject(i);
                    Actors actor = new Actors(actorJSON.getString("actorName"), actorJSON.getString("actorBirth"), actorJSON.getString("actorDesc"), actorJSON.getString("actorUrl"));
                    db.actorsDao().insertAll(actor);
                }
                JSONArray saJoin = json.getJSONArray("sajoin");
                for (int i = 0; i < saJoin.length(); i++) {
                    JSONObject sajoinJSON = saJoin.getJSONObject(i);
                    SAJoin sajoin = new SAJoin(sajoinJSON.getString("saShowName"), sajoinJSON.getString("saActorName"), sajoinJSON.getString("actorRole"));
                    db.saJoinDao().insertAll(sajoin);
                }
                JSONArray sgJoin = json.getJSONArray("sgjoin");
                for (int i = 0; i < sgJoin.length(); i++) {
                    JSONObject sgJoinJSON = sgJoin.getJSONObject(i);
                    SGJoin sgjoin = new SGJoin(sgJoinJSON.getString("showName"), sgJoinJSON.getString("showGenre"));
                    db.sgJoinDao().insertAll(sgjoin);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            this.finish();
            setFragment(showsFragment);
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("testjson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Do you really want to close the app?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}

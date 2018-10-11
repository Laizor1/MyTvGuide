package com.example.stefan.newapp3;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.*;
import android.content.*;
import android.support.annotation.NonNull;
import android.content.*;
import java.util.concurrent.Executors;

/**
 * Created by Stefan on 2/27/2018.
 */

@Database(entities = {Shows.class,PersonalList.class,Genres.class,SGJoin.class,Actors.class,SAJoin.class,Countries.class,Releasers.class,Recents.class}, version =45)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ShowsDao showsDao();
    public abstract PersonalListDao personalListDao();
    public abstract GenresDao genresDao();
    public abstract SGJoinDao sgJoinDao();
    public abstract ActorsDao actorsDao();
    public abstract SAJoinDao saJoinDao();
    public abstract CountriesDao countriesDao();
    public abstract ReleasersDao releasersDao();
    public abstract RecentsDao recentsDao();
    public abstract RawDao rawDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =buildDatabase(context);
                   /* Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            .allowMainThreadQueries()
                            .build();*/
        }
        return INSTANCE;
    }
    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "user-database").addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                //getInstance(context).showsDao().insertAll(Shows.populateData());

                            }
                        });
                    }
                })
                .build();
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
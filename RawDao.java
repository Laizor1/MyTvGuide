package com.example.stefan.newapp3;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.RawQuery;

import java.util.List;

/**
 * Created by Stefan on 6/3/2018.
 */
@Dao
public interface RawDao {

    @RawQuery(observedEntities = Shows.class)
    List<Shows> getAdvancedSearchList(SupportSQLiteQuery query);

}

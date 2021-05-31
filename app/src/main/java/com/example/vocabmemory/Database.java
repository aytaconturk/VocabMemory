package com.example.vocabmemory;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Verbs.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract IVerbsDao myVerbsDao();
}

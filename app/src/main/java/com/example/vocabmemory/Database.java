package com.example.vocabmemory;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vocabmemory.Modal.Adjectives;
import com.example.vocabmemory.Modal.Adverbs;
import com.example.vocabmemory.Modal.IAdjectivesDao;
import com.example.vocabmemory.Modal.IAdverbsDao;
import com.example.vocabmemory.Modal.IPhrasesAndIdiomsDao;
import com.example.vocabmemory.Modal.IUserWordsDao;
import com.example.vocabmemory.Modal.IVerbsDao;
import com.example.vocabmemory.Modal.PhrasesAndIdioms;
import com.example.vocabmemory.Modal.UserWords;
import com.example.vocabmemory.Modal.Verbs;

@androidx.room.Database(entities = {Verbs.class, Adverbs.class, Adjectives.class, PhrasesAndIdioms.class, UserWords.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static final String DB_Name = "vocabulary";
    private static Database instance;

    public abstract IVerbsDao myVerbsDao();

    public abstract IAdverbsDao getAdverbsDao();

    public abstract IAdjectivesDao getAdjectivesDao();

    public abstract IPhrasesAndIdiomsDao getPhrasesAndIdiomsDao();

    public abstract IUserWordsDao getUserWordsDao();

    public static synchronized Database getInstance(Context context)
    {
        if(instance == null)
        {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),Database.class,DB_Name)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }

}

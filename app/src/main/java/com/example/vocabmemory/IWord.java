package com.example.vocabmemory;

import android.view.View;

public interface IWord {

    public void getLayoutObjects(View view);

    public void nextWord();

    public void updateWordStatus(int id, String word, String meaning, String example, String synonyms, boolean status);

    public boolean isWordLearned(int id);
}

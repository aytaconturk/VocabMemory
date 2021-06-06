package com.example.vocabmemory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vocabmemory.Modal.UserWords;
import com.example.vocabmemory.Modal.Verbs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable handlerTask;

    private ImageButton imgBtnVerbsClose;
    private Button btnAddWordFragment, dontKnowButton, learnedButton;
    private TextView tvWord, tvMeaning, tvExample, tvLearningStatus, tvDontKnowWordNumber;
    private LinearLayout synonymsLinearLayout;

    private int id;
    private String word;
    private String meaning;
    private String example;
    private String synonyms;
    private String[] synonymsValues;

    private Button bottomNavigationView;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


//        btnAddWordFragment = findViewById(R.id.btn_add_verb_fragment);

        imgBtnVerbsClose = findViewById(R.id.img_btn_words_close);
        dontKnowButton = findViewById(R.id.btn_dont_know);
        learnedButton = findViewById(R.id.btn_know);
        tvWord = findViewById(R.id.tv_word);
        tvMeaning = findViewById(R.id.tv_meaning);
        tvExample = findViewById(R.id.tv_example);
        synonymsLinearLayout = findViewById(R.id.linearLayout_synonyms);
        tvDontKnowWordNumber = findViewById(R.id.tv_dont_know_word_number);



        int tableLength = Database.getInstance(getApplicationContext()).getUserWordsDao().getUserWordsLength();

        // Get a random between 1 and tableLength.length
        int randomVerb;
        randomVerb = (int) (Math.random() * (tableLength) + 1);

        // random sayi indexinin verb objesini getir
        UserWords userWords = new UserWords();
        userWords = Database.getInstance(getApplicationContext()).getUserWordsDao().getUserWordById(randomVerb);

        id = userWords.getId();
        word = userWords.getWord();
        meaning = userWords.getMeaning();
        example = userWords.getExample();
        synonyms = userWords.getSynonyms();
        synonymsValues = synonyms.split(",");

        tvWord.setText(word);
        tvMeaning.setText(meaning);
        tvExample.setText(Html.fromHtml(example));

        for (int i = 0; i < synonymsValues.length; i++) {
            TextView valueTV = new TextView(this);
            valueTV.setText(" " + synonymsValues[i] + " ");
            valueTV.setTextSize(18);
            valueTV.setPadding(0, 5, 0, 5);

            valueTV.setBackgroundResource(R.color.light);
            valueTV.setBackgroundResource(R.drawable.rounded_corner);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 30, 0);
            valueTV.setLayoutParams(params);

            synonymsLinearLayout.addView(valueTV);
        }





        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

}
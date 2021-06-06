package com.example.vocabmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vocabmemory.Modal.Adjectives;
import com.example.vocabmemory.Modal.PhrasesAndIdioms;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PhrasesAndIdiomsFragment extends Fragment implements IWord {

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

    public PhrasesAndIdiomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phrases_and_idioms, container, false);

        getLayoutObjects(view);

        nextWord();

        // I learned butonuna tiklaninca o word'un learning statusunu true yap
        learnedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateWordStatus(id, word, meaning, example, synonyms, true);

                // delay 1 seconds calling new word
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synonymsLinearLayout.removeAllViews();

                nextWord();

            }
        });

        // I don't know butonuna tiklaninca o word'un learning statusunu false yap
        dontKnowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateWordStatus(id, word, meaning, example, synonyms, false);

                // delay 1 seconds calling new word
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synonymsLinearLayout.removeAllViews();

                nextWord();

            }
        });


        imgBtnVerbsClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to Home fragment
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        btnAddWordFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add verb word to the Database
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddWordFragment()).addToBackStack(null).commit();
            }
        });



        return view;
    }

    @Override
    public void getLayoutObjects(View view) {
        btnAddWordFragment = view.findViewById(R.id.btn_add_verb_fragment);

        imgBtnVerbsClose = view.findViewById(R.id.img_btn_words_close);
        dontKnowButton = view.findViewById(R.id.btn_dont_know);
        learnedButton = view.findViewById(R.id.btn_know);
        tvWord = view.findViewById(R.id.tv_word);
        tvMeaning = view.findViewById(R.id.tv_meaning);
        tvExample = view.findViewById(R.id.tv_example);
        synonymsLinearLayout = view.findViewById(R.id.linearLayout_synonyms);
        tvDontKnowWordNumber = view.findViewById(R.id.tv_dont_know_word_number);
    }

    @Override
    public void updateWordStatus(int id, String word, String meaning, String example, String synonyms, boolean status) {
        handler = new Handler();
        handlerTask = new Runnable() {
            @Override
            public void run() {
                PhrasesAndIdioms phrasesAndIdiom = new PhrasesAndIdioms();

                phrasesAndIdiom.setId(id);
                phrasesAndIdiom.setWord(word);
                phrasesAndIdiom.setMeaning(meaning);
                phrasesAndIdiom.setExample(example);
                phrasesAndIdiom.setSynonyms(synonyms);


                if (status)
                    phrasesAndIdiom.setLearningStatus(1); // mark learningStatus as true
                else
                    phrasesAndIdiom.setLearningStatus(0); // mark learningStatus as false


                Database.getInstance(getContext()).getPhrasesAndIdiomsDao().updateWord(phrasesAndIdiom);

                handler.postDelayed(handlerTask, 500);
            }
        };
        handlerTask.run();
    }

    @Override
    public void nextWord() {
        int tableLength = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdiomsLength();

        int learnedWord = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getKnowWordNumber();
        int notLearnedWord = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getDontKnowWordNumber();


        System.out.println("Table Length: " + tableLength);
        System.out.println("learned word: " + learnedWord);

        // if all words learned in this category finish this activity
        if (tableLength == learnedWord) {

            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new CongratsFragment()).addToBackStack(null).commit();
        } else {

            // Get a random between 1 and tableLength.length
            int randomVerb;
            randomVerb = (int) (Math.random() * (tableLength) + 1);

            // random sayi indexi ile databasede search yap eger indexin learning statusu true ise tekrar random deger ata
            while (isWordLearned(randomVerb)) {
                randomVerb = (int) (Math.random() * (tableLength) + 1);
            }

            // random sayi indexinin verb objesini getir
            PhrasesAndIdioms phrasesAndIdiom = new PhrasesAndIdioms();
            phrasesAndIdiom = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdiomsById(randomVerb);

            id = phrasesAndIdiom.getId();
            word = phrasesAndIdiom.getWord();
            meaning = phrasesAndIdiom.getMeaning();
            example = phrasesAndIdiom.getExample();
            synonyms = phrasesAndIdiom.getSynonyms();

            tvWord.setText(word);
            tvMeaning.setText(meaning);
            tvExample.setText(Html.fromHtml(example));

            System.out.println("length: " + synonyms.isEmpty());
            if(!synonyms.isEmpty()){
                synonymsValues = synonyms.split(",");

                for (int i = 0; i < synonymsValues.length; i++) {
                    TextView valueTV = new TextView(getContext());
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
            }

            tvDontKnowWordNumber.setText("" + Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getDontKnowWordNumber());

        }
    }

    @Override
    public boolean isWordLearned(int id) {
        PhrasesAndIdioms phrasesAndIdiom = new PhrasesAndIdioms();
        phrasesAndIdiom = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdiomsById(id);

        if (phrasesAndIdiom.getLearningStatus() == 0)
            return false;
        return true;

    }

    public void resetAllLearningStatus() {
        List<PhrasesAndIdioms> phrasesAndIdioms = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdioms();

        for (PhrasesAndIdioms phrasesAndIdiom : phrasesAndIdioms) {
            int id = phrasesAndIdiom.getId();
            String word = phrasesAndIdiom.getWord();
            String meaning = phrasesAndIdiom.getMeaning();
            String example = phrasesAndIdiom.getExample();
            String synonyms = phrasesAndIdiom.getSynonyms();


            PhrasesAndIdioms renewPhrasesAndIdiom = new PhrasesAndIdioms();

            renewPhrasesAndIdiom.setId(id);
            renewPhrasesAndIdiom.setWord(word);
            renewPhrasesAndIdiom.setMeaning(meaning);
            renewPhrasesAndIdiom.setExample(example);
            renewPhrasesAndIdiom.setSynonyms(synonyms);
            renewPhrasesAndIdiom.setLearningStatus(0);

            Database.getInstance(getContext()).getPhrasesAndIdiomsDao().updateWord(renewPhrasesAndIdiom);
        }
    }
}
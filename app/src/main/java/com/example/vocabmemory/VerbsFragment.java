package com.example.vocabmemory;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.vocabmemory.Modal.Verbs;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VerbsFragment extends Fragment implements IWord {

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

    public VerbsFragment() {
        // Required empty public constructor
    }

    public void resetAllLearningStatus() {
        List<Verbs> verbs = Database.getInstance(getContext()).myVerbsDao().getVerbs();

        for (Verbs verb : verbs) {
            int id = verb.getId();
            String word = verb.getWord();
            String meaning = verb.getMeaning();
            String example = verb.getExample();
            String synonyms = verb.getSynonyms();


            Verbs renewVerb = new Verbs();

            renewVerb.setId(id);
            renewVerb.setWord(word);
            renewVerb.setMeaning(meaning);
            renewVerb.setExample(example);
            renewVerb.setSynonyms(synonyms);
            renewVerb.setLearningStatus(0);

            Database.getInstance(getContext()).myVerbsDao().updateVerb(renewVerb);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verbs, container, false);

        getLayoutObjects(view);

        nextWord();

//        resetAllLearningStatus();


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
    public void updateWordStatus(int id, String word, String meaning, String example, String syllabus, boolean status) {
        handler = new Handler();
        handlerTask = new Runnable() {
            @Override
            public void run() {
                Verbs updateVerb = new Verbs();

                updateVerb.setId(id);
                updateVerb.setWord(word);
                updateVerb.setMeaning(meaning);
                updateVerb.setExample(example);
                updateVerb.setSynonyms(syllabus);


                if (status)
                    updateVerb.setLearningStatus(1); // mark learningStatus as true
                else
                    updateVerb.setLearningStatus(0); // mark learningStatus as false


                Database.getInstance(getContext()).myVerbsDao().updateVerb(updateVerb);

                handler.postDelayed(handlerTask, 500);
            }
        };
        handlerTask.run();
    }


    @Override
    public void nextWord() {

        int tableLength = Database.getInstance(getContext()).myVerbsDao().getVerbsLength();

        int learnedWord = Database.getInstance(getContext()).myVerbsDao().getKnowWordNumber();
        int notLearnedWord = Database.getInstance(getContext()).myVerbsDao().getDontKnowWordNumber();


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
            Verbs verb = new Verbs();
            verb = Database.getInstance(getContext()).myVerbsDao().getVerbById(randomVerb);

            id = verb.getId();
            word = verb.getWord();
            meaning = verb.getMeaning();
            example = verb.getExample();
            synonyms = verb.getSynonyms();
            synonymsValues = synonyms.split(",");

            tvWord.setText(word);
            tvMeaning.setText(meaning);
            tvExample.setText(Html.fromHtml(example));

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

            tvDontKnowWordNumber.setText("" + Database.getInstance(getContext()).myVerbsDao().getDontKnowWordNumber());

        }
    }

    @Override
    public boolean isWordLearned(int id) {
        Verbs verb = new Verbs();
        verb = Database.getInstance(getContext()).myVerbsDao().getVerbById(id);

        if (verb.getLearningStatus() == 0)
            return false;
        return true;

    }
}
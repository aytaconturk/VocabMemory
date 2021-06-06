package com.example.vocabmemory;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private CardView verbsCard;
    private Button btnVerbs, btnAdverbs, btnAdjectives, btnPhrases, btnQuiz;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        verbsCard = view.findViewById(R.id.cardView1);
        btnVerbs = view.findViewById(R.id.btn_verbs);
        btnAdverbs = view.findViewById(R.id.btn_adverbs);
        btnAdjectives = view.findViewById(R.id.btn_adjectives);
        btnPhrases = view.findViewById(R.id.btn_phrases_idioms);
        btnQuiz = view.findViewById(R.id.btn_quiz);

        btnVerbs.setOnClickListener(this);
        btnAdverbs.setOnClickListener(this);
        btnAdjectives.setOnClickListener(this);
        btnPhrases.setOnClickListener(this);
        btnQuiz.setOnClickListener(this);

        verbsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //verbs start buttonuna tiklaninca VerbsFragment fragmentina gider
            case R.id.btn_verbs:
                //Main Activitedeki fragmentde bir gecis(transacion) baslat ve oradaki fragmentte fragment containerin icine VerbsFragmentini cagir ve degistir.
                //addToBackStack(null) fonksiyonu uygulamada geri tusuna basinca geri atmasin diye
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new VerbsFragment()).addToBackStack(null).commit();
                break;

            //adverbs start butonuna tiklaninca AdverbsFragment fragmentina gider
            case R.id.btn_adverbs:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AdverbFragment()).addToBackStack(null).commit();
                break;

            //adjectives start butonuna tiklaninca AdjectivesFragment fragmentina gider
            case R.id.btn_adjectives:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AdjectivesFragment()).addToBackStack(null).commit();
                break;

            //phrases and idioms start butonuna tiklaninca PhrasesAndIdiomsFragment fragmentina gider
            case R.id.btn_phrases_idioms:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new PhrasesAndIdiomsFragment()).addToBackStack(null).commit();
                break;

            //Quiz start butonuna tiklaninca Quiz fragmentina gider
            case R.id.btn_quiz:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new QuizFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
package com.example.vocabmemory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class QuizFragment extends Fragment {
    private Context mContext;

    private String[] categories= {"Verbs", "Adverbs", "Adjectives", "Phrases & Idioms"};
    private String categoryName = "";

    private Spinner categorySpinner;
    private ArrayAdapter categoryAdapter;

    private Button btnStartQuiz;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        categorySpinner = view.findViewById(R.id.category_select);
        btnStartQuiz = view.findViewById(R.id.btn_start_quiz);

        categoryAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, categories);
        categoryAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        categorySpinner.setAdapter(categoryAdapter);


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String category = categories[position];

                switch (category){
                    case "Verbs":
                        categoryName = "Verbs";
                        break;

                    case "Adverbs":
                        categoryName = "Adverbs";
                        break;

                    case "Adjectives":
                        categoryName = "Adjectives";
                        break;

                    case "Phrases & Idioms":
                        categoryName = "Phrases & Idioms";

                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("categoryName", categoryName);
                editor.apply();

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new MakeQuizFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
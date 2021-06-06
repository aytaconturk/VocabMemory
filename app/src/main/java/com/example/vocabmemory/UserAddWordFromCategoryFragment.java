package com.example.vocabmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vocabmemory.Modal.Adjectives;
import com.example.vocabmemory.Modal.Adverbs;
import com.example.vocabmemory.Modal.PhrasesAndIdioms;
import com.example.vocabmemory.Modal.UserWords;
import com.example.vocabmemory.Modal.Verbs;

import java.util.List;

public class UserAddWordFromCategoryFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String[] categories= {"Verbs", "Adverbs", "Adjectives", "Phrases & Idioms"};
    String[] words= {"Please choose category first"};
    String[] verb_words, adverb_words, adjectives_words, phrases_words;
    private String categoryName = "";

    private Spinner categorySpinner, wordSpinner;
    private ArrayAdapter categoryAdapter, wordAdapter;

    private String userWord, meaning, example, synonyms;
    private int lastId;

    private Button btnSave;

    public UserAddWordFromCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_add_word_from_category, container, false);

        List<String> verbs = Database.getInstance(getContext()).myVerbsDao().getVerbWords();
        List<String> adverbs = Database.getInstance(getContext()).getAdverbsDao().getAdverbWords();
        List<String> adjectives = Database.getInstance(getContext()).getAdjectivesDao().getAdjectivesWords();
        List<String> phrasesAndIdioms = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdiomsWords();


        verb_words = verbs.toArray(new String[0]);
        adverb_words = adverbs.toArray(new String[0]);
        adjectives_words = adjectives.toArray(new String[0]);
        phrases_words = phrasesAndIdioms.toArray(new String[0]);


        categorySpinner = view.findViewById(R.id.category_select);
        wordSpinner = view.findViewById(R.id.word_select);
        btnSave = view.findViewById(R.id.btn_start_quiz);



        categoryAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, categories);
        wordAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, words);


        categoryAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        wordAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);


        categorySpinner.setAdapter(categoryAdapter);
        wordSpinner.setAdapter(wordAdapter);


        categorySpinner.setOnItemSelectedListener(this);
        wordSpinner.setOnItemSelectedListener(this);

//        categorySpinner.setOnItemClickListener(this);
//        wordSpinner.setOnItemClickListener(this);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String category = categories[position];

                switch (category){
                    case "Verbs":
                        categoryName = "Verbs";
                        words = verb_words;
                        wordAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, verb_words);
                        wordAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                        wordSpinner.setAdapter(wordAdapter);
                        break;

                    case "Adverbs":
                        categoryName = "Adverbs";
                        words = adverb_words;
                        wordAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, adverb_words);
                        wordAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                        wordSpinner.setAdapter(wordAdapter);
                        break;

                    case "Adjectives":
                        categoryName = "Adjectives";
                        words = adjectives_words;
                        wordAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, adjectives_words);
                        wordAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                        wordSpinner.setAdapter(wordAdapter);
                        break;

                    case "Phrases & Idioms":
                        categoryName = "Phrases & Idioms";
                        words = phrases_words;
                        wordAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, phrases_words);
                        wordAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                        wordSpinner.setAdapter(wordAdapter);
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        wordSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String word = words[position];
                System.out.println("category Name: " + categoryName);
                System.out.println("selected word: " + word);

                switch (categoryName){
                    case "Verbs":
                        Verbs verb = Database.getInstance(getContext()).myVerbsDao().getVerbByWord(word);
                        userWord = verb.getWord();
                        meaning = verb.getMeaning();
                        example = verb.getExample();
                        synonyms = verb.getSynonyms();
                        break;

                    case "Adverbs":
                        Adverbs adverb = Database.getInstance(getContext()).getAdverbsDao().getAdverbByWord(word);
                        userWord = adverb.getWord();
                        meaning = adverb.getMeaning();
                        example = adverb.getExample();
                        synonyms = adverb.getSynonyms();
                        break;

                    case "Adjectives":
                        Adjectives adjective = Database.getInstance(getContext()).getAdjectivesDao().getAdjectiveByWord(word);
                        userWord = adjective.getWord();
                        meaning = adjective.getMeaning();
                        example = adjective.getExample();
                        synonyms = adjective.getSynonyms();
                        break;

                    case "Phrases & Idioms":
                        PhrasesAndIdioms phrasesAndIdiom = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdiomByWord(word);
                        userWord = phrasesAndIdiom.getWord();
                        meaning = phrasesAndIdiom.getMeaning();
                        example = phrasesAndIdiom.getExample();
                        synonyms = phrasesAndIdiom.getSynonyms();
                        break;
                }

            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserWords user = new UserWords();
                lastId = Database.getInstance(getContext()).getUserWordsDao().getLastId();
                user.setId(lastId + 1);
                user.setWord(userWord);
                user.setMeaning(meaning);
                user.setExample(example);
                user.setSynonyms(synonyms);

                // Save user into database
                Database.getInstance(getContext()).getUserWordsDao().addWord(user);
                Toast.makeText(getActivity(), "Successfully word added into your list", Toast.LENGTH_SHORT).show();

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {

        if (view == categorySpinner) {
            categorySpinner.performClick();
            wordSpinner.performClick();
        }
        if (view == wordSpinner) {
            categorySpinner.performClick();
            wordSpinner.performClick();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
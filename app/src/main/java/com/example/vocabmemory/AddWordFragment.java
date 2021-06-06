package com.example.vocabmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vocabmemory.Modal.Adjectives;
import com.example.vocabmemory.Modal.Adverbs;
import com.example.vocabmemory.Modal.PhrasesAndIdioms;
import com.example.vocabmemory.Modal.Verbs;

import java.util.List;

public class AddWordFragment extends Fragment {

    private EditText verbId, verbName, verbMeaning, verbExample;
    private TextView verbListTV;
    private Button addWord;

    public AddWordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);

/*      verbId = view.findViewById(R.id.et_verbs_id);
        verbName = view.findViewById(R.id.et_verb_name);
        verbMeaning = view.findViewById(R.id.et_verb_meaning);
        verbExample = view.findViewById(R.id.et_verb_example);*/
        verbListTV = view.findViewById(R.id.tvVerbsList);
        addWord = view.findViewById(R.id.btn_add_new_word);

        //MainActivity.database.myVerbsDao().deleteVerb(MainActivity.database.myVerbsDao().getVerbById(id));

        int id = 20;
        String word = "Bite the bullet";
        String meaning = "Decide to do something unpleasant that you have avoiding doing.";
        String example = "hate going to the dentist, but I'll just have to " + " <font color=\"red\"><em>" + "bite the bullet." + "</em></font> " + "";
        String synonyms = "";
        ///////////////////////////////////////////////

        PhrasesAndIdioms phrasesAndIdiom = new PhrasesAndIdioms();
        phrasesAndIdiom.setId(id);
        phrasesAndIdiom.setWord(word);
        phrasesAndIdiom.setMeaning(meaning);
        phrasesAndIdiom.setExample(example);
        phrasesAndIdiom.setSynonyms(synonyms);

//        Adjectives adjective = new Adjectives();
//        adjective.setId(id);
//        adjective.setWord(word);
//        adjective.setMeaning(meaning);
//        adjective.setExample(example);
//        adjective.setSynonyms(synonyms);


//        Adverbs adverb = new Adverbs();
//        adverb.setId(id);
//        adverb.setWord(word);
//        adverb.setMeaning(meaning);
//        adverb.setExample(example);
//        adverb.setSynonyms(synonyms);

//        Verbs verb = new Verbs();
//        verb.setId(id);
//        verb.setWord(word);
//        verb.setMeaning(meaning);
//        verb.setExample(example);
//        verb.setSynonyms(synonyms);

        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database.getInstance(getContext()).getPhrasesAndIdiomsDao().addWord(phrasesAndIdiom);
//                Database.getInstance(getContext()).getAdjectivesDao().addWord(adjective);
//                Database.getInstance(getContext()).getAdverbsDao().updateWord(adverb);
//                Database.getInstance(getContext()).getAdverbsDao().addWord(adverb);
//                Database.getInstance(getContext()).myVerbsDao().addVerb(verb);

                Toast.makeText(getActivity(), "kelime basariyla eklendi", Toast.LENGTH_SHORT).show();
            }
        });

        List<PhrasesAndIdioms> phrasesAndIdioms = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdioms();
//        List<Adjectives> adjectives = Database.getInstance(getContext()).getAdjectivesDao().getAdjectives();
//        List<Adverbs> adverbs = Database.getInstance(getContext()).getAdverbsDao().getAdverbs();
//        List<Verbs> verbs = Database.getInstance(getContext()).myVerbsDao().getVerbs();


        int size = Database.getInstance(getContext()).getPhrasesAndIdiomsDao().getPhrasesAndIdiomsLength();
//        int size = Database.getInstance(getContext()).getAdjectivesDao().getAdjectivesLength();
//        int size = Database.getInstance(getContext()).getAdverbsDao().getAdverbsLength();
//        int size = Database.getInstance(getContext()).myVerbsDao().getVerbsLength();

        String data = "";

        for (PhrasesAndIdioms phrasesAndIdioms1: phrasesAndIdioms){
            id = 0 + phrasesAndIdioms1.getId();

            word = "" + phrasesAndIdioms1.getWord();
            meaning = "" +  phrasesAndIdioms1.getMeaning();
            example = "" +  phrasesAndIdioms1.getExample();
            int learningStatus = 0 +  phrasesAndIdioms1.getLearningStatus();

            synonyms = "" + phrasesAndIdioms1.getSynonyms();


            data = data + "id: " + id + "\nword: " + word + "\nmeaning: " + meaning + "\nexample: " + example + "\nLearning status: " + learningStatus + "\nsynonyms: " + synonyms;
            data = data + "\n------------------------------------------\n";

        }

//        for (Adjectives adjectives1: adjectives){
//            id = 0 + adjectives1.getId();
//
//            word = "" + adjectives1.getWord();
//            meaning = "" +  adjectives1.getMeaning();
//            example = "" +  adjectives1.getExample();
//            int learningStatus = 0 +  adjectives1.getLearningStatus();
//
//            synonyms = "" + adjectives1.getSynonyms();
//
//
//            data = data + "id: " + id + "\nword: " + word + "\nmeaning: " + meaning + "\nexample: " + example + "\nLearning status: " + learningStatus + "\nsynonyms: " + synonyms;
//            data = data + "\n------------------------------------------\n";
//
//        }


//        for (Adverbs adverbs1: adverbs){
//            id = 0 + adverbs1.getId();
//
//            word = "" + adverbs1.getWord();
//            meaning = "" +  adverbs1.getMeaning();
//            example = "" +  adverbs1.getExample();
//            int learningStatus = 0 +  adverbs1.getLearningStatus();
//
//            synonyms = "" + adverbs1.getSynonyms();
//
//
//            data = data + "id: " + id + "\nword: " + word + "\nmeaning: " + meaning + "\nexample: " + example + "\nLearning status: " + learningStatus + "\nsynonyms: " + synonyms;
//            data = data + "\n------------------------------------------\n";
//
//        }

//        for (Verbs verbs1: verbs){
//            id = 0 + verbs1.getId();
//
//            word = "" + verbs1.getWord();
//            meaning = "" +  verbs1.getMeaning();
//            example = "" +  verbs1.getExample();
//            int learningStatus = 0 +  verbs1.getLearningStatus();
//
//            synonyms = "" + verbs1.getSynonyms();
//
//
//            data = data + "id: " + id + "\nword: " + word + "\nmeaning: " + meaning + "\nexample: " + example + "\nLearning status: " + learningStatus + "\nsynonyms: " + synonyms;
//            data = data + "\n------------------------------------------\n";
//
//        }

        verbListTV.setText("size: " + size + "\n" + data);


        return view;
    }
}
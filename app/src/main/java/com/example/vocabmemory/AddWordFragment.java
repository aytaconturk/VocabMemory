package com.example.vocabmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWordFragment extends Fragment {

    private EditText verbId, verbName, verbMeaning, verbExample;
    private Button addWord;

    public AddWordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);

        verbId = view.findViewById(R.id.et_verbs_id);
        verbName = view.findViewById(R.id.et_verb_name);
        verbMeaning = view.findViewById(R.id.et_verb_meaning);
        verbExample = view.findViewById(R.id.et_verb_example);

        addWord = view.findViewById(R.id.btn_add_new_user);

        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int id = Integer.parseInt(verbId.getText().toString());

//                String word = verbName.getText().toString();
//                String meaning = verbMeaning.getText().toString();
//                String example = verbExample.getText().toString();

                int id = 4;
                String word = "annotate";
                String meaning = "Add notes to (a text or diagram) giving explanation or comment.";
                String example = "documentation should be <font color=\"red\"> <em> annotated <em> </font> with explanatory notes";

                Verbs verb = new Verbs();

                verb.setId(id);
                verb.setWord(word);
                verb.setMeaning(meaning);
                verb.setExample(example);


                MainActivity.database.myVerbsDao().addVerb(verb);

                Toast.makeText(getActivity(), "kelime basariyla eklendi", Toast.LENGTH_SHORT).show();

                verbId.setText("");
                verbName.setText("");
                verbMeaning.setText("");
                verbExample.setText("");
            }
        });



        return view;
    }
}
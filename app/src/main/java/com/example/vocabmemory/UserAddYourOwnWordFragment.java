package com.example.vocabmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vocabmemory.Modal.UserWords;

public class UserAddYourOwnWordFragment extends Fragment {

    private EditText etWord, etMeaning, etExample, etSynonyms;
    private Button btnSave;
    private int lastId;

    public UserAddYourOwnWordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_add_your_own_word, container, false);

        etWord = view.findViewById(R.id.et_own_word);
        etMeaning = view.findViewById(R.id.et_own_meaning);
        etExample = view.findViewById(R.id.et_own_example);
        etSynonyms = view.findViewById(R.id.et_own_synonyms);
        btnSave = view.findViewById(R.id.btn_own_save);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserWords user = new UserWords();
                lastId = Database.getInstance(getContext()).getUserWordsDao().getLastId();
                user.setId(lastId + 1);
                user.setWord(etWord.getText().toString());
                user.setMeaning(etMeaning.getText().toString());
                user.setExample(etExample.getText().toString());
                user.setSynonyms(etSynonyms.getText().toString());

                // Save user into database
                Database.getInstance(getContext()).getUserWordsDao().addWord(user);
                Toast.makeText(getActivity(), "Successfully word added into your list", Toast.LENGTH_SHORT).show();

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserFragment()).addToBackStack(null).commit();
            }
        });




        return view;
    }
}
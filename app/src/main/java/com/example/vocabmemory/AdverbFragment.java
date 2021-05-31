package com.example.vocabmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AdverbFragment extends Fragment {

    private Button btnDeleteVerb;

    public AdverbFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adverb, container, false);

        btnDeleteVerb = view.findViewById(R.id.btn_delete_verb);

        btnDeleteVerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Verbs verb = new Verbs();

                verb.setId(1);
                verb.setLearningStatus(0);

                MainActivity.database.myVerbsDao().updateVerb(verb);

                Toast.makeText(getActivity(), "kelime basariyla guncellendi!",Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
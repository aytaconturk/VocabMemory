package com.example.vocabmemory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MakeQuizFragment extends Fragment {

    private Context mContext;
    private TextView textView;

    public MakeQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_quiz, container, false);

        textView = view.findViewById(R.id.tv_make_quiz);


        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());
        String categoryName = sharedPreferences.getString("categoryName", "empty");

        textView.setText("" + categoryName);

        return view;
    }
}
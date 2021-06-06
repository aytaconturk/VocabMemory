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

public class UserAddWordFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button AddFromCategory, AddYourOwn;

    public UserAddWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_add_word, container, false);

        AddFromCategory = view.findViewById(R.id.btn_add_word_from_categories);
        AddYourOwn = view.findViewById(R.id.btn_add_your_own_word);

        AddFromCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to User Add Word From Category Fragment
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserAddWordFromCategoryFragment()).addToBackStack(null).commit();
            }
        });

        AddYourOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to User Add Your Own Word Fragment
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserAddYourOwnWordFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
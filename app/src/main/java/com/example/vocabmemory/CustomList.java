package com.example.vocabmemory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] word;
    private final String[] meaning;

    public CustomList(Activity context,
                      String[] word, String[] meaning) {
        super(context, R.layout.list_single, word);
        this.context = context;
        this.word = word;
        this.meaning = meaning;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtWord = (TextView) rowView.findViewById(R.id.custom_list_word);
        TextView txtMeaning = (TextView) rowView.findViewById(R.id.custom_list_meaning);

        txtWord.setText(word[position]);
        txtMeaning.setText(meaning[position]);

        return rowView;
    }
}

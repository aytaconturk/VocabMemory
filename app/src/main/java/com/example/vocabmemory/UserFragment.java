package com.example.vocabmemory;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vocabmemory.Modal.PhrasesAndIdioms;
import com.example.vocabmemory.Modal.UserWords;
import com.example.vocabmemory.Modal.Verbs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserFragment extends Fragment {

    private NotificationManagerCompat notificationManager;
    private ListView userWordList;
    private int id;
    private String[] words, meanings;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        createNotificationChannel();

        userWordList = view.findViewById(R.id.userWordList);

        List<String> wordList = new ArrayList<String>();
        List<String> meaningList = new ArrayList<String>();

        List<UserWords> userWords = Database.getInstance(getContext()).getUserWordsDao().getUserWordsReverseOrder();

        for (UserWords userWord: userWords){
            id = 0 + userWord.getId();

            wordList.add(userWord.getWord());
            meaningList.add(userWord.getMeaning());
        }

        words = wordList.toArray(new String[0]);
        meanings = meaningList.toArray(new String[0]);

        CustomList adapter = new
                CustomList(getActivity(), words, meanings);


//        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);

        userWordList.setAdapter(adapter);

        userWordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println("You Clicked at " + words[position]);
                Toast.makeText(getActivity(), "You Clicked at " +words[position], Toast.LENGTH_SHORT).show();

            }
        });


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserAddWordFragment()).addToBackStack(null).commit();
            }
        });

        int tableLength = Database.getInstance(getContext()).getUserWordsDao().getUserWordsLength();

        // Get a random between 1 and tableLength.length
        int randomVerb;
        randomVerb = (int) (Math.random() * (tableLength) + 1);

        UserWords userWord = new UserWords();
        userWord = Database.getInstance(getContext()).getUserWordsDao().getUserWordById(randomVerb);


        String word = userWord.getWord();
        String meaning = userWord.getMeaning();

        Intent notificationIntent = new Intent(this.getContext(), NotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.getContext(), 0, notificationIntent, 0);



        notificationManager = NotificationManagerCompat.from(getContext());

        Notification notification = new NotificationCompat.Builder(getContext(), "channel1")
                .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setContentTitle(word)
                .setContentText(meaning)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .build();


        notificationManager.notify(1, notification);


        return view;
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Channel 1";
            String  description = "Channel for user words";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("channel1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
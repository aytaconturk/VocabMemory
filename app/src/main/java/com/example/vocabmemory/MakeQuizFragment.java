package com.example.vocabmemory;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vocabmemory.Modal.Verbs;

public class MakeQuizFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private TextView questionWord, tvQuestion1, tvQuestion2, tvQuestion3, tvQuestion4, tvPoints;
    private int id, correctAnswerId;
    private String word, meaning;
    private CardView card1, card2, card3, card4;
    private static int points = 0;
    private Button nextBtn;

    public MakeQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_quiz, container, false);

        createNotificationChannel();

        questionWord = view.findViewById(R.id.tv_make_quiz);
        tvQuestion1 = view.findViewById(R.id.tv_question_1);
        tvQuestion2 = view.findViewById(R.id.tv_question_2);
        tvQuestion3 = view.findViewById(R.id.tv_question_3);
        tvQuestion4 = view.findViewById(R.id.tv_question_4);

        card1 = view.findViewById(R.id.cardView1);
        card2 = view.findViewById(R.id.cardView2);
        card3 = view.findViewById(R.id.cardView3);
        card4 = view.findViewById(R.id.cardView4);

        tvPoints = view.findViewById(R.id.tv_points);
        nextBtn = view.findViewById(R.id.btn_next_question);


        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());
        String categoryName = sharedPreferences.getString("categoryName", "empty");

        questionWord.setText("" + categoryName);

        nextWord(categoryName);

        tvQuestion1.setOnClickListener(this);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "channel1");
        builder.setSmallIcon(R.drawable.ic_baseline_add_alert_24);
        builder.setContentTitle("Notication Test");
        builder.setContentText("lorem1 sdfko v fdvgfdo fdgdfog");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        builder.setContentIntent()

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                notificationManager.notify(100, builder.build());

                Toast.makeText(getContext(), "Reminder set", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();
                long tenSecondsInMillis = 1000 * 10;

                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeAtButtonClick + tenSecondsInMillis,
                        pendingIntent);

                nextWord(categoryName);
            }
        });


        return view;
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "userChannel";
            String  description = "Channel for user words";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel1", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void nextWord(String categoryName) {


        card1.setBackgroundResource(R.color.white);
        card2.setBackgroundResource(R.color.white);
        card3.setBackgroundResource(R.color.white);
        card4.setBackgroundResource(R.color.white);

        int tableLength = Database.getInstance(getContext()).myVerbsDao().getVerbsLength();

        // Get a random between 1 and tableLength.length
        int randomVerb, rndMeaning;
        randomVerb = (int) (Math.random() * (tableLength) + 1);
        rndMeaning = (int) (Math.random() * (4) + 1);

        int rand3 = (int) (Math.random() * (20) + 1);
        System.out.println("random sayi: " + rand3);

        // random sayi indexinin verb objesini getir
        Verbs verb = new Verbs();
        verb = Database.getInstance(getContext()).myVerbsDao().getVerbById(randomVerb);

        id = verb.getId();
        word = verb.getWord();
        meaning = verb.getMeaning();

        questionWord.setText(word);

        setMeaning(rndMeaning, meaning);

        correctAnswerId = rndMeaning;

        for (int i=0; i<3; i++){
            int rnd =  (int) (Math.random() * (tableLength) + 1);
            while(rnd == id){
                rnd =  (int) (Math.random() * (tableLength) + 1);
            }

            Verbs verb1 = Database.getInstance(getContext()).myVerbsDao().getVerbMeaning(rnd);

            String meaningRnd = verb1.getMeaning();

            int rnd2 = (int) (Math.random() * (4) + 1);
            while(rnd2 == rndMeaning){
                rnd2 = (int) (Math.random() * (4) + 1);
            }

            setMeaning(rnd2, meaningRnd);

        }



    }

    public void setMeaning(int i, String meaning){
        switch (i){
            case 1:
                tvQuestion1.setText(meaning);
                break;
            case 2:
                tvQuestion2.setText(meaning);
                break;
            case 3:
                tvQuestion3.setText(meaning);
                break;
            case 4:
                tvQuestion4.setText(meaning);
                break;
        }
    }


    private void setCorrectAnswerBackground(int i){
        switch (i){
            case 1:
                card1.setBackgroundResource(R.color.correct_answer);
                break;
            case 2:
                card2.setBackgroundResource(R.color.correct_answer);
                break;
            case 3:
                card3.setBackgroundResource(R.color.correct_answer);
                break;
            case 4:
                card4.setBackgroundResource(R.color.correct_answer);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int mId;
        String text;
        switch (v.getId()){
            case R.id.tv_question_1:
                text =  tvQuestion1.getText().toString();
                mId = Database.getInstance(getContext()).myVerbsDao().getMeaningId(text);

                if(mId == id){
                    card1.setBackgroundResource(R.color.correct_answer);
                    card1.setRadius(10);
                    points = points + 10;
                    tvPoints.setText("" + points);
                }
                else {
                    card1.setBackgroundResource(R.color.wrong_asnwer);
                    card1.setRadius(10);
                    setCorrectAnswerBackground(correctAnswerId);
                }
                break;
            case R.id.tv_question_2:
                text =  tvQuestion2.getText().toString();
                mId = Database.getInstance(getContext()).myVerbsDao().getMeaningId(text);

                if(mId == id){
                    card2.setBackgroundResource(R.color.correct_answer);
                    card1.setRadius(10);
                    points = points + 10;
                    tvPoints.setText("" + points);
                }
                else {
                    card2.setBackgroundResource(R.color.wrong_asnwer);
                    setCorrectAnswerBackground(correctAnswerId);
                }
                break;
            case R.id.tv_question_3:
                text =  tvQuestion3.getText().toString();
                mId = Database.getInstance(getContext()).myVerbsDao().getMeaningId(text);

                if(mId == id){
                    card3.setBackgroundResource(R.color.correct_answer);
                    card3.setCardElevation(50);
                    points = points + 10;
                    tvPoints.setText("" + points);
                }
                else {
                    card3.setBackgroundResource(R.color.wrong_asnwer);
                    setCorrectAnswerBackground(correctAnswerId);
                }
                break;
            case R.id.tv_question_4:
                text =  tvQuestion4.getText().toString();
                mId = Database.getInstance(getContext()).myVerbsDao().getMeaningId(text);

                if(mId == id){
                    card4.setBackgroundResource(R.color.correct_answer);
                    card4.setCardElevation(50);
                    points = points + 10;
                    tvPoints.setText("" + points);
                }
                else {
                    card4.setBackgroundResource(R.color.wrong_asnwer);
                    setCorrectAnswerBackground(correctAnswerId);
                }
                break;
        }
    }
}
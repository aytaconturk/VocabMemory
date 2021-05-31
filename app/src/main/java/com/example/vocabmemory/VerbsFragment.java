package com.example.vocabmemory;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class VerbsFragment extends Fragment {

    private ImageButton imgBtnVerbsClose;
    private Button btnAddVerbsFragment;
    private TextView tvWord, tvMeaning, tvExample, tvLearningStatus;
    WebView webView;
    private LinearLayout syllabusLinearLayout;

    public VerbsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verbs, container, false);

        imgBtnVerbsClose = view.findViewById(R.id.img_btn_verbs_close);
        btnAddVerbsFragment = view.findViewById(R.id.btn_add_verb_fragment);
        tvWord = view.findViewById(R.id.tv_verbs_word);
        tvMeaning = view.findViewById(R.id.tv_verbs_meaning);
        tvExample = view.findViewById(R.id.tv_verbs_example);
//        tvLearningStatus = view.findViewById(R.id.tv_verbs_learning_status);

        List<Verbs> verbs = MainActivity.database.myVerbsDao().getVerbs();

        String data = "";

        String path = MainActivity.database.getOpenHelper().getWritableDatabase().getPath();


        for (Verbs verb: verbs){
            int id = verb.getId();
            String word = verb.getWord();
            String meaning = verb.getMeaning();
            String example = verb.getExample();
            int learningStatus = verb.getLearningStatus();

            tvWord.setText(word);
            tvMeaning.setText(meaning);
            if(example != null)
            {
                tvExample.setText(Html.fromHtml(example)); }

//            tvLearningStatus.setText(learningStatus + "");

        }
        String syllabus = "displease";

        String syllabusHtml = "<p>" + "<font border=\"2px solid red\">" + syllabus + "</p>";

        syllabusLinearLayout = view.findViewById(R.id.linearLayout_verbs_syllabus);

        String syllabuses = "hallo, apple, yellow";
        String[] syllabusValues = syllabuses.split(",");

        System.out.println("boyutu: " + syllabusValues[1]);

        for(int i=0; i < syllabusValues.length; i++){
            TextView valueTV = new TextView(getContext());
            valueTV.setText(" " + syllabusValues[i] + " ");
            valueTV.setTextSize(18);
            valueTV.setPadding(0, 5, 0, 5);

            valueTV.setBackgroundResource(R.color.light);
            valueTV.setBackgroundResource(R.drawable.rounded_corner);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,30,0);
            valueTV.setLayoutParams(params);

            syllabusLinearLayout.addView(valueTV);
        }






//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            String str = "<span style=\"background-color:#f3f402; padding-left: 5px;\">" + syllabus + "</span>";
//            tvLearningStatus.setText(Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY));
//            System.out.println("inside if !!!!!");
//        } else {
//            String str = "<font color='#f3f402'>" + syllabus + "</font>";
//            tvLearningStatus.setText(Html.fromHtml(str));
//            System.out.println("else inside !!!!");
//        }

        int colorCode = Color.parseColor("#f1f1f1") ;


//        int padding = 10; // in pixels
//        tvLearningStatus.setShadowLayer(padding /* radius */, 0, 0, 0 /* transparent */);
//        tvLearningStatus.setPadding(padding, padding, padding, padding);
//
//        // Now create the new span
//        String str = "displease";
//        Spannable spannable = new SpannableString(str);
//        spannable.setSpan(new PaddingBackgroundColorSpan(
//                getResources().getColor(android.R.color.darker_gray),
//                padding
//        ), 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvLearningStatus.setText(spannable);

//        tvLearningStatus.setText(Html.fromHtml(syllabusHtml));

        imgBtnVerbsClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to Home fragment
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        btnAddVerbsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add verb word to the Database
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddWordFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
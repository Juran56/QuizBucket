package com.pranabchakma.mcqprepartion;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pranabchakma.mcqprepartion.Extractor.Utils;
import com.pranabchakma.mcqprepartion.Model.Question;
import com.pranabchakma.mcqprepartion.Model.Result;

import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Play extends AppCompatActivity implements View.OnClickListener{
    AdView adView;
    InterstitialAd interstitialAd;
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView timerTextView;
    CountDownTimer countDownTimer;
    int questionListSize;
    long timeleftInMilliSeconds;
    boolean timerunning, isPause;
    int life = 5;

    //ProgressBar progressBar;
    TextView question, optionOne, optionTwo, optionThree, optionFour, correctProgress, nextquestionbtn,previousquestionbtn;
    CardView linearLayout;


    int index = 0, score = 0, thisQuestion = 1;
//    int progressValue = 0, timerProgress = 0;
    List<Question> questions = new ArrayList<>();
    List<Question> limitedQuestion = new ArrayList<>();
    ArrayList<Result> results = new ArrayList<>();
    Random r = new Random();
    int startNumber;
    Bundle bundle;
    String category;
    int level,size,start;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(this, "ca-app-pub-7485935816424837~3698962005");

        adView = findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        //final AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-7485935816424837/8553254313");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        //interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(getApplicationContext(), Done.class);
                Bundle data = new Bundle();
                data.putString("CAT",category);
                data.putInt("LEVEL",level);
                data.putInt("SCORE", score);
                data.putInt("TOTAL", limitedQuestion.size());
                data.putParcelableArrayList("RESULTS", results);
                intent.putExtras(data);
                startActivity(intent);
                finish();
                interstitialAd.loadAd(new AdRequest.Builder().build());
                //interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }
        });

        bundle = getIntent().getExtras();
        level = bundle.getInt("LEVEL");
        category = bundle.getString("CATEGORY");
        questions = Utils.extractQuestion(getJsonQuestions(), category);
        //Collections.shuffle(questions);
        //Collections.shuffle(questions);
        //Collections.shuffle(questions);
        //limitedQuestion =questions;
        getQuestionByLevel();
//        startNumber =r.nextInt((questions.size()-20));
//        if (startNumber >= 0 && (startNumber + 20) < questions.size()) {
//            limitedQuestion = questions.subList(startNumber, startNumber + 20);
//        }
        Collections.shuffle(limitedQuestion);
        //View , textview , layout view reference
        getAllTextandLayoutView();
        questionListSize = limitedQuestion.size();
        //timeleftInMilliSeconds = 20000*questionListSize;
        //startTimer();
    }

    private void getQuestionByLevel() {
        start =0;
        int intervel = 30;
        int levels=questions.size()/30;
        int remain = questions.size()%30;
        if (level == levels){
            int s =(30*levels);
            int end =((30*levels)+remain);
            limitedQuestion=questions.subList(s,end);
        }
        else {
            switch (level){
                case 0:
                    limitedQuestion = questions.subList(start, intervel);
                    break;
                case 1:
                    limitedQuestion = questions.subList(intervel, (intervel*2));
                    break;
                case 2:
                    limitedQuestion = questions.subList((intervel*2), (intervel*3));
                    break;
                case 3:
                    limitedQuestion = questions.subList((intervel*3), (intervel*4));
                    break;
                case 4:
                    limitedQuestion = questions.subList((intervel*4), (intervel*5));
                    break;
                case 5:
                    limitedQuestion = questions.subList((intervel*5), (intervel*6));
                    break;
                case 6:
                    limitedQuestion = questions.subList((intervel*6), (intervel*7));
                    break;
                case 7:
                    limitedQuestion = questions.subList((intervel*7), (intervel*8));
                    break;
                case 8:
                    limitedQuestion = questions.subList((intervel*8), (intervel*9));
                    break;
                case 9:
                    limitedQuestion = questions.subList((intervel*9), (intervel*10));
                    break;
                case 10:
                    limitedQuestion = questions.subList((intervel*10), (intervel*11));
                    break;
                case 11:
                    limitedQuestion = questions.subList((intervel*11), (intervel*12));
                    break;
                case 12:
                    limitedQuestion = questions.subList((intervel*12), (intervel*13));
                    break;
                case 13:
                    limitedQuestion = questions.subList((intervel*13), (intervel*14));
                    break;
                case 14:
                    limitedQuestion = questions.subList((intervel*14), (intervel*15));
                    break;
                case 15:
                    limitedQuestion = questions.subList((intervel*15), (intervel*16));
                    break;
            }
        }
    }

    private void getAllTextandLayoutView() {
        //timerTextView = findViewById(R.id.timerId);
        linearLayout = findViewById(R.id.idAnim);
        //progressBar = findViewById(R.id.scoreProgressbar);
        //progressBar.setScaleY(3);
        question = findViewById(R.id.questionText);
        optionOne = findViewById(R.id.optionOne);
        optionTwo = findViewById(R.id.optionTwo);
        optionThree = findViewById(R.id.optionThree);
        optionFour = findViewById(R.id.optionFour);
        correctProgress = findViewById(R.id.correctProgress);
        //nextquestionbtn = findViewById(R.id.nextquestion);
        previousquestionbtn = findViewById(R.id.previousquestion);

        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);
        optionFour.setOnClickListener(this);
        previousquestionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thisQuestion >1)
                {
                    doAnimateRev();
                    thisQuestion--;
                    showNextQuestion(--index);
                    previousquestionbtn.setVisibility(View.GONE);
                    setcorrectans();
                    setTextDisable();
                    timerNextQuestion(4000);
                    thisQuestion++;
                }
                else
                {
                    showNextQuestion(index);
                }
            }
        });

        //progressBar.setMax(questions.size());
    }


    private String getJsonQuestions() {
        Resources resources = getResources();
        InputStream inputStream = resources.openRawResource(R.raw.general_knowledge);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }


    @Override
    public void onClick(View view) {
        String question, correctans, wrongans;
        TextView textView = (TextView) view;
        if (index < limitedQuestion.size()) {
            question = limitedQuestion.get(index).getQuestionText().trim();
            correctans = limitedQuestion.get(index).getCorrectAns().trim();
            wrongans = textView.getText().toString().trim();
            if (textView.getText().equals(correctans)) {
                score += 1;
                thisQuestion++;
                results.add(new Result(question, correctans, true));
                textView.setTextColor(getResources().getColor(R.color.darkGreen));
                textView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_correctans,0);
                setTextDisable();
                timerNextQuestion(1500);
                //doAnimate();
                //showNextQuestion(++index);
            } else {
                results.add(new Result(question, correctans, wrongans, false));
                thisQuestion++;
                textView.setTextColor(Color.RED);
                textView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_incorrect_ans,0);
                Toast.makeText(this, " " + correctans + " ", Toast.LENGTH_SHORT).show();
                doReverseAnimate();
                setcorrectans();
                setTextDisable();
                timerNextQuestion(1500);
                //showNextQuestion(++index);
            }
        }
    }

    private void doReverseAnimate() {
        //linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //ObjectAnimator exitlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, "translationX", 0, 50, -50,50, -50, 500,0);
        ObjectAnimator exitlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0);
        ObjectAnimator enterlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, "translationX", 0, 0);
        //ObjectAnimator fadeInlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, View.ALPHA, 1, 0,1);
        exitlayoutAnimator.setDuration(200);
        enterlayoutAnimator.setDuration(0);
        //fadeInlayoutAnimator.setDuration(100);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(exitlayoutAnimator, enterlayoutAnimator);
        //set.play(exitlayoutAnimator);
        set.start();
    }
    private void doAnimate() {
//        ObjectAnimator questionAnimator = ObjectAnimator.ofFloat(question,"translationX",900,0);
//        ObjectAnimator optionOneAnimator = ObjectAnimator.ofFloat(optionOne,"translationX",-150,0);
//        ObjectAnimator optionTwoAnimator = ObjectAnimator.ofFloat(optionTwo,"translationX",150,0);
//        ObjectAnimator optionThreeAnimator = ObjectAnimator.ofFloat(optionThree,"translationX",-150,0);
//        ObjectAnimator optionFourAnimator = ObjectAnimator.ofFloat(optionFour,"translationX",150,0);

        ObjectAnimator exitlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, "translationX", 0, -1000);
        ObjectAnimator enterlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, "translationX", 1000, 0);
        exitlayoutAnimator.setDuration(100);
        enterlayoutAnimator.setDuration(100);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(exitlayoutAnimator, enterlayoutAnimator);
        set.start();
        resetBackgroundColor();
//            linearLayout.setTranslationX(1000);
//            linearLayout.animate().translationX(0).start();

        //question.setTranslationX(1500);
        //question.animate().translationX(0).start();
//        question.setScaleX(0);
//        question.setScaleY(0);
//        question.animate().scaleX(1).scaleY(1).start();
//        optionOne.setTranslationX(-1000);
//        optionOne.animate().translationX(0).start();
//        optionTwo.setTranslationX(1000);
//        optionTwo.animate().translationX(0).start();
//        optionThree.setTranslationX(-1000);
//        optionThree.animate().translationX(0).start();
//        optionFour.setTranslationX(1500);
//        optionFour.animate().translationX(0).start();
//        optionOne.setScaleX(0);
//        optionOne.setScaleY(0);
//        optionOne.animate().scaleX(1).scaleY(1).start();
//        optionTwo.setScaleX(0);
//        optionTwo.setScaleY(0);
//        optionTwo.animate().scaleX(1).scaleY(1).start();
//        optionThree.setScaleX(0);
//        optionThree.setScaleY(0);
//        optionThree.animate().scaleX(1).scaleY(1).start();
//        optionFour.setScaleX(0);
//        optionFour.setScaleY(0);
//        optionFour.animate().scaleX(1).scaleY(1).start();

//        AnimatorSet set = new AnimatorSet();
//        set.play(questionAnimator);
//        set.playTogether(optionOneAnimator,optionThreeAnimator);
//        set.playTogether(optionTwoAnimator,optionFourAnimator);
//        set.start();
    }
    private void doAnimateRev(){
        ObjectAnimator exitlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, "translationX", 0, 1000);
        ObjectAnimator enterlayoutAnimator = ObjectAnimator.ofFloat(linearLayout, "translationX", -1000, 0);
        exitlayoutAnimator.setDuration(100);
        enterlayoutAnimator.setDuration(100);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(exitlayoutAnimator, enterlayoutAnimator);
        set.start();
        resetBackgroundColor();
    }
    private void showNextQuestion(int i) {
        //progressBar.setProgress(progressValue);
        previousquestionbtn.setVisibility(View.VISIBLE);
        resetBackgroundColor();
        if (index < limitedQuestion.size()) {
            correctProgress.setText(String.format("%d / %d", thisQuestion, limitedQuestion.size()));
            question.setText(limitedQuestion.get(index).getQuestionText().trim());
            optionOne.setText(limitedQuestion.get(index).getOptionOne().trim());
            optionTwo.setText(limitedQuestion.get(index).getOptionTwo().trim());
            optionThree.setText(limitedQuestion.get(index).getOptionThree().trim());
            optionFour.setText(limitedQuestion.get(index).getOptionFour().trim());
        } else {
            stopTimer();
            doneIntent();
        }
    }
    private void resetBackgroundColor() {
        optionOne.setBackgroundColor(Color.WHITE);
        optionTwo.setBackgroundColor(Color.WHITE);
        optionThree.setBackgroundColor(Color.WHITE);
        optionFour.setBackgroundColor(Color.WHITE);
        optionOne.setTextColor(getResources().getColor(R.color.defaultTextColor));
        optionTwo.setTextColor(getResources().getColor(R.color.defaultTextColor));
        optionThree.setTextColor(getResources().getColor(R.color.defaultTextColor));
        optionFour.setTextColor(getResources().getColor(R.color.defaultTextColor));
        optionOne.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        optionTwo.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        optionThree.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        optionFour.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
    }

    void startTimer() {
        countDownTimer = new CountDownTimer(timeleftInMilliSeconds, 1000) {
            @Override
            public void onTick(long millis) {
                timeleftInMilliSeconds = millis;
                updateTimer();
            }

            @Override
            public void onFinish() {
                if (!isPause) {
                    doneIntent();
                }
            }
        }.start();
        timerunning = true;
    }
    private void updateTimer() {
        int minutes = (int) timeleftInMilliSeconds / 60000;
        int seconds = (int) timeleftInMilliSeconds % 60000 / 1000;
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        timerTextView.setText(timeLeftText);
    }
    void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
            return;
        }
        //countDownTimer.cancel();
        //timerunning= false;
    }

    void doneIntent() {
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }else
        {
        Intent intent = new Intent(getApplicationContext(), Done.class);
        Bundle data = new Bundle();
        data.putString("CAT",category);
        data.putInt("LEVEL",level);
        data.putInt("SCORE", score);
        data.putInt("TOTAL", limitedQuestion.size());
        data.putParcelableArrayList("RESULTS", results);
        intent.putExtras(data);
        startActivity(intent);
        finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        doAnimate();
        showNextQuestion(index);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void nextQuestion() {
        showNextQuestion(++index);
        setTextEnable();
    }
    private void setcorrectans() {
        String correctAns = limitedQuestion.get(index).getCorrectAns().trim();
        String optionOnestr = limitedQuestion.get(index).getOptionOne().trim();
        String optionTwostr = limitedQuestion.get(index).getOptionTwo().trim();
        String optionThreestr = limitedQuestion.get(index).getOptionThree().trim();
        String optionFourstr = limitedQuestion.get(index).getOptionFour().trim();
        if (correctAns.equals(optionOnestr) ) {
            optionOne.setTextColor(getResources().getColor(R.color.darkGreen));
            optionOne.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_correctans,0);
        } else if (correctAns.equals(optionTwostr)) {
            optionTwo.setTextColor(getResources().getColor(R.color.darkGreen));
            optionTwo.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_correctans,0);
        } else if (correctAns.equals(optionThreestr)) {
            optionThree.setTextColor(getResources().getColor(R.color.darkGreen));
            optionThree.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_correctans,0);
        } else if (correctAns.equals(optionFourstr)){
            optionFour.setTextColor(getResources().getColor(R.color.darkGreen));
            optionFour.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_correctans,0);
        }
    }
    void timerNextQuestion(long time){
        new CountDownTimer(time, 500) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                doAnimate();
                nextQuestion();
            }
        }.start();
    };
    void setTextEnable() {
        optionOne.setClickable(true);
        optionTwo.setClickable(true);
        optionThree.setClickable(true);
        optionFour.setClickable(true);
    }
    void setTextDisable() {
        optionOne.setClickable(false);
        optionTwo.setClickable(false);
        optionThree.setClickable(false);
        optionFour.setClickable(false);
    }


}


package com.pranabchakma.mcqprepartion;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pranabchakma.mcqprepartion.Adapter.ResultRecycleAdapter;
import com.pranabchakma.mcqprepartion.Model.Result;
import com.pranabchakma.mcqprepartion.UserData.UserContract.UserEntry;
import com.pranabchakma.mcqprepartion.UserData.UserDbHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Done extends AppCompatActivity  {
    //database variables
    AdView adView;
    InterstitialAd interstitialAd;
    private FirebaseAnalytics firebaseAnalytics;
    //normal variables
    Bundle data;
    int score,total,level,wrong;
    ArrayList<Result> results;
    String datetimenow, category;

    RecyclerView resultRecyclerView;
    ResultRecycleAdapter resultRecycleAdapter;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_done);

        MobileAds.initialize(this, "ca-app-pub-7485935816424837~3698962005");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        TextView done = findViewById(R.id.done);
       data = getIntent().getExtras();
       category = data.getString("CAT");
       level = data.getInt("LEVEL");
       score = data.getInt("SCORE");
       total = data.getInt("TOTAL");
       wrong = total - score;
       datetimenow = DateFormat.getDateTimeInstance().format(new Date());
       results = data.getParcelableArrayList("RESULTS");
       TextView scoretext = findViewById(R.id.score);
       scoretext.setText(String.format("correct answer %d out of %d questions",(score),total));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        resultRecyclerView = findViewById(R.id.donelist);
        layoutManager = new LinearLayoutManager(this);
        resultRecyclerView.setLayoutManager(layoutManager);
        resultRecycleAdapter = new ResultRecycleAdapter(this,results);
        resultRecyclerView.setAdapter(resultRecycleAdapter);
        animationListLoading();
        insertUserScore();
    }
    private void insertUserScore(){
        UserDbHelper dbHelper = new UserDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserEntry.CATEGORY,category);
        contentValues.put(UserEntry.LEVEL,level);
        contentValues.put(UserEntry.DATE_TIME,datetimenow);
        contentValues.put(UserEntry.CORRECT_ANSWER,score);
        contentValues.put(UserEntry.TOTAL_ANSWERED, total);
        contentValues.put(UserEntry.WRONG_ANSWER, wrong);
        long rowid = db.insert(UserEntry.TABLE_NAME,null,contentValues);
        if (rowid == -1){
            Toast.makeText(this," Save error", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this," Save succesful", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void animationListLoading() {
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_fall_down);
        resultRecyclerView.setLayoutAnimation(animationController);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        data.clear();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}

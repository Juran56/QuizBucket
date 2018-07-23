package com.pranabchakma.mcqprepartion;

import android.content.IntentSender;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pranabchakma.mcqprepartion.Adapter.LevelAdapter;
import com.pranabchakma.mcqprepartion.Extractor.Utils;
import com.pranabchakma.mcqprepartion.Model.Level;
import com.pranabchakma.mcqprepartion.Model.Question;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelActivity extends AppCompatActivity {
    AdView adView;
    private FirebaseAnalytics mFirebaseAnalytics;
    Bundle bundle;
    String category;
    List<Question> questions = new ArrayList<>();
    int size;

    RecyclerView recyclerView;
    LevelAdapter levelAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Level> levels = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(this, "ca-app-pub-7485935816424837~3698962005");

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        bundle = getIntent().getExtras();
        category = bundle.getString("CATEGORY");
        questions = Utils.extractQuestion(getJsonQuestions(),category);
        size = questions.size();


        recyclerView = findViewById(R.id.levelsRecyclerView);
        levels = getLevels();
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        levelAdapter = new LevelAdapter(levels, this);
        recyclerView.setAdapter(levelAdapter);
    }

    private List<Level> getLevels(){
        List<Level> levels = new ArrayList<>();
        int number;
        if (size%30==0){
            number = size/30;
        }
        else {
            number = size/30;
            number++;
        }

        for (int i=0;i<number;i++){
            levels.add(new Level(category,i));
        }
        return levels;
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

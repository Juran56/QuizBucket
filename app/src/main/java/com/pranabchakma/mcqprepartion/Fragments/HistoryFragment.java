package com.pranabchakma.mcqprepartion.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.pranabchakma.mcqprepartion.Adapter.UserDataAdapter;
import com.pranabchakma.mcqprepartion.Model.UserData;
import com.pranabchakma.mcqprepartion.R;
import com.pranabchakma.mcqprepartion.UserData.UserContract;
import com.pranabchakma.mcqprepartion.UserData.UserContract.UserEntry;
import com.pranabchakma.mcqprepartion.UserData.UserDbHelper;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Pranab on 3/28/2018.
 */

public class HistoryFragment extends Fragment {
    UserDbHelper dbHelper;
    TextView textView;

    List<UserData> userDatas = new ArrayList<>();
    RecyclerView recyclerView;
    UserDataAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_history,container,false);
        dbHelper = new UserDbHelper(getContext());


        textView = view.findViewById(R.id.datetime);
        textView.setText(DateFormat.getDateTimeInstance().format(new Date()));
        userDatas = getUserDatas();
        Collections.reverse(userDatas);
        recyclerView = view.findViewById(R.id.historyRecyclerview);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserDataAdapter(getContext(),userDatas);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private List<UserData> getUserDatas(){
        List<UserData> userDatalist = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {UserEntry.CATEGORY, UserEntry.LEVEL, UserEntry.DATE_TIME, UserEntry.TOTAL_ANSWERED, UserEntry.CORRECT_ANSWER};
        Cursor cursor = db.query(UserEntry.TABLE_NAME,projection,null,null,null,null,null);
        try {
            //textView.setText("Users "+cursor.getCount()+" \n");
            //textView.append(UserEntry.CATEGORY+" - "+UserEntry.LEVEL+" - "+UserEntry.DATE_TIME+"-"+UserEntry.CORRECT_ANSWER+" "+UserEntry.TOTAL_ANSWERED+"\n");
            int categoryIndex = cursor.getColumnIndex(UserEntry.CATEGORY);
            int levelIndex = cursor.getColumnIndex(UserEntry.LEVEL);
            int datetimeIndex = cursor.getColumnIndex(UserEntry.DATE_TIME);
            int correctAnsIndex = cursor.getColumnIndex(UserEntry.CORRECT_ANSWER);
            int totalAnsIndex = cursor.getColumnIndex(UserEntry.TOTAL_ANSWERED);
            while (cursor.moveToNext()){
                String currentCategory = cursor.getString(categoryIndex);
                String currentLevel = cursor.getString(levelIndex);
                String currentDateTime = cursor.getString(datetimeIndex);
                int currentCorectAns = cursor.getInt(correctAnsIndex);
                int currentTotalAns = cursor.getInt(totalAnsIndex);
                UserData userData = new UserData(currentCategory,currentLevel,currentDateTime,String.valueOf(currentTotalAns), String.valueOf(currentCorectAns),String.valueOf(currentTotalAns-currentCorectAns));
                userDatalist.add(userData);
            }
        }finally {
            cursor.close();
        }
        return userDatalist;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        userDatas.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

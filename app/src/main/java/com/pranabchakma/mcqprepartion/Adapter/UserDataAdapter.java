package com.pranabchakma.mcqprepartion.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pranabchakma.mcqprepartion.Model.UserData;
import com.pranabchakma.mcqprepartion.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Pranab on 3/29/2018.
 */

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder> {
    Context context;
    List<UserData> userDatas;

    public UserDataAdapter(Context context, List<UserData> userData) {
        this.context = context;
        this.userDatas = userData;
    }

    @Override
    public UserDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_list_item,parent,false);
        return new UserDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserDataViewHolder holder, int position) {
        final UserData userData = userDatas.get(position);
        holder.category.setText(""+userData.getCategory());
        holder.level.setText("Level: "+userData.getLevel());
        holder.totalans.setText("Total answered: "+userData.getTotal());
        holder.correctAns.setText("Correct Answered: "+userData.getCorrect());
        holder.wrongAns.setText("Wrong Answered: "+userData.getWrong());
        holder.datetime.setText(userData.getDatetime());
    }

    @Override
    public int getItemCount() {
        return userDatas.size();
    }

    public static class UserDataViewHolder extends RecyclerView.ViewHolder{
        TextView category,level,totalans,correctAns,wrongAns,datetime;

        public UserDataViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.historyCategory);
            level = itemView.findViewById(R.id.historyLevel);
            totalans = itemView.findViewById(R.id.historyTotalAns);
            correctAns = itemView.findViewById(R.id.historyCorrectAns);
            wrongAns = itemView.findViewById(R.id.historyWorngAns);
            datetime = itemView.findViewById(R.id.historyDateTime);
        }
    }
}

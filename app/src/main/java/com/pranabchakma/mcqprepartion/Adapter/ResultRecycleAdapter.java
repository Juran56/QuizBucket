package com.pranabchakma.mcqprepartion.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pranabchakma.mcqprepartion.Model.Result;
import com.pranabchakma.mcqprepartion.R;

import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by Pranab on 3/16/2018.
 */

public class ResultRecycleAdapter extends RecyclerView.Adapter<ResultRecycleAdapter.ResultViewHolder> {
    Context context;
    List<Result> resultList;
    public ResultRecycleAdapter(Context context,List<Result> results) {
        this.context =  context;
        this.resultList = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.result_list_item,parent,false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        final Result result = resultList.get(position);
        holder.questiontext.setText(result.getQuestions());
        holder.correctanstext.setText("সঠিক উত্তরঃ "+result.getCorrect_answer());
        if (result.isCorrect()){
            holder.givenAnswer.setVisibility(View.GONE);
            holder.givenAnswer.setText(result.getGiven_answer());
            holder.correct.setImageResource(R.drawable.ic_correctans);
        }
        else {
            holder.givenAnswer.setVisibility(View.VISIBLE);
            holder.givenAnswer.setText("আপনার উত্তরঃ "+result.getGiven_answer());
            holder.correct.setImageResource(R.drawable.ic_incorrect_ans);
        }

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView questiontext, correctanstext, givenAnswer;
        ImageView correct;
        public ResultViewHolder(View itemView) {
            super(itemView);
            questiontext = itemView.findViewById(R.id.questiontext);
            correctanstext = itemView.findViewById(R.id.correctans);
            givenAnswer = itemView.findViewById(R.id.givenAnswer);
            correct = itemView.findViewById(R.id.correct);
        }
    }
}

package com.pranabchakma.mcqprepartion.Adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pranabchakma.mcqprepartion.Model.Level;
import com.pranabchakma.mcqprepartion.Play;
import com.pranabchakma.mcqprepartion.R;

import java.util.List;

/**
 * Created by Pranab on 3/26/2018.
 */

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelViewHolder> {
    List<Level> levels;
    Context context;
    public LevelAdapter(List<Level> levels,Context context) {
        this.levels = levels;
        this.context = context;
    }

    @Override
    public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.level_list_item,parent,false);
        return new LevelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LevelViewHolder holder, int position) {
        final Level level = levels.get(position);
        holder.leveltext.setText(String.format(" Level "+level.getLevel()));
        holder.leveltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Play.class);
                Bundle bundle = new Bundle();
                bundle.putString("CATEGORY",level.getCategory());
                bundle.putInt("LEVEL",level.getLevel());
                intent.putExtras(bundle);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class LevelViewHolder extends RecyclerView.ViewHolder{
        TextView leveltext;
        public LevelViewHolder(View itemView) {
            super(itemView);
            leveltext = itemView.findViewById(R.id.leveltitle);
        }
    }
}

package com.pranabchakma.mcqprepartion.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.pranabchakma.mcqprepartion.LevelActivity;
import com.pranabchakma.mcqprepartion.Model.Category;
import com.pranabchakma.mcqprepartion.Play;
import com.pranabchakma.mcqprepartion.R;

import java.util.List;

/**
 * Created by Pranab on 3/5/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    List<Category> categories;
    public CategoryAdapter(Context context, List<Category> categories) {
       this.context = context;
       this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.category_list_view,parent,false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        final Category category = categories.get(position);
        holder.categoryText.setText(category.getCategoryName());
        holder.categoryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LevelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CATEGORY",category.getCategoryValue());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView questiontext, optionone, optiontwo, optionthree,optionfour, categoryText;
        public CardView cardView;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.Category_text);
//            questiontext = itemView.findViewById(R.id.Question_text);
//            optionone = itemView.findViewById(R.id.option_one);
//            optiontwo = itemView.findViewById(R.id.option_two);
//            optionthree = itemView.findViewById(R.id.option_three);
//            optionfour = itemView.findViewById(R.id.option_four);
//            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

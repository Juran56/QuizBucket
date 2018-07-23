package com.pranabchakma.mcqprepartion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.pranabchakma.mcqprepartion.Adapter.CategoryAdapter;
import com.pranabchakma.mcqprepartion.Model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranab on 3/22/2018.
 */

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Category> categories= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = view.findViewById(R.id.category_recyclerview);
        categories = getCategories();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getContext(),categories);
        recyclerView.setAdapter(categoryAdapter);
        animationListLoading();
        return view;
    }

    private void animationListLoading() {
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animationController);
    }
    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("বাংলা ভাষা","Bangla"));
        categories.add(new Category("বাংলা সাহিত্য ","Bangla_literature"));
        categories.add(new Category("বাংলাদেশ বিষয়াবলী","Bangladesh_Affairs"));
        categories.add(new Category("আন্তর্জাতিক বিষয়াবলী","International_Affairs"));
        //categories.add(new Category("English","English"));
        //categories.add(new Category("Sports","Sports"));


        return categories;
    }
}

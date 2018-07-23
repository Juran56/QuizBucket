package com.pranabchakma.mcqprepartion.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pranabchakma.mcqprepartion.R;

import java.util.zip.Inflater;

/**
 * Created by Pranab on 3/28/2018.
 */

public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_about,container,false);
        return view;
    }
}

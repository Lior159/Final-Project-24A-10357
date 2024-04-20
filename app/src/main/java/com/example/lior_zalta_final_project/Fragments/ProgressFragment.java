package com.example.lior_zalta_final_project.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lior_zalta_final_project.Adapters.BudgetAdapter;
import com.example.lior_zalta_final_project.Adapters.ProgressAdapter;
import com.example.lior_zalta_final_project.R;

import java.util.HashMap;

public class ProgressFragment extends Fragment {

    private RecyclerView fProgress_LST_progressList;
    private AppCompatActivity context;
    private HashMap<String, Double> budget;
    private HashMap<String, Double> transactionsSumCategory;
    private int screenWidth;

    public ProgressFragment(AppCompatActivity context, HashMap<String, Double> budget, HashMap<String, Double> transactionsSumCategory, int screenWidth) {
        this.context = context;
        this.budget = budget;
        this.transactionsSumCategory = transactionsSumCategory;
        this.screenWidth = screenWidth;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        findViews(view);
        initViews(view);
        return view;
    }

    private void findViews(View view) {
        fProgress_LST_progressList = view.findViewById(R.id.fProgress_LST_progressList);
    }

    private void initViews(View view) {
        ProgressAdapter progressAdapter = new ProgressAdapter(this.context, this.budget, this.transactionsSumCategory, this.screenWidth);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        fProgress_LST_progressList.setLayoutManager(linearLayoutManager);
        fProgress_LST_progressList.setAdapter(progressAdapter);
    }

}
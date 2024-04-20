package com.example.lior_zalta_final_project.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lior_zalta_final_project.Adapters.BudgetAdapter;
import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.example.lior_zalta_final_project.R;

import java.util.HashMap;

public class BudgetFragment extends Fragment {

    private RecyclerView budget_LST_budget_list;
    private AppCompatActivity context;
    private HashMap<String, Double> budget;

    public BudgetFragment(AppCompatActivity context, HashMap<String, Double> budget) {
        this.context = context;
        this.budget = budget;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        findViews(view);
        initViews(view);
        return view;
    }

    private void findViews(View view) {
        budget_LST_budget_list = view.findViewById(R.id.budget_LST_budget_list);
    }

    private void initViews(View view) {
        BudgetAdapter budgetAdapter = new BudgetAdapter(this.context, this.budget);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        budget_LST_budget_list.setLayoutManager(linearLayoutManager);
        budget_LST_budget_list.setAdapter(budgetAdapter);
    }
}
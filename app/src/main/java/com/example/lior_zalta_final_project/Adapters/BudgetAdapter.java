package com.example.lior_zalta_final_project.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lior_zalta_final_project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder> {
    private Context context;
    private HashMap<String, Double> budget;
    private ArrayList<String> categories;

    public BudgetAdapter(Context context, HashMap<String, Double> budget) {
        this.context = context;
        this.budget = budget;
        categories = new ArrayList<>(budget.keySet());
    }

    @NonNull
    @Override
    public BudgetAdapter.BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.budget_line, parent, false);
        return new BudgetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetAdapter.BudgetViewHolder holder, int position) {
        holder.category = categories.get(position);
        holder.amount = budget.get(holder.category);
        holder.budget_LBL_category.setText(holder.category);
        holder.budget_ET_amount.setText(holder.amount + "");
        holder.budget_ET_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("") || s.toString().equals("."))
                    budget.put(holder.category, holder.amount);
                else {
                    budget.put(holder.category, Double.parseDouble(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class BudgetViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView budget_LBL_category;
        private TextInputEditText budget_ET_amount;
        private Double amount;
        private String category;

        public BudgetViewHolder(@NonNull View itemView) {
            super(itemView);
            budget_LBL_category = itemView.findViewById(R.id.budget_LBL_category);
            budget_ET_amount = itemView.findViewById(R.id.budget_ET_amount);
        }
    }
}



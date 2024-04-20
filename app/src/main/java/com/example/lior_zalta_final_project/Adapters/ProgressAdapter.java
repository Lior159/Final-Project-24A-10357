package com.example.lior_zalta_final_project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.example.lior_zalta_final_project.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder> {

    private Context context;
    private HashMap<String, Double> budget;
    private HashMap<String, Double> transactionsSumCategory;
    private ArrayList<String> categories;
    int screenWidth;

    public ProgressAdapter(Context context, HashMap<String, Double> budget, HashMap<String, Double> transactionsSumCategory, int screenWidth) {
        this.context = context;
        this.budget = budget;
        this.transactionsSumCategory = transactionsSumCategory;
        categories = new ArrayList<>(budget.keySet());
        this.screenWidth = screenWidth;
    }

    @NonNull
    @Override
    public ProgressAdapter.ProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_line, parent, false);
        return new ProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgressAdapter.ProgressViewHolder holder, int position) {
        String category = categories.get(position);

        Double budgetSum = budget.get(category);
        Double transactionsSum = transactionsSumCategory.get(category);

        holder.progress_line_LBL_category.setText(category);
        holder.progress_line_LBL_actual.setText("Actual: $" + transactionsSum);
        holder.progress_line_LBL_budget.setText("Budget: $" + budgetSum);

        int actualWidth;
        int budgetWidth;

        if (transactionsSum >= budgetSum){
            actualWidth = screenWidth;
            budgetWidth = 0;
        } else {
            actualWidth = (int)(screenWidth * transactionsSum / budgetSum);
            budgetWidth = screenWidth - actualWidth;
        }

        RelativeLayout.LayoutParams actualParams = new RelativeLayout.LayoutParams(actualWidth, 100);
        RelativeLayout.LayoutParams budgetParams = new RelativeLayout.LayoutParams(budgetWidth, 100);;
        budgetParams.addRule(RelativeLayout.END_OF, R.id.progress_line_VIEW_actual);

        holder.progress_line_VIEW_actual.setLayoutParams(actualParams);
        holder.progress_line_VIEW_budget.setLayoutParams(budgetParams);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView progress_line_LBL_category;
        MaterialTextView progress_line_LBL_actual;
        MaterialTextView progress_line_LBL_budget;
        View progress_line_VIEW_actual;
        View progress_line_VIEW_budget;

        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
            progress_line_LBL_category = itemView.findViewById(R.id.progress_line_LBL_category);
            progress_line_LBL_actual = itemView.findViewById(R.id.progress_line_LBL_actual);
            progress_line_LBL_budget = itemView.findViewById(R.id.progress_line_LBL_budget);
            progress_line_VIEW_actual = itemView.findViewById(R.id.progress_line_VIEW_actual);
            progress_line_VIEW_budget = itemView.findViewById(R.id.progress_line_VIEW_budget);
        }
    }
}

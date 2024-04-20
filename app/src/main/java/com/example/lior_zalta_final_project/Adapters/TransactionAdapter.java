package com.example.lior_zalta_final_project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lior_zalta_final_project.Model.Transaction;
import com.example.lior_zalta_final_project.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.AdapterViewHolder> {
    private Context context;
    private ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_line, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Transaction transaction = getTransaction(position);
        holder.transaction_line_LBL_date.setText("Date: " + transaction.getDate());
        holder.transaction_line_LBL_category.setText("Category: " + transaction.getCategory());
        holder.transaction_line_LBL_label.setText("Label: " + transaction.getLabel());
        holder.transaction_line_LBL_amount.setText("Amount: $" + transaction.getAmount());
    }

    @Override
    public int getItemCount() {
        return this.transactions.size();
    }

    private Transaction getTransaction(int position) {
        return this.transactions.get(position);
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView transaction_line_LBL_date;
        private MaterialTextView transaction_line_LBL_category;
        private MaterialTextView transaction_line_LBL_label;
        private MaterialTextView transaction_line_LBL_amount;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            transaction_line_LBL_date = itemView.findViewById(R.id.transaction_line_LBL_date);
            transaction_line_LBL_category = itemView.findViewById(R.id.transaction_line_LBL_category);
            transaction_line_LBL_label = itemView.findViewById(R.id.transaction_line_LBL_label);
            transaction_line_LBL_amount = itemView.findViewById(R.id.transaction_line_LBL_amount);
        }
    }
}

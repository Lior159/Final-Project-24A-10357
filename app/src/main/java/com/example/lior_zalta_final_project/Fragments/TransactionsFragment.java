package com.example.lior_zalta_final_project.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lior_zalta_final_project.Adapters.TransactionAdapter;
import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.example.lior_zalta_final_project.Model.Transaction;
import com.example.lior_zalta_final_project.R;

import java.util.ArrayList;

public class TransactionsFragment extends Fragment {

    private RecyclerView ftransactions_LST_transactions;
    private AppCompatActivity context;
    private ArrayList<Transaction> transactions;
    private boolean isFiltered;
    private String categoryFilter;
    private String yearFilter;
    private String monthFilter;

    public TransactionsFragment(AppCompatActivity context) {
        this.context = context;
        isFiltered = false;
    }

    public TransactionsFragment(AppCompatActivity context, String categoryFilter, String yearFilter, String monthFilter) {
        this.context = context;
        this.categoryFilter = categoryFilter;
        this.yearFilter = yearFilter;
        this.monthFilter = monthFilter;
        isFiltered = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        findViews(view);
        initViews(view);
        return view;
    }

    private void findViews(View view) {
        ftransactions_LST_transactions = view.findViewById(R.id.ftransactions_LST_transactions);
    }

    private void initViews(View view) {
        this.transactions = LoggedInUser.getUser().getTransactions();
        if (isFiltered){
            filterTransactions();
        }
        TransactionAdapter transactionAdapter = new TransactionAdapter(this.context, this.transactions);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        ftransactions_LST_transactions.setLayoutManager(linearLayoutManager);
        ftransactions_LST_transactions.setAdapter(transactionAdapter);
    }

    private void filterTransactions(){
        ArrayList<Transaction> filteredTransactions = new ArrayList<Transaction>();
        for (Transaction transaction: LoggedInUser.getUser().getTransactions()) {
            String[] dateParts = transaction.getDate().split("-");
            String category = transaction.getCategory();
            String year = dateParts[2];
            String month = dateParts[1];

            boolean validCategory = categoryFilter.equals("") || categoryFilter.equals("All") || categoryFilter.equals(category);
            boolean validYear = yearFilter.equals("") || yearFilter.equals("All") || yearFilter.equals(year);
            boolean validMonth = monthFilter.equals("") || monthFilter.equals("All") || monthFilter.equals(month);

            if (validCategory && validYear && validMonth)
                filteredTransactions.add(transaction);
        }
        transactions = filteredTransactions;
    }
}
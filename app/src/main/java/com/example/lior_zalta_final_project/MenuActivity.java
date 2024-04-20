package com.example.lior_zalta_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";
    private MaterialButton menu_BTN_new_transaction;
    private MaterialButton login_BTN_set_overview;
    private MaterialButton menu_BTN_all_transactions;
    private MaterialButton login_BTN_set_budget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();
        initViews();
    }

    private void findViews() {
        menu_BTN_new_transaction = findViewById(R.id.menu_BTN_new_transaction);
        menu_BTN_all_transactions = findViewById(R.id.menu_BTN_all_transactions);
        login_BTN_set_overview = findViewById(R.id.login_BTN_set_overview);
        login_BTN_set_budget = findViewById(R.id.login_BTN_set_budget);
    }


    private void initViews() {
        menu_BTN_new_transaction.setOnClickListener(v -> redirectToNewTransactionPage());
        menu_BTN_all_transactions.setOnClickListener(v -> redirectToAllTransactionsPage());
        login_BTN_set_budget.setOnClickListener(v -> redirectToSetBudgetPage());
        login_BTN_set_overview.setOnClickListener(v -> redirectToOverViewPage());
    }

    private void redirectToSetBudgetPage() {
        startActivity(new Intent(this, SetBudgetActivity.class));
        finish();
    }

    private void redirectToAllTransactionsPage() {
        startActivity(new Intent(this, AllTransactionsActivity.class));
        finish();
    }

    private void redirectToNewTransactionPage() {
        startActivity(new Intent(this, NewTransactionActivity.class));
        finish();
    }

    private void redirectToOverViewPage() {
        startActivity(new Intent(this, OverviewActivity.class));
        finish();
    }
}
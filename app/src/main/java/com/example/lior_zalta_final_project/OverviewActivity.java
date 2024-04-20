package com.example.lior_zalta_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lior_zalta_final_project.Fragments.ProgressFragment;
import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.HashMap;


public class OverviewActivity extends AppCompatActivity {
    private View overview_VIEW_actual;
    private View overview_VIEW_budget;
    private MaterialTextView overview_LBL_actual;
    private MaterialTextView overview_LBL_budget;
    private MaterialButton overview_BTN_back;
    private HashMap<String, Double> budget;
    private HashMap<String, Double> transactionsSumCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        findViews();
        initViews();

    }

    private void initViews() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels - 200;

        Double budgetSum = LoggedInUser.getBudgetSum();
        Double transactionsSum = LoggedInUser.getTransactionsSum();
        budget = LoggedInUser.getUser().getBudget();
        transactionsSumCategory = LoggedInUser.getTransactionsSumCategory();

        overview_LBL_actual.setText("Actual: $" + transactionsSum);
        overview_LBL_budget.setText("Budget: $" + budgetSum);

        int actualWidth;
        int budgetWidth;

        if (transactionsSum >= budgetSum) {
            actualWidth = screenWidth;
            budgetWidth = 0;
        } else {
            actualWidth = (int) (screenWidth * transactionsSum / budgetSum);
            budgetWidth = screenWidth - actualWidth;
        }

        RelativeLayout.LayoutParams actualParams = new RelativeLayout.LayoutParams(actualWidth, 100);
        RelativeLayout.LayoutParams budgetParams = new RelativeLayout.LayoutParams(budgetWidth, 100);
        budgetParams.addRule(RelativeLayout.END_OF, R.id.overview_VIEW_actual);

        overview_VIEW_actual.setLayoutParams(actualParams);
        overview_VIEW_budget.setLayoutParams(budgetParams);

        ProgressFragment progressFragment = new ProgressFragment(this, budget, transactionsSumCategory, screenWidth);
        getSupportFragmentManager().beginTransaction().add(R.id.overview_FRAME_progress, progressFragment).commit();

        overview_BTN_back.setOnClickListener(v -> redirectToMainMenuPage());
    }

    private void findViews() {
        overview_VIEW_actual = findViewById(R.id.overview_VIEW_actual);
        overview_VIEW_budget = findViewById(R.id.overview_VIEW_budget);
        overview_LBL_actual = findViewById(R.id.overview_LBL_actual);
        overview_LBL_budget = findViewById(R.id.overview_LBL_budget);
        overview_BTN_back = findViewById(R.id.overview_BTN_back);
    }

    private void redirectToMainMenuPage() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
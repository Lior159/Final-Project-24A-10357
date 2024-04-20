package com.example.lior_zalta_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.lior_zalta_final_project.Fragments.TransactionsFragment;
import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class AllTransactionsActivity extends AppCompatActivity {

    private MaterialButton all_transactions_BTN_back;
    private AutoCompleteTextView all_transactions_DROPDOWN_category;
    private AutoCompleteTextView all_transactions_DROPDOWN_year;
    private AutoCompleteTextView all_transactions_DROPDOWN_month;
    private ArrayList<String> categories;
    private String[] years = {"All", "2027", "2026", "2024", "2024", "2023", "2022", "2021"};
    private String[] months = {"All", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private String categoryFilter = "";
    private String yearFilter = "";
    private String monthFilter = "";
    private ArrayAdapter<String> adapterCategories;
    private ArrayAdapter<String> adapterYears;
    private ArrayAdapter<String> adapterMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions);

        categories = LoggedInUser.getUser().getCategories();
        categories.add(0, "All");
        findViews();
        initViews();
    }

    private void findViews() {
        all_transactions_BTN_back = findViewById(R.id.all_transactions_BTN_back);
        all_transactions_DROPDOWN_category = findViewById(R.id.all_transactions_DROPDOWN_category);
        all_transactions_DROPDOWN_year = findViewById(R.id.all_transactions_DROPDOWN_year);
        all_transactions_DROPDOWN_month = findViewById(R.id.all_transactions_DROPDOWN_month);
    }

    private void initViews() {
        all_transactions_BTN_back.setOnClickListener(v -> redirectToMainMenuPage());

        adapterCategories = new ArrayAdapter<String>(this, R.layout.list_item, categories);
        all_transactions_DROPDOWN_category.setAdapter(adapterCategories);
        all_transactions_DROPDOWN_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryFilter = parent.getItemAtPosition(position).toString();
                filterTransactions();
//                Toast.makeText(AllTransactionsActivity.this, "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        adapterYears = new ArrayAdapter<String>(this, R.layout.list_item, years);
        all_transactions_DROPDOWN_year.setAdapter(adapterYears);
        all_transactions_DROPDOWN_year.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yearFilter = parent.getItemAtPosition(position).toString();
                filterTransactions();
            }
        });

        adapterMonths = new ArrayAdapter<String>(this, R.layout.list_item, months);
        all_transactions_DROPDOWN_month.setAdapter(adapterMonths);
        all_transactions_DROPDOWN_month.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                monthFilter = parent.getItemAtPosition(position).toString();
                filterTransactions();
            }
        });

        TransactionsFragment transactionsFragment = new TransactionsFragment(this);
        getSupportFragmentManager().beginTransaction().add(R.id.all_transactions_FRAME_transactions, transactionsFragment).commit();
    }

    private void filterTransactions() {
        TransactionsFragment transactionsFragment;
        if (isFiltered()) {
            transactionsFragment = new TransactionsFragment(this, categoryFilter, yearFilter, monthFilter);
        } else {
            transactionsFragment = new TransactionsFragment(this);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.all_transactions_FRAME_transactions, transactionsFragment).commit();
    }

    private boolean isFiltered(){
        if (!categoryFilter.equals("") && !categoryFilter.equals("All"))
            return true;
        if (!yearFilter.equals("") && !yearFilter.equals("All"))
            return true;
        if (!monthFilter.equals("") && !monthFilter.equals("All"))
            return true;
        return false;
    }

    private void redirectToMainMenuPage() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }


}
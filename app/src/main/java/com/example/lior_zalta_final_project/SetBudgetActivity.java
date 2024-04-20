package com.example.lior_zalta_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.lior_zalta_final_project.Fragments.BudgetFragment;
import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.example.lior_zalta_final_project.Model.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SetBudgetActivity extends AppCompatActivity {
    private User user;
    private MaterialButton budget_BTN_save;
    private HashMap<String, Double> budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        user = LoggedInUser.getUser();
        budget = new HashMap<>(user.getBudget());

        findViews();
        initViews();
    }

    private void findViews() {
        budget_BTN_save = findViewById(R.id.budget_BTN_save);
    }

    private void initViews() {
        BudgetFragment budgetFragment = new BudgetFragment(this, budget);
        getSupportFragmentManager().beginTransaction().add(R.id.budget_FRAME_budget, budgetFragment).commit();

        budget_BTN_save.setOnClickListener(v -> save());
    }

    private void save() {
        LoggedInUser.getUser().setBudget(budget);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUsername());
        ref.setValue(user);
        redirectToMainMenuPage();
    }

    private void redirectToMainMenuPage() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
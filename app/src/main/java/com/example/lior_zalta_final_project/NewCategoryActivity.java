package com.example.lior_zalta_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.example.lior_zalta_final_project.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class NewCategoryActivity extends AppCompatActivity {

    private User user;
    private TextInputEditText new_category_ET_category;
    private MaterialButton new_category_BTN_add;
    private MaterialButton new_category_BTN_back;
    private boolean btnEnabled = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        user = LoggedInUser.getUser();

        findViews();
        initViews();
    }

    private void findViews() {
        new_category_ET_category = findViewById(R.id.new_category_ET_category);
        new_category_BTN_add = findViewById(R.id.new_category_BTN_add);
        new_category_BTN_back = findViewById(R.id.new_category_BTN_back);
    }

    private void initViews() {
        new_category_BTN_add.setOnClickListener(v -> saveCategory());
        new_category_BTN_back.setOnClickListener(v -> redirectToNewTransactionPage());
    }

    private void saveCategory() {
        ArrayList<String> categories = user.getCategories();
        HashMap<String, Double> budget = user.getBudget();
        String category = new_category_ET_category.getText().toString().trim().toLowerCase();

        if (category.equals("")) {
            Toast.makeText(NewCategoryActivity.this, "Category Can't Be Blank!", Toast.LENGTH_LONG).show();
            return;
        }

        if (categories.contains(category)) {
            Toast.makeText(NewCategoryActivity.this, "Category Already Exist!", Toast.LENGTH_LONG).show();
            new_category_ET_category.setText("");
            return;
        }

        categories.add(category);
        budget.put(category, 0.0);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUsername());
        ref.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                new_category_ET_category.setText("");
                Toast.makeText(NewCategoryActivity.this, "Category Created!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void redirectToNewTransactionPage() {
        startActivity(new Intent(this, NewTransactionActivity.class));
        finish();
    }
}
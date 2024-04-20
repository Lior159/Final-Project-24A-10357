package com.example.lior_zalta_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.example.lior_zalta_final_project.Model.Transaction;
import com.example.lior_zalta_final_project.Model.User;
import com.example.lior_zalta_final_project.Util.PasswordEncryption;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class NewTransactionActivity extends AppCompatActivity {

    private User user;
    private TextInputEditText new_transaction_ET_label;
    private AutoCompleteTextView new_transaction_DROPDOWN_category;
    private MaterialTextView new_transaction_LBL_add_category;
    private TextInputEditText new_transaction_ET_amount;
    private TextInputEditText new_transaction_ET_date;
    private RadioGroup new_transaction_RG_transaction_type;
    private RadioButton new_transaction_RB_expense;
    private RadioButton new_transaction_RB_income;
    private MaterialButton new_transaction_BTN_add;
    private MaterialButton new_transaction_BTN_back;
    private MaterialTextView new_transaction_LBL_validation;
    private ArrayAdapter<String> adapterCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        user = LoggedInUser.getUser();

        findViews();
        initViews();
    }

    private void findViews() {
        new_transaction_ET_label = findViewById(R.id.new_transaction_ET_label);
        new_transaction_DROPDOWN_category = findViewById(R.id.new_transaction_DROPDOWN_category);
        new_transaction_LBL_add_category = findViewById(R.id.new_transaction_LBL_add_category);
        new_transaction_ET_amount = findViewById(R.id.new_transaction_ET_amount);
        new_transaction_ET_date = findViewById(R.id.new_transaction_ET_date);
        new_transaction_RG_transaction_type = findViewById(R.id.new_transaction_RG_transaction_type);
        new_transaction_RB_expense = findViewById(R.id.new_transaction_RB_expense);
        new_transaction_RB_income = findViewById(R.id.new_transaction_RB_income);
        new_transaction_BTN_add = findViewById(R.id.new_transaction_BTN_add);
        new_transaction_BTN_back = findViewById(R.id.new_transaction_BTN_back);
        new_transaction_LBL_validation = findViewById(R.id.new_transaction_LBL_validation);
    }

    private void initViews() {
        new_transaction_BTN_add.setOnClickListener(v -> pushTransaction());
        new_transaction_BTN_back.setOnClickListener(v -> redirectToMenuPage());
        new_transaction_ET_date.setOnClickListener(v -> showCalendar());
        new_transaction_LBL_add_category.setOnClickListener(v -> redirectToNewCategoryPage());
        adapterCategories = new ArrayAdapter<String>(this, R.layout.list_item, user.getCategories());
        new_transaction_DROPDOWN_category.setAdapter(adapterCategories);
    }

    private void pushTransaction() {
        String label = new_transaction_ET_label.getText().toString().trim();
        String category = new_transaction_DROPDOWN_category.getText().toString();
        String amount_str = new_transaction_ET_amount.getText().toString();
        String date = new_transaction_ET_date.getText().toString();

        if (label.equals("") || category.equals("") || amount_str.equals("") || date.equals("")) {
            new_transaction_LBL_validation.setText("Fields should not be blank!!");
            return;
        }

        Double amount = Double.parseDouble(new_transaction_ET_date.getText().toString());

        Transaction transaction = new Transaction();
        transaction.setLabel(label)
                .setCategory(category)
                .setDate(date)
                .setAmount(amount)
                .setTransactionType(new_transaction_RB_expense.isChecked() ? Transaction.TransactionType.EXPENSE : Transaction.TransactionType.INCOME);

        user.getTransactions().add(transaction);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUsername());
        ref.setValue(user);
    }

    private void showCalendar() {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                NewTransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                new_transaction_ET_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void redirectToMenuPage() {
        Intent mainManuIntent = new Intent(this, MenuActivity.class);
        startActivity(mainManuIntent);
        finish();
    }

    private void redirectToNewCategoryPage() {
        startActivity(new Intent(this, NewCategoryActivity.class));
        finish();
    }
}
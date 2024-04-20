package com.example.lior_zalta_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.lior_zalta_final_project.Model.LoggedInUser;
import com.example.lior_zalta_final_project.Model.User;
import com.example.lior_zalta_final_project.Util.PasswordEncryption;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {
    private TextInputEditText login_ET_username;
    private TextInputEditText login_ET_password;
    private MaterialTextView login_LBL_validation;
    private MaterialButton login_BTN_login;
    private MaterialButton login_BTN_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        initViews();
    }

    private void findViews() {
        login_ET_username = findViewById(R.id.login_ET_username);
        login_ET_password = findViewById(R.id.login_ET_password);
        login_LBL_validation = findViewById(R.id.login_LBL_validation);
        login_BTN_login = findViewById(R.id.login_BTN_login);
        login_BTN_signup = findViewById(R.id.login_BTN_signup);
    }

    private void initViews() {
        login_BTN_login.setOnClickListener(v -> {
            login();
        });

        login_BTN_signup.setOnClickListener(v -> {
            redirectToSignupPage();
        });
    }

    private void login() {
        String username = login_ET_username.getText().toString().trim();

        if (username.equals("")) {
            login_LBL_validation.setText("Username should not be blank!");
            return;
        }

        if (!username.matches("[a-zA-Z0-9]+")) {
            login_LBL_validation.setText("Username must include only letters and numbers!");
            return;
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/" + username);
        ref.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                User user = task.getResult().getValue(User.class);

                if (user == null)
                    login_LBL_validation.setText("Username not exist!");
                else if (!PasswordEncryption.validatePassword(login_ET_password.getText().toString(), user.getPassword()))
                    login_LBL_validation.setText("Invalid password!");
                else{
                    LoggedInUser.setUser(user);
                    redirectToMainMenuPage();
                }
            }
        });
    }

    private void redirectToSignupPage() {
        startActivity(new Intent(this, SignupActivity.class));
        finish();
    }

    private void redirectToMainMenuPage() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

}
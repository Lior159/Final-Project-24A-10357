package com.example.lior_zalta_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lior_zalta_final_project.Model.User;
import com.example.lior_zalta_final_project.Util.PasswordEncryption;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private MaterialTextView signup_LBL_validation;
    private TextInputEditText signup_ET_username;
    private TextInputEditText signup_ET_password;
    private TextInputEditText signup_ET_confirm_password;
    private MaterialButton signup_BTN_signup;
    private MaterialButton signup_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViews();
        initviews();
    }

    private void findViews() {
        signup_ET_username = findViewById(R.id.signup_ET_username);
        signup_ET_password = findViewById(R.id.signup_ET_password);
        signup_ET_confirm_password = findViewById(R.id.signup_ET_confirm_password);
        signup_BTN_signup = findViewById(R.id.signup_BTN_signup);
        signup_BTN_back = findViewById(R.id.signup_BTN_back);
        signup_LBL_validation = findViewById(R.id.signup_LBL_validation);
    }

    private void initviews() {
        signup_BTN_signup.setOnClickListener(v -> signup());
        signup_BTN_back.setOnClickListener(v -> redirectToLoginPage());
    }

    private void signup() {
        String username = signup_ET_username.getText().toString();
        String password = signup_ET_password.getText().toString();
        String confirm_password = signup_ET_confirm_password.getText().toString();

        if (!username.matches("[a-zA-Z0-9]+")){
            signup_LBL_validation.setText("Username must include only letters and numbers!");
            return;
        }

        if (password.contains(" ") || password.length() < 8){
            signup_LBL_validation.setText("Password must include at least 8 chars (no sapces)");
            return;
        }

        if (!password.equals(confirm_password)){
            signup_LBL_validation.setText("Password Confirm should match Password");
            return;
        }

        User user = new User()
                .setUsername(username)
                .setPassword(PasswordEncryption.encryptPassword(password));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(username);
        ref.setValue(user);

        redirectToLoginPage();
    }

    private void redirectToLoginPage() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }


}
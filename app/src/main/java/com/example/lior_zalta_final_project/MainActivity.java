package com.example.lior_zalta_final_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText typeActive, typeDone, typeOverdue;
    private Button btnSubmit;
    private View viewDone, viewOverDue, viewActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeActive = findViewById(R.id.editActive);
        typeDone = findViewById(R.id.editDone);
        typeOverdue = findViewById(R.id.editOverdue);

        btnSubmit = findViewById(R.id.btnSubmit);

        viewDone = findViewById(R.id.viewDone);
        viewActive = findViewById(R.id.viewActive);
        viewOverDue = findViewById(R.id.viewOverdue);

        viewDone.setVisibility(View.INVISIBLE);
        viewOverDue.setVisibility(View.INVISIBLE);
        viewActive.setVisibility(View.INVISIBLE);

        //Type all the number of tasks and press the submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tasksActive = typeActive.getText().toString();
                final String tasksDone = typeDone.getText().toString();
                final String tasksOverdue = typeOverdue.getText().toString();

                int intTaskActive = Integer.parseInt(tasksActive);
                int intTaskDone = Integer.parseInt(tasksDone);
                int intTaskOverdue = Integer.parseInt(tasksOverdue);

                int totalTasks = intTaskActive + intTaskOverdue + intTaskDone;

                float percentageActive = (intTaskActive*100.0f)/totalTasks;
                float percentageDone   = (intTaskDone*100.0f)/totalTasks;
                float percentageOverdue = (intTaskOverdue*100.0f)/totalTasks;

                int intPercActive = (int)percentageActive;
                int intPercDone = (int)percentageDone;
                int intPercOverdue = (int)percentageOverdue;

                viewDone.setVisibility(View.VISIBLE);
                viewOverDue.setVisibility(View.VISIBLE);
                viewActive.setVisibility(View.VISIBLE);

                viewDone.setLayoutParams(new RelativeLayout.LayoutParams(intPercDone*10, 50));
                viewActive.setLayoutParams(new RelativeLayout.LayoutParams(intPercActive*10, 50));
                viewOverDue.setLayoutParams(new RelativeLayout.LayoutParams(intPercOverdue*10, 50));

            }
        });

    }
}
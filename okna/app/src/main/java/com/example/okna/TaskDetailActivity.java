package com.example.okna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private String taskName;
    private String taskDescription;
    private int taskPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        Button buttonEdit = findViewById(R.id.buttonEdit);

        taskName = getIntent().getStringExtra("taskName");
        taskDescription = getIntent().getStringExtra("taskDescription");
        taskPosition = getIntent().getIntExtra("taskPosition", -1);

        textViewName.setText(taskName);
        textViewDescription.setText(taskDescription);

        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(TaskDetailActivity.this, AddTaskActivity.class);
            intent.putExtra("taskName", taskName);
            intent.putExtra("taskDescription", taskDescription);
            intent.putExtra("taskPosition", taskPosition);
            startActivityForResult(intent, 2);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 2 && data != null) {
            setResult(RESULT_OK, data);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

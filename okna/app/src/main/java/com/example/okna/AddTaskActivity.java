package com.example.okna;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextName, editTextDescription;
    private int taskPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        Button buttonSave = findViewById(R.id.buttonSave);

        Intent intent = getIntent();
        if (intent.hasExtra("taskName") && intent.hasExtra("taskDescription")) {
            editTextName.setText(intent.getStringExtra("taskName"));
            editTextDescription.setText(intent.getStringExtra("taskDescription"));
            taskPosition = intent.getIntExtra("taskPosition", -1);
        }

        buttonSave.setOnClickListener(v -> {
            String taskName = editTextName.getText().toString();
            String taskDescription = editTextDescription.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("taskName", taskName);
            resultIntent.putExtra("taskDescription", taskDescription);
            resultIntent.putExtra("taskPosition", taskPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}

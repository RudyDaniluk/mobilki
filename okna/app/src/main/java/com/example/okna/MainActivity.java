package com.example.okna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList, this::openTaskDetail);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_task);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String taskName = data.getStringExtra("taskName");
            String taskDescription = data.getStringExtra("taskDescription");
            int taskPosition = data.getIntExtra("taskPosition", -1);

            if (requestCode == 1) {
                taskList.add(new Task(taskName, taskDescription));
                taskAdapter.notifyDataSetChanged();
            } else if (requestCode == 2 && taskPosition != -1) {
                Task updatedTask = taskList.get(taskPosition);
                updatedTask.setName(taskName);
                updatedTask.setDescription(taskDescription);
                taskAdapter.notifyItemChanged(taskPosition);
            }
        }
    }


    private void openTaskDetail(int position) {
        Task task = taskList.get(position);
        Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
        intent.putExtra("taskName", task.getName());
        intent.putExtra("taskDescription", task.getDescription());
        intent.putExtra("taskPosition", position);
        startActivityForResult(intent, 2);
    }
}

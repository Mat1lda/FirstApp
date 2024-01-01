package com.quanvm.meplanner.plan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quanvm.meplanner.R;
import com.quanvm.meplanner.alarmmanager.SetAlarmActivity;
import com.quanvm.meplanner.calendar.ScheduleCalendar;
import com.quanvm.meplanner.task.AddTaskActivity;
import com.quanvm.meplanner.task.TaskManagement;
import com.quanvm.meplanner.timetable.Schedule;

public class AddingPlan extends AppCompatActivity {
    LinearLayout addTask, addAlarm, addCalendar, addTimeTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_plan);
        addTask = findViewById(R.id.addTask);
        addAlarm = findViewById(R.id.addAlarm2);
        addCalendar = findViewById(R.id.addCalendar);
        addTimeTable = findViewById(R.id.addTimeTable);

        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingPlan.this, SetAlarmActivity.class));
            }
        });
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingPlan.this, AddTaskActivity.class));
            }
        });
        addCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingPlan.this, ScheduleCalendar.class));
            }
        });
        addTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingPlan.this, Schedule.class));
            }
        });
    }
}
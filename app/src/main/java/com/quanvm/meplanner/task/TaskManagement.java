package com.quanvm.meplanner.task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.quanvm.meplanner.R;
import com.quanvm.meplanner.alarmmanager.AlarmsList;
import com.quanvm.meplanner.calendar.ShowCalendar;
import com.quanvm.meplanner.plan.AddingPlan;
import com.quanvm.meplanner.timetable.ShowSchedule;

import java.util.ArrayList;

public class TaskManagement extends AppCompatActivity {
    FloatingActionButton addTaskFAB;
    //TextView overAllTxt;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<TaskDataModel> taskDataModelList;

    FirebaseFirestore firestore;

    FirebaseAuth firebaseAuth;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_management);

        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.tasktoolbar);
        setSupportActionBar(materialToolbar);

        addTaskFAB = (FloatingActionButton) findViewById(R.id.addTaskFABBtn);
        //overAllTxt = (TextView) findViewById(R.id.overallTxt);
        recyclerView = (RecyclerView) findViewById(R.id.taskList);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            uid = firebaseUser.getUid();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskDataModelList = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(this, taskDataModelList);

        recyclerView.setAdapter(recyclerViewAdapter);

        EventChangeListener();

        addTaskFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaskManagement.this, AddingPlan.class));
            }
        });


    }
    private void EventChangeListener() {
        CollectionReference collectionReference = firestore
                .collection("users").document(uid).collection("TaskDetails");
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    //Toast.makeText(TaskManagement.this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (value != null) {
                    for(DocumentChange documentChange: value.getDocumentChanges()){
                        if (documentChange.getType() == DocumentChange.Type.ADDED){

                            taskDataModelList.add(documentChange.getDocument().toObject(TaskDataModel.class)); // here is error

                        }

                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.calendarIcon) {
            startActivity(new Intent(TaskManagement.this, ShowCalendar.class));
            return true;
        }
        /*
         else if(id == R.id.addAlarmIcon){
            startActivity(new Intent(TaskManagement.this, SetAlarmActivity.class));
            return true;
        }
        */
        else if(id == R.id.AlarmIcon){
            startActivity(new Intent(TaskManagement.this, AlarmsList.class));
            return true;
        }
        else if (id == R.id.timeTableIcon) {
            startActivity(new Intent(TaskManagement.this, ShowSchedule.class));
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}
package com.quanvm.meplanner.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.quanvm.meplanner.R;

import java.util.ArrayList;

public class NotificationList extends AppCompatActivity {
    RecyclerView recyclerView;

    NotificationRVAdapter notificationRVAdapter;

    ArrayList<NotificationDataModel> notificationList;

    FirebaseFirestore firestore;

    FirebaseAuth firebaseAuth;

    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
    }
}
package com.rucha.sastainstagram;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private String TAG = "ABCD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        loadEvent();

    }

    private void loadEvent(){


        db.collection("feeds")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {

                                Log.d(TAG, document.getId() + " => " + document.getData().get("caption"));
                                Log.d(TAG, document.getId() + " => " + document.getData().get("comment1"));
                                Log.d(TAG, document.getId() + " => " + document.getData().get("comment2"));
                                Log.d(TAG, document.getId() + " => " + document.getData().get("like_status"));
                                Log.d(TAG, document.getId() + " => " + document.getData().get("liked_by"));
                                Log.d(TAG, document.getId() + " => " + document.getData().get("others"));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}

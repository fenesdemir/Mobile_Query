package com.example.softlab22_cdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Query1 extends AppCompatActivity {

    public FirebaseDatabase database;
    public DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query1);
        final ArrayList<TextView> twList = new ArrayList<>();
        final ArrayList<String> keyList = new ArrayList<>();
        TextView t1 = findViewById(R.id.textView1);
        twList.add(t1);
        TextView t2 = findViewById(R.id.textView2);
        twList.add(t2);
        TextView t3 = findViewById(R.id.textView3);
        twList.add(t3);
        TextView t4 = findViewById(R.id.textView4);
        twList.add(t4);
        TextView t5 = findViewById(R.id.textView5);
        twList.add(t5);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        ref.orderByChild("trip_distance").limitToLast(5).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                keyList.add(snapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int i = 0; i<5; i++){
                    String distance = snapshot.child(keyList.get(i)).child("trip_distance").getValue().toString();
                    java.util.Date d1 = new Date((Long) snapshot.child(keyList.get(i)).child("tpep_dropoff_datetime").getValue());
                    java.util.Date d2 = new Date((Long) snapshot.child(keyList.get(i)).child("tpep_pickup_datetime").getValue());
                    String key = keyList.get(i);
                    String text = "Mesafe: " + distance + ", Yolcunun alinma tarihi: " + d2.toString() +  ", Yolcunun birakilma tarihi: " + d1.toString() + ", Giris no: " +key;
                    twList.get(i).setText(text);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
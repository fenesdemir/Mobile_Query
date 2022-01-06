package com.example.softlab22_cdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Query2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Date date_pick;
    Date date_drop;
    long pickMili, dropMili;
    public FirebaseDatabase database;
    public DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query2);

        final ArrayList<TextView> twList = new ArrayList<>();
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

        final ArrayList<String> keylist1 = new ArrayList<>();
        final ArrayList<String> keylist2 = new ArrayList<>();
        final ArrayList<String> keylist3 = new ArrayList<>();
        final ArrayList<String> keylistFin = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        Spinner sp1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.dates, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(this);

        Spinner sp2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.dates, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = database.getReference();
                ref.orderByChild("tpep_pickup_datetime").startAt(pickMili).endAt(dropMili).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        keylist1.add(snapshot.getKey());
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

                ref.orderByChild("tpep_dropoff_datetime").startAt(pickMili).endAt(dropMili).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        keylist2.add(snapshot.getKey());
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

                ref.orderByChild("trip_distance").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        keylist3.add(snapshot.getKey());
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
                        int count = 0;
                        keylist1.retainAll(keylist2);

                        for (int i = 0; i < keylist3.size(); i++){
                            if(keylist1.contains(keylist3.get(i)) && count < 5){
                                keylistFin.add(keylist3.get(i));
                                count++;
                            }
                        }

                        for (int i = 0; i<5; i++){
                            String distance = snapshot.child(keylistFin.get(i)).child("trip_distance").getValue().toString();
                            java.util.Date d1 = new Date((Long) snapshot.child(keylistFin.get(i)).child("tpep_dropoff_datetime").getValue());
                            java.util.Date d2 = new Date((Long) snapshot.child(keylistFin.get(i)).child("tpep_pickup_datetime").getValue());
                            String passanger = snapshot.child(keylistFin.get(i)).child("passenger_count").getValue().toString();
                            String fare = snapshot.child(keylistFin.get(i)).child("total_amount").getValue().toString();
                            String key = keylistFin.get(i);
                            String text = "Mesafe: " + distance + ", Yolcunun alinma tarihi: " + d2.toString() + ", Yolcunun birakilma tarihi: " + d1.toString() + ", Yolcu sayisi: " + passanger + ", Ucret: " + fare + ", Giris no: " +key;
                            twList.get(i).setText(text);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.spinner1:
                try {
                    date_pick = new SimpleDateFormat("yyyy-MM-dd").parse(adapterView.getItemAtPosition(i).toString());
                    pickMili = date_pick.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.spinner2:
                try {
                    date_drop = new SimpleDateFormat("yyyy-MM-dd").parse(adapterView.getItemAtPosition(i).toString());
                    dropMili = date_drop.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
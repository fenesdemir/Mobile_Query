package com.example.softlab22_cdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ListViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button buttonSorgu1;
    public Button buttonSorgu2;
    public Button buttonSorgu3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSorgu1 = findViewById(R.id.bSorgu1);
        buttonSorgu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuery1();
            }
        });

        buttonSorgu2 = findViewById(R.id.bSorgu2);
        buttonSorgu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuery2();
            }
        });

        buttonSorgu3 = findViewById(R.id.bSorgu3);
        buttonSorgu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuery3();
            }
        });
    }

    public void openQuery1(){
        Intent intent = new Intent(this, Query1.class);
        startActivity(intent);
    }

    public void openQuery2(){
        Intent intent = new Intent(this, Query2.class);
        startActivity(intent);
    }

    public void openQuery3(){
        Intent intent = new Intent(this, Query3.class);
        startActivity(intent);
    }
}
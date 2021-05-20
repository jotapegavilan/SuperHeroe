package com.gavilan.superheroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gavilan.superheroapp.model.Heroe;
import com.gavilan.superheroapp.model.HeroeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerHeroes;
    Button btnNuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerHeroes = findViewById(R.id.recyclerHeroes);
        btnNuevo = findViewById(R.id.btnNuevo);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NuevoHeroeActivity.class));
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("heroes");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Heroe> heroesDesdeFb = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    // Sacar uno a uno los heros de firebase
                    // cargos en el arraylist local
                    Heroe heroe = ds.getValue(Heroe.class);
                    heroesDesdeFb.add(heroe);
                }
                HeroeAdapter adapter = new HeroeAdapter(heroesDesdeFb);
                recyclerHeroes.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerHeroes.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException());
            }
        });


    }
}
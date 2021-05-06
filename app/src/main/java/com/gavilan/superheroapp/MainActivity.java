package com.gavilan.superheroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gavilan.superheroapp.model.Heroe;
import com.gavilan.superheroapp.model.HeroeAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerHeroes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerHeroes = findViewById(R.id.recyclerHeroes);

        ArrayList<Heroe> listHeroes = new ArrayList<>();

        Heroe h1 = new Heroe();
        h1.setIdHeroe(1);
        h1.setNombreHeroe("Batman");
        h1.setSuperPoder("Dinero");
        h1.setEdad(35);
        h1.setMundo("DC");

        Heroe h2 = new Heroe();
        h2.setIdHeroe(2);
        h2.setNombreHeroe("Iron man");
        h2.setSuperPoder("Dinero");
        h2.setEdad(37);
        h2.setMundo("Marvel");

        Heroe h3 = new Heroe();
        h3.setIdHeroe(3);
        h3.setNombreHeroe("Wolverine");
        h3.setSuperPoder("Regeneración");
        h3.setEdad(1000);
        h3.setMundo("Marvel");

        Heroe h4 = new Heroe();
        h4.setIdHeroe(4);
        h4.setNombreHeroe("Cap. América");
        h4.setSuperPoder("Dinero");
        h4.setEdad(37);
        h4.setMundo("Marvel");

        listHeroes.add(h1);
        listHeroes.add(h2);
        listHeroes.add(h3);
        listHeroes.add(h4);


        HeroeAdapter adapter = new HeroeAdapter(listHeroes);

        recyclerHeroes.setLayoutManager(new LinearLayoutManager(this));

        recyclerHeroes.setAdapter(adapter);


    }
}
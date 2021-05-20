package com.gavilan.superheroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gavilan.superheroapp.model.Heroe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NuevoHeroeActivity extends AppCompatActivity {
    // Declarar todos los elementos a utilizar
    Spinner spMundo;
    EditText txtNombre, txtId, txtEdad, txtSuperPoder;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_heroe);
        // Relación entre objeto de java y elementos visuales
        spMundo = findViewById(R.id.spMundo);
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        txtId = findViewById(R.id.txtID);
        txtSuperPoder = findViewById(R.id.txtSuperPoder);
        btn = findViewById(R.id.btnRegistrar);
        // Array contiene los elementos que cargaremos en el spinner
        ArrayList<String> mundos = new ArrayList<>();
        mundos.add("Seleccione mundo");
        mundos.add("Marvel");
        mundos.add("DC");
        // Cargamos el array de string en el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, mundos );
        spMundo.setAdapter(adapter);

    }
    // Método que recibe View es seleccionable desde la prop. onClick
    public void ClickButton( View v ){
        // Obteniendo valores por caja de texto
        int id = Integer.parseInt(txtId.getText().toString());
        String nombre = txtNombre.getText().toString();
        int edad = Integer.parseInt(txtEdad.getText().toString());
        String poder = txtSuperPoder.getText().toString();
        //Obtener valor del spinner seleccionado
        String mundo = spMundo.getSelectedItem().toString();

        // Encapsular los datos en un objeto
        Heroe h = new Heroe();
        h.setIdHeroe(id);
        h.setNombreHeroe(nombre);
        h.setEdad(edad);
        h.setSuperPoder(poder);
        h.setMundo(mundo);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("heroes");
        // Accedo a la colección heroes
        // Crear un registro con el id del heroe
        // Asignamos a ese child el objeto completo del heroe

        //Validar que el id no exista

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                // Validar que no exista el child con el id que se ingreso en la GUI
                if( task.getResult().child(h.getIdHeroe()+"").exists()  ){
                    Tostada("Error : el id está ocupado!");
                }else{
                    myRef.child(h.getIdHeroe()+"").setValue(h);
                    Tostada("Registro exitoso!");
                }
            }
        });





    }

    public void Tostada(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

}
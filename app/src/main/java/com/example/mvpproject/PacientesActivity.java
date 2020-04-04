package com.example.mvpproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpproject.Model.Paciente;
import com.example.mvpproject.Model.PacientesInteractorImpl;
import com.example.mvpproject.Presenter.PacientesPresenter;
import com.example.mvpproject.Presenter.PacientesPresenterImpl;
import com.example.mvpproject.Resources.MyAdapter;
import com.example.mvpproject.View.PacientesView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PacientesActivity extends AppCompatActivity implements PacientesView {

    private MyAdapter myAdapter;
    private PacientesPresenter presenter;
    private ArrayList<Paciente> sa = new ArrayList<>();
    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);
        presenter =  new PacientesPresenterImpl(this,new PacientesInteractorImpl());
        Toast.makeText(getApplicationContext(),"Cargando...",Toast.LENGTH_SHORT).show();
        final String token = getIntent().getStringExtra("token");
        final String sa = getIntent().getStringExtra("user");
        final int id = Integer.parseInt(sa);
        presenter.getInfo(token,id);
        FloatingActionButton nuevoPaciente = findViewById(R.id.newc);
        nuevoPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PacientesActivity.this);
                View viewc = getLayoutInflater().inflate(R.layout.paciente_cu, null);
                final EditText nombre = viewc.findViewById(R.id.named);
                final EditText apellidop = viewc.findViewById(R.id.apellidop);
                final EditText apellidom = viewc.findViewById(R.id.apellidom);
                final EditText fecha = viewc.findViewById(R.id.fechita);
                Button crear = viewc.findViewById(R.id.crear);
                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Paciente a = new Paciente(nombre.getText().toString(),apellidop.getText().toString(),apellidom.getText().toString(),fecha.getText().toString(),0,token,String.valueOf(id));
                        presenter.createPaciente(token,a,id);
                    }
                });
                mBuilder.setView(viewc);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    @Override
    public void showPacientes(ArrayList<Paciente> rows) {
        RecyclerView pacientes = findViewById(R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        pacientes.setLayoutManager(linearLayoutManager);

        myAdapter = new MyAdapter(rows,this);
        pacientes.setAdapter(myAdapter);

    }

    @Override
    public void showMessageError() {
        Toast.makeText(getApplicationContext(),"Ha ocurrido un error, favor de intentarlo mas tarde",Toast.LENGTH_LONG).show();
    }

    @Override
    public void createdPaciente() {
        Toast.makeText(getApplicationContext(),"Paciente Creado",Toast.LENGTH_LONG);
        Intent aas = new Intent(getApplicationContext(),PacientesActivity.class);
        aas.putExtra("token",getIntent().getStringExtra("token"));
        aas.putExtra("user",getIntent().getStringExtra("user"));
        startActivity(aas);
        finish();
    }
}

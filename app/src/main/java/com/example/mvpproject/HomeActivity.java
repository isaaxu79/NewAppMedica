package com.example.mvpproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final String token = getIntent().getStringExtra("token");
        final String id = getIntent().getStringExtra("id");
        System.out.println("-----------id = "+id+ "   ------------.tk = "+token);

        CardView toPerfil = findViewById(R.id.perf);
        toPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perfil = new Intent(getApplicationContext(),PacientesActivity.class);
                perfil.putExtra("token",token);
                perfil.putExtra("user",id);
                startActivity(perfil);
            }
        });

        CardView toMedicina = findViewById(R.id.medica);
        toMedicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perfil = new Intent(getApplicationContext(),PerfilActivity.class);
                perfil.putExtra("token",token);
                perfil.putExtra("user",id);
                startActivity(perfil);
            }
        });
    }
}

package com.example.mvpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpproject.Model.CurrentUser;
import com.example.mvpproject.Model.PerfilInteractorImpl;
import com.example.mvpproject.Presenter.PerfilPresenter;
import com.example.mvpproject.Presenter.PerfilPresenterImpl;
import com.example.mvpproject.View.PerfilView;

public class PerfilActivity extends AppCompatActivity implements PerfilView {

    private PerfilPresenter presenter;
    private TextView name;
    private TextView especial;
    private String token;
    private String sa;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        name = findViewById(R.id.nameper);
        especial = findViewById(R.id.especial);
        token = getIntent().getStringExtra("token");
        sa = getIntent().getStringExtra("user");
        id = Integer.parseInt(sa);
        presenter = new PerfilPresenterImpl(this, new PerfilInteractorImpl());
        presenter.getInfo(token,id);
    }

    @Override
    public void fillProfile(CurrentUser user) {
        System.out.println("*********************************************************************************************");
        String nombre ="Dr. "+ user.getNombre() +" "+user.getApellidop()+" "+user.getApellidom();
        String especiala = user.getEspecialidad();
        name.setText(nombre);
        especial.setText(especiala);
    }

    @Override
    public void showMessageError() {
        Toast.makeText(getApplicationContext(),"Ocurrio un error, intentalo más tarde",Toast.LENGTH_LONG).show();
    }
}

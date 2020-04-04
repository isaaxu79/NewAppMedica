package com.example.mvpproject.Resources;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvpproject.Model.Paciente;
import com.example.mvpproject.Model.PacientesInteractor;
import com.example.mvpproject.Model.PacientesInteractorImpl;
import com.example.mvpproject.PacientesActivity;
import com.example.mvpproject.R;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Paciente> mDataset;
    private Activity ac;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView fechas;
        Button borrar;
        Button editar;
        MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.nombre);
            fechas = v.findViewById(R.id.fecha);
            borrar = v.findViewById(R.id.eliminar);
            editar = v.findViewById(R.id.editar);
;        }
    }

    public MyAdapter(ArrayList<Paciente> myDataset, Activity ac) {
        mDataset = myDataset;
        this.ac = ac;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String nombre = mDataset.get(position).getNombre() + " " + mDataset.get(position).getApellidoPaterno() + " " + mDataset.get(position).getApellidoMaterno();
        holder.nombre.setText(nombre);
        holder.fechas.setText(mDataset.get(position).getFecha());
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PacientesInteractorImpl s = new PacientesInteractorImpl();
                s.deletePaciente("sa", mDataset.get(position).getId());
                Intent i = new Intent(ac, PacientesActivity.class);
                i.putExtra("token",mDataset.get(position).getToken());
                i.putExtra("user",mDataset.get(position).getUser_id());
                ac.startActivity(i);
                ac.finish();
            }
        });
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(ac);
                final PacientesInteractorImpl s = new PacientesInteractorImpl();
                View viewc = ac.getLayoutInflater().inflate(R.layout.paciente_cu, null);
                final EditText nombre = viewc.findViewById(R.id.named);
                final EditText apellidop = viewc.findViewById(R.id.apellidop);
                final EditText apellidom = viewc.findViewById(R.id.apellidom);
                final EditText fecha = viewc.findViewById(R.id.fechita);
                Button crear = viewc.findViewById(R.id.crear);
                nombre.setText(mDataset.get(position).getNombre());
                apellidop.setText(mDataset.get(position).getApellidoPaterno());
                apellidom.setText(mDataset.get(position).getApellidoMaterno());
                fecha.setText(mDataset.get(position).getFecha());
                crear.setText("Editar");
                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Paciente a = new Paciente(nombre.getText().toString(),apellidop.getText().toString(),apellidom.getText().toString(),fecha.getText().toString(),0,mDataset.get(position).getToken(),mDataset.get(position).getUser_id());
                        s.updatePaciente(mDataset.get(position).getToken(),mDataset.get(position).getId(),a);
                        Intent i = new Intent(ac, PacientesActivity.class);
                        i.putExtra("token",mDataset.get(position).getToken());
                        i.putExtra("user",mDataset.get(position).getUser_id());
                        ac.startActivity(i);
                        ac.finish();
                    }
                });
                mBuilder.setView(viewc);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
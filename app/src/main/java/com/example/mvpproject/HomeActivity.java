package com.example.mvpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpproject.Model.HomeInteractorImplement;
import com.example.mvpproject.Presenter.HomePresenter;
import com.example.mvpproject.Presenter.HomePresenterImplement;
import com.example.mvpproject.View.HomeView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private HomePresenter presenter;
    private TableLayout tableLayouts;
    String[] headers ={"id","name","lastname","age",""};
    private ArrayList<String[]> rowss = new ArrayList<>();
    private TableRow tableRows;
    private TextView txtCells;
    private int indexRs;
    private int indexCs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.init).setBackgroundColor(Color.parseColor("#00695C"));
        presenter = new HomePresenterImplement(this,new HomeInteractorImplement());
        String token = getIntent().getStringExtra("token");
        Boolean superUser = Boolean.valueOf(getIntent().getStringExtra("super"));
        presenter.getInfo(token,superUser);
        tableLayouts = findViewById(R.id.table);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showTable(ArrayList<String[]> rows, boolean user) {
        TextView d = findViewById(R.id.use);

        if(user){
            d.setText("Administrador");
            String[] header ={"  id  ","  name  ","  lastname  ","  age  ",""};
            headers = null;
            headers =header;
            rowss = rows;
            createHeaders();
            createDataTables();
        }else{
            d.setText("Comun");
            String[] header ={"  id  ","  Nombre  ","  Periodo  ","  Codigo  ",""};
            headers = null;
            headers = header;
            rowss = rows;
            createHeaders();
            createDataTables();
        }
    }

    @Override
    public void showMessageError() {
        Toast.makeText(this,"Error con el servioio, contacte a soporte",Toast.LENGTH_LONG).show();
    }

    private void newRow(){
        tableRows = new TableRow(getApplicationContext());
    }

    private void newCell(){
        txtCells = new TextView(getApplicationContext());
        txtCells.setGravity(Gravity.CENTER);
        txtCells.setTextSize(15);
    }

    private void createHeaders(){
        indexCs = 0;
        newRow();
        while (indexCs < headers.length){
            newCell();
            txtCells.setText(headers[indexCs++]);
            tableRows.addView(txtCells, newTableRowParamss());
        }
        tableLayouts.addView(tableRows);
    }

    private void createDataTables(){
        String info;
        for(indexRs=1;indexRs<rowss.size();indexRs++){
            newRow();
            for(indexCs=0;indexCs<headers.length;indexCs++){
                if(headers.length-1 == indexCs){
                    final int id = Integer.parseInt(rowss.get(indexRs-1)[0]);
                    newCell();
                    Button x = new Button(getApplicationContext());
                    x.setId(id);
                    x.setText("Ver");
                    final String idx = rowss.get(indexRs-1)[0];
                    final String name = rowss.get(indexRs-1)[1];
                    final String last = rowss.get(indexRs-1)[2];
                    final String age=rowss.get(indexRs-1)[3];
                    x.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(HomeActivity.this);
                            alerta.setMessage("Nombre: "+name+"\nApellido: "+last+"\nEdad: "+age);
                            alerta.setCancelable(false);
                            alerta.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            AlertDialog titlulo = alerta.create();
                            titlulo.show();
                        }
                    });
                    tableRows.addView(x,newTableRowParamss());
                }else{
                    newCell();
                    String[] columns= rowss.get(indexRs-1);
                    info=(indexCs<columns.length)?columns[indexCs]:"";
                    txtCells.setText(info);
                    tableRows.addView(txtCells, newTableRowParamss());
                }
            }

            tableLayouts.addView(tableRows);
        }
    }

    private TableRow.LayoutParams newTableRowParamss(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }
}

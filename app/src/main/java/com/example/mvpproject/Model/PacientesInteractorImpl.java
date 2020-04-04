package com.example.mvpproject.Model;

import android.os.Handler;

import com.example.mvpproject.Resources.ConnectionApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

public class PacientesInteractorImpl implements PacientesInteractor {

    private ConnectionApi API_REST = new ConnectionApi();
    private static final Logger logger = Logger.getLogger(PacientesInteractorImpl.class.getName());
    private ArrayList<Paciente> rows = new ArrayList<>();

    @Override
    public void getDataRequest(final String token, final int id, final onRequestListener listener) {
        logger.log(Level.SEVERE,"--------Que paso.......");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                API_REST.getPacientes(token, id, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                        try {
                            for(int i = 0; i< responseBody.length();i++){
                                JSONObject dato = responseBody.getJSONObject(i);
                                String nome = dato.getString("nombre");
                                String apm = dato.getString("apellidom");
                                String app = dato.getString("apellidop");
                                rows.add(new Paciente(nome,app,apm,dato.getString("fecha_naci"),Integer.parseInt(dato.getString("id")),token,String.valueOf(id)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listener.onSucessRequest(rows);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable error, JSONArray responseBody) {
                        listener.onBadRequest();
                    }
                });
            }
        },1000);
    }

    @Override
    public void createPaciente(final String token, final Paciente paciente, final int id, final onRequestListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams();
                params.put("nombre",paciente.getNombre());
                params.put("apellidom",paciente.getApellidoMaterno());
                params.put("apellidop",paciente.getApellidoPaterno());
                params.put("fecha_naci",paciente.getFecha());
                params.put("user_id",id);
                API_REST.createPaciente(token,params,new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        listener.onCreated();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        logger.log(Level.SEVERE, "error de conecxion");
                    }
                });
            }
        },1000);
    }

    @Override
    public void deletePaciente(String token, int id) {
        API_REST.deletePaciente("ndd",id, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONArray responseBody) {
                logger.log(Level.INFO, "Falle al borrar");
            }
        });
    }

    @Override
    public void updatePaciente(String token, int id, Paciente paciente) {
        RequestParams params = new RequestParams();
        params.put("nombre",paciente.getNombre());
        params.put("apellidom",paciente.getApellidoMaterno());
        params.put("apellidop",paciente.getApellidoPaterno());
        params.put("fecha_naci",paciente.getFecha());
        API_REST.update(token,id,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                logger.log(Level.SEVERE, "error de conecxion");
            }
        });
    }
}

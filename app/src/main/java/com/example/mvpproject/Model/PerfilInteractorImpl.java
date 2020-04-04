package com.example.mvpproject.Model;

import com.example.mvpproject.Resources.ConnectionApi;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class PerfilInteractorImpl implements PerfilInteractor {

    ConnectionApi API_REST = new ConnectionApi();

    @Override
    public void getDataRequest(String token, int id, final PerfilInteractor.onRequestListener listener) {
        API_REST.getInfo(token, id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    JSONObject object = new JSONObject(new String(responseBody));
                    int id = Integer.parseInt(object.getString("id"));
                    String nombre = object.getString("nombre");
                    String apellidop = object.getString("apellidop");
                    String apellidom = object.getString("apellidom");
                    String especialidad = object.getString("especialidad");
                    CurrentUser user = new CurrentUser(id,nombre,apellidop,apellidom,especialidad,null,null);
                    listener.onSucessRequest(user);
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}

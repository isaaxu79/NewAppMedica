package com.example.mvpproject.Resources;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ConnectionApi {
    private AsyncHttpClient client = new AsyncHttpClient();
    private final String BASE_URL= "http://androback.ddns.net";
    private final String VERSION = "/api/v1";
    private final String LOGIN = "/login";
    private final String PROFILE = "/profile";
    private final String PACIENTES = "/paciente";
    private final String MIS_PACIENTES = "/mispacientes/";

    public void login(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
        this.client.post(BASE_URL+LOGIN,params,asyncHttpResponseHandler);
    }

    public void getPacientes(String token, int id, JsonHttpResponseHandler jsonHttpResponseHandler){
        client.addHeader("Authorization", "token "+token);
        client.get(BASE_URL+VERSION+MIS_PACIENTES+id,null, jsonHttpResponseHandler);
    }

    public void deletePaciente(String token, int id ,JsonHttpResponseHandler jsonHttpResponseHandler){
        client.addHeader("Authorization", "token "+token);
        client.delete(BASE_URL+VERSION+PACIENTES+"/"+id,null, jsonHttpResponseHandler);
    }

    public void createPaciente(String token, RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.addHeader("Authorization", "token "+token);
        client.post(BASE_URL+VERSION+PACIENTES,params, asyncHttpResponseHandler);
    }

    public void update(String token, int id, RequestParams params,AsyncHttpResponseHandler asyncHttpResponseHandler) {
        client.addHeader("Authorization", "token "+token);
        client.put(BASE_URL+VERSION+PACIENTES+"/"+id,params, asyncHttpResponseHandler);
    }

    public void getInfo(String token, int id,AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.addHeader("Authorization", "token "+token);
        client.get(BASE_URL+VERSION+PROFILE+"/"+id, null, asyncHttpResponseHandler);
    }
}

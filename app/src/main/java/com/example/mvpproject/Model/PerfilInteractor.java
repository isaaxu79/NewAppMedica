package com.example.mvpproject.Model;

public interface PerfilInteractor {

    interface onRequestListener{

        void onBadRequest();

        void onSucessRequest(CurrentUser user);

    }

    void getDataRequest(String token, int id, PerfilInteractor.onRequestListener listener);

}

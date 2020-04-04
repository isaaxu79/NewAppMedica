package com.example.mvpproject.Presenter;

import com.example.mvpproject.Model.CurrentUser;
import com.example.mvpproject.Model.PacientesInteractor;
import com.example.mvpproject.Model.PerfilInteractor;
import com.example.mvpproject.View.PerfilView;

public class PerfilPresenterImpl implements PerfilPresenter, PerfilInteractor.onRequestListener {

    private PerfilView perfilView;
    private PerfilInteractor perfilInteractor;

    public PerfilPresenterImpl(PerfilView perfilView, PerfilInteractor perfilInteractor) {
        this.perfilView = perfilView;
        this.perfilInteractor = perfilInteractor;
    }

    @Override
    public void onBadRequest() {
        if(perfilView != null){
            perfilView.showMessageError();
        }

    }

    @Override
    public void onSucessRequest(CurrentUser user) {
        if(perfilView != null){
            perfilView.fillProfile(user);
        }
    }

    @Override
    public void getInfo(String token, int id) {
        perfilInteractor.getDataRequest(token, id, this);
    }

    @Override
    public void onDestroy() {
        perfilView = null;
    }
}

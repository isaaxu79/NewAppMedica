package com.example.mvpproject.Presenter;

import com.example.mvpproject.Model.Paciente;
import com.example.mvpproject.Model.PacientesInteractor;
import com.example.mvpproject.View.PacientesView;

import java.util.ArrayList;

public class PacientesPresenterImpl implements PacientesPresenter, PacientesInteractor.onRequestListener {

    private PacientesView pacientesView;
    private PacientesInteractor pacientesInteractor;

    public PacientesPresenterImpl(PacientesView pacientesView, PacientesInteractor pacientesInteractor) {
        this.pacientesView = pacientesView;
        this.pacientesInteractor = pacientesInteractor;
    }

    @Override
    public void onBadRequest() {
        if(pacientesView != null) {
            pacientesView.showMessageError();
        }

    }

    @Override
    public void onSucessRequest(ArrayList<Paciente> rows) {
        if (pacientesView != null){
            pacientesView.showPacientes(rows);
        }

    }

    @Override
    public void onCreated() {
        if(pacientesView != null){
            pacientesView.createdPaciente();
        }
    }

    @Override
    public void getInfo(String token,int id) {
        pacientesInteractor.getDataRequest(token, id,this);

    }

    @Override
    public void createPaciente(String token, Paciente paciente, int id) {
        pacientesInteractor.createPaciente(token,paciente,id,this);
    }

    @Override
    public void onDestroy() {
        pacientesView = null;
    }
}

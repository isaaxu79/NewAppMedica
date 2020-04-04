package com.example.mvpproject.Presenter;

import com.example.mvpproject.Model.Paciente;

public interface PacientesPresenter {

    void getInfo(String token,int id);

    void createPaciente(String token, Paciente paciente, int idDoctor);

    void onDestroy();
}

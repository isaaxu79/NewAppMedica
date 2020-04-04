package com.example.mvpproject.Model;

import java.util.ArrayList;

public interface PacientesInteractor {
    interface onRequestListener{

        void onBadRequest();

        void onSucessRequest(ArrayList<Paciente> rows);

        void onCreated();
    }

    void getDataRequest(String token, int id, onRequestListener listener);

    void createPaciente(String token, Paciente paciente, int id, onRequestListener listener);

    void deletePaciente(String token, int id);

    void updatePaciente(String token, int id, Paciente paciente);
}

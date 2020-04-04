package com.example.mvpproject.View;

import com.example.mvpproject.Model.Paciente;

import java.util.ArrayList;

public interface PacientesView {

    void showPacientes(ArrayList<Paciente> rows);

    void showMessageError();

    void createdPaciente();
}

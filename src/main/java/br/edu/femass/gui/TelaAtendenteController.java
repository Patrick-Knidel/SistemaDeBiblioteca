package br.edu.femass.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaAtendenteController implements Initializable {

    @FXML
    public void btnProfessor(ActionEvent event){
        TelaInicialController.trocarTelaBibliotecario(5);
    }
    @FXML
    public void btnAluno(ActionEvent event){
        TelaInicialController.trocarTelaBibliotecario(6);
    }
    @FXML
    public void btnEmprestimo(ActionEvent event){
        TelaInicialController.trocarTelaBibliotecario(7);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}

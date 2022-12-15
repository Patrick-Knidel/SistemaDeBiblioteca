package br.edu.femass.gui;

import br.edu.femass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaBibliotecarioController implements Initializable{

    @FXML
    public void btnLivros(ActionEvent event){
        TelaInicialController.trocarTelaBibliotecario(1);
    }
    @FXML
    public void btnAutores(ActionEvent event){
        TelaInicialController.trocarTelaBibliotecario(2);
    }
    @FXML
    public void btnLeitoresAtraso(ActionEvent event){
        TelaInicialController.trocarTelaBibliotecario(4);
    }
    @FXML
    public void btnExemplares(ActionEvent event){
        TelaInicialController.trocarTelaBibliotecario(3);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}

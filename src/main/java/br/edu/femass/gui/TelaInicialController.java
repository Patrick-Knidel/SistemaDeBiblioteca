package br.edu.femass.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

    public static Scene sceneLivro;
    public static Scene sceneAutor;
    public static Scene sceneExemplar;
    public static Scene sceneLeitores;
    public static Scene sceneProfessor;
    public static Scene sceneAluno;
    public static Scene sceneEmprestimo;
    public static Scene sceneDevolucao;
    public static Stage primaryStage;

    @FXML
    private void btnBibliotecario(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Tela-Bibliotecario.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Bibliotecario");
            stage.setScene(scene);
            stage.show();
            primaryStage = stage;
            FXMLLoader bibliotecarioLivro = new FXMLLoader (getClass().getResource("/fxml/Tela-BibliotecarioLivro.fxml"));
            FXMLLoader bibliotecarioAutor = new FXMLLoader (getClass().getResource("/fxml/Tela-BibliotecarioAutor.fxml"));
            FXMLLoader bibliotecarioExemplar = new FXMLLoader (getClass().getResource("/fxml/Tela-BibliotecarioExemplares.fxml"));
            FXMLLoader bibliotecarioLeitores = new FXMLLoader (getClass().getResource("/fxml/Tela-BibliotecarioLeitores.fxml"));


            Parent parentBibliotecarioLivro = bibliotecarioLivro.load();
            Parent parentBibliotecarioAutor = bibliotecarioAutor.load();
            Parent parentBibliotecarioExemplar = bibliotecarioExemplar.load();
            Parent parentBibliotecarioLeitores = bibliotecarioLeitores.load();


            sceneLivro = new Scene(parentBibliotecarioLivro);
            sceneAutor = new Scene(parentBibliotecarioAutor);
            sceneExemplar = new Scene(parentBibliotecarioExemplar);
            sceneLeitores = new Scene(parentBibliotecarioLeitores);

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void trocarTelaBibliotecario(int opcao){
        switch (opcao){
            case 1:
                primaryStage.setScene(sceneLivro);
                primaryStage.show();
                break;

            case 2:
                primaryStage.setScene(sceneAutor);
                primaryStage.show();
                break;

            case 3:
                primaryStage.setScene(sceneExemplar);
                primaryStage.show();
                break;

            case 4:
                primaryStage.setScene(sceneLeitores);
                primaryStage.show();
                break;

            case 5:
                primaryStage.setScene(sceneProfessor);
                primaryStage.show();
                break;

            case 6:
                primaryStage.setScene(sceneAluno);
                primaryStage.show();
                break;

            case 7:
                primaryStage.setScene(sceneEmprestimo);
                primaryStage.show();
                break;

        }
    }

    @FXML
    private void btnAtendente(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Tela-Atendente.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Atendente");
            stage.setScene(scene);
            stage.show();
            primaryStage = stage;
            FXMLLoader atendenteProfessor = new FXMLLoader (getClass().getResource("/fxml/Tela-AtendenteProfessor.fxml"));
            FXMLLoader atendenteAluno = new FXMLLoader (getClass().getResource("/fxml/Tela-AtendenteAluno.fxml"));
            FXMLLoader atendenteEmprestimo = new FXMLLoader (getClass().getResource("/fxml/Tela-AtendenteEmprestimos.fxml"));

            Parent parentAtendenteProfessor = atendenteProfessor.load();
            Parent parentAtendenteAluno = atendenteAluno.load();
            Parent parentAtendenteEmprestimo = atendenteEmprestimo.load();

            sceneProfessor = new Scene(parentAtendenteProfessor);
            sceneAluno = new Scene(parentAtendenteAluno);
            sceneEmprestimo = new Scene(parentAtendenteEmprestimo);

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}

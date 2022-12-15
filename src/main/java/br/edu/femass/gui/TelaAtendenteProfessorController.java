package br.edu.femass.gui;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TelaAtendenteProfessorController implements Initializable {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtDisciplina;
    @FXML
    private TableView<Professor> tblLeitor = new TableView<>();
    @FXML
    private TableColumn<Professor,String> colNome = new TableColumn<>();
    @FXML
    private TableColumn<Professor,String> colEndereco = new TableColumn<>();
    @FXML
    private TableColumn<Professor,String> colTelefone = new TableColumn<>();
    @FXML
    private TableColumn<Professor,String> colDisciplina = new TableColumn<>();
    private Boolean incluindo;
    private Professor professor;
    private DaoProfessor dao = new DaoProfessor();

    @FXML
    private void btnInserir(ActionEvent event){
        editar(true);

        incluindo = true;

        professor = new Professor();
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtDisciplina.setText("");
        txtNome.requestFocus();
        preencherTabela();
    }
    @FXML
    private void btnAlterar(ActionEvent event){
        editar(true);

        incluindo = false;
    }
    @FXML
    private void btnExcluir(ActionEvent event){
        dao.apagar(professor);
        preencherTabela();
    }
    @FXML
    private void btnAdicionar(ActionEvent event){
        professor.setNome(txtNome.getText());
        professor.setEndereco(txtEndereco.getText());
        professor.setTelefone(txtTelefone.getText());
        professor.setDisciplina(txtDisciplina.getText());
        professor.setPrazoMaximoDevolucao(professor.getPrazoMaximoDevolucao());

        if (incluindo) {
            dao.inserir(professor);
        } else {
            dao.alterar(professor);
        }

        preencherTabela();
        editar(false);
    }
    private void editar(boolean habilitar) {
        tblLeitor.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtEndereco.setDisable(!habilitar);
        txtTelefone.setDisable(!habilitar);
        txtDisciplina.setDisable(!habilitar);
    }
    private void exibirDados(){
        this.professor = tblLeitor.getSelectionModel().getSelectedItem();

        if(professor==null) return;

        txtNome.setText(professor.getNome());
        txtEndereco.setText(professor.getEndereco());
        txtTelefone.setText(professor.getTelefone());
        txtDisciplina.setText(professor.getDisciplina());
    }
    public void preencherTabela(){
        List<Professor> professores = dao.buscarTodosPorId();
        ObservableList<Professor> data = FXCollections.observableArrayList(professores);
        tblLeitor.setItems(data);
        tblLeitor.refresh();
    }

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
        colNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<Professor, String>("endereco"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<Professor, String>("telefone"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<Professor, String>("disciplina"));
        txtNome.setDisable(true);
        txtEndereco.setDisable(true);
        txtTelefone.setDisable(true);
        txtDisciplina.setDisable(true);
        preencherTabela();
    }
    public void ListarAutoresOKP(javafx.scene.input.KeyEvent keyEvent) {
        exibirDados();
    }

    public void ListarAutoresOMC(javafx.scene.input.MouseEvent mouseEvent) {
        exibirDados();
    }
}

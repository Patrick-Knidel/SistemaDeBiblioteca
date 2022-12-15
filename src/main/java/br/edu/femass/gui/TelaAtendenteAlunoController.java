package br.edu.femass.gui;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TelaAtendenteAlunoController implements Initializable {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtMatricula;
    @FXML
    private TableView<Aluno> tblAluno = new TableView<>();
    @FXML
    private TableColumn<Aluno,String> colNome = new TableColumn<>();
    @FXML
    private TableColumn<Aluno,String> colEndereco = new TableColumn<>();
    @FXML
    private TableColumn<Aluno,String> colTelefone = new TableColumn<>();
    @FXML
    private TableColumn<Aluno,String> colMatricula = new TableColumn<>();
    private Boolean incluindo;
    private Aluno aluno;
    private DaoAluno dao = new DaoAluno();

    @FXML
    private void btnInserir(ActionEvent event){
        editar(true);

        incluindo = true;

        aluno = new Aluno();
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtMatricula.setText("");
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
        dao.apagar(aluno);
        preencherTabela();
    }
    @FXML
    private void btnAdicionar(ActionEvent event){
        aluno.setNome(txtNome.getText());
        aluno.setEndereco(txtEndereco.getText());
        aluno.setTelefone(txtTelefone.getText());
        aluno.setMatricula(txtMatricula.getText());
        aluno.setPrazoMaximoDevolucao(aluno.getPrazoMaximoDevolucao());

        if (incluindo) {
            dao.inserir(aluno);
        } else {
            dao.alterar(aluno);
        }

        preencherTabela();
        editar(false);
    }
    private void editar(boolean habilitar) {
        tblAluno.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtEndereco.setDisable(!habilitar);
        txtTelefone.setDisable(!habilitar);
        txtMatricula.setDisable(!habilitar);
    }
    private void exibirDados(){
        this.aluno = tblAluno.getSelectionModel().getSelectedItem();

        if(aluno==null) return;

        txtNome.setText(aluno.getNome());
        txtEndereco.setText(aluno.getEndereco());
        txtTelefone.setText(aluno.getTelefone());
        txtMatricula.setText(aluno.getMatricula());
    }
    public void preencherTabela(){
        List<Aluno> alunos = dao.buscarTodosPorId();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tblAluno.setItems(data);
        tblAluno.refresh();
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
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<Aluno, String>("endereco"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<Aluno, String>("telefone"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));
        txtNome.setDisable(true);
        txtEndereco.setDisable(true);
        txtTelefone.setDisable(true);
        txtMatricula.setDisable(true);
        preencherTabela();
    }
    public void ListarAutoresOKP(javafx.scene.input.KeyEvent keyEvent) {
        exibirDados();
    }

    public void ListarAutoresOMC(javafx.scene.input.MouseEvent mouseEvent) {
        exibirDados();
    }
}

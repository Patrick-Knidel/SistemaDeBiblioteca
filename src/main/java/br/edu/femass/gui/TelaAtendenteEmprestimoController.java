package br.edu.femass.gui;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.bytebuddy.asm.Advice;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class TelaAtendenteEmprestimoController implements Initializable {
    @FXML
    private ComboBox<Exemplar> cboLivro;
    @FXML
    private ComboBox<Leitor> cboLeitor;
    @FXML
    private TableView<Emprestimo> tblEmprestimo = new TableView<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colEmprestimo = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDevolucao = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDevolucao1 = new TableColumn<>();
    @FXML
    private TableColumn<Exemplar, String> colLivro = new TableColumn<Exemplar, String>();
    @FXML
    private TableColumn<Leitor, String> colLeitor = new TableColumn<Leitor, String>();
    private Boolean incluindo;
    private DaoEmprestimo dao = new DaoEmprestimo();
    private DaoExemplar daoExemplar = new DaoExemplar();
    private DaoLeitor daoLeitor = new DaoLeitor();
    private Emprestimo emprestimo;
    private Leitor leitor;
    private Exemplar exemplar;
    @FXML
    private void btnInserir(ActionEvent event){
        editar(true);

        incluindo = true;

        emprestimo = new Emprestimo();
        cboLivro.setItems(null);
        cboLeitor.setItems(null);
        cboLivro.requestFocus();
        preencherCboLeitor();
        preencherCboExemplar();
        preencherTabela();
    }
    @FXML
    private void btnAlterar(ActionEvent event){
        editar(true);

        incluindo = false;
        preencherCboLeitor();
        preencherCboExemplar();
    }
    @FXML
    private void btnExcluir(ActionEvent event){
        dao.apagar(emprestimo);
        preencherCboLeitor();
        preencherCboExemplar();
        preencherTabela();
    }
    @FXML
    private void btnDevolucao(ActionEvent event){
        emprestimo = tblEmprestimo.getSelectionModel().getSelectedItem();
        emprestimo.setDataDevolucao(LocalDate.now());
        dao.alterar(emprestimo);
        preencherTabela();
    }
    @FXML
    private void btnAdicionar(ActionEvent event){
        emprestimo.setLeitor(cboLeitor.getSelectionModel().getSelectedItem());
        emprestimo.setExemplar(cboLivro.getSelectionModel().getSelectedItem());
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevisaoDevolucao(emprestimo.getLeitor().getPrazoMaximoDevolucao());



        if (incluindo) {
            dao.inserir(emprestimo);
        } else {
            dao.alterar(emprestimo);
        }
        preencherCboLeitor();
        preencherCboExemplar();
        preencherTabela();
        editar(false);
    }
    private void editar(boolean habilitar) {
        tblEmprestimo.setDisable(habilitar);
        cboLivro.setDisable(!habilitar);
        cboLeitor.setDisable(!habilitar);
    }
    private void exibirDados(){
        this.emprestimo = tblEmprestimo.getSelectionModel().getSelectedItem();

        if(emprestimo==null) return;

        cboLivro.setItems(cboLivro.getItems());
        cboLeitor.setItems(cboLeitor.getItems());
    }
    public void preencherTabela(){
        List<Emprestimo> emprestimos = dao.buscarTodosPorId();
        ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimos);
        tblEmprestimo.setItems(data);

        tblEmprestimo.refresh();
    }
    public void preencherCboExemplar(){
        List<Exemplar> exemplares = daoExemplar.buscarTodosPorId();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        cboLivro.setItems(data);
    }
    public void preencherCboLeitor(){
        List<Leitor> leitores = daoLeitor.buscarTodosPorId();
        ObservableList<Leitor> data = FXCollections.observableArrayList(leitores);
        cboLeitor.setItems(data);
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
        colEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo"));
        colDevolucao1.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("DataDevolucao"));
        colDevolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevisaoDevolucao"));
        colLivro.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("exemplar"));
        colLeitor.setCellValueFactory(new PropertyValueFactory<Leitor, String>("leitor"));
        cboLivro.setDisable(true);
        cboLeitor.setDisable(true);
        preencherCboExemplar();
        preencherCboLeitor();
        preencherTabela();
    }
    public void ListarAutoresOKP(javafx.scene.input.KeyEvent keyEvent) {
        exibirDados();
    }

    public void ListarAutoresOMC(javafx.scene.input.MouseEvent mouseEvent) {
        exibirDados();
    }
}

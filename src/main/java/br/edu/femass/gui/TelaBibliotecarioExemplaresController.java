package br.edu.femass.gui;

import br.edu.femass.Main;
import br.edu.femass.dao.*;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class TelaBibliotecarioExemplaresController implements Initializable {
    private Boolean incluindo;
    private Exemplar exemplar;
    private Livro livro;
    private Autor autor;
    private DaoExemplar dao = new DaoExemplar();
    private DaoLivro daoLivro = new DaoLivro();
    @FXML
    private ComboBox<Livro> cboLivros;
    @FXML
    private TableView<Exemplar> tblExemplares = new TableView<>();
    @FXML
    private TableColumn<Exemplar, Long> colCodigo = new TableColumn<>();
    @FXML
    private TableColumn<Exemplar, LocalDate> colAquisicao = new TableColumn<>();
    @FXML
    private TableColumn<Livro, String> colLivro = new TableColumn<>();
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAdicionar;

    @FXML
    private void btnInserir(ActionEvent event){
        editar(true);

        incluindo = true;

        exemplar = new Exemplar();
        cboLivros.setItems(null);
        cboLivros.requestFocus();

        preencherTabela();
    }
    @FXML
    private void btnAlterar(ActionEvent event){
        editar(true);

        incluindo = false;
    }
    @FXML
    private void btnExcluir(ActionEvent event){
        dao.apagar(exemplar);
        preencherTabela();
    }
    @FXML
    public void btnAdicionar(ActionEvent event){
        exemplar.setDataAquisicao(LocalDate.now());
        exemplar.setLivro(cboLivros.getSelectionModel().getSelectedItem());

        if (incluindo) {
            dao.inserir(exemplar);
        } else {
            dao.alterar(exemplar);
        }

        preencherTabela();
        editar(false);
    }
    private void editar(boolean habilitar) {
        tblExemplares.setDisable(habilitar);
        cboLivros.setDisable(!habilitar);
    }
    private void exibirDados(){
        this.exemplar = tblExemplares.getSelectionModel().getSelectedItem();

        if(exemplar==null) return;

        cboLivros.setItems(cboLivros.getItems());

    }
    public void preencherTabela(){
        List<Exemplar> exemplares = dao.buscarTodosPorId();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tblExemplares.setItems(data);

        List<Livro> livros = daoLivro.buscarTodosPorId();
        ObservableList<Livro> data1 = FXCollections.observableArrayList(livros);
        cboLivros.setItems(data1);
        tblExemplares.refresh();
    }
    public void preencherCboLivro(){
        List<Livro> livros = daoLivro.buscarTodosPorId();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        cboLivros.setItems(data);
    }

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

    public void initialize(URL url, ResourceBundle rb){
        colCodigo.setCellValueFactory(new PropertyValueFactory<Exemplar, Long>("id"));
        colAquisicao.setCellValueFactory(new PropertyValueFactory<Exemplar, LocalDate>("dataAquisicao"));
        colLivro.setCellValueFactory(new PropertyValueFactory<Livro, String>("livro"));

        preencherTabela();
        preencherCboLivro();
        cboLivros.setDisable(true);
    }

    public void ListarExemplaresOKP(KeyEvent keyEvent) {
        exibirDados();
    }

    public void ListarExemplaresOMC(MouseEvent mouseEvent) {
        exibirDados();
    }
}

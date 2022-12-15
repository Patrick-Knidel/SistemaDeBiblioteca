package br.edu.femass.gui;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TelaBibliotecarioLivroController implements Initializable{
    private DaoLivro dao = new DaoLivro();
    private DaoAutor daoAutor = new DaoAutor();
    private Livro livro;
    private Boolean incluindo;
    @FXML
    private TableColumn<Livro, Long> colCodigo = new TableColumn<>();

    @FXML
    private TableColumn<Livro, String> colTitulo = new TableColumn<>();
    @FXML
    private TableColumn<Livro, String> colAutor = new TableColumn<>();
    @FXML
    private TextField txtTitulo;
    @FXML
    private TableColumn<Livro, String> colAno = new TableColumn<>();
    @FXML
    private TableView<Livro> tblLivro = new TableView<>();
    @FXML
    private TextField txtAno;
    @FXML
    private ComboBox<Autor> cboAutor;
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

        livro = new Livro();
        txtTitulo.setText("");
        txtAno.setText("");
        cboAutor.setItems(null);
        txtTitulo.requestFocus();

        preencherTabela();
        preencherCboAutor();
    }
    @FXML
    private void btnAlterar(ActionEvent event){
        editar(true);

        incluindo = false;
    }
    @FXML
    private void btnExcluir(ActionEvent event){
        dao.apagar(livro);
        preencherTabela();
        preencherCboAutor();
    }
    @FXML
    public void btnAdicionar(ActionEvent event){
        livro.setTitulo(txtTitulo.getText());
        livro.setAutor(cboAutor.getSelectionModel().getSelectedItem());
        livro.setAno(txtAno.getText());


        if (incluindo) {
            dao.inserir(livro);
        } else {
            dao.alterar(livro);
        }

        preencherTabela();
        preencherCboAutor();
        editar(false);
    }
    private void editar(boolean habilitar) {
        tblLivro.setDisable(habilitar);
        txtTitulo.setDisable(!habilitar);
        txtAno.setDisable(!habilitar);
        cboAutor.setDisable(!habilitar);

    }
    private void exibirDados(){
        this.livro = tblLivro.getSelectionModel().getSelectedItem();

        if(livro==null) return;

        txtTitulo.setText(livro.getTitulo());
        cboAutor.setItems(cboAutor.getItems());
        txtAno.setText(livro.getAno());

    }
    public void preencherTabela(){
        List<Livro> livros = dao.buscarTodosPorId();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tblLivro.setItems(data);
        tblLivro.refresh();
    }

    public void preencherCboAutor(){
        List<Autor> autores = daoAutor.buscarTodosPorId();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        cboAutor.setItems(data);

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
        colCodigo.setCellValueFactory(new PropertyValueFactory<Livro, Long>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        colAno.setCellValueFactory(new PropertyValueFactory<Livro, String>("ano"));
        preencherTabela();
        preencherCboAutor();
        txtTitulo.setDisable(true);
        txtAno.setDisable(true);
        cboAutor.setDisable(true);
    }
    public void ListarAutoresOKP(javafx.scene.input.KeyEvent keyEvent) {
        exibirDados();
    }
    public void ListarAutoresOMC(javafx.scene.input.MouseEvent mouseEvent) {
        exibirDados();
    }

}

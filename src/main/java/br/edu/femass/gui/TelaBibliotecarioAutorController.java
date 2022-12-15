package br.edu.femass.gui;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TelaBibliotecarioAutorController implements Initializable{
    private DaoAutor dao = new DaoAutor();
    private Autor autor;
    private Boolean incluindo;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtNacionalidade;

    @FXML
    private TableColumn<Autor, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> colNacionalidade = new TableColumn<>();

    @FXML
    private TableColumn<Autor, Long> colCodigo = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> colSobrenome = new TableColumn<>();

    @FXML
    private TableView<Autor> tblAutor = new TableView<Autor>();

    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    @FXML
    private void btnInserir(ActionEvent event){
        editar(true);

        incluindo = true;

        autor = new Autor();
        txtNome.setText("");
        txtSobrenome.setText("");
        txtNacionalidade.setText("");
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
        dao.apagar(autor);
        preencherTabela();
    }

    @FXML
    public void btnAdicionar(ActionEvent event){
        autor.setNome(txtNome.getText());
        autor.setSobreNome(txtSobrenome.getText());
        autor.setNacionalidade(txtNacionalidade.getText());

        if (incluindo) {
            dao.inserir(autor);
        } else {
            dao.alterar(autor);
        }

        preencherTabela();
        editar(false);
    }
    private void editar(boolean habilitar) {
        tblAutor.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtSobrenome.setDisable(!habilitar);
        txtNacionalidade.setDisable(!habilitar);

    }
    private void exibirDados(){
        this.autor = tblAutor.getSelectionModel().getSelectedItem();

        if(autor==null) return;

        txtNome.setText(autor.getNome());
        txtSobrenome.setText(autor.getSobreNome());
        txtNacionalidade.setText(autor.getNacionalidade());
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

    public void preencherTabela(){
        List<Autor> autores = dao.buscarTodosPorId();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        tblAutor.setItems(data);
        tblAutor.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        colCodigo.setCellValueFactory(new PropertyValueFactory<Autor, Long>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<Autor, String>("nome"));
        colSobrenome.setCellValueFactory(new PropertyValueFactory<Autor, String>("sobreNome"));
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<Autor, String>("nacionalidade"));
        preencherTabela();
        txtNome.setDisable(true);
        txtNacionalidade.setDisable(true);
        txtSobrenome.setDisable(true);
    }

    public void ListarAutoresOKP(javafx.scene.input.KeyEvent keyEvent) {
        exibirDados();
    }

    public void ListarAutoresOMC(javafx.scene.input.MouseEvent mouseEvent) {
        exibirDados();
    }
}


package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Mário
 */
public class PesquisaController implements Initializable {


    @FXML
    private TextField pesquisaTextField;
    @FXML
    private SplitMenuButton menuTipoLocal;
    @FXML
    private TextField distanciaTextField;
    @FXML
    private TextField horaInicTextField;
    @FXML
    private TextField horaFinalTextField;
    @FXML
    private TextField generoTextField;
    @FXML
    private TextField entradaTextField;
    @FXML
    private TableView<Estabelecimento> pesquisaTableView;
    @FXML
    private TableColumn<Estabelecimento, String> nomeColumn;
    @FXML
    private TableColumn<Estabelecimento, String> tipoColumn;
    @FXML
    private TableColumn<Estabelecimento, Double> distanciaColumn;
    @FXML
    private TableColumn<Estabelecimento, String> horaAbreColumn;
    @FXML
    private TableColumn<Estabelecimento, String> horaFechaColumn;
    @FXML
    private TableColumn<Estabelecimento, String> generoColumn;
    @FXML
    private TableColumn<Estabelecimento, String> entradaColumn;
    
    static ObservableList<Estabelecimento> estabelecimentos;
    static Estabelecimento estabelecimento;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estabelecimento = new Estabelecimento();
        this.nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.distanciaColumn.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        this.horaAbreColumn.setCellValueFactory(new PropertyValueFactory<>("horaAbertura"));
        this.horaFechaColumn.setCellValueFactory(new PropertyValueFactory<>("horaFechamento"));
        this.generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        this.entradaColumn.setCellValueFactory(new PropertyValueFactory<>("entrada"));
        
        this.estabelecimentos = pesquisaTableView.getItems();
        
        atualizarLista();
    }    
    
    void atualizarLista() {
        estabelecimentos.clear();
        estabelecimentos.addAll(new EstabelecimentoDAO().getAll());
        pesquisaTableView.refresh();
        
    }
    
    @FXML
    private void sair() throws IOException {
        App.setRoot("login");
    }
    
    @FXML
    private void pesquisa() {
        String searchNome = pesquisaTextField.getText();
        String searchTipo = menuTipoLocal.getText();
        String searchHoraAbertura = horaInicTextField.getText();
        String searchHoraFechamento = horaFinalTextField.getText();
        Double searchDistancia = Double.parseDouble(distanciaTextField.getText());
        String searchGenero = generoTextField.getText();
        Double searchEntrada = Double.parseDouble(entradaTextField.getText());
        
        if (searchTipo.equals("Qualquer Tipo")){
            searchTipo = "";
        }
        
        System.out.println("nome: " + searchNome);
        System.out.println("tipo: " + searchTipo);
        System.out.println("abertura: " + searchHoraAbertura);
        System.out.println("fechamento: " + searchHoraFechamento);
        System.out.println("distancia: " + searchDistancia);
        System.out.println("genero: " + searchGenero);
        System.out.println("entrada: " + searchEntrada);
        
        estabelecimentos.clear();
        estabelecimentos.addAll(new EstabelecimentoDAO().pesquisa(
                                    searchNome, searchTipo, searchHoraAbertura, searchHoraFechamento, //
                                    searchDistancia, searchGenero, searchEntrada));
        pesquisaTableView.refresh();
    }
    
    @FXML
    private void debug(){
        System.out.println("nome: " + pesquisaTextField.getText());
        System.out.println("tipo: " + menuTipoLocal.getText());
        System.out.println("abertura: " + horaInicTextField.getText());
        System.out.println("fechamento: " + horaFinalTextField.getText());
        System.out.println("distancia: " + distanciaTextField.getText());
        System.out.println("genero: " + generoTextField.getText());
        System.out.println("entrada: " + entradaTextField.getText());
    }
    
    @FXML
    private void acessar() throws IOException {
        estabelecimento = pesquisaTableView.getSelectionModel().getSelectedItem();
        App.setRoot("paginaEstabelecimento");
    }
    
    @FXML
    private void setTipoBar(){
        menuTipoLocal.setText("Bar");
    }
    @FXML
    private void setTipoRestaurante(){
        menuTipoLocal.setText("Restaurante");
    }
    @FXML
    private void setTipoQualquer(){
        menuTipoLocal.setText("Qualquer Tipo");
    }

}

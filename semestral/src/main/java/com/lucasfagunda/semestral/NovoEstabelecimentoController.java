package com.lucasfagunda.semestral;

import static com.lucasfagunda.semestral.PesquisaController.estabelecimento;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.Time;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class NovoEstabelecimentoController implements Initializable {

    @FXML
    private TextField cnpjTextField;

    @FXML
    private TextField distanciaTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private TextField generoMusicalTextField;

    @FXML
    private TextField horaAberturaTextField;

    @FXML
    private TextField horaFechamentoTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField precoTextField;

    @FXML
    private TextField senhaTextField;

    @FXML
    private TextField telefoneTextField;
    
    @FXML
    private TextField tipoTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
     @FXML
    void salvar() throws IOException, Exception {
        AES aes = new AES();
        aes.init();
        estabelecimento = new Estabelecimento();
        estabelecimento.setId(String.valueOf(new Random().nextInt(999)));
        estabelecimento.setNome(nomeTextField.getText());
        estabelecimento.setEmail(emailTextField.getText());
        estabelecimento.setSenha(aes.encrypt(senhaTextField.getText()));
         System.out.println(estabelecimento.senha);
        estabelecimento.setEndereco(enderecoTextField.getText());
        estabelecimento.setHoraAbertura(horaAberturaTextField.getText());
        estabelecimento.setHoraFechamento(horaFechamentoTextField.getText());
        estabelecimento.setDistancia(Double.parseDouble(distanciaTextField.getText()));
        estabelecimento.setGenero(generoMusicalTextField.getText());
        estabelecimento.setCnpj(cnpjTextField.getText());
        estabelecimento.setFone(telefoneTextField.getText());
        estabelecimento.setEntrada(Double.parseDouble(precoTextField.getText()));
        estabelecimento.setTipo(tipoTextField.getText());
        
        new EstabelecimentoDAO().save(estabelecimento);
        
        App.setRoot("login");
    }

    @FXML
    void voltar() throws IOException {
        App.setRoot("login");
    }
    
}

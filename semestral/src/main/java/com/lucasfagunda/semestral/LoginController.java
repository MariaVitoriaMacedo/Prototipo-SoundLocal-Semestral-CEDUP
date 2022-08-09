
package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mário
 */
public class LoginController implements Initializable {
    
    @FXML
    TextField loginEmailTextField;
    
    @FXML
    PasswordField loginSenhaPasswordField;
    
    static Estabelecimento estabelecimentoAtivo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void login() throws IOException, SQLException{
        
        
        Estabelecimento EstabelecimentoLogin = new Estabelecimento();
        EstabelecimentoLogin.setEmail(loginEmailTextField.getText());
        EstabelecimentoLogin.setSenha(loginSenhaPasswordField.getText());
        
        if(new EstabelecimentoDAO().login(EstabelecimentoLogin) == true){
            estabelecimentoAtivo = new Estabelecimento();
            estabelecimentoAtivo.idAtivo = new EstabelecimentoDAO().getByLogin(EstabelecimentoLogin);
            System.out.println(estabelecimentoAtivo.idAtivo);
            App.setRoot("pesquisa");
        }else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Autenticação");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou senha inválidos");
            
            alert.showAndWait(); 
        }
    }
    
    @FXML
    void adminLogin() throws IOException{
        App.setRoot("pesquisa");
    }
    
    @FXML
    public void cadastrar() throws IOException {
        App.setRoot("novoEstabelecimento");
    }
    
    //Método chamado quando o usuário pressiona alguma tecla no teclado (em um TextField marcado com ele)
    @FXML
    void onUserTyped(){
        System.out.println("Tais digitando");
    }
    
}

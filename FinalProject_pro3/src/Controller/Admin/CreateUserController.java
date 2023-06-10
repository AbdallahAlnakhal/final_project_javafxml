/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;
import Model.User;
import View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {

    @FXML
    private Button saveNewUserBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField phoneTF;

    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup roleGroup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

  


    @FXML
    private void saveNewUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String email = emailTF.getText();
        String firstname = firstnameTF.getText();
        String lastname = lastnameTF.getText();
        String age = ageTF.getText();
        String phone = phoneTF.getText();
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton) roleGroup.getSelectedToggle()).getText();

        User user = new User(username, password, firstname, lastname, age, email, phone, gender, role);
        user.save();

       ViewManager.logpage.changeSceneToAccountsManagment();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User inserted");
        alert.setContentText("User inserted successfully!");
        alert.showAndWait();
    }

    @FXML
    private void cancelUserCreation(ActionEvent event) {
        ViewManager.logpage.changeSceneToAccountsManagment();
    }

}

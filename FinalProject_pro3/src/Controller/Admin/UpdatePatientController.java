/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;
import Model.User;
import javafx.scene.control.Alert;
import Model.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author MSI GL65
 */
public class UpdatePatientController implements Initializable {
  private User oldUser;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private RadioButton adminRadio;
    @FXML
    private Button UpdatePatientBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           //get selected user data from UsersManagmentController updatedUser var
        this.oldUser = Controller.Admin.UsersManagmentController.selectedUserToUpdate;
        
        //set text field's data the same as updatedUser data
        usernameTF.setText(oldUser.getUsername());
        emailTF.setText(oldUser.getEmail());
        passwordTF.setText(oldUser.getPassword());
         firstnameTF.setText(oldUser.getFirstname());
          lastnameTF.setText(oldUser.getLastname());
           ageTF.setText(oldUser.getAge());
            phoneTF.setText(oldUser.getPhone());
        
        if (oldUser.getGender().equals("female")) {
            genderGroup.selectToggle(femaleRadio);
        }
        
        if (oldUser.getRole().equals("admin")) {
            roleGroup.selectToggle(adminRadio);
        }
    }    


    @FXML
    private void cancelUserCreation(ActionEvent event) {
         Controller.Admin.UsersManagmentController.updateStage.close(); 
    }

    @FXML
    private void UpdatePatientbt(ActionEvent event)throws SQLException, ClassNotFoundException {
        
        //get the new data from text field's and store it in new user object
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String firstname=firstnameTF.getText();
        String lastname=lastnameTF.getText();
        String age=ageTF.getText();
        String phone=phoneTF.getText();
        String email = emailTF.getText();
        String gender = ((RadioButton)genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton)roleGroup.getSelectedToggle()).getText();
        
        
        User newUser = new User(username,password,firstname,lastname,age,email,phone,gender,role);
        
        newUser.setId(oldUser.getId());
        
        newUser.update();
        
       Controller.Admin.UsersManagmentController.updateStage.close(); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated");
        alert.setContentText("User updated");
        alert.showAndWait();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI GL65
 */
public class CreateBooked_AppoController implements Initializable {

    @FXML
    private Button saveNewAppBtn;
    @FXML
    private Button cancelAppBtn;
    @FXML
    private TextField appointment_dateTF;
    @FXML
    private TextField appointment_timeTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveNewAppr(ActionEvent event) {
    }

    @FXML
    private void cancelAppCreation(ActionEvent event) {
    }
    
}

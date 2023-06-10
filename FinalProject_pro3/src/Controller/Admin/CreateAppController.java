/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import static Controller.Admin.AppointmentController.createappStage;
import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI GL65
 */
public class CreateAppController implements Initializable {

    @FXML
    private Button saveNewAppBtn;
    @FXML
    private Button cancelAppBtn;
    @FXML
    private TextField appointment_dateTF;
    @FXML
    private TextField appointment_timeTF;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private TextField appointment_dayTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveNewAppr(ActionEvent event) throws SQLException, ClassNotFoundException {
         String appointment_date = appointment_dateTF.getText();
        String appointment_time = appointment_timeTF.getText();
        String appointment_day = appointment_dayTF.getText();
        String role = ((RadioButton) roleGroup.getSelectedToggle()).getText();

        Appointment appointment = new Appointment(appointment_date,appointment_day,appointment_time,role);
        appointment.saveapp();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User inserted");
        alert.setContentText("User inserted successfully!");
        alert.showAndWait();
    }

    @FXML
    private void cancelAppCreation(ActionEvent event) throws IOException {
          FXMLLoader loaderCreateApp = new FXMLLoader(getClass().getResource("/View/AdminFXML/Appointment.fxml"));
        Parent rootCreateApp = loaderCreateApp.load();     
        Scene ShowAppScene = new Scene(rootCreateApp); 
        createappStage = new Stage();
        createappStage.setScene(ShowAppScene);
        createappStage.show();
    }
    
}

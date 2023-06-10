/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import static Controller.Admin.UsersManagmentController.loginpatStage;
import static Controller.Admin.UsersManagmentController.logoutpatStage;
import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI GL65
 */
public class PatientController implements Initializable {

    @FXML
    private TableColumn<Appointment, Integer> tcid;
    @FXML
    private Button updateSelectedUserBtn;
    @FXML
    private Button deleteSelectedUserBtn;
    @FXML
    private Button showAppoBtn1;
    @FXML
    private TextField searchTF;
    @FXML
    private Button sbt;
    @FXML
    private Button showFreeAppBtn;
    @FXML
    private Button createAppBtn;
    @FXML
    private Button outBtn;
    @FXML
     private TableColumn<Appointment, Date> tcappointment_date;
    @FXML
    private TableColumn<Appointment, String> tcappointment_day;
    @FXML
    private TableColumn<Appointment, Time> tcappointment_time;
    @FXML
    private TableColumn<Appointment, String> tcstatus;
    @FXML
    private TableView<Appointment> aplist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          tcid.setCellValueFactory(new PropertyValueFactory<>("id"));
    tcappointment_date.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
    tcappointment_day.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
    tcappointment_time.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
    tcstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
     
    }    

    @FXML
    private void updateSelectedUser(ActionEvent event) {
    }

    @FXML
    private void deleteSelectedUser(ActionEvent event) {
    }

    @FXML
    private void showAllUsers(ActionEvent event) {
    }


    @FXML
    private void searchbtn(ActionEvent event) {
         //check if there is an user selected from the TableView
        if(aplist.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in new user object
            Appointment selectedAppointment = aplist.getSelectionModel().getSelectedItem();
            
            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    //delete the selected user from database table using delete method in our User model
                    selectedAppointment.delete();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
            deletedSuccessAlert.setTitle("User deleted");
            deletedSuccessAlert.setContentText("User deleted");
            deletedSuccessAlert.show();  
            }
            });
        
        }else{
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle("Select an Appointment");
        warnAlert.setContentText("Please select an user from the table view");
        warnAlert.show();  
        }
    }

    @FXML
    private void showAllFreeApp(ActionEvent event) throws SQLException, ClassNotFoundException {
          ObservableList<Appointment> Appointmentlist =
      FXCollections.observableArrayList(Appointment.getAllFreeApp());
                aplist.setItems(Appointmentlist);
    }

    @FXML
    private void CreationAppPage(ActionEvent event) {
        
    }

    @FXML
    private void logoutpatBtn(ActionEvent event) throws IOException {
           FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/FXMLDocument.fxml"));
        Parent rootPatient = loaderUpdate.load();     
        Scene patientDash = new Scene(rootPatient); 
        logoutpatStage = new Stage();
        logoutpatStage.setScene(patientDash);
        logoutpatStage.show();
         loginpatStage.close();
    }
    
}

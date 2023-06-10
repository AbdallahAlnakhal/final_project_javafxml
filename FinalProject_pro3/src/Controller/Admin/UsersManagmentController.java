/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Appointment;
import Model.DB;
import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * @author 
 */
public class UsersManagmentController implements Initializable {
public static User selectedUserToUpdate;
public static Stage updateStage;
public static Stage appStage;
public static Stage loginpatStage;
public static Stage logoutpatStage;
    @FXML
    private TableView<User> plist;
    @FXML
    private TableColumn<User, Integer> tcid;
    @FXML
    private TableColumn<User, String> tcusername;
    @FXML
    private TableColumn<User, String> tcpassword;
    @FXML
    private TableColumn<User, String> rcemail;
    @FXML
    private TableColumn<User, String> tcfirstname;
    @FXML
    private TableColumn<User, String> tclastname;
    @FXML
    private TableColumn<User, String> rcage;
    @FXML
    private TableColumn<User, String> tcphone;
    @FXML
    private TableColumn<User, String> tcgender;
    @FXML
    private TableColumn<User, String> tcRol;
    @FXML
    private Button showAppoBtn1;
    @FXML
    private Button updateSelectedUserBtn;
    @FXML
    private Button deleteSelectedUserBtn;
    @FXML
    private Button showAllUsersBtn;
    @FXML
    private Button createNewUserBtn;
    @FXML
    private Button sbt;
    @FXML
    private Button LogoutaBtn;
      
    ObservableList<User> listM;
    ObservableList<User> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TextField filterField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcid.setCellValueFactory(new PropertyValueFactory("id"));
        tcusername.setCellValueFactory(new PropertyValueFactory("username"));
        tcpassword.setCellValueFactory(new PropertyValueFactory("password"));
        rcemail.setCellValueFactory(new PropertyValueFactory("email"));
        tcfirstname.setCellValueFactory(new PropertyValueFactory("firstname"));
        tclastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        rcage.setCellValueFactory(new PropertyValueFactory("age"));
        tcphone.setCellValueFactory(new PropertyValueFactory("phone"));
        tcgender.setCellValueFactory(new PropertyValueFactory("gender"));
        tcRol.setCellValueFactory(new PropertyValueFactory("role"));
        
        
    }    


   


    @FXML
    private void showUserCreationPage(ActionEvent event) {
        ViewManager.logpage.changeSceneToCreateUser();
    }

    @FXML
    private void showAllUsers(ActionEvent event)  throws SQLException, ClassNotFoundException {
        
     

         dataList = DB.getDatausers();
    
    plist.setItems(dataList);
    
    FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);
    
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<User> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(plist.comparatorProperty());  
  plist.setItems(sortedData);      
    
             

       
    
             

       
    }

    @FXML
    private void updateSelectedUser(ActionEvent event) throws IOException {
          if (plist.getSelectionModel().getSelectedItem() != null) {
        selectedUserToUpdate = plist.getSelectionModel().getSelectedItem();
        FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdatePatient.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateUserScene = new Scene(rootUpdate); 
        // Store loaded fxml in our global stage updateStage and show it
        updateStage = new Stage();
        updateStage.setScene(updateUserScene);
        updateStage.setTitle("Update user " + selectedUserToUpdate.getUsername());
        updateStage.show();
    }
    }

    @FXML
    private void deleteSelectedUser(ActionEvent event) {
           //check if there is an user selected from the TableView
        if(plist.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in new user object
            User selectedUser = plist.getSelectionModel().getSelectedItem();
            
            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    //delete the selected user from database table using delete method in our User model
                    selectedUser.delete();
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
        warnAlert.setTitle("Select an user");
        warnAlert.setContentText("Please select an user from the table view");
        warnAlert.show();  
        }
    }

    @FXML
    private void searchbtn(ActionEvent event) throws SQLException, ClassNotFoundException {
       
    }

    @FXML
    private void LogoutAdBtn(ActionEvent event) {
        ViewManager.logpage.changeSceneToUsersManagment();
    }


  

    @FXML
    private void showAllApp(ActionEvent event) throws IOException {
          FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/Appointment.fxml"));
        Parent rootApp = loaderUpdate.load();     
        Scene ShowAppScene = new Scene(rootApp); 
        appStage = new Stage();
        appStage.setScene(ShowAppScene);
        appStage.show();
    }
    }
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class FXMLDocumantPage extends Stage{
    
    private Scene loginScene;
    private Scene createUserScene;
    private Scene usersManagmentScene;


    public FXMLDocumantPage() throws IOException {
        
        FXMLLoader usersManagmentLoader = new FXMLLoader(getClass().getResource("AdminFXML/FXMLDocument.fxml"));
        Parent usersManagmentRoot = usersManagmentLoader.load();     
        loginScene = new Scene(usersManagmentRoot);
        
        
        FXMLLoader createUserLoader = new FXMLLoader(getClass().getResource("AdminFXML/CreateUser.fxml"));
        Parent createUserRoot = createUserLoader.load();     
        createUserScene = new Scene(createUserRoot);
        
        FXMLLoader accountsLoader = new FXMLLoader(getClass().getResource("AdminFXML/UsersManagment.fxml"));
        Parent accountsRoot = accountsLoader.load();     
        usersManagmentScene = new Scene(accountsRoot);

        
        this.setScene(loginScene);
        this.setTitle("Admin Dasboard");
        
        
    }
    public void changeSceneToCreateUser(){
        this.setScene(createUserScene);
    }
    public void changeSceneToUsersManagment(){
        this.setScene(loginScene);
    }
    
    public void changeSceneToAccountsManagment(){
        this.setScene(usersManagmentScene);
    }

    
    
    
}

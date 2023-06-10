/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.project;

import Model.User;
import View.FXMLDocumantPage;
import View.ViewManager;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class JavaFXProject extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException, IOException {
      ViewManager.openLogpage();
     
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

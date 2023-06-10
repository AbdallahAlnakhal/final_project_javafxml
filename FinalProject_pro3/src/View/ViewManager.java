/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;

/**
 *
 * @author 
 */
public class ViewManager {
    public static FXMLDocumantPage logpage;
    
    private ViewManager(){   
    }
    
   

     public static void openLogpage() throws IOException{
        if (logpage == null) {
            logpage = new FXMLDocumantPage();
            logpage.show();
        } else {
            logpage.show();
        }
        
    }
    public static void closelogpage(){
        if(logpage != null)
            logpage.close();
    }
}
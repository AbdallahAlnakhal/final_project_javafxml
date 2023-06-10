/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class DB {

    private static DB db = null;

    public DB() {

    }

    public static DB getInstance() {
        if (db == null) {
            db = new DB();
            return db;

        } else {
            return db;
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor_appointment", "root", "");
        return con;
    }
        public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/doctor_appointment","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    
    }
      public static ObservableList<User> getDatausers() throws SQLException{
        Connection conn = ConnectDb();
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users WHERE role LIKE '%patient%'");
ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String age = rs.getString("age");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String role = rs.getString("role");

                User user = new User(username, password, firstname, lastname, age, email, phone, gender, role);
                user.setId(id);
                list.add(user);
            }  

        } catch (Exception e) {
        }
        return list;
    }
    

}

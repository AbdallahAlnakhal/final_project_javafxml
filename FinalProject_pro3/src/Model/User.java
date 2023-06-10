package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String age;
    private String email;
    private String phone;
    private String gender;
    private String role;

    // Constructor
    public User(String username, String password, String firstname, String lastname, String age, String email,
                String phone, String gender, String role) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    // Getters and setters for the properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Save the user to the database
    public int save() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO users (username, password, firstname, lastname, age, email, phone, gender, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.getUsername());
            statement.setString(2, this.getPassword());
            statement.setString(3, this.getFirstname());
            statement.setString(4, this.getLastname());
            statement.setString(5, this.getAge());
            statement.setString(6, this.getEmail());
            statement.setString(7, this.getPhone());
            statement.setString(8, this.getGender());
            statement.setString(9, this.getRole());

            return statement.executeUpdate();
        }
    }

    // Get all users from the database
   

    // Update the user in the database
    public int update() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE users SET username=?, password=?, firstname=?, lastname=?, age=?, email=?, phone=?, " +
                     "gender=?, role=? WHERE id=?";

        try (Connection connection = DB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.getUsername());
            statement.setString(2, this.getPassword());
            statement.setString(3, this.getFirstname());
            statement.setString(4, this.getLastname());
            statement.setString(5, this.getAge());
            statement.setString(6, this.getEmail());
            statement.setString(7, this.getPhone());
            statement.setString(8, this.getGender());
            statement.setString(9, this.getRole());
            statement.setInt(10, this.getId());

            return statement.executeUpdate();
        }
    }

    // Delete the user from the database
    public int delete() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection connection = DB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, this.getId());

            return statement.executeUpdate();
        }
    }

   
}
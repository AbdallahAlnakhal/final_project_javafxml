/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Booked_Appointment {
    private int id;                   

    private Appointment appointment_id;
    private User user_id;
    private String doctor_commnet;

    public Booked_Appointment(int id, Appointment appointment_id, User user_id, String doctor_commnet) {
        this.id = id;
        this.appointment_id = appointment_id;
        this.user_id = user_id;
        this.doctor_commnet = doctor_commnet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Appointment appointment_id) {
        this.appointment_id = appointment_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getDoctor_commnet() {
        return doctor_commnet;
    }

    public void setDoctor_commnet(String doctor_commnet) {
        this.doctor_commnet = doctor_commnet;
    }

   

   


 

   
  

    
   public int saveapp() throws SQLException, ClassNotFoundException {
    Connection c = DB.getInstance().getConnection();
    PreparedStatement ps = null;	
    int recordCounter = 0;
    String sql = "INSERT INTO booked_appointments (id, appointment_id, user_id, doctor_commnet) VALUES (?, ?, ?, ?, ?)";
    ps = c.prepareStatement(sql);
    ps.setInt(1, this.getId());
  
    recordCounter = ps.executeUpdate();
    
    if (ps != null) {
        ps.close();
    }
    c.close();
    return recordCounter;
}

    
   public static ArrayList<Appointment> getAllFreeApp() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointment> users = new ArrayList<>();
       String sql = "SELECT * FROM appointments WHERE status LIKE '%Free%'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Appointment app = new Appointment(rs.getString("appointment_date"), rs.getString("appointment_day"), rs.getString("appointment_time"), rs.getString("status"));
            app.setId(rs.getInt("id"));
            users.add(app);
        }				

        if (ps != null) {
            ps.close();
        }
        c.close();
        return users;
    }
 public static ArrayList<Appointment> getAllBookedApp() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointment> users = new ArrayList<>();
       String sql = "SELECT * FROM booked_appointments WHERE status LIKE '%Booked%'";

        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Appointment app = new Appointment(rs.getString("appointment_date"), rs.getString("appointment_day"), rs.getString("appointment_time"), rs.getString("status"));
            app.setId(rs.getInt("id"));
            users.add(app);
        }				

        if (ps != null) {
            ps.close();
        }
        c.close();
        return users;
    }

 public int delete() throws SQLException, ClassNotFoundException {
    String sql = "DELETE FROM booked_appointments WHERE id=?";

    try (Connection connection = DB.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, this.getId());

        return statement.executeUpdate();
    }
 }
}

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

public class Appointment {
    private int id;
    private String appointmentDate;
    private String appointmentDay;
    private String appointmentTime;
    private String status;

    public Appointment( String appointmentDate, String appointmentDay, String appointmentTime, String status) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

 

   
  

    
   public int saveapp() throws SQLException, ClassNotFoundException {
    Connection c = DB.getInstance().getConnection();
    PreparedStatement ps = null;
    int recordCounter = 0;
    String sql = "INSERT INTO appointments (id, appointment_date, appointment_day, appointment_time, status) VALUES (?, ?, ?, ?, ?)";
    ps = c.prepareStatement(sql);
    ps.setInt(1, this.getId());
    ps.setString(2, this.getAppointmentDate());
    ps.setString(3, this.getAppointmentDay());
    ps.setString(4, this.getAppointmentTime());
    ps.setString(5, this.getStatus());
   
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
       String sql = "SELECT * FROM appointments WHERE status LIKE '%Booked%'";

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
    String sql = "DELETE FROM appointments WHERE id=?";

    try (Connection connection = DB.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, this.getId());

        return statement.executeUpdate();
    }
 }
}

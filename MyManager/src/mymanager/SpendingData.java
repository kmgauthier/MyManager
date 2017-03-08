/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author Matt
 */
public class SpendingData {
    
    private String date, description;
    private double cost, spentWeek;
    private ArrayList<SpendingData> data = new ArrayList<SpendingData>();
    public SpendingData(String newDate, String newDescription, double newCost){
        date = newDate;
        description = newDescription;
        cost = newCost;
    }
    
    public SpendingData(){
        date = "MM/DD/YY";
        description = "";
        cost = 0.0;
        spentWeek = 0;
    }
    
    public void addSpending(SpendingData spendData){

        
    }
    
    public void write(){
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*    
    //SQL connection stuff
    private Connection con;
    private Statement stmt;
    private PreparedStatement ps;
    private void connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://96.42.187.211:3306/mymanager", "dbuser", "Xhk@5rak1@rc");
    }

    public void write(String newDate, String newDescription, double newCost) {
        try {
            connect();
            ps = con.prepareStatement("INSERT INTO spending VALUES ('"+newDate+"', '"+newDescription+"', "+newCost+")");
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SpendingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<SpendingData> read(){
        ArrayList<SpendingData> data = new ArrayList<SpendingData>();
        try {
            connect();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from spending");
            while (rs.next()) {
                data.add(new SpendingData(rs.getString(1), rs.getString(2), rs.getDouble(3)));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SpendingData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public String getDate(){
        return date;
    }
    
    public String getDesc(){
        return description;
    }
    
    public double getCost(){
        return cost;
    }

    public double getSpentWeek(){
        return spentWeek;
    }
    
    */
}

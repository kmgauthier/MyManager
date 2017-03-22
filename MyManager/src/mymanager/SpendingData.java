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
public class SpendingData implements Serializable {
    
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
    
    //public void addSpending(SpendingData spendData){
        
    //}
    
    
    public String getDate(){
        return date;
    }
    
    public String getDesc(){
        return description;
    }
    
    public double getCost(){
        return cost;
    }
    public void write(String date, String description, double amount){
        SpendingData sd = new SpendingData(date, description, amount);
        try {
            FileOutputStream fileOutSpending = new FileOutputStream("spending.ser");
            ObjectOutputStream outSpending = new ObjectOutputStream(fileOutSpending);
             outSpending.writeObject(sd);
            outSpending.close();
            fileOutSpending.close();
            System.out.printf("Serialized spending data is saved in spending.ser");
        }catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    // private ArrayList<SpendingData> dataArray = null;
    public ArrayList<SpendingData> read(){
        ArrayList<SpendingData> alsd = new ArrayList<SpendingData>();
        try {
            File file = new File("spending.ser");
            FileInputStream fileInSpending = new FileInputStream(file);
            ObjectInputStream inSpending = new ObjectInputStream(fileInSpending);
            SpendingData sd =null;
            while(file.canRead()){
                sd = (SpendingData) inSpending.readObject();
                alsd.add(sd);
            }
            
            
            inSpending.close();
            fileInSpending.close();
            System.out.printf("Serialized student data is saved in spending.ser");
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return alsd;
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

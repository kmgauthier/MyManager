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
    
    private String date, description;//creating string variables for date and description
    private double cost;//creates double variable for cost
    private ArrayList<SpendingData> data = new ArrayList<SpendingData>();//creates array for spending data
    public SpendingData(String newDate, String newDescription, double newCost){//declaring array for spending data
        date = newDate;
        description = newDescription;
        cost = newCost;
    }
    
    public SpendingData(){
        date = "MM/DD/YY";
        description = "";
        cost = 0.0;
    }
    
    public void addSpending(SpendingData spendData){//method to add spending data
        if(fileExists()){
            data = read();
            data.add(spendData);
            
            write(data);
            
        } else {
            data.add(spendData);
            write(data);
        }
    }
    
    
    public String getDate(){//method to get date value
        return date;
    }
    
    public String getDesc(){//method to get description
        return description;
    }
    
    public double getCost(){//method to get cost
        return cost;
    }
    
    public static void write(ArrayList<SpendingData> listWrite){//method to write to array
        
        try {
            FileOutputStream fileOutSpending = new FileOutputStream("spending.txt");
            ObjectOutputStream outSpending = new ObjectOutputStream(fileOutSpending);
             outSpending.writeObject(listWrite);
            outSpending.close();
            fileOutSpending.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static ArrayList<SpendingData> read(){//method to read from array
        ArrayList<SpendingData> alsd = new ArrayList<SpendingData>();
        try {
            File file = new File("spending.txt");
            FileInputStream fileInSpending = new FileInputStream(file);
            ObjectInputStream inSpending = new ObjectInputStream(fileInSpending);
            if(file.canRead()){
                alsd = (ArrayList<SpendingData>) inSpending.readObject();
            } else {
                //alsd = new ArrayList<SpendingData>();
            }
            inSpending.close();
            fileInSpending.close();
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("ioe");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("cnfe");
        }
        return alsd;
    }

    public boolean fileExists(){//method to check if file exists
        File file = new File("spending.txt");
        if (file.canRead()) {
            return true;
        } else {
            return false;
        }
    }


}

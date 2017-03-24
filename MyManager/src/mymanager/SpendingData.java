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
    private double cost;
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
    }
    
    public void addSpending(SpendingData spendData){
        if(fileExists()){
            data = read();
            data.add(spendData);
            
            write(data);
            
        } else {
            data.add(spendData);
            write(data);
        }
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
    
    public static void write(ArrayList<SpendingData> listWrite){
        
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
    
    public static ArrayList<SpendingData> read(){
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

    public boolean fileExists(){
        File file = new File("spending.txt");
        if (file.canRead()) {
            return true;
        } else {
            return false;
        }
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;
import java.io.*;
/**
 *
 * @author Matthew Fair
 */
public class FinancialData implements Serializable{
    
    private double savingsAmount, accountBalance;//double variables for savings amount and account balance
    private boolean justReset; //boolean value to tell whether it was just reset
    private static final long serialVersionUID = -5508928421573314610L;
    
    public FinancialData(){
        ProfileData storedData = ProfileData.read();
        if(storedData != null){
            savingsAmount = storedData.getStartSavings();
            accountBalance = storedData.getStartBalance();
        } else{
            savingsAmount = 0;
            accountBalance = 0;
        }
    }
    public FinancialData(boolean reset){
        savingsAmount = 0;
        accountBalance = 0;
        justReset = true;
    }
        
    public void addSavings(double addAmount){//method to add savings
        savingsAmount += addAmount;
        justReset = false;
    }
    
    public void removeSavings(double removeAmount){//method to remove savings
        savingsAmount -= removeAmount;
        justReset = false;
    }
    
    public void addIncome(double addAmount){//method to add account balance
        accountBalance += addAmount;
        justReset = false;
    }
    
    public void removeIncome(double removeAmount){//method to remove from account balance
        accountBalance -= removeAmount;
        justReset = false;
    }
    
    public double getCurrentSavingsBalance(){//method to get the current savings balance
        return savingsAmount;
    }
    
    public double getCurrentAccountBalance(){//method to get the current account balance
        return accountBalance;
    }
    
    
    public static void write(FinancialData newData){//method to write financial data to text file
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try{
            ops = new FileOutputStream("financial_storage.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(newData);
            objOps.flush();
            
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static FinancialData read(){//method to read financial data
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        FinancialData data = null;
        try {
            fileIs = new FileInputStream("financial_storage.txt");
            objIs = new ObjectInputStream(fileIs);
            FinancialData nData = (FinancialData) objIs.readObject();
            data = nData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objIs != null) objIs.close();
            } catch (Exception ex){
                 
            }
        }
        
        return data;
    }
    
    public boolean getResetStatus(){//method to get the reset status
        return justReset;
    }
    
}

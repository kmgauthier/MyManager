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
    
    private double savingsAmount, accountBalance;//creating private double variables
    private static final long serialVersionUID = -5508928421573314610L;
    
    public FinancialData(){//constructor for FinancialData
        ProfileData storedData = ProfileData.read();
        if(storedData != null){
            savingsAmount = storedData.getStartSavings();
            accountBalance = storedData.getStartBalance();
        } else{
            savingsAmount = 0;
            accountBalance = 0;
        }
    }
        
    public void addSavings(double addAmount){//addSavings method
        savingsAmount += addAmount;
    }
    
    public void removeSavings(double removeAmount){//removeSavings method
        savingsAmount -= removeAmount;
    }
    
    public void addIncome(double addAmount){//addIncome method
        accountBalance += addAmount;
    }
    
    public void removeIncome(double removeAmount){//removeAmount method
        accountBalance -= removeAmount;
    }
    
    public double getCurrentSavingsBalance(){//method to get Current Savings Balance
        return savingsAmount;
    }
    
    public double getCurrentAccountBalance(){//method to get Current Account Balance
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
    
    public static FinancialData read(){//method to read financial data from text file
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
    
}

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
    
    private double savingsAmount, accountBalance;
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
        
    public void addSavings(double addAmount){
        savingsAmount += addAmount;
    }
    
    public void removeSavings(double removeAmount){
        savingsAmount -= removeAmount;
    }
    
    public void addIncome(double addAmount){
        accountBalance += addAmount;
    }
    
    public void removeIncome(double removeAmount){
        accountBalance -= removeAmount;
    }
    
    public double getCurrentSavingsBalance(){
        return savingsAmount;
    }
    
    public double getCurrentAccountBalance(){
        return accountBalance;
    }
    
    public static void write(FinancialData newData){
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
    
    public static FinancialData read(){
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

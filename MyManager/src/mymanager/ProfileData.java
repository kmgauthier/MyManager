/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;
import java.io.*;
/**
 *
 * @author Matt
 */
public class ProfileData implements Serializable{
    
    private int weight, age;
    private double startBalance, startSavings, savingsGoal;
    private Height height;
    private String firstName, lastName, date, gender;
    
    
    
    public ProfileData(Height newHeight, int newWeight, int newAge, String newFirstName, String newLastName, String newGender, double newStartBalance, double newStartSavings, double newSaveGoal){
        setWeight(newWeight);
        setHeight(newHeight);
        setAge(newAge);
        setName(newFirstName, newLastName);
        setStartBalance(newStartBalance);
        setStartSavings(newStartSavings);
        setSavingsGoal(newSaveGoal);
        setGender(newGender);
    }
    
    public void setHeight(Height newHeight){
        height = newHeight;
    }
    
    public void setWeight(int newWeight){
        if(newWeight > 0){
            weight = newWeight;
        } else {
            
            
            //Error prompt????
            
            
        }
    }
    
    public void setAge(int newAge){
        if(newAge > 0){
            age = newAge;
        } else {
            //error prompt...
        }
    }
    
    public void setName(String newFirstName, String newLastName){
        firstName = newFirstName;
        lastName = newLastName;
    }
    
    public void setStartBalance(double newStartBalance){
        if(newStartBalance >= 0){
            startBalance = newStartBalance;
        }
        else{
            startBalance = 0;
        }
    }
    
    public void setStartSavings(double newStartSavings ){
        if( newStartSavings >=0 ){
            startSavings = newStartSavings;
        }
        else{
            startSavings = 0;
        }
    }
    
    public void setSavingsGoal(double newSaveGoal){
        if( newSaveGoal >0 ){
            savingsGoal = newSaveGoal;
        }
        else{
            //error
        }
    }
    
    public void setGender(String newGender){
        if(newGender.equals("Male") || newGender.equals("Female")){
            gender = newGender;
        } else {
            
            
            //Error or something
            
            
        }
    }
    
    public int getWeight(){
        return weight;
    }
    
    public Height getHeight(){
        return height;
    }
    
    public int getAge(){
        return age;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public double getStartSavings(){
        return startSavings;
    }
    
    public double getStartBalance(){
        return startBalance;
    }

    public double getSavingsGoal(){
        return savingsGoal;
    }
    
    public String getGender(){
        return gender;
    }
    
    @Override
    public String toString(){
        return "Name: "+firstName+" "+lastName+", Age: "+age+", Height: "+height+", Weight: "+weight + 
                ", Starting Balance: $" + startBalance + ", Starting SAvings: $" + startSavings + ", Goal Savings: $" + savingsGoal;
    }
    
    public static void write(ProfileData data){
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try{
            ops = new FileOutputStream("profile_storage.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(data);
            objOps.flush();
            
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }        
        
    }

    public static ProfileData read(){
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        ProfileData data = null;
        try {
            fileIs = new FileInputStream("profile_storage.txt");
            objIs = new ObjectInputStream(fileIs);
            ProfileData nData = (ProfileData) objIs.readObject();
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

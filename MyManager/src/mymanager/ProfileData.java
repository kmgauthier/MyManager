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
    
    private int weight, age;//creating integers for weight and age
    private double startBalance, startSavings, savingsGoal;//creates double variables for starting balance, starting savings, and savings goal
    private Height height;//creates variable of height class
    private String firstName, lastName, gender;//creates strings for first and last name and gender
    
    public ProfileData(){
        weight = 0;
        age = 0;
        startBalance = 0;
        startSavings = 0;
        savingsGoal = 0;
        height = new Height();
        firstName = "";
        lastName = "";
        gender = "";
        
    }
    
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
    
    public void setHeight(Height newHeight){//method to set the height
        height = newHeight;
    }
    
    public void setWeight(int newWeight){//method to set the weight
        if(newWeight > 0){
            weight = newWeight;
        } else {
            
            
            //Error prompt????
            
            
        }
    }
    
    public void setAge(int newAge){//method to set the age
        if(newAge > 0){
            age = newAge;
        } else {
            //error prompt...
        }
    }
    
    public void setName(String newFirstName, String newLastName){//method to set the name
        firstName = newFirstName;
        lastName = newLastName;
    }
    
    public void setStartBalance(double newStartBalance){//method to set starting balance
        if(newStartBalance >= 0){
            startBalance = newStartBalance;
        }
        else{
            startBalance = 0;
        }
    }
    
    public void setStartSavings(double newStartSavings ){//method to set the starting savings
        if( newStartSavings >=0 ){
            startSavings = newStartSavings;
        }
        else{
            startSavings = 0;
        }
    }
    
    public void setSavingsGoal(double newSaveGoal){//method to set the savings goal
        if( newSaveGoal >0 ){
            savingsGoal = newSaveGoal;
        }
        else{
            //error
        }
    }
    
    public void setGender(String newGender){//method to set gender
        if(newGender.equals("Male") || newGender.equals("Female")){
            gender = newGender;
        } else {
            
            
            //Error or something
            
            
        }
    }
    
    public int getWeight(){//method to get weight
        return weight;
    }
    
    public Height getHeight(){//method to get height
        return height;
    }
    
    public int getAge(){//method to get age
        return age;
    }
    
    public String getFirstName(){//method to get first name
        return firstName;
    }
    
    public String getLastName(){//method to get last name
        return lastName;
    }
    
    public double getStartSavings(){//method to get starting savings
        return startSavings;
    }
    
    public double getStartBalance(){//method to get starting balance
        return startBalance;
    }

    public double getSavingsGoal(){//method to get savings goal
        return savingsGoal;
    }
    
    public String getGender(){//method to get gender
        return gender;
    }
    
    @Override
    public String toString(){//method to make the variables into a string
        return "Name: "+firstName+" "+lastName+", Age: "+age+", Height: "+height+", Weight: "+weight + 
                ", Starting Balance: $" + startBalance + ", Starting SAvings: $" + startSavings + ", Goal Savings: $" + savingsGoal;
    }
    
    public static void write(ProfileData data){//method to write data to text file
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

    public static ProfileData read(){//method to read profile data
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

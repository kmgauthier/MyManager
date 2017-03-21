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
    
    private int weight, age;//creating private integer variables
    private double startBalance, startSavings, savingsGoal;//creating private double variables
    private Height height;//creating private height object
    private String firstName, lastName, gender;//creating private string variables
    
    
    //creating public profiledata constructor with parameters
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
    
    public void setHeight(Height newHeight){//setHeight method
        height = newHeight;
    }
    
    public void setWeight(int newWeight){//setWeight method
        if(newWeight > 0){
            weight = newWeight;
        } else {
            
            
            //Error prompt????
            
            
        }
    }
    
    public void setAge(int newAge){//setAge method
        if(newAge > 0){
            age = newAge;
        } else {
            //error prompt...
        }
    }
    
    public void setName(String newFirstName, String newLastName){//setName method
        firstName = newFirstName;
        lastName = newLastName;
    }
    
    public void setStartBalance(double newStartBalance){//set Starting Balance method
        if(newStartBalance >= 0){
            startBalance = newStartBalance;
        }
        else{
            startBalance = 0;
        }
    }
    
    public void setStartSavings(double newStartSavings ){//set Starting savings method
        if( newStartSavings >=0 ){
            startSavings = newStartSavings;
        }
        else{
            startSavings = 0;
        }
    }
    
    public void setSavingsGoal(double newSaveGoal){//set savings goal method
        if( newSaveGoal >0 ){
            savingsGoal = newSaveGoal;
        }
        else{
            //error
        }
    }
    
    public void setGender(String newGender){ //set gender method
        if(newGender.equals("Male") || newGender.equals("Female")){
            gender = newGender;
        } else {
            
            
            //Error or something
            
            
        }
    }
    
    public int getWeight(){//getweight method
        return weight;
    }
    
    public Height getHeight(){//getheight method
        return height;
    }
    
    public int getAge(){// get age method
        return age;
    }
    
    public String getFirstName(){//get first name method
        return firstName;
    }
    
    public String getLastName(){//get last name method
        return lastName;
    }
    
    public double getStartSavings(){//get starting savings method
        return startSavings;
    }
    
    public double getStartBalance(){//get starting balance method
        return startBalance;
    }

    public double getSavingsGoal(){//get savings goal method
        return savingsGoal;
    }
    
    public String getGender(){//get gender method
        return gender;
    }
    
    @Override
    public String toString(){//to string method
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

    public static ProfileData read(){//method to read data from text file
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

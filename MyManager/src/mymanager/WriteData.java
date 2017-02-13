/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;
import java.io.Serializable;
/**
 *
 * @author Matt
 */
public class WriteData implements Serializable{
    
    private int weight, age, startBalance, startSavings, savingsGoal;
    private Height height;
    private String firstName, lastName, date;
    private boolean hasData = false;
    
    
    public WriteData(Height newHeight, int newWeight, int newAge, String newFirstName, String newLastName, 
            int newStartBalance, int newStartSavings, int newSaveGoal){
        setWeight(newWeight);
        setHeight(newHeight);
        setAge(newAge);
        setName(newFirstName, newLastName);
        setStartBalance(newStartBalance);
        setStartSavings(newStartSavings);
        setSavingsGoal(newSaveGoal);
        hasData = true;
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
    
    public void setStartBalance(int newStartBalance){
        if(newStartBalance >= 0){
            startBalance = newStartBalance;
        }
        else{
            startBalance = 0;
        }
    }
    
    public void setStartSavings(int newStartSavings ){
        if( newStartSavings >=0 ){
            startSavings = newStartSavings;
        }
        else{
            startSavings = 0;
        }
    }
    
    public void setSavingsGoal(int newSaveGoal){
        if( newSaveGoal >0 ){
            savingsGoal = newSaveGoal;
        }
        else{
            //error
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
    
    public int getStartSavings(){
        return startSavings;
    }
    
    public int getStartBalance(){
        return startBalance;
    }

    public int getSavingsGoal(){
        return savingsGoal;
    }
    
    @Override
    public String toString(){
        return "Name: "+firstName+" "+lastName+", Age: "+age+", Height: "+height+", Weight: "+weight + 
                ", Starting Balance: $" + startBalance + ", Starting SAvings: $" + startSavings + ", Goal Savings: $" + savingsGoal;
    }
    
    
}

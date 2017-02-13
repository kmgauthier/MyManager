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
    
    private int weight, age;
    private Height height;
    private String firstName, lastName;
    private boolean hasData = false;
    
    
    public WriteData(Height newHeight, int newWeight, int newAge, String newFirstName, String newLastName){
        setWeight(newWeight);
        setHeight(newHeight);
        setAge(newAge);
        setName(newFirstName, newLastName);
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
    
    

    @Override
    public String toString(){
        return "Name: "+firstName+" "+lastName+", Age: "+age+", Height: "+height+", Weight: "+weight;
    }
    
    
}

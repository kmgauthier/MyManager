/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

/**
 *
 * @author Matt
 */
public class WriteData {
    
    private int weight, age;
    private Height height;
    private String name;
    private boolean hasData = false;
    
    
    public WriteData(Height newHeight, int newWeight, int newAge, String newName){
        setWeight(newWeight);
        setHeight(newHeight);
        setAge(newAge);
        setName(newName);
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
    
    public void setName(String newName){
        name = newName;
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
    
    public String getName(){
        return name;
    }
    
    public boolean hasData(){
        return hasData;
    }
    
    
}

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
public class Height implements Serializable {
    
    private int feet = 0, inches = 0;//creating integers for feet and inches
    
    public Height(int ft, int in){
        if(ft > 0 && in >= 0){
            feet = ft;
            inches = in;
        } else {
            feet = 0;
            inches = 0;
        }
    }
    
    public Height(){
        feet = 0;
        inches = 0;
    }
    
    public int getFoot(){//method to get feet
        return feet;
    }
    
    public int getInches(){//method to get inches
        return inches;
    }
    
    public void setFoot(int ft){//method to set feet
        if(ft > 0){
            feet = ft;
        } else {
            feet = 0;
        }
    }
    
    public void setInches(int in){//method to set inches
        if(in >0){
            inches = in;
        } else {
            inches = 0;
        }
    }
    
    @Override
    public String toString(){//method to make feet and inches into a string
        return feet+" foot "+inches+" inches";
    }
    
    
    
}

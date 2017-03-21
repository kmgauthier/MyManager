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
    
    private int feet = 0, inches = 0;//creates private integers feet and inches
    
    public Height(int ft, int in){//public constructor height
        if(ft > 0 && in >= 0){//if feet is greater than 0 and inches is greater than or equal to 0
            feet = ft;//variable feet = parameter ft
            inches = in;//variable inches = parameter in
        } else {//else they both = 0
            feet = 0;
            inches = 0;
        }
    }
    
    public Height(){
        feet = 0;
        inches = 0;
    }
    
    public int getFoot(){//method to get value of feet
        return feet;
    }
    
    public int getInches(){//method to get value of inches
        return inches;
    }
    
    public void setFoot(int ft){//method to set value of feet
        if(ft > 0){
            feet = ft;
        } else {
            feet = 0;
        }
    }
    
    public void setInches(int in){//method to set value of inches
        if(in >0){
            inches = in;
        } else {
            inches = 0;
        }
    }
    
    @Override
    public String toString(){//tostring method to return value of feet and inches
        return feet+" foot "+inches+" inches";
    }
    
    
    
}

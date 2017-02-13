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
public class Height {
    
    private int feet = 0, inches = 0;
    
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
    
    public int getFoot(){
        return feet;
    }
    
    public int getInches(){
        return inches;
    }
    
    public void setFoot(int ft){
        if(ft > 0){
            feet = ft;
        } else {
            feet = 0;
        }
    }
    
    public void setInches(int in){
        if(in >0){
            inches = in;
        } else {
            inches = 0;
        }
    }
    
    
    
}

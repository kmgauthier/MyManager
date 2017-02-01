/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

<<<<<<< HEAD
import javax.swing.JFrame;
import java.util.*;
=======
import javax.swing.*;

>>>>>>> origin/master
/**
 *
 * @author Matthew Fair
 */
public class MyManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame main = new JFrame("MyManager"); //create a main frame
        //frame modifications go here
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when you hit the X
        main.setBounds(300, 300, 500, 300); //location the window pops up and size of the window
        main.setVisible(true); 
        
        JPanel mainPanel = new JPanel(); //create panel for adding buttons to
        main.add(mainPanel); //ad panel to the frame
        
        JButton login = new JButton("Submit"); //create login button
        login.setBounds(100, 200, 20, 4); //set size and location of button
        main.add(login); //add the button to the panel
        
        
        
    }
    
}

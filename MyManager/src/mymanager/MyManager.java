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
        
        System.out.println("This is a test");
        System.out.println("testing netbeans push");
        System.out.println("test");
        
        JFrame main = new JFrame("MyManager");
        //frame modifications go here
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setBounds(300, 300, 500, 300);
        main.setVisible(true); 
        
        JPanel mainPanel = new JPanel();
        main.add(mainPanel);
        JButton login = new JButton("Submit");
        main.add(login);
        
    }
    
}

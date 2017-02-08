/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

//<<<<<<< HEAD
import javax.swing.*;
import java.util.*;
import java.awt.Color;
//=======
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//>>>>>>> origin/master
/**
 *
 * @author Matthew Fair
 */
public class MyManager extends JFrame{
    
    JPanel pnlButton = new JPanel();
    JButton btnFinancialAssistant = new JButton("Financial Assistant");
    JButton btnHealthAssistant = new JButton("Health Assistant");
    
    public MyManager(){
        super("Button");
        btnFinancialAssistant.setBounds(60, 400, 220, 30);
        btnHealthAssistant.setBounds(60, 500, 220, 30);
       
        
        //pnlButton.setLayout(null);
        
        pnlButton.setBounds(800, 800, 200, 100);
               
        pnlButton.add(btnFinancialAssistant);        
        pnlButton.add(btnHealthAssistant);
        
        add(pnlButton);
        btnFinancialAssistant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new FinancialAssistant();

            }
            
        });
        
        btnHealthAssistant.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new HealthAssistant();
                
            }
            
        });
        
        setSize(400,400);
        setBackground(Color.WHITE);
        setTitle("My Manager");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    public static void main(String[] args) {
        
        new MyManager();
          
    }
    
}

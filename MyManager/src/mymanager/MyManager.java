/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 *
 * @author Matthew Fair
 */
public class MyManager extends JFrame {

    JFrame frame; //home screen
    JButton financialAssistant, healthAssistant, myProfile;
    JLabel welcome, netCalories, currentSavings;

    public MyManager() {

        frame = new JFrame("My Manager"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        financialAssistant = new JButton("Financial Assistant");
        healthAssistant = new JButton("Health Assistant");
        
        myProfile = new JButton("My Profile");
        welcome = new JLabel("Welcome to My Manager!");
        FinancialData storedFinData = FinancialData.read();
        
        if(storedFinData != null){
            currentSavings = new JLabel("Current Savings: $"+Double.toString(storedFinData.getCurrentSavingsBalance())); //should look like "Current Savings: " + savings
            } else {
            currentSavings = new JLabel("Current Savings: "); //should look like "Current Savings: " + savings
        }
        HealthData storedHealthData = HealthData.read();
        if(storedHealthData != null){
            netCalories = new JLabel("Net Calories Today: "+Integer.toString(storedHealthData.getNetCals())); //should look like "Net Calories Today: " + netCals
        } else {
            netCalories = new JLabel("Net Calories Today: 0"); //should look like "Net Calories Today: " + netCals
        }
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.VERTICAL; //ADD COMMENT
        gc.insets = new Insets(10, 10, 10, 10); //Declares default spacing between objects on the 

        gc.gridx = 0; //ADD COMMENT
        gc.gridy = 0; //ADD COMMENT
        frame.add(welcome, gc);
        
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(netCalories, gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(currentSavings, gc);
        

        gc.gridx = 1; //ADD COMMENT
        gc.gridy = 0; //ADD COMMENT
        frame.add(myProfile, gc);
        myProfile.addActionListener(new ActionListener() { //to go to profile page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyProfile();
            }

        });

        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(financialAssistant, gc);
        financialAssistant.addActionListener(new ActionListener() { //to go to financial assistant page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new FinancialAssistant();
            }

        });

        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(healthAssistant, gc);
        healthAssistant.addActionListener(new ActionListener() { //to go to health assistant page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new HealthAssistant();
            }

        });

        frame.pack(); //comment what does this do?
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new MyManager();

    }

}

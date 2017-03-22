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
import java.awt.font.*;
import java.io.*;
import javax.imageio.ImageIO;

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

        try {
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);
        Font font2 = new Font("Monotype Corsiva", Font.BOLD, 30);
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);

        //frame.getContentPane().setBackground(new Color(102, 213, 247));
        frame.getContentPane().setBackground(new Color(73, 172, 229));

        financialAssistant = new JButton("Financial Assistant");
        healthAssistant = new JButton("Health Assistant");

        //financialAssistant.setBackground(new Color(99,158, 192));
        financialAssistant.setBackground(new Color(102, 213, 247));
        healthAssistant.setBackground(new Color(102, 213, 247));
        financialAssistant.setFont(font1);
        healthAssistant.setFont(font1);

        myProfile = new JButton("My Profile");
        myProfile.setBackground(new Color(102, 213, 247));
        myProfile.setFont(font1);
        welcome = new JLabel("Welcome to My Manager!");
        welcome.setFont(font2);
        FinancialData storedFinData = FinancialData.read();

        if (storedFinData != null) {
            currentSavings = new JLabel("Current Savings: $" + Double.toString(storedFinData.getCurrentSavingsBalance())); //should look like "Current Savings: " + savings
        } else {
            currentSavings = new JLabel("Current Savings: "); //should look like "Current Savings: " + savings
        }
        HealthData storedHealthData = HealthData.read();
        if (storedHealthData != null) {
            netCalories = new JLabel("Net Calories Today: " + Integer.toString(storedHealthData.getNetCals())); //should look like "Net Calories Today: " + netCals
        } else {
            netCalories = new JLabel("Net Calories Today: 0"); //should look like "Net Calories Today: " + netCals
        }

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.VERTICAL; //ADD COMMENT
        gc.insets = new Insets(10, 10, 10, 10); //Declares default spacing between objects on the 

        gc.gridx = 0; //ADD COMMENT
        gc.gridy = 0; //ADD COMMENT
        frame.add(welcome, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(netCalories, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        frame.add(currentSavings, gc);

        gc.gridx = 2; //ADD COMMENT
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

        netCalories.setFont(font3);
        currentSavings.setFont(font3);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new MyManager();

    }

}

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
    JButton financialAssistant, healthAssistant, myProfile; //creating buttons for home screen
    JLabel welcome, netCalories, currentSavings; //creating labels  for home screen

    public MyManager() {

        frame = new JFrame("My Manager"); //declaring jframe and titling it "My Manager"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ends the program once it is closed
        frame.setLayout(new GridBagLayout()); //sets the layout of the frame to the gridbaglayout

        try {
            frame.setIconImage(ImageIO.read(new File("newLogo.png"))); //changes the default icon image to the mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creates main font
        Font font2 = new Font("Monotype Corsiva", Font.BOLD, 30);//creates font for title on home screen
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20); //creates 3rd font

        //frame.getContentPane().setBackground(new Color(102, 213, 247));
        frame.getContentPane().setBackground(new Color(73, 172, 229)); //setting background color of home screen

        financialAssistant = new JButton("Financial Assistant"); //declares button for financial assistant
        healthAssistant = new JButton("Health Assistant");//declares button for health assistant

        //financialAssistant.setBackground(new Color(99,158, 192));
        financialAssistant.setBackground(new Color(102, 213, 247)); //sets background of financial assistant button
        healthAssistant.setBackground(new Color(102, 213, 247)); //sets background of health assistant button
        financialAssistant.setFont(font1); //sets font of financial assistant page to font 1
        healthAssistant.setFont(font1);//sets font of health assistant page to font 1

        myProfile = new JButton("My Profile");//declaring button for user's profile page
        myProfile.setBackground(new Color(102, 213, 247));//sets background color of profile button
        myProfile.setFont(font1);//sets font of profile page to font 1
        welcome = new JLabel("Welcome to My Manager!"); //declares welcome label and makes it say "welcome to mymanager"
        welcome.setFont(font2); //sets font of welcome label to font 2
       
        //setting the value of the currentsavings label
        FinancialData storedFinData = FinancialData.read(); 
        if (storedFinData != null) { 
            currentSavings = new JLabel("Current Savings: $" + Double.toString(storedFinData.getCurrentSavingsBalance())); //should look like "Current Savings: " + savings
        } else {
            currentSavings = new JLabel("Current Savings: "); //should look like "Current Savings: " + savings
        }
        
        //setting the value of the netcalories label
        HealthData storedHealthData = HealthData.read();
        if (storedHealthData != null) {
            netCalories = new JLabel("Net Calories Today: " + Integer.toString(storedHealthData.getNetCals())); //should look like "Net Calories Today: " + netCals
        } else {
            netCalories = new JLabel("Net Calories Today: 0"); //should look like "Net Calories Today: " + netCals
        }

        GridBagConstraints gc = new GridBagConstraints(); //declares new gridbagconstraints
        gc.fill = GridBagConstraints.VERTICAL; //to fill the grid bag layout
        gc.insets = new Insets(10, 10, 10, 10); //Declares default spacing between objects on the gridbag layout

        //adding welcome label to gridbag layout
        gc.gridx = 0; 
        gc.gridy = 0; 
        frame.add(welcome, gc);

        //adding netcalories label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(netCalories, gc);

        //adding currentsavings label to gridbag layout
        gc.gridx = 0;
        gc.gridy = 2;
        frame.add(currentSavings, gc);

        //adding profile button to gridbag layout
        gc.gridx = 2; 
        gc.gridy = 0; 
        frame.add(myProfile, gc);
        myProfile.addActionListener(new ActionListener() { //to go to profile page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyProfile();
            }

        });

        //adding financial assistant button to gridbag layout
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

        //adding health assistant button to gridbag layout
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

        netCalories.setFont(font3);//setting the font of the netcalories label to font 3
        currentSavings.setFont(font3);//setting the font of the current savings label to font 3

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);//setting the frame visibility to true

    }

    public static void main(String[] args) {

        new MyManager();//calling the mymanager method

    }

}

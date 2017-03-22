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

    JFrame frame; //home screen             //creating jframe for home page
    JButton financialAssistant, healthAssistant, myProfile;     //creating buttons for home page
    JLabel welcome, netCalories, currentSavings;                //creating labels for home page

    public MyManager() {

        frame = new JFrame("My Manager");       //declaring jframe and titling it "My Manager"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends the program when the user closes the window
        frame.setLayout(new GridBagLayout());//setting the layout to gridbag layout

        try {//setting the icon image to the mymanager logo
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creating font for buttons
        Font font2 = new Font("Monotype Corsiva", Font.BOLD, 30);//creating font for header
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);//creating font for labels at bottom of screen

        //frame.getContentPane().setBackground(new Color(102, 213, 247));
        frame.getContentPane().setBackground(new Color(73, 172, 229));//setting background color of home page

        financialAssistant = new JButton("Financial Assistant");//declaring financial assistant button
        healthAssistant = new JButton("Health Assistant");//declaring health assistant button

        //financialAssistant.setBackground(new Color(99,158, 192));
        financialAssistant.setBackground(new Color(102, 213, 247));//setting background color of financial assistant button
        healthAssistant.setBackground(new Color(102, 213, 247));//setting background color of health assistant button
        financialAssistant.setFont(font1);//setting font of financial assistant button to font 1
        healthAssistant.setFont(font1);//setting font of health assistant button to font 1

        myProfile = new JButton("My Profile");//declaring my profile button
        myProfile.setBackground(new Color(102, 213, 247));//setting background color of myprofile button
        myProfile.setFont(font1);//setting font of my profile button to font 1
        welcome = new JLabel("Welcome to My Manager!");//creating welcome header label
        welcome.setFont(font2);//setting font of header label to font 2
        FinancialData storedFinData = FinancialData.read();//creating object of class financialdata

        if (storedFinData != null) {//adding data to storedFinData so that the current savings label will display the user's current savings
            currentSavings = new JLabel("Current Savings: $" + Double.toString(storedFinData.getCurrentSavingsBalance())); //should look like "Current Savings: " + savings
        } else {
            currentSavings = new JLabel("Current Savings: "); //should look like "Current Savings: " + savings
        }
        HealthData storedHealthData = HealthData.read();//creating object of class HealthData
        if (storedHealthData != null) {//adding data to storedHealthData so that the net calories label will display the user's net calories
            netCalories = new JLabel("Net Calories Today: " + Integer.toString(storedHealthData.getNetCals())); //should look like "Net Calories Today: " + netCals
        } else {
            netCalories = new JLabel("Net Calories Today: 0"); //should look like "Net Calories Today: " + netCals
        }

        GridBagConstraints gc = new GridBagConstraints();//creates new gridbagconstraints
        gc.fill = GridBagConstraints.VERTICAL; //ADD COMMENT                //fills gridbagconstraints
        gc.insets = new Insets(10, 10, 10, 10); //Declares default spacing between objects on the 

        gc.gridx = 0; //ADD COMMENT
        gc.gridy = 0; //ADD COMMENT
        frame.add(welcome, gc);//adding welcome label to gridbag layout

        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(netCalories, gc);//adding netcalories label to gridbag layout

        gc.gridx = 0;
        gc.gridy = 2;
        frame.add(currentSavings, gc);//adding currentsavings label to gridbag layout

        gc.gridx = 2; //ADD COMMENT
        gc.gridy = 0; //ADD COMMENT
        frame.add(myProfile, gc);//adding myProfile button to gridbag layout
        myProfile.addActionListener(new ActionListener() { //to go to profile page              //Action Listener method for myProfile button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//when user clicks my profile button the visibility of the frame for the home screen will be set to false 
                new MyProfile();//displays the user's profile page
            }

        });

        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(financialAssistant, gc);//adding financial assistant button to the gridbag layout
        financialAssistant.addActionListener(new ActionListener() { //to go to financial assistant page             //action listener method for financial assistant button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//when user clicks financial assistant button the visibility of the frame for the home screen will be set to false
                new FinancialAssistant();//displays the financial assistant page
            }

        });

        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(healthAssistant, gc);//adding health assistant button to gridbag layout
        healthAssistant.addActionListener(new ActionListener() { //to go to health assistant page           //Action Listener method for health assistant button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//when user clicks health assistant button the visibility of the frame for the home screen will be set to false
                new HealthAssistant();//displays the health assistant page
            }

        });

        netCalories.setFont(font3);//setting the font for the netcalories label to font 3
        currentSavings.setFont(font3);//setting the font for the currentSavings label to font 3

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new MyManager();//calling MyManager method to display the home page

    }

}

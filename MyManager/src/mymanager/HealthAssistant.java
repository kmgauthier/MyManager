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
public class HealthAssistant extends JFrame {

    private JFrame frame = new JFrame();
    private JButton backButton, addCalsConsumed, addCalsBurn;
    private JTextField currentWeight, inAddCalsBurn, inAddCalsConsumed;
    private JLabel welcome, netCals, calsToday, BMICalc;
    private HealthData data = new HealthData();

    public HealthAssistant() {

        frame = new JFrame("Health Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        if(HealthData.read() != null){
            data = HealthData.read();
            netCals = new JLabel("Net Calories: "+data.getNetCals()); //this should look like "Net Calories: " + userNetCals
        } else {
            netCals = new JLabel("Net Calories: "); //this should look like "Net Calories: " + userNetCals
        }

        calsToday = new JLabel("Calories Today: "); //this should look like "Calories Today: " + totalCals
        currentWeight = new JTextField(20);
        backButton = new JButton("Back");
        BMICalc = new JLabel("BMI: "); //this should look like "BMI: " + bmi
        addCalsConsumed = new JButton("Add Calories");
        addCalsBurn = new JButton("Add Calories Burned");
         
        welcome = new JLabel("Welcome to the Health Assistant!");
        inAddCalsBurn = new JTextField(5); //input box for calories burned
        inAddCalsConsumed = new JTextField(5); //input box for calories consumed

        GridBagConstraints gridC = new GridBagConstraints();
        gridC.fill = GridBagConstraints.HORIZONTAL;
        gridC.insets = new Insets(10, 50, 10, 50); //TOP, LEFT, BOTTOM, RIGHT

        gridC.gridx = 0;
        gridC.gridy = 0;
        //frame.add(welcome, gridC); //Not sure we need to right welcome on every screen?

        //this block is for displaying net calories, calories consumed for today, and BMI
        gridC.gridx = 1;
        gridC.gridy = 0;
        frame.add(calsToday, gridC);
        gridC.gridx = 0;
        gridC.gridy = 0;
        frame.add(netCals, gridC);
        gridC.gridx = 2;
        gridC.gridy = 0;
        frame.add(BMICalc, gridC);

        //back button
        gridC.gridx = 0;
        gridC.gridy = 5;
        frame.add(backButton, gridC);

        //this block is for adding calories consumed
        gridC.gridx = 0;
        gridC.gridy = 3;
        frame.add(addCalsConsumed, gridC);

        addCalsConsumed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addCalsConsumed(Integer.parseInt(inAddCalsConsumed.getText()));
                HealthData.write(data);
            }
        });

        gridC.gridx = 1;
        gridC.gridy = 3;
        frame.add(inAddCalsConsumed, gridC);

        //this block is for adding calories burned
        gridC.gridx = 0;
        gridC.gridy = 4;
        frame.add(addCalsBurn, gridC);
        gridC.gridx = 1;
        gridC.gridy = 4;
        frame.add(inAddCalsBurn, gridC);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();
            }

        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

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
    private JLabel welcome, netCals, calsToday, BMICalc, calsBurned;
    private HealthData data = new HealthData();
    

    public HealthAssistant() {
        
        
        ProfileData profileData = ProfileData.read();
        frame = new JFrame("Health Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        //show net calories
        if (HealthData.read() != null) {
            data = HealthData.read();
            netCals = new JLabel("Net Calories: " + data.getNetCals()); 
        } else {
            netCals = new JLabel("Net Calories: "); 
        }
        
        //show calories burned
        if (HealthData.read() != null) {
            data = HealthData.read();
            calsBurned = new JLabel("Calories Burned: " + data.getCalsBurned()); 
        } else {
            calsBurned = new JLabel("Calories Burned: "); 
        }
        
        //show calories consumed
        if (HealthData.read() != null) {
            data = HealthData.read();
            calsToday = new JLabel("Calories Consumed: " + data.getCalsConsumed());
        } else {
            netCals = new JLabel("Calories Consumed: "); 
        }
        
        
        //bmi = calcBMI(profileData.getHeight(), profileData.getWeight());
        currentWeight = new JTextField(20);
        backButton = new JButton("Back");
        BMICalc = new JLabel("BMI: "+ calcBMI(profileData));
        //BMICalc = new JLabel("BMI: " );
        addCalsConsumed = new JButton("Add Calories");
        addCalsBurn = new JButton("Add Calories Burned");

        welcome = new JLabel("Welcome to the Health Assistant!");
        inAddCalsBurn = new JTextField("0", 5); //input box for calories burned
        inAddCalsConsumed = new JTextField("0", 5); //input box for calories consumed

        GridBagConstraints gridC = new GridBagConstraints();
        gridC.fill = GridBagConstraints.HORIZONTAL;
        gridC.insets = new Insets(10, 50, 10, 50); //TOP, LEFT, BOTTOM, RIGHT

        gridC.gridx = 0;
        gridC.gridy = 0;
        //frame.add(welcome, gridC); //Not sure we need to right welcome on every screen?

        //this block is for displaying net calories, calories consumed for today, and BMI
        gridC.gridx = 0;
        gridC.gridy = 0;
        frame.add(calsToday, gridC);
        gridC.gridx = 2;
        gridC.gridy = 0;
        frame.add(netCals, gridC);
        gridC.gridx = 3;
        gridC.gridy = 0;
        frame.add(BMICalc, gridC);
        gridC.gridx = 1;
        gridC.gridy = 0;
        frame.add(calsBurned, gridC);

        //back button
        gridC.gridx = 0;
        gridC.gridy = 5;
        frame.add(backButton, gridC);

        //this block is for adding calories consumed
        gridC.gridx = 0;
        gridC.gridy = 3;
        frame.add(addCalsConsumed, gridC);

        addCalsConsumed.addActionListener(new ActionListener() { // Adds callories consumed to net callorie intake
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addCalsConsumed(Integer.parseInt(inAddCalsConsumed.getText()));
                HealthData.write(data);
                netCals = new JLabel("Net Calories: " + data.getNetCals()); //this should look like "Net Calories: " + userNetCals
                saveData();
            }
        });

        gridC.gridx = 1;
        gridC.gridy = 3;
        frame.add(inAddCalsConsumed, gridC);

        //this block is for adding calories burned
        gridC.gridx = 0;
        gridC.gridy = 4;
        frame.add(addCalsBurn, gridC);
        
         addCalsBurn.addActionListener(new ActionListener() { // Adds callories consumed to net callorie intake
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addCalsBurned(Integer.parseInt(inAddCalsBurn.getText()));
                HealthData.write(data);
                netCals = new JLabel("Net Calories: " + data.getNetCals()); //this should look like "Net Calories: " + userNetCals
                saveData();
            }
        });
        
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

    
    private void saveData() {
        JFrame save = new JFrame("Save Successful");
        JButton ok = new JButton("OK");
        JLabel success = new JLabel("Your settings have been saved successfully!");

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.setLayout(new GridBagLayout());

        GridBagConstraints sc = new GridBagConstraints();
        sc.fill = GridBagConstraints.HORIZONTAL;
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);
                new HealthAssistant();
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }
    // returns BMI front info entered in the myProfile window
    private double calcBMI(ProfileData p) {
        double calculatedBMI;
        double heightInches = (p.getHeight().getFoot()*12 + p.getHeight().getInches());
        double heightSq = heightInches * heightInches;
        double weight = p.getWeight();
        if ((heightSq) > 0) {
            calculatedBMI = (weight / (heightSq));
            calculatedBMI *= 703;
        } else {
            calculatedBMI = 0;
        }
        calculatedBMI = (Math.round(calculatedBMI *100) / 100.0);
        return calculatedBMI;
        
    }
}

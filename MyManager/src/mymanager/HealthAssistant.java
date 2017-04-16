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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Matthew Fair
 */
public class HealthAssistant extends JFrame {
    private static final long serialVersionUID = -7346543021312279802L;

    private JFrame frame = new JFrame();
    private JButton backButton, addCalsConsumed, addCalsBurn, resetCals;
    private JTextField currentWeight, inAddCalsBurn, inAddCalsConsumed;
    private JLabel welcome, netCals, calsToday, BMICalc, calsBurned;
    private HealthData data = new HealthData();

    public HealthAssistant() {

        ProfileData profileData = ProfileData.read(); //reading from profile data
        frame = new JFrame("Health Assistant");//creates new jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        frame.setLayout(new GridBagLayout());//sets layout to gridbaglayout

        try {
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));//sets icon as mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        frame.getContentPane().setBackground(new Color(122, 94, 213));//setting background color
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creating fonts
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);

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
        //currentWeight = new JTextField(20);
        backButton = new JButton("Back");//creates back button
        BMICalc = new JLabel("BMI: " + calcBMI(profileData));//creates label for bmi calculator
        addCalsConsumed = new JButton("Add Calories");//creates button for adding calories consumed
        addCalsBurn = new JButton("Add Calories Burned");//creates button for adding calories burned
        resetCals = new JButton("Reset Calorie Data");//creates button to reset calorie data

       // currentWeight.setFont(font1);
        backButton.setFont(font1); //setting font for back button to font 1
        BMICalc.setFont(font3);//setting font for bmi calculator label to font 3
        addCalsConsumed.setFont(font1);//setting font for addCalsConsumed button to font 1
        addCalsBurn.setFont(font1);//setting font for addCalsBurn button to font 1
        calsToday.setFont(font3);//setting font for calsToday label to font 3
        netCals.setFont(font3);//setting font for netCals label to font 3
        calsBurned.setFont(font3);//setting font for calsBurned label to font 3
        addCalsBurn.setBackground(new Color(163, 146, 219));//setting background color of addCalsBurn button
        addCalsConsumed.setBackground(new Color(163, 146, 219));//setting background color of addCalsConsumed button
        resetCals.setBackground(new Color(253, 42, 42)); resetCals.setFont(font1);//setting background color and font of resetCals button
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
        frame.add(calsToday, gridC);//adding calsToday label to frame
        gridC.gridx = 2;
        gridC.gridy = 0;
        frame.add(netCals, gridC);//adding netCalories label to frame
        gridC.gridx = 3;
        gridC.gridy = 0;
        frame.add(BMICalc, gridC);//adding bmi calculator label to frame
        gridC.gridx = 1;
        gridC.gridy = 0;
        frame.add(calsBurned, gridC);//adding calories burned label to frame

        //back button
        gridC.gridx = 0;
        gridC.gridy = 5;
        frame.add(backButton, gridC);//adding back button to frame

        //this block is for adding calories consumed
        gridC.gridx = 0;
        gridC.gridy = 3;
        frame.add(addCalsConsumed, gridC);//adding calories consumed button to frame

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
        frame.add(inAddCalsConsumed, gridC);//adding Calories Consumed textfield to frame

        //this block is for adding calories burned
        gridC.gridx = 0;
        gridC.gridy = 4;
        frame.add(addCalsBurn, gridC);//adding calories burned button to frame

        addCalsBurn.addActionListener(new ActionListener() { // Adds callories consumed to net callorie intake
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addCalsBurned(Integer.parseInt(inAddCalsBurn.getText()));
                HealthData.write(data);
                netCals = new JLabel("Net Calories: " + data.getNetCals()); //this should look like "Net Calories: " + userNetCals
                saveData();
            }
        });

        //resetting the calories
        gridC.gridx = 1;
        gridC.gridy = 5;
        frame.add(resetCals, gridC);//adding reset calories button to frame
        resetCals.addActionListener(new ActionListener() //reset the calorie data
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //maybe just delete the file instead of overwrite?
                //data.reset();
                HealthData.write(data.reset());
                netCals = new JLabel("Net Calories: " + data.getNetCals());
                saveData();
            }
        });
        
        gridC.gridx = 1;
        gridC.gridy = 4;
        frame.add(inAddCalsBurn, gridC);//adding Calories Burned textfield to frame
        backButton.addActionListener(new ActionListener() {//action listener event for back button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();//returns user to homepage when back button is clicked
            }

        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void saveData() {//method that is called when user adds calories burned or consumed
        JFrame save = new JFrame("Save Successful");//creating new jframe titled "Save Successful"
        JButton ok = new JButton("OK");//creating ok button
        JLabel success = new JLabel("Your settings have been saved successfully!");//creating label to say the user's settings are saved

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends the program when the user closes the window
        save.setLayout(new GridBagLayout());//sets layout of frame to gridbaglayout

        try {
            save.setIconImage(ImageIO.read(new File("newLogo.png")));//sets icon to mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        GridBagConstraints sc = new GridBagConstraints();
        sc.fill = GridBagConstraints.HORIZONTAL;
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adding success label to frame
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adding ok button to frame

        ok.addActionListener(new ActionListener() {//action listener event for ok button
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);
                new HealthAssistant();//returns user to health assistant page when ok button is clicked
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

    // returns BMI front info entered in the myProfile window
    private double calcBMI(ProfileData p) {//method for calculating bmi
        double calculatedBMI;
        double heightInches = (p.getHeight().getFoot() * 12 + p.getHeight().getInches());
        double heightSq = heightInches * heightInches;
        double weight = p.getWeight();
        if ((heightSq) > 0) {
            calculatedBMI = (weight / (heightSq));
            calculatedBMI *= 703;
        } else {
            calculatedBMI = 0;
        }
        calculatedBMI = (Math.round(calculatedBMI * 100) / 100.0);
        return calculatedBMI;

    }
}

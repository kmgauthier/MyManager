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

    private JFrame frame = new JFrame();//creates new private jframe
    private JButton backButton, addCalsConsumed, addCalsBurn;//creates private buttons
    private JTextField currentWeight, inAddCalsBurn, inAddCalsConsumed;//creates private textfields
    private JLabel welcome, netCals, calsToday, BMICalc, calsBurned;//creates private labels
    private HealthData data = new HealthData();//creates private object of HealthData class

    public HealthAssistant() {

        ProfileData profileData = ProfileData.read();//reading data from profiledata file into variable profileDAta
        frame = new JFrame("Health Assistant");//declaring jframe and titling it "Health Assistant"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program if user closes window
        frame.setLayout(new GridBagLayout());//setting layout as gridbag layout

        try {//setting icon image as mymanager logo
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        frame.getContentPane().setBackground(new Color(73, 172, 229));//setting background color of health assistant page
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creating font for buttons
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);//creating font for labels

        //show net calories
        if (HealthData.read() != null) {//gets net calories from Health Data and returns it to net calories label
            data = HealthData.read();
            netCals = new JLabel("Net Calories: " + data.getNetCals());
        } else {
            netCals = new JLabel("Net Calories: ");
        }

        //show calories burned
        if (HealthData.read() != null) {//gets calories burned from Health Data and returns it to calories burned label
            data = HealthData.read();
            calsBurned = new JLabel("Calories Burned: " + data.getCalsBurned());
        } else {
            calsBurned = new JLabel("Calories Burned: ");
        }

        //show calories consumed
        if (HealthData.read() != null) {//gets calories consumed from Health Data and returns it to calories consumed label
            data = HealthData.read();
            calsToday = new JLabel("Calories Consumed: " + data.getCalsConsumed());
        } else {
            netCals = new JLabel("Calories Consumed: ");
        }

        //bmi = calcBMI(profileData.getHeight(), profileData.getWeight());
        //currentWeight = new JTextField(20);//declares currentWeight textfield                                 //NATHAN: I don't think we need this textfield. it currently is not being used at all and the user's weight is inputted in the profile page anyway, so I'm going to comment it out
        backButton = new JButton("Back");//declares back button
        BMICalc = new JLabel("BMI: " + calcBMI(profileData));//declares bmi calculator label
        addCalsConsumed = new JButton("Add Calories");//declares add calories button
        addCalsBurn = new JButton("Add Calories Burned");//declares add calories burned button

        //currentWeight.setFont(font1);//sets font for current weight textfield as font 1
        backButton.setFont(font1);//sets font for back button as font 1
        BMICalc.setFont(font3);//sets font for bmi calculator label as font 3
        addCalsConsumed.setFont(font1);//sets font for adds calories consumed button as font 1
        addCalsBurn.setFont(font1);//sets font for add calories burned button as font 1
        calsToday.setFont(font3);//sets font for calories consumed label as font 3
        netCals.setFont(font3);//sets font for net calories label as font 3
        calsBurned.setFont(font3);//sets font for calories burned label as font 3
        addCalsBurn.setBackground(new Color(102, 213, 247));//sets background color for add calories burned button
        addCalsConsumed.setBackground(new Color(102, 213, 247));//sets background color for add calories consumed button

        inAddCalsBurn = new JTextField("0", 5); //input box for calories burned
        inAddCalsConsumed = new JTextField("0", 5); //input box for calories consumed

        GridBagConstraints gridC = new GridBagConstraints();//creates new gridbagconstraints
        gridC.fill = GridBagConstraints.HORIZONTAL;//filling gridbagconstraints
        gridC.insets = new Insets(10, 50, 10, 50); //TOP, LEFT, BOTTOM, RIGHT

        gridC.gridx = 0;
        gridC.gridy = 0;
        //frame.add(welcome, gridC); //Not sure we need to right welcome on every screen?

        //this block is for displaying net calories, calories consumed for today, and BMI
        gridC.gridx = 0;
        gridC.gridy = 0;
        frame.add(calsToday, gridC);//adding calories consumed label to gridbag layout
        gridC.gridx = 2;
        gridC.gridy = 0;
        frame.add(netCals, gridC);//adding net calories label to gridbag layout
        gridC.gridx = 3;
        gridC.gridy = 0;
        frame.add(BMICalc, gridC);//adding bmi calculator label to gridbag layout
        gridC.gridx = 1;
        gridC.gridy = 0;
        frame.add(calsBurned, gridC);//adding calories burned label to gridbag layout

        //back button
        gridC.gridx = 0;
        gridC.gridy = 5;
        frame.add(backButton, gridC);//adding back button to gridbag layout

        //this block is for adding calories consumed
        gridC.gridx = 0;
        gridC.gridy = 3;
        frame.add(addCalsConsumed, gridC);//adding the add calories consumed button to gridbag layout

        addCalsConsumed.addActionListener(new ActionListener() { // Adds callories consumed to net calorie intake       //Action Listener for addCaloriesConsumed button
            @Override
            public void actionPerformed(ActionEvent e) {//takes value from inAddCalsConsumed textfield and writes it to HealthData text file
                data.addCalsConsumed(Integer.parseInt(inAddCalsConsumed.getText()));
                HealthData.write(data);
                netCals = new JLabel("Net Calories: " + data.getNetCals()); //this should look like "Net Calories: " + userNetCals      //updates net calories label
                saveData();//calls saveData method
            }
        });

        gridC.gridx = 1;
        gridC.gridy = 3;
        frame.add(inAddCalsConsumed, gridC);//adding inAddCalsConsumed textfield to gridbag layout

        //this block is for adding calories burned
        gridC.gridx = 0;
        gridC.gridy = 4;
        frame.add(addCalsBurn, gridC);//adding addCalsBurn button to gridbag layout

        addCalsBurn.addActionListener(new ActionListener() { // Adds callories consumed to net callorie intake              //Action Listener method for addCalsBurn button
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addCalsBurned(Integer.parseInt(inAddCalsBurn.getText()));//takes value from inAddCalsBurn textfield and writes it to HealthData text file
                HealthData.write(data);
                netCals = new JLabel("Net Calories: " + data.getNetCals()); //this should look like "Net Calories: " + userNetCals          //updates net calories label
                saveData();//calls saveData method
            }
        });

        gridC.gridx = 1;
        gridC.gridy = 4;
        frame.add(inAddCalsBurn, gridC);//adding inAddCalsBurn textfield to gridbag layout

        backButton.addActionListener(new ActionListener() {//Action Listener method for back button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//sets visibility of health assistant page to false 
                new MyManager();//returns user to home screen
            }

        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void saveData() {//saveData method
        JFrame save = new JFrame("Save Successful");//creating new jframe and titling it "Save Successful"
        JButton ok = new JButton("OK");//declaring ok button
        JLabel success = new JLabel("Your settings have been saved successfully!");//declaring success label

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program if user closes window
        save.setLayout(new GridBagLayout());//setting layout to gridbag layout

        try {//setting icon image as mymanager logo
            save.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        GridBagConstraints sc = new GridBagConstraints();//creating new gridbagconstraints
        sc.fill = GridBagConstraints.HORIZONTAL;//filling gridbagconstraints
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adding success label to gridbag layout
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adding ok button to gridbag layout

        ok.addActionListener(new ActionListener() {//action listener method for ok button
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);//setting visibility of save frame to false
                new HealthAssistant();//returns user to health assistant page
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

    // returns BMI front info entered in the myProfile window
    private double calcBMI(ProfileData p) {//method to calculate BMI
        double calculatedBMI;//double to hold the calculated bmi
        double heightInches = (p.getHeight().getFoot() * 12 + p.getHeight().getInches());//double to hold the height in inches
        double heightSq = heightInches * heightInches;//double to hold the height squared
        double weight = p.getWeight();//double to hold the weight
        if ((heightSq) > 0) {//if statement to calculate the bmi as long as the height squared is greater than 0
            calculatedBMI = (weight / (heightSq));
            calculatedBMI *= 703;
        } else {
            calculatedBMI = 0;
        }
        calculatedBMI = (Math.round(calculatedBMI * 100) / 100.0);
        return calculatedBMI;//returns the calculated bmi

    }
}

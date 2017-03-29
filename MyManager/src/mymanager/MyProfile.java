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
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Matthew Fair
 */
public class MyProfile {

    private JFrame frame;//creating jframe for MyProfile page
    private JTextField weight, heightFeet, heightInches, age, firstName, lastName, goalSavings, startingSavings, startingBalance;//creating textfields for profile page
    private JButton saveButton, backButton, resetButton;//creating buttons for profile page
    private JLabel weightLabel, heightLabel, ft, in, ageLabel, nameLabel, first, last, gender, goalSavingsLabel, startBalanceLabel, startSaveLabel;//creating labels for profile page
    private ProfileData storedData;//creating object of class ProfileData
    private JComboBox genderBox;//creating combo box for gender

    public MyProfile() {

        frame = new JFrame("My Profile");//declaring jframe and titling it "My Profile"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        frame.setLayout(new GridBagLayout());//setting layout to gridbag layout

        try {//setting icon image to my manager logo
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        frame.getContentPane().setBackground(new Color(122, 94, 213));//setting background of profile page
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creating font for labels

        weight = new JTextField("", 4);//declaring weight textfield
        heightFeet = new JTextField("", 5);//declaring height (feet) textfield
        heightInches = new JTextField("", 3);//declaring height (inches) textfield
        age = new JTextField("", 5);//declaring age textfield
        firstName = new JTextField("", 10);//declaring first name textfield
        lastName = new JTextField("", 10);//declaring last name textfield
        goalSavings = new JTextField("", 10);//declaring goal savings textfield
        startingSavings = new JTextField("", 10);//declaring starting savings textfield
        startingBalance = new JTextField("", 10);//declaring starting balance textfield

        weightLabel = new JLabel("Weight");//declaring weight label
        heightLabel = new JLabel("Height");//declaring height label
        ageLabel = new JLabel("Age");//declaring age label
        nameLabel = new JLabel("Name");//declaring name label
        first = new JLabel("First");//declaring first label
        last = new JLabel("Last");//declaring last label
        ft = new JLabel("ft");//declaring ft label
        in = new JLabel("in");//declaring in label
        gender = new JLabel("Gender");//declaring gender label
        String[] genders = {"Female", "Male"};//declaring string array for genders
        genderBox = new JComboBox(genders);//declaring gender combobox
        startBalanceLabel = new JLabel("Starting Account Balance: $");//declaring starting balance label
        startSaveLabel = new JLabel("Starting Savings Amount: $");//declaring starting savings label
        goalSavingsLabel = new JLabel("Goal Savings: $");//declaring goal savings label

        weightLabel.setFont(font1);//setting weight label font as font 1
        heightLabel.setFont(font1);//setting height label font as font 1
        ageLabel.setFont(font1);//setting age label font as font 1
        nameLabel.setFont(font1);//setting name label font as font 1
        first.setFont(font1);//setting first label font as font1
        last.setFont(font1);//setting last label font as font 1
        ft.setFont(font1);//setting ft label font as font 1
        in.setFont(font1);//setting in label font as font 1
        gender.setFont(font1);//setting gender font as font 1
        startBalanceLabel.setFont(font1);//setting starting balance label font as font 1
        startSaveLabel.setFont(font1);//setting starting savings label font as font 1
        goalSavingsLabel.setFont(font1);//setting goal savings label font as font 1

        saveButton = new JButton("Save");//declaring save button
        backButton = new JButton("Back");//declaring back button
        resetButton = new JButton("RESET ALL"); //Declare reset button for master reset of program

        saveButton.setFont(font1);//setting save button font as font 1
        backButton.setFont(font1);//setting back button font as font 1
        saveButton.setBackground(new Color(163, 146, 219));//setting background color of save button
        resetButton.setBackground(new Color(253, 42, 42)); //Set color to red

        storedData = ProfileData.read();//saving data from Profile Data into storedData
        if (storedData != null) {//to automatically display the user's saved data in the textfields
            weight.setText(Integer.toString(storedData.getWeight()));
            heightFeet.setText(Integer.toString(storedData.getHeight().getFoot()));
            heightInches.setText(Integer.toString(storedData.getHeight().getInches()));
            age.setText(Integer.toString(storedData.getAge()));
            firstName.setText(storedData.getFirstName());
            lastName.setText(storedData.getLastName());
            goalSavings.setText(Double.toString(storedData.getSavingsGoal()));
            startingSavings.setText(Double.toString(storedData.getStartSavings()));
            startingBalance.setText(Double.toString(storedData.getStartBalance()));
            genderBox.setSelectedItem(storedData.getGender());
        }

        GridBagConstraints gc = new GridBagConstraints();//new gridbag constraints
        gc.fill = GridBagConstraints.HORIZONTAL;//fills gridbag constraints
        gc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(heightLabel, gc);//adding height label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(heightFeet, gc);//adding height (feet) textfield to gridbag layout
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(ft, gc);//adding ft label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(heightInches, gc);//adding height (inches) textfield to gridbag layout
        gc.gridx = 2;
        gc.gridy = 1;
        frame.add(in, gc);//adding in label to gridbag layout
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(weightLabel, gc);//adding weight label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(weight, gc);//adding weight textfield to gridbag layout
        gc.gridx = 0;
        gc.gridy = 4;
        frame.add(ageLabel, gc);//adding age label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 4;
        frame.add(age, gc);//adding age textfield to gridbag layout
        gc.gridx = 0;
        gc.gridy = 5;
        frame.add(nameLabel, gc);//adding name label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 5;
        frame.add(firstName, gc);//adding first name textfield to gridbag layout
        gc.gridx = 2;
        gc.gridy = 5;
        frame.add(first, gc);//adding first textfield to gridbag layout
        gc.gridx = 2;
        gc.gridy = 6;
        frame.add(last, gc);//adding last textfield to gridbag layout
        gc.gridx = 1;
        gc.gridy = 6;
        frame.add(lastName, gc);//adding last name textfield to gridbag layout
        gc.gridx = 0;
        gc.gridy = 7;
        frame.add(gender, gc);//adding gender label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 7;
        frame.add(genderBox, gc);//adding gender combobox to gridbag layout
        gc.gridx = 0;
        gc.gridy = 8;
        frame.add(startBalanceLabel, gc);//adding starting balance label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 8;
        frame.add(startingBalance, gc);//adding starting balance to textfield to gridbag layout
        gc.gridx = 0;
        gc.gridy = 9;
        frame.add(startSaveLabel, gc);//adding starting savings label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 9;
        frame.add(startingSavings, gc);//adding starting savings textfield to gridbag layout
        gc.gridx = 0;
        gc.gridy = 10;
        frame.add(goalSavingsLabel, gc);//adding goal savings label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 10;
        frame.add(goalSavings, gc);//adding goal savings textfield to gridbag layout

        gc.gridx = 0;
        gc.gridy = 11;
        frame.add(backButton, gc);//adding back button to gridbag layout

        gc.gridx = 1;
        gc.gridy = 11;
        frame.add(saveButton, gc);//adding save button to gridbag layout
        
        gc.gridx = 2;
        gc.gridy = 11;
        frame.add(resetButton, gc); //Put the rest button on the screen

        backButton.addActionListener(new ActionListener() {//Action Listener method for back button 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//sets visibility of profile page to false when user clicks back
                new MyManager();//sends user to my manager home screen
            }

        });

        saveButton.addActionListener(new ActionListener() {//Action Listener method for save button
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();//calls saveData method when user clicks save button
            }
        });
        
        resetButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            SpendingData.write(new ArrayList<SpendingData>());
            FinancialData.write(new FinancialData(true));
            HealthData.write(new HealthData());
            ProfileData.write(new ProfileData());
            frame.setVisible(false);
            resetPrompt();
        }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void saveData() {
        try{
        Height saveHeight = new Height(Integer.parseInt(heightFeet.getText()), Integer.parseInt(heightInches.getText()));//declares new object of class Height and gives it parameters that the user inputted
        int saveWeight = Integer.parseInt(weight.getText());//saves user's weight
        int saveAge = Integer.parseInt(age.getText());//saves user's age
        //save gender
        double startBalance = Double.parseDouble(startingBalance.getText());//saves starting balance that user inputs
        double startSavings = Double.parseDouble(startingSavings.getText());//saves starting savings that user inputs
        double savingsGoal = Double.parseDouble(goalSavings.getText());//saves goal savings that user inputs
        String userGender = (String) genderBox.getSelectedItem();//saves user's gender to a string
        //need to have gender in here
        storedData = new ProfileData(saveHeight, saveWeight, saveAge, firstName.getText(), lastName.getText(), userGender, startBalance, startSavings, savingsGoal);//adds these variables to storedData

        ProfileData.write(storedData);
        } catch(Exception e){
            errorPrompt();
            return;
        }
        
        JFrame save = new JFrame("Save Successful");//new jframe for "Save Successful" screen
        JButton ok = new JButton("OK");//creating ok button
        JLabel success = new JLabel("Your settings have been saved successfully!");//declaring success label

        try {//setting icon image to my manager logo
            save.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        save.setLayout(new GridBagLayout());//sets layout to gridbag layout

        GridBagConstraints sc = new GridBagConstraints();//creates new gridbag constraints
        sc.fill = GridBagConstraints.HORIZONTAL;//fills gridbag constraints
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adds success label to gridbag layout
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adds ok button to gridbag layout

        ok.addActionListener(new ActionListener() {//Action Listener method for ok button
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);//sets visiblity of save frame to false
                frame.setVisible(true);//directs user back to profile page
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);

    }
    
    private void resetPrompt() {
        JFrame save = new JFrame("Reset Successful");
        JButton ok = new JButton("OK");
        JLabel success = new JLabel("Your profile has been reset. All associated Data has been erased.");

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.setLayout(new GridBagLayout());

        try {
            save.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

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
                new MyProfile();
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }
    
    
    private void errorPrompt() {
        JFrame save = new JFrame("There was an error");
        JButton ok = new JButton("OK");
        JLabel success = new JLabel("The data for one of the inputs is an invalid format. Please try again.");

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.setLayout(new GridBagLayout());

        try {
            save.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

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
                frame.setVisible(true);
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

}

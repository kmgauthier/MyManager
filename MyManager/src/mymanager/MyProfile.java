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
import javax.imageio.ImageIO;

/**
 *
 * @author Matthew Fair
 */
public class MyProfile {

    private JFrame frame; //creating private jframe
    private JTextField weight, heightFeet, heightInches, age, firstName, lastName, goalSavings, startingSavings, startingBalance;//creating private textfields
    private JButton saveButton, backButton;//creating private buttons
    private JLabel weightLabel, heightLabel, ft, in, ageLabel, nameLabel, first, last, gender, goalSavingsLabel, startBalanceLabel, startSaveLabel;//creating private labels
    private ProfileData storedData;//creating object of ProfileData class
    private JComboBox genderBox;//creating private comboBox

    public MyProfile() {

        frame = new JFrame("My Profile");//declaring the frame and titling it "My Profile"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting the program to end once you close it
        frame.setLayout(new GridBagLayout());//setting the layout of the frame to the gridbaglayout

        try {//setting the icon image to the mymanager logo
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        frame.getContentPane().setBackground(new Color(73, 172, 229));//setting the background color of profile page
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//setting font of profile page to font 1

        weight = new JTextField("", 4);//declaring weight textfield
        heightFeet = new JTextField("", 5);//declaring heightFeet textfield
        heightInches = new JTextField("", 3);//declaring heightInches textfield
        age = new JTextField("", 5);//declaring age textfield
        firstName = new JTextField("", 10);//declaring firstName textfield
        lastName = new JTextField("", 10);//declaring lastName textfield
        goalSavings = new JTextField("", 10);//declaring goalSavings textfield
        startingSavings = new JTextField("", 10);//declaring startingSavings textfield
        startingBalance = new JTextField("", 10);//declaring startingBalance textfield

        weightLabel = new JLabel("Weight");//declaring weight label
        heightLabel = new JLabel("Height");//declaring height label
        ageLabel = new JLabel("Age");//declaring age label
        nameLabel = new JLabel("Name");//declaring name label
        first = new JLabel("First");//declaring first label
        last = new JLabel("Last");//declaring last label
        ft = new JLabel("ft");//declaring feet label
        in = new JLabel("in");//declaring inches label
        gender = new JLabel("Gender");//declaring gender label
        String[] genders = {"Female", "Male"};//creating array for genders
        genderBox = new JComboBox(genders);//declaring gender combobox
        startBalanceLabel = new JLabel("Starting Account Balance: $");//declaring startbalance label
        startSaveLabel = new JLabel("Starting Savings Amount: $");//declaring starting savings label
        goalSavingsLabel = new JLabel("Goal Savings: $");//declaring goal savings label

        //setting font for labels to font1
        weightLabel.setFont(font1);
        heightLabel.setFont(font1);
        ageLabel.setFont(font1);
        nameLabel.setFont(font1);
        first.setFont(font1);
        last.setFont(font1);
        ft.setFont(font1);
        in.setFont(font1);
        gender.setFont(font1);
        startBalanceLabel.setFont(font1);
        startSaveLabel.setFont(font1);
        goalSavingsLabel.setFont(font1);

        saveButton = new JButton("Save");//declaring save button
        backButton = new JButton("Back");//declaring back button

        saveButton.setFont(font1);//setting font of save button to font 1
        backButton.setFont(font1);//setting font of back button to font 1
        saveButton.setBackground(new Color(102, 213, 247));//setting color of save button
        //backButton.setBackground(new Color(102, 213, 247));

        storedData = ProfileData.read();//reading data from ProfileData file into variable storedData
        if (storedData != null) {
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

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(heightLabel, gc);//adding height label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(heightFeet, gc);//adding heightFeet textfield into gridbag layout
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(ft, gc);//adding ft label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(heightInches, gc);//adding heightInches textfield into gridbag layout
        gc.gridx = 2;
        gc.gridy = 1;
        frame.add(in, gc);//adding in label into gridbag layout
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(weightLabel, gc);//adding weight label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(weight, gc);//adding weight textfield into gridbag layout
        gc.gridx = 0;
        gc.gridy = 4;
        frame.add(ageLabel, gc);//adding age label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 4;
        frame.add(age, gc);//adding age textfield into gridbag layout
        gc.gridx = 0;
        gc.gridy = 5;
        frame.add(nameLabel, gc);//adding name label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 5;
        frame.add(firstName, gc);//adding firstname textfield into gridbag layout
        gc.gridx = 2;
        gc.gridy = 5;
        frame.add(first, gc);//adding first label into gridbag layout
        gc.gridx = 2;
        gc.gridy = 6;
        frame.add(last, gc);//adding last label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 6;
        frame.add(lastName, gc);//adding lastname textfield into gridbag layout
        gc.gridx = 0;
        gc.gridy = 7;
        frame.add(gender, gc);//adding gender label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 7;
        frame.add(genderBox, gc);//adding gender combobox into gridbag layout
        gc.gridx = 0;
        gc.gridy = 8;
        frame.add(startBalanceLabel, gc);//adding starting balance label into grid bag layout
        gc.gridx = 1;
        gc.gridy = 8;
        frame.add(startingBalance, gc);//adding starting balance textfield into gridbag layout
        gc.gridx = 0;
        gc.gridy = 9;
        frame.add(startSaveLabel, gc);//adding starting savings label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 9;
        frame.add(startingSavings, gc);//adding starting savings textfield into gridbag layout
        gc.gridx = 0;
        gc.gridy = 10;
        frame.add(goalSavingsLabel, gc);//adding goal savings label into gridbag layout
        gc.gridx = 1;
        gc.gridy = 10;
        frame.add(goalSavings, gc);//adding goal savings textfield into gridbag layou

        gc.gridx = 0;
        gc.gridy = 11;
        frame.add(backButton, gc);//adding back button into gridbag layout

        gc.gridx = 1;
        gc.gridy = 11;
        frame.add(saveButton, gc);//adding save button into gridbag layout

        backButton.addActionListener(new ActionListener() {//setting back button to return you to home screen when you click it
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();
            }

        });

        saveButton.addActionListener(new ActionListener() {//setting save button to save your data when you click it
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void saveData() {//creating method to save data

        Height saveHeight = new Height(Integer.parseInt(heightFeet.getText()), Integer.parseInt(heightInches.getText()));//variable to store height
        int saveWeight = Integer.parseInt(weight.getText());//variable to store weight
        int saveAge = Integer.parseInt(age.getText());//variable to store age
        
        double startBalance = Double.parseDouble(startingBalance.getText());//variable to store starting balance
        double startSavings = Double.parseDouble(startingSavings.getText());//variable to store starting savings
        double savingsGoal = Double.parseDouble(goalSavings.getText());//variable to store savings goal
        String userGender = (String) genderBox.getSelectedItem();//string variable to store gender
        
        storedData = new ProfileData(saveHeight, saveWeight, saveAge, firstName.getText(), lastName.getText(), userGender, startBalance, startSavings, savingsGoal);
        //declaring object of profiledata class with the data that the user inputted
        
        
        ProfileData.write(storedData);//writes stored data to textfile

        JFrame save = new JFrame("Save Successful");//displays save window titled Save Successful
        JButton ok = new JButton("OK");//ok button
        JLabel success = new JLabel("Your settings have been saved successfully!");//success label to let the user know that their data was saved successfully

        try {//setting the icon image to the mymanager logo
            save.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ends the program when the user closes the window
        save.setLayout(new GridBagLayout());//sets layout to gridbag layout

        GridBagConstraints sc = new GridBagConstraints();//declares new gridbagconstraints
        sc.fill = GridBagConstraints.HORIZONTAL;//fills gridbagconstraints
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adds success label to grid bag layout
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adds ok button to gridbag layout

        ok.addActionListener(new ActionListener() {//when the user clicks the ok button the save successful window goes away
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);
                frame.setVisible(true);
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);//when the save successful window is shown the profile screen is not visible
        save.setVisible(true);//but the save successful window, of course, is

    }

}

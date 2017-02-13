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

/**
 *
 * @author Matthew Fair
 */
public class MyProfile {

    private JFrame frame;
    private JTextField weight, heightFeet, heightInches, age, firstName, lastName;
    private JButton saveButton, backButton;
    private JLabel weightLabel, heightLabel, ft, in, ageLabel, nameLabel, first, last;
    private WriteData newData;
    

    public MyProfile() {

        frame = new JFrame("My Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        weight = new JTextField(5);
        heightFeet = new JTextField(5);
        heightInches = new JTextField(5);
        age = new JTextField(5);
        firstName = new JTextField(5);
        lastName = new JTextField(5);

        weightLabel = new JLabel("Weight");
        heightLabel = new JLabel("Height");
        ageLabel = new JLabel("Age");
        nameLabel = new JLabel("Name");
        first = new JLabel("First");
        last = new JLabel("Last");
        ft = new JLabel("ft");
        in = new JLabel("in");
        

        saveButton = new JButton("Save");
        backButton = new JButton("Back");

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT
        
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(heightLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(heightFeet, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(ft, gc);
        gc.gridx = 3;
        gc.gridy = 0;
        frame.add(heightInches, gc);
        gc.gridx = 4;
        gc.gridy = 0;
        frame.add(in, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(weightLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(weight, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        frame.add(ageLabel, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(age, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(firstName, gc);
        gc.gridx = 2;
        gc.gridy = 3;
        frame.add(first, gc);
        gc.gridx = 4;
        gc.gridy = 3;
        frame.add(last, gc);
        gc.gridx = 3;
        gc.gridy = 3;
        frame.add(lastName, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        frame.add(backButton, gc);
    

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();
            }

        });

        gc.gridx = 1;
        gc.gridy = 4;
        frame.add(saveButton, gc);

        saveButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                saveData();
            }
        });
        
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    private void saveData(){
        
        String fullName = firstName.getText() + " " + lastName.getText();
        Height saveHeight = new Height(Integer.parseInt(heightFeet.getText()), Integer.parseInt(heightInches.getText()));
        int saveWeight = Integer.parseInt(weight.getText());
        int saveAge = Integer.parseInt(age.getText());
        
        newData = new WriteData(saveHeight, saveWeight, saveAge, fullName);
        
        
        

        
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
        
        ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
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

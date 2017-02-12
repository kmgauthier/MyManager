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
public class MyProfile {

    private JFrame frame;
    private JTextField weightInches, weightFeet, height, age, name;
    private JButton saveButton, backButton;
    private JLabel weightLabel, heightLabel, ageLabel, nameLabel;

    public MyProfile() {

        frame = new JFrame("My Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        weightFeet = new JTextField(15);
        weightInches = new JTextField(15);
        height = new JTextField(15);
        age = new JTextField(15);

        weightLabel = new JLabel("Weight");
        heightLabel = new JLabel("Height");
        ageLabel = new JLabel("Age");

        saveButton = new JButton("Save");
        backButton = new JButton("Back");

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50); //TOP, LEFT, BOTTOM, RIGHT

        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(weightLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(weightFeet, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(weightInches, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(heightLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(height, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        frame.add(ageLabel, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(age, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(backButton, gc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();
            }

        });

        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(saveButton, gc);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}

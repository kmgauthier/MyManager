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
    private JButton backButton; // = new JButton("Back");
    private JTextField currentWeight;
    private JLabel welcome;

    public HealthAssistant() {

        frame = new JFrame("Health Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        currentWeight = new JTextField(20);
        backButton = new JButton("Back");
        welcome = new JLabel("Welcome to the Health Assistant!");

        GridBagConstraints gridC = new GridBagConstraints();
        gridC.fill = GridBagConstraints.HORIZONTAL;
        gridC.insets = new Insets(10, 50, 10, 50); //TOP, LEFT, BOTTOM, RIGHT

        gridC.gridx = 0;
        gridC.gridy = 0;
        frame.add(welcome, gridC);

        gridC.gridx = 0;
        gridC.gridy = 1;
        frame.add(currentWeight, gridC);

        gridC.gridx = 0;
        gridC.gridy = 2;
        frame.add(backButton, gridC);

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

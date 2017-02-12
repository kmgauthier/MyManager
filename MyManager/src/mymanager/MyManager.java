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
public class MyManager extends JFrame {

    JFrame frame;
    JButton financialAssistant, healthAssistant, myProfile;
    JLabel welcome;

    public MyManager() {

        frame = new JFrame("My Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        financialAssistant = new JButton("Financial Assistant");
        healthAssistant = new JButton("Health Assistant");
        myProfile = new JButton("My Profile");
        welcome = new JLabel("Welcome to My Manager!");

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.VERTICAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(welcome, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(myProfile, gc);
        myProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyProfile();
            }

        });

        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(financialAssistant, gc);
        financialAssistant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new FinancialAssistant();
            }

        });

        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(healthAssistant, gc);
        healthAssistant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new HealthAssistant();
            }

        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new MyManager();

    }

}

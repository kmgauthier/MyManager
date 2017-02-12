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

public class FinancialAssistant extends JFrame {

    private JFrame frame;
    private JButton backButton;
    private JLabel welcome;

    public FinancialAssistant() {

        frame = new JFrame("Financial Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        backButton = new JButton("Back");
        welcome = new JLabel("Welcome to the Financial Assistant!");
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50);
        
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(welcome, gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(backButton, gc);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author kmgauthier
 */

public class SpendingsHistory extends JFrame {
    
    private JFrame frame;
    private JLabel date, description, cost;
    private JButton backButton;
    
    
    public SpendingsHistory(){
        
        frame = new JFrame("Spendings History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        date = new JLabel("Date");
        description = new JLabel("Description");
        cost = new JLabel("Cost");
        backButton = new JButton("Back");
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50);
        
        //headers to the history
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(date, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(description, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(cost, gc);
        
        //back button
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(backButton, gc);
        
        backButton.addActionListener(new ActionListener() { //go to home page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new FinancialAssistant();
            }

        });
                
        
    
    }
}

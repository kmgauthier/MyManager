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
    
    
    public HealthAssistant(){
        
        /*
        backButton.setBounds(60, 400, 220, 30);
        pnlButton.setBounds(800,800,200,100);
        
        pnlButton.add(backButton);
        add(pnlButton);
        pnlButton.setBackground(Color.WHITE);
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new MyManager();
            }
        
        });
        
        setSize(400, 400);
        setBackground(Color.BLACK);
        setTitle("Health Assistant");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        */
        
        
        frame = new JFrame("Health Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        
        currentWeight = new JTextField(5);
        backButton = new JButton("Set Weight");
        
        GridBagConstraints gridC = new GridBagConstraints();
        gridC.fill = GridBagConstraints.HORIZONTAL;
        gridC.insets = new Insets(10, 10, 10, 10);
        
        gridC.gridx = 0;
        gridC.gridy = 0;
        frame.add(currentWeight, gridC);
        
        gridC.gridx = 5;
        gridC.gridy = 0;
        frame.add(backButton, gridC);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    
}

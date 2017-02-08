/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;



/**
 *
 * @author Matthew Fair
 */
public class HealthAssistant extends JFrame {
    JPanel pnlButton = new JPanel();
    
    JButton backButton = new JButton("Back");
    FlowLayout layout = new FlowLayout(400);
    
    public HealthAssistant(){
        this.setLayout(layout);
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
    }
    
    
}

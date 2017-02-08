/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

import javax.swing.*;
import java.awt.Color;

public class FinancialAssistant extends JFrame {
    
    JPanel pnlButton = new JPanel();
    
    JButton billsButton = new JButton("Monthly Bills");
    
    public FinancialAssistant(){
        
        billsButton.setBounds(60, 400, 220, 30);
        pnlButton.setBounds(800,800,200,100);
        
        pnlButton.add(billsButton);
        add(pnlButton);
        
        setSize(400, 400);
        setBackground(Color.BLACK);
        setTitle("Financial Assistant");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
              
    }
    
    
}

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
    private JButton backButton, addSavings, addIncome, addSpendings, spendHistory;
    private JLabel welcome, goalSavings, totalSavings, totalAccount, weeklySpendings;
    private JTextField inSavings, inIncome, inSpent, inDate, inDescript; //inputs for what was saved, spent, or income

    public FinancialAssistant() {

        frame = new JFrame("Financial Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        backButton = new JButton("Back");
        addSavings = new JButton("Add Savings");
        addIncome = new JButton("Add Income");
        addSpendings = new JButton("Add Spendings");
        spendHistory = new JButton("Spendings History");
        welcome = new JLabel("Welcome to the Financial Assistant!");
        weeklySpendings = new JLabel("Spent This Week: $"); //should look like "Spent This Week: $" + weekSpent
        totalSavings = new JLabel("Total Savings: $"); //should look like "Total Savings: $" + savings
        goalSavings = new JLabel("Goal Savings: $"); //should look like "Goal Savings: $" + goalSavings
        totalAccount = new JLabel("Total Accounts: ???");
        inDate = new JTextField("MM/DD/YY", 15);
        inDescript = new JTextField("Desciption", 15);
        inSpent = new JTextField("Cost", 15);
        inSavings = new JTextField("Savings", 15);
        inIncome = new JTextField("Income?", 15);
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50);
        
        gc.gridx = 0;
        gc.gridy = 0;
        //frame.add(welcome, gc); //do not really need welcome on every screen?
        
        //display weekly spendings and total savings and goal savings and total account balance
        gc.gridx=0;
        gc.gridy=0;
        frame.add(totalSavings, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(goalSavings, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(totalAccount, gc); //total money in account, not savings
        gc.gridx = 3;
        gc.gridy = 0;
        frame.add(weeklySpendings, gc);
        
        //adding amount to savings
        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(addSavings, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(inSavings, gc);
        
        //adding amount to income/acccount
        gc.gridx = 0;
        gc.gridy = 2;
        frame.add(addIncome, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(inIncome, gc);
        
        //adding amount spent
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(inDate, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(inDescript, gc);
        gc.gridx = 2;
        gc.gridy = 3;
        frame.add(inSpent, gc);
        gc.gridx = 3;
        gc.gridy = 3;
        frame.add(addSpendings, gc);
        
        //spending history button
        gc.gridx = 1;
        gc.gridy = 4;
        frame.add(spendHistory, gc);
        
        gc.gridx = 0;
        gc.gridy = 4;
        frame.add(backButton, gc);
        
        backButton.addActionListener(new ActionListener() { //go to home page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();
            }

        });
        
        spendHistory.addActionListener(new ActionListener() { //go to SpendingsHistory page
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                new SpendingsHistory();
            }
        });
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

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
    private JLabel goalSavings, totalSavings, totalAccount, weeklySpendings;
    private JTextField inSavings, inIncome, inSpent, inDate, inDescript; //inputs for what was saved, spent, or income

    public FinancialAssistant() {

        frame = new JFrame("Financial Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        backButton = new JButton("Back");
        addSavings = new JButton("Add Savings");
        addIncome = new JButton("Add Income");
        addSpendings = new JButton("Add Spendings");
        spendHistory = new JButton("Spending History");
        weeklySpendings = new JLabel("Spent This Week: $"); //should look like "Spent This Week: $" + weekSpent
        
        //total savings and goal savings and account total
        ProfileData storedData = ProfileData.read();
        if (storedData != null) {                                             
            FinancialData finData = FinancialData.read();
            if (finData != null) {    
                totalSavings = new JLabel("Savings Balance: $" + finData.getCurrentSavingsBalance());
                totalAccount = new JLabel("Account Balance: $" + finData.getCurrentAccountBalance());
            } else {
                totalSavings = new JLabel("Savings Balance: $" + storedData.getStartSavings()); 
                totalAccount = new JLabel("Account Balance: $" + storedData.getStartBalance());
            }
            goalSavings = new JLabel("Goal Savings: $" + storedData.getSavingsGoal()); 
        } 
        else {
            totalSavings = new JLabel("Savings Balance: $0"); 
            goalSavings = new JLabel("Goal Savings: No Goal Set"); 
        }
        
        
        //totalAccount = new JLabel("Account Total: $");//should look like "Account Balance: $" + balanceAccount (for how much money is in general account, not savings)
        inDate = new JTextField("MM/DD/YY", 15);
        inDescript = new JTextField("Desciption", 15);
        inSpent = new JTextField("Cost", 15);
        inSavings = new JTextField("Savings", 15);
        inIncome = new JTextField("Income", 15);

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50);


        //display weekly spendings and total savings and goal savings and total account balance
        gc.gridx = 0;
        gc.gridy = 0;
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
        frame.add(inSavings, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(addSavings, gc);
        addSavings.addActionListener(new ActionListener() { //go to home page
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inSavings.getText().equals("Savings") || Double.parseDouble(inSavings.getText()) < 0) {
                    frame.setVisible(false);
                    errorPrompt();
                } else {
                    FinancialData data = FinancialData.read();
                    if (data != null) {
                        data.addSavings(Double.parseDouble(inSavings.getText()));
                        FinancialData.write(data);
                        frame.setVisible(false);
                        saveData();
                    } else {
                        data = new FinancialData();
                        FinancialData.write(data);
                        frame.setVisible(false);
                        saveData();
                    }
                }
            }

        });
        
        //adding amount to income/acccount
        gc.gridx = 0;
        gc.gridy = 2;
        frame.add(inIncome, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(addIncome, gc);
        addIncome.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent E){
                if(inIncome.getText().equals("Income") || Double.parseDouble(inIncome.getText()) < 0)
                {   
                    frame.setVisible(false);
                    errorPrompt();
                }
                else{
                    FinancialData data = FinancialData.read();
                    if (data != null) {
                        data.addIncome(Double.parseDouble(inIncome.getText()));
                        FinancialData.write(data);
                        frame.setVisible(false);
                        saveData();
                    } else {
                        data = new FinancialData();
                        FinancialData.write(data);
                        frame.setVisible(false);
                        saveData();
                    }
                }  
                
            }
        });
        
    
     
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
        gc.gridy = 6;
        frame.add(spendHistory, gc);

        gc.gridx = 0;
        gc.gridy = 6;
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
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SpendingsHistory();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void saveData() {
        JFrame save = new JFrame("Deposit Successful");
        JButton ok = new JButton("OK");
        JLabel success = new JLabel("$" + inSavings.getText() + " has been added to your savings!");

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

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);
                new FinancialAssistant();
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

    private void errorPrompt() {
        JFrame save = new JFrame("There was an error");
        JButton ok = new JButton("OK");
        JLabel success = new JLabel("The amount you're adding to the account is invalid. Please try again.");

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

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);
                new FinancialAssistant();
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

}

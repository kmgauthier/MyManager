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
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Locale;

public class FinancialAssistant extends JFrame {

    private JFrame frame;
    private JButton backButton, addSavings, addIncome, addSpendings, spendHistory;
    private JLabel goalSavings, totalSavings, totalAccount, percentToGoal; //weeklySpendings;
    private JTextField inSavings, inIncome, inSpent, inDate, inDescript; //inputs for what was saved, spent, or income
    private ArrayList<SpendingData> spendingHistory = new ArrayList<SpendingData>();

    public FinancialAssistant() {

        frame = new JFrame("Financial Assistant");//creates jframe for financial assistant
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        frame.setLayout(new GridBagLayout());//sets layout of frame to gridbaglayout

        try {
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));//sets icon to mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        frame.getContentPane().setBackground(new Color(122, 94, 213));//sets background color of frame
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creating fonts
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);

        backButton = new JButton("Back");//creates back button
        addSavings = new JButton("Add Savings");//creates add savings button
        addIncome = new JButton("Add Income");//creates add income button
        addSpendings = new JButton("Add Spendings");//creates add spending button
        spendHistory = new JButton("Spending History");//creates spending history button
        //weeklySpendings = new JLabel("Spent This Week: $"); //should look like "Spent This Week: $" + weekSpent

        backButton.setFont(font1);//setting font of back button to font 1
        addSavings.setFont(font1);//setting font of add savings button to font 1
        addSavings.setBackground(new Color(163, 146, 219));//setting background color of add savings button
        addIncome.setFont(font1);//setting font of add income button to font 1
        addIncome.setBackground(new Color(163, 146, 219));//setting background color of add income button
        addSpendings.setFont(font1);//setting font of add spendings button to font 1
        addSpendings.setBackground(new Color(163, 146, 219));//setting background color of add spendings button
        spendHistory.setFont(font1);//setting font of spending history button to font 1
        spendHistory.setBackground(new Color(163, 146, 219));//setting background color of spending history button
        //weeklySpendings.setFont(font3);

        DecimalFormat moneyFormat = new DecimalFormat("#,###,###,##0.00");//setting decimal format
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);//setting currency format
        
       //(finData.getCurrentSavingsBalance()/ storedData.getSavingsGoal())*100.00)
        
        //total savings and goal savings and account total
        ProfileData storedData = ProfileData.read();//reading profile data
        if (storedData != null) {
            FinancialData finData = FinancialData.read();
            if (finData != null) {
                if(finData.getCurrentSavingsBalance() == 0 || finData.getResetStatus()){
                    totalSavings = new JLabel("Savings Balance: $" + moneyFormat.format(storedData.getStartSavings()));
                    totalAccount = new JLabel("Account Balance: $" + moneyFormat.format(storedData.getStartBalance()));
                    double roundedPercent = (finData.getCurrentSavingsBalance()/ storedData.getSavingsGoal())*100.00;
                    DecimalFormat format = new DecimalFormat("#.00");
                    percentToGoal = new JLabel(" Percentage Towards Goal: "+ format.format(roundedPercent) + "%");
                    finData.addSavings(storedData.getStartSavings());
                    finData.addIncome(storedData.getStartBalance());
                    FinancialData.write(finData);
                } else {
                    totalSavings = new JLabel("Savings Balance: $" + moneyFormat.format(finData.getCurrentSavingsBalance()));
                    totalAccount = new JLabel("Account Balance: $" + moneyFormat.format(finData.getCurrentAccountBalance()));
                    double roundedPercent = (finData.getCurrentSavingsBalance()/ storedData.getSavingsGoal())*100.00;
                    DecimalFormat format = new DecimalFormat("#.00");
                    percentToGoal = new JLabel(" Percentage Towards Goal: "+ format.format(roundedPercent) + "%");
                }
            } else {
                double roundedPercent = (finData.getCurrentSavingsBalance()/ storedData.getSavingsGoal())*100.00;
                DecimalFormat format = new DecimalFormat("#.00");
                totalSavings = new JLabel("Savings Balance: $" + moneyFormat.format(storedData.getStartBalance()));
                percentToGoal = new JLabel(" Percentage Towards Goal: "+ format.format(roundedPercent) + "%");
            }
            double roundedPercent = (finData.getCurrentSavingsBalance()/ storedData.getSavingsGoal())*100.00;
            DecimalFormat format = new DecimalFormat("#.00");
            goalSavings = new JLabel("Goal Savings: $" + moneyFormat.format(storedData.getSavingsGoal()));
            percentToGoal = new JLabel(" Percentage Towards Goal: "+ format.format(roundedPercent) + "%");
        } else {
            totalSavings = new JLabel("Savings Balance: $0");
            goalSavings = new JLabel("Goal Savings: No Goal Set");
            percentToGoal = new JLabel(" Percentage Towards Goal: 0%");
        }

        totalSavings.setFont(font3);//sets font for total Savings label to font 3
        totalAccount.setFont(font3);//sets font of total account balance label to font 3
        goalSavings.setFont(font3);//sets font of goal savings label to font 3
        percentToGoal.setFont(font3);//sets font for percenttogoal label to font 3

        //totalAccount = new JLabel("Account Total: $");//should look like "Account Balance: $" + balanceAccount (for how much money is in general account, not savings)
        inDate = new JTextField("MM/DD/YY", 15);//creates date textfield
        inDescript = new JTextField("Description", 15);//creates Description textfield
        inSpent = new JTextField("Cost", 15);//creates amount spent textfield
        inSavings = new JTextField("Savings", 15);//creates Savings textfield
        inIncome = new JTextField("Income", 15);//creates income textfield

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50);

        //display weekly spendings and total savings and goal savings and total account balance
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(totalSavings, gc);//adding totalSavings label to frame
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(goalSavings, gc);//adding goal savings label to frame
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(totalAccount, gc);//adding account balance label to frame
        gc.gridx = 3;
        gc.gridy = 0;
        
        frame.add(percentToGoal,gc);//adding percentToGoal label to frame
        
//total money in account, not savings
        //gc.gridx = 3;
        //gc.gridy = 0;
        //frame.add(weeklySpendings, gc);

        //adding amount to savings
        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(inSavings, gc);//adding Savings textfield to frame
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(addSavings, gc);//adding add Savings button to frame
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
        frame.add(inIncome, gc);//adding Income textfield to frame
        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(addIncome, gc);//adding addIncome button to frame
        addIncome.addActionListener(new ActionListener() {//action listener method for addIncome button
            @Override
            public void actionPerformed(ActionEvent E) {
                if (inIncome.getText().equals("Income") || Double.parseDouble(inIncome.getText()) < 0) {
                    frame.setVisible(false);
                    errorPrompt();
                } else {
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
        frame.add(inDate, gc);//adding Date textfield to frame
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(inDescript, gc);//adding description textfield to frame
        gc.gridx = 2;
        gc.gridy = 3;
        frame.add(inSpent, gc);//adding amount spent textfield to frame
        gc.gridx = 3;
        gc.gridy = 3;
        frame.add(addSpendings, gc);//adding addSpending button to frame
        addSpendings.addActionListener(new ActionListener() { //go to SpendingsHistory page
            @Override
            public void actionPerformed(ActionEvent e) {

                if (inSpent.getText().equals("Cost") || Double.parseDouble(inSpent.getText()) < 0) {
                    frame.setVisible(false);
                    errorPrompt();
                } else {
                    double amount = Double.parseDouble(inSpent.getText());
                    SpendingData sd = new SpendingData(inDate.getText(), inDescript.getText(), amount);
                    sd.addSpending(sd);
                    //spendingHistory.add(sd); // loop thru for each 
                    FinancialData data = FinancialData.read();
                    if (data != null) {
                        data.removeIncome(amount);
                        FinancialData.write(data);
                        frame.setVisible(false);
                        saveDataSpend();
                    } else {
                        data = new FinancialData();
                        FinancialData.write(data);
                        frame.setVisible(false);
                        saveDataSpend();
                    }
                }
            }
        });

        gc.gridx = 0;
        gc.gridy = 6;
        frame.add(backButton, gc);//adding back button to frame
        backButton.addActionListener(new ActionListener() { //go to home page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();//returns user to mymanager homepage
            }

        });

        //spending history button
        gc.gridx = 1;
        gc.gridy = 6;
        frame.add(spendHistory, gc);//adding spending history button to frame
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

    private void saveData() {//method to save savings and income data that user enters
        JFrame save = new JFrame("Deposit Successful");//creates new jframe titled "Deposit Successful"
        JButton ok = new JButton("OK");//creates ok button
        JLabel success = new JLabel("$" + inSavings.getText() + " has been added to your savings!");//creates success label

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        save.setLayout(new GridBagLayout());//sets layout of frame to gridbaglayout

        try {
            save.setIconImage(ImageIO.read(new File("newLogo.png")));//sets icon to mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        GridBagConstraints sc = new GridBagConstraints();
        sc.fill = GridBagConstraints.HORIZONTAL;
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adding success label to frame
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adding ok button to frame

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

    private void saveDataSpend() {//method to save spending history data
        JFrame save = new JFrame("Spending Added Successfully");//creates new jframe titled "Spending Added Successfully"
        JButton ok = new JButton("OK");//creates new ok button
        JLabel success = new JLabel("$" + inSpent.getText() + " has been removed from your account balance");//creates new success label

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        save.setLayout(new GridBagLayout());//sets layout of frame to gridbaglayout

        try {
            save.setIconImage(ImageIO.read(new File("newLogo.png")));//sets icon of frame as mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        GridBagConstraints sc = new GridBagConstraints();
        sc.fill = GridBagConstraints.HORIZONTAL;
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adding success label to frame
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adding ok button to frame

        ok.addActionListener(new ActionListener() {//action listener method for ok button
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);
                new FinancialAssistant();//returns user to financial assistant page
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

    private void errorPrompt() {//error prompt method
        JFrame save = new JFrame("There was an error");//creates new jframe titled "There was an Error"
        JButton ok = new JButton("OK");//creates ok button
        JLabel success = new JLabel("The amount you're adding to the account is invalid. Please try again.");//creates success label

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        save.setLayout(new GridBagLayout());//sets layout of frame to gridbaglayout

        try {
            save.setIconImage(ImageIO.read(new File("newLogo.png")));//sets icon to mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        GridBagConstraints sc = new GridBagConstraints();
        sc.fill = GridBagConstraints.HORIZONTAL;
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adding success label to frame
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adding ok button to frame

        ok.addActionListener(new ActionListener() {//action listener method for ok button
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);
                new FinancialAssistant();//returns user to financial assistant page
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

}

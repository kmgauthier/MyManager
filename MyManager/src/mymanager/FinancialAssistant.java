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
import javax.imageio.ImageIO;

public class FinancialAssistant extends JFrame {

    private JFrame frame;//creates private jframe
    private JButton backButton, addSavings, addIncome, addSpendings, spendHistory;//creates private buttons
    private JLabel goalSavings, totalSavings, totalAccount; //weeklySpendings;          //creates private labels
    private JTextField inSavings, inIncome, inSpent, inDate, inDescript; //inputs for what was saved, spent, or income          //creates private textfields

    public FinancialAssistant() {

        frame = new JFrame("Financial Assistant");//titling jframe "Financial Assistant"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the program when the user closes the window
        frame.setLayout(new GridBagLayout());//sets the layout of the frame to the gridbag layout

        try {//sets the icon image of this page to the mymanager logo
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        frame.getContentPane().setBackground(new Color(73, 172, 229));//sets the background color of the financial assistant page
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creates font for the buttons
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);//creates font for the labels

        backButton = new JButton("Back");//declares back button
        addSavings = new JButton("Add Savings");//declares add savings button
        addIncome = new JButton("Add Income");//declares add income button
        addSpendings = new JButton("Add Spendings");//declares the add spending button
        spendHistory = new JButton("Spending History");//declares the spending history button
        //weeklySpendings = new JLabel("Spent This Week: $"); //should look like "Spent This Week: $" + weekSpent

        backButton.setFont(font1);//setting the font for the back button to font 1
        addSavings.setFont(font1);//setting the font for the add savings button to font 1
        addSavings.setBackground(new Color(102, 213, 247));//setting the background color for the add savings button
        addIncome.setFont(font1);//setting the font for the add income button to font 1
        addIncome.setBackground(new Color(102, 213, 247));//setting the background color for the add income button
        addSpendings.setFont(font1);//setting the font for the add spending button to font 1
        addSpendings.setBackground(new Color(102, 213, 247));//setting the background color for the add spending button
        spendHistory.setFont(font1);//setting the font for the spending history button to font 1
        spendHistory.setBackground(new Color(102, 213, 247));//setting the background color for the spending history button
        //weeklySpendings.setFont(font3);

        //total savings and goal savings and account total
        ProfileData storedData = ProfileData.read();//reading profile data into variable storedData
        if (storedData != null) {//if storedData is not null
            FinancialData finData = FinancialData.read();//read financial data into variable finData
            if (finData != null) {//if finData is not null
                totalSavings = new JLabel("Savings Balance: $" + finData.getCurrentSavingsBalance());//displaying current savings balance in totalSavings label
                totalAccount = new JLabel("Account Balance: $" + finData.getCurrentAccountBalance());//displaying current account balance in totalAccount label
            } else {
                totalSavings = new JLabel("Savings Balance: $" + storedData.getStartSavings());//if finData is null it will show the user's starting savings from profile page
                totalAccount = new JLabel("Account Balance: $" + storedData.getStartBalance());//if finData is null it will show the user's starting account balance from profile page
            }
            goalSavings = new JLabel("Goal Savings: $" + storedData.getSavingsGoal());//shows the user's savings goal in goalsavings label
        } else {
            totalSavings = new JLabel("Savings Balance: $0");//if storedData is null it will show $0 as savings balance
            goalSavings = new JLabel("Goal Savings: No Goal Set");//if storedData is null it will show "No Goal set"
        }

        totalSavings.setFont(font3);//sets totalSavings label font as font 3
        totalAccount.setFont(font3);//sets totalAccount label font as font 3
        goalSavings.setFont(font3);//sets goalSavings label font as font 3

        //totalAccount = new JLabel("Account Total: $");//should look like "Account Balance: $" + balanceAccount (for how much money is in general account, not savings)
        inDate = new JTextField("MM/DD/YY", 15);//declaring inDate textfield
        inDescript = new JTextField("Description", 15);//declaring inDescript textfield
        inSpent = new JTextField("Cost", 15);//declaring inSpent textfield
        inSavings = new JTextField("Savings", 15);//declaring inSavings textfield
        inIncome = new JTextField("Income", 15);//declaring inIncome textfield

        GridBagConstraints gc = new GridBagConstraints();//creating new gridbagconstraints
        gc.fill = GridBagConstraints.HORIZONTAL;//filling gridbagconstraints
        gc.insets = new Insets(10, 50, 10, 50);

        //display weekly spendings and total savings and goal savings and total account balance
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(totalSavings, gc);//adding totalSavings label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(goalSavings, gc);//adding goalSavings label to gridbag layout
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(totalAccount, gc); //total money in account, not savings        //adding totalAccount label to gridbag layout
        //gc.gridx = 3;
        //gc.gridy = 0;
        //frame.add(weeklySpendings, gc);

        //adding amount to savings
        gc.gridx = 0;
        gc.gridy = 1;
        frame.add(inSavings, gc);//adding inSavings textfield to gridbag layout
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(addSavings, gc);//adding addSavings button to gridbag layout
        addSavings.addActionListener(new ActionListener() { //go to home page       //actionListener method for addSavings button
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inSavings.getText().equals("Savings") || Double.parseDouble(inSavings.getText()) < 0) {//displays error message if user clicked add savings button without adding amount or adding an amout equal to or less than 0
                    frame.setVisible(false);
                    errorPrompt();
                } else {
                    FinancialData data = FinancialData.read();
                    if (data != null) {                                 //else the data from insavings is saved
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
        frame.add(inIncome, gc);//adding the inIncome textfield to gridbag layout
        gc.gridx = 1;
        gc.gridy = 2;
        frame.add(addIncome, gc);//adding the addincome button to gridbag layout
        addIncome.addActionListener(new ActionListener() {//action listener method for addIncome button
            @Override
            public void actionPerformed(ActionEvent E) {
                if (inIncome.getText().equals("Income") || Double.parseDouble(inIncome.getText()) < 0) {//displays error message if user clicked button without inputting an amount or putting in an amount equal to or below 0
                    frame.setVisible(false);
                    errorPrompt();
                } else {
                    FinancialData data = FinancialData.read();
                    if (data != null) {                                     //else the data from inIncome is saved
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
        frame.add(inDate, gc);//adding inDate textfield to gridbag layout
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(inDescript, gc);//adding inDescript textfield to gridbag layout
        gc.gridx = 2;
        gc.gridy = 3;
        frame.add(inSpent, gc);//adding inSpent textfield to gridbag layout
        gc.gridx = 3;
        gc.gridy = 3;
        frame.add(addSpendings, gc);//adding addSpendings button to gridbag layout
        addSpendings.addActionListener(new ActionListener() { //go to SpendingsHistory page         //Action Listener method for addSpendings button
            @Override
            public void actionPerformed(ActionEvent e) {
                SpendingData sd = new SpendingData();//creates SpendingData object
                sd.write(inDate.getText(), inDescript.getText(), Double.parseDouble(inSpent.getText()));//writes date, description, and spending amount the user inputted to Spending History database
            }
        });

        //spending history button
        gc.gridx = 1;
        gc.gridy = 6;
        frame.add(spendHistory, gc);//adding spending history button to gridbag layout

        gc.gridx = 0;
        gc.gridy = 6;
        frame.add(backButton, gc);//adding back button to gridbag layout

        backButton.addActionListener(new ActionListener() { //go to home page               //Action Listener for back button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//sets visibility of financial assstant page to false and takes user back to home screen
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

    private void saveData() {//saveData method
        JFrame save = new JFrame("Deposit Successful");//declaring new jframe to let user know it saved their data
        JButton ok = new JButton("OK");//declaring ok button
        JLabel success = new JLabel("$" + inSavings.getText() + " has been added to your savings!");//declaring success label to show how much has been added to savings

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends the program when the user closes the window
        save.setLayout(new GridBagLayout());//setting layout as gridbaglayout
                
        try {//setting the icon image as the mymanager logo
            save.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        GridBagConstraints sc = new GridBagConstraints();//creating new gridbagconstraints
        sc.fill = GridBagConstraints.HORIZONTAL;//filling gridbagconstraints
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adding success label to gridbaglayout
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adding ok button to gridbaglayout

        ok.addActionListener(new ActionListener() {//Action Listener for ok button
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);//setting visibility of save window to false when ok button is clicked
                new FinancialAssistant();//returns user to financial assistant page
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

    private void errorPrompt() {//error prompt for when the user inputs an invalid amount into savings or income fields
        JFrame save = new JFrame("There was an error");//declaring jframe and titling the jframe as "there was an error"
        JButton ok = new JButton("OK");//declaring ok button
        JLabel success = new JLabel("The amount you're adding to the account is invalid. Please try again.");//declaring success label

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends the program if the user closes the window
        save.setLayout(new GridBagLayout());//setting layout as gridbaglayout

        try {//setting icon image of window as the mymanager logo
            save.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        GridBagConstraints sc = new GridBagConstraints();//creating new gridbagconstraints
        sc.fill = GridBagConstraints.HORIZONTAL;//filling gridbagconstraints
        sc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT

        sc.gridx = 0;
        sc.gridy = 0;
        save.add(success, sc);//adding success label to gridbaglayout
        sc.gridx = 0;
        sc.gridy = 1;
        save.add(ok, sc);//adding ok button to gridbaglayout

        ok.addActionListener(new ActionListener() {//Action Listener for ok button
            @Override
            public void actionPerformed(ActionEvent e) {
                save.setVisible(false);//sets visibility of window to false when ok button is clicked
                new FinancialAssistant();//returns user to financial assistant page
            }
        });

        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
    }

}

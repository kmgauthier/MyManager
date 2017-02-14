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
import java.io.*;

/**
 *
 * @author Matthew Fair
 */
public class MyProfile {

    private JFrame frame;
    private JTextField weight, heightFeet, heightInches, age, firstName, lastName, goalSavings, startingSavings, startingBalance;
    private JButton saveButton, backButton;
    private JLabel weightLabel, heightLabel, ft, in, ageLabel, nameLabel, first, last, gender, goalSavingsLabel, startBalanceLabel, startSaveLabel;
    private WriteData storedData;
    private JComboBox genderBox;

    public MyProfile() {
        
        frame = new JFrame("My Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        weight = new JTextField("", 4);
        heightFeet = new JTextField("", 5);
        heightInches = new JTextField("", 3);
        age = new JTextField("", 5);
        firstName = new JTextField("", 10);
        lastName = new JTextField("", 10);
        goalSavings = new JTextField("", 10);
        startingSavings = new JTextField("", 10);
        startingBalance = new JTextField("", 10);

        weightLabel = new JLabel("Weight");
        heightLabel = new JLabel("Height");
        ageLabel = new JLabel("Age");
        nameLabel = new JLabel("Name");
        first = new JLabel("First");
        last = new JLabel("Last");
        ft = new JLabel("ft");
        in = new JLabel("in");
        gender = new JLabel("Gender");
        String[] genders = {"Female", "Male"};
        genderBox = new JComboBox(genders);
        startBalanceLabel = new JLabel("Starting Account Balance: $");
        startSaveLabel = new JLabel("Starting Savings Amount: $");
        goalSavingsLabel = new JLabel("Goal Savings: $");
        

        saveButton = new JButton("Save");
        backButton = new JButton("Back");
        
        
        storedData = WriteData.read();
        if(storedData != null){
            weight.setText(Integer.toString(storedData.getWeight()));
            heightFeet.setText(Integer.toString(storedData.getHeight().getFoot()));
            heightInches.setText(Integer.toString(storedData.getHeight().getInches()));
            age.setText(Integer.toString(storedData.getAge()));
            firstName.setText(storedData.getFirstName());
            lastName.setText(storedData.getLastName());
            goalSavings.setText(Double.toString(storedData.getSavingsGoal()));
            startingSavings.setText(Double.toString(storedData.getStartSavings()));
            startingBalance.setText(Double.toString(storedData.getStartBalance()));
            genderBox.setSelectedItem(storedData.getGender());
        } 
        
        



        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 2, 10, 2); //TOP, LEFT, BOTTOM, RIGHT
        
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(heightLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(heightFeet, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(ft, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        frame.add(heightInches, gc);
        gc.gridx = 2;
        gc.gridy = 1;
        frame.add(in, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        frame.add(weightLabel, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        frame.add(weight, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        frame.add(ageLabel, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        frame.add(age, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        frame.add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        frame.add(firstName, gc);
        gc.gridx = 2;
        gc.gridy = 5;
        frame.add(first, gc);
        gc.gridx = 2;
        gc.gridy = 6;
        frame.add(last, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        frame.add(lastName, gc);
        gc.gridx = 0;
        gc.gridy = 7;
        frame.add(gender, gc);
        gc.gridx = 1;
        gc.gridy = 7;
        frame.add(genderBox, gc);
        gc.gridx = 0;
        gc.gridy = 8;
        frame.add(startBalanceLabel, gc);
        gc.gridx = 1;
        gc.gridy = 8;
        frame.add(startingBalance, gc);
        gc.gridx = 0;
        gc.gridy = 9;
        frame.add(startSaveLabel, gc);
        gc.gridx = 1;
        gc.gridy = 9;
        frame.add(startingSavings, gc);
        gc.gridx = 0;
        gc.gridy = 10;
        frame.add(goalSavingsLabel, gc);
        gc.gridx=1;
        gc.gridy = 10;
        frame.add(goalSavings, gc);
        
        gc.gridx = 0;
        gc.gridy = 11;
        frame.add(backButton, gc);
        
        gc.gridx = 1;
        gc.gridy = 11;
        frame.add(saveButton, gc);
        
        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MyManager();
            }

        });

        saveButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                saveData();
            }
        });
        
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    private void saveData(){
        
        Height saveHeight = new Height(Integer.parseInt(heightFeet.getText()), Integer.parseInt(heightInches.getText()));
        int saveWeight = Integer.parseInt(weight.getText());
        int saveAge = Integer.parseInt(age.getText());
        //save gender
        double startBalance = Double.parseDouble(startingBalance.getText());
        double startSavings = Double.parseDouble(startingSavings.getText());
        double savingsGoal = Double.parseDouble(goalSavings.getText());
        String userGender = (String) genderBox.getSelectedItem();
        //need to have gender in here
        storedData = new WriteData(saveHeight, saveWeight, saveAge, firstName.getText(), lastName.getText(), userGender, startBalance, startSavings, savingsGoal);
        

        WriteData.write(storedData);

        
        JFrame save = new JFrame("Save Successful");
        JButton ok = new JButton("OK");
        JLabel success = new JLabel("Your settings have been saved successfully!");
        
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
        
        ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                save.setVisible(false);
                frame.setVisible(true);
            }
        });
        
        save.pack();
        save.setLocationRelativeTo(null);
        frame.setVisible(false);
        save.setVisible(true);
        
        
    }
    
    
    /**
    private void write(WriteData data){
        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try{
            ops = new FileOutputStream("data_storage.txt");
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(data);
            objOps.flush();
            
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }        
        
    }

    public static WriteData read(){
        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        WriteData data = null;
        try {
            fileIs = new FileInputStream("data_storage.txt");
            objIs = new ObjectInputStream(fileIs);
            WriteData nData = (WriteData) objIs.readObject();
            data = nData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objIs != null) objIs.close();
            } catch (Exception ex){
                 
            }
        }
        
        return data;
    }
    **/
}

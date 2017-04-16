/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author kmgauthier
 */
public class SpendingsHistory extends JFrame {

    private JFrame frame;//creating new jframe for spending history
    private JLabel date, description, cost;//creating labels
    private JButton backButton, resetSpending;//creating buttons
    private SpendingData sd = new SpendingData();//creating spendingdata object
    private ArrayList<SpendingData> dataArray = null;//creating array for spending data

    public SpendingsHistory() {

        frame = new JFrame("Spending History");//declaring jframe titled "Spending History"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        frame.setLayout(new GridBagLayout());//sets layout of frame to gridbaglayout
        
        frame.getContentPane().setBackground(new Color(122, 94, 213));//sets background color of frame
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);//creates fonts
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);

        try {
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));//sets icon to mymanager logo
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        date = new JLabel("Date");//creates date label
        description = new JLabel("Description");//creates description label
        cost = new JLabel("Cost");//creates cost label
        backButton = new JButton("Back"); backButton.setFont(font1);//creates back button and sets font of back button to font 1
        resetSpending = new JButton("RESET HISTORY");//creates reset spending button
        resetSpending.setFont(font1); resetSpending.setBackground(new Color(253, 42, 42));//sets font and background color of reset button

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50);

        //headers to the history
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(date, gc);//adding date label to frame
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(description, gc);//adding description label to frame
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(cost, gc);//adding cost label to frame

        dataArray = sd.read();//reading array
        if (dataArray != null) {
            int y = 1;
            while (!dataArray.isEmpty()) {
                SpendingData newSd = dataArray.remove(0);
                gc.gridx = 0;
                gc.gridy = y;
                frame.add(new JLabel(newSd.getDate()), gc);//adding date of array element into frame
                gc.gridx = 1;
                gc.gridy = y;
                frame.add(new JLabel(newSd.getDesc()), gc);//adding description of array element into frame
                gc.gridx = 2;
                gc.gridy = y;
                frame.add(new JLabel(Double.toString(newSd.getCost())), gc);//adding cost of array element into frame
                y++;
            }
            gc.gridx = 0;
            gc.gridy = y;
            frame.add(backButton, gc);//adding back button into frame
            gc.gridx = 1;
            gc.gridy = y;
            frame.add(resetSpending, gc);//adding reset button into frame
        } else {

            //back button
            gc.gridx = 0;
            gc.gridy = 3;
            frame.add(backButton, gc);//adding back button into frame
            gc.gridx = 1;
            gc.gridy = 3;
            frame.add(resetSpending, gc);//adding reset button to frame
        }
        backButton.addActionListener(new ActionListener() { //go to home page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new FinancialAssistant();//returns user to financial assistant page
            }

        });
        

        resetSpending.addActionListener(new ActionListener(){//action listener method for reset button
            @Override
            public void actionPerformed(ActionEvent e){
                SpendingData.write(new ArrayList<SpendingData>());
                resetPrompt();//sends user to reset page
                
            }
        });
        

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private void resetPrompt() {//method for resetting spending history
        JFrame save = new JFrame("Reset Successful");//creates jframe titled "Reset Successful"
        JButton ok = new JButton("OK");//creates ok button
        JLabel success = new JLabel("Spending history has been reset.");//creates success label

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

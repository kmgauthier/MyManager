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

    private JFrame frame;//creates private jframe
    private JLabel date, description, cost;//creates private labels
    private JButton backButton;//creates private button
    private SpendingData sd = new SpendingData();//creates private object of SpendingData class
    private ArrayList<SpendingData> dataArray = null;//creates private array

    public SpendingsHistory() {

        frame = new JFrame("Spendings History");//declares jframe titled Spendings History
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends program when user closes window
        frame.setLayout(new GridBagLayout());//sets layout to gridbag layout

        try {//sets icon image to mymanager logo
            frame.setIconImage(ImageIO.read(new File("newLogo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        date = new JLabel("Date");//declares date label
        description = new JLabel("Description");//declares description label
        cost = new JLabel("Cost");//declares cost label
        backButton = new JButton("Back");//declares back button

        GridBagConstraints gc = new GridBagConstraints();//creates new gridbagconstraints
        gc.fill = GridBagConstraints.HORIZONTAL;//fills gridbagconstraints
        gc.insets = new Insets(10, 50, 10, 50);

        //headers to the history
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(date, gc);//adding date label to gridbag layout
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(description, gc);//adding description label to gridbag layout
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(cost, gc);//adding cost label to gridbag layout

        dataArray = sd.read();//reads data array
        if (dataArray != null) {
            int y = 1;
            while (!dataArray.isEmpty()) {
                SpendingData newSd = dataArray.remove(0);
                gc.gridx = 0;
                gc.gridy = y;
                frame.add(new JLabel(newSd.getDate()), gc);
                gc.gridx = 1;
                gc.gridy = y;
                frame.add(new JLabel(newSd.getDesc()), gc);
                gc.gridx = 2;
                gc.gridy = y;
                frame.add(new JLabel(Double.toString(newSd.getCost())), gc);
                y++;
            }
            gc.gridx = 0;
            gc.gridy = y;
            frame.add(backButton, gc);//adding back button to gridbag layout
        } else {

            //back button
            gc.gridx = 0;
            gc.gridy = 3;
            frame.add(backButton, gc);//adding back button to gridbag layout
        }
        backButton.addActionListener(new ActionListener() { //go to home page           //Action Listener method for back button
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//sets visibility of spending history page to false
                new FinancialAssistant();//returns user to financial assistant page
            }

        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}

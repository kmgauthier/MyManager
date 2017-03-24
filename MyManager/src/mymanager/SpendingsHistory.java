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

    private JFrame frame;
    private JLabel date, description, cost;
    private JButton backButton, resetSpending;
    private SpendingData sd = new SpendingData();
    private ArrayList<SpendingData> dataArray = null;

    public SpendingsHistory() {

        frame = new JFrame("Spendings History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        frame.getContentPane().setBackground(new Color(73, 172, 229));
        Font font1 = new Font("Book Antiqua", Font.BOLD, 14);
        Font font3 = new Font("Book Antiqua", Font.BOLD, 20);

        try {
            frame.setIconImage(ImageIO.read(new File("logo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        date = new JLabel("Date");
        description = new JLabel("Description");
        cost = new JLabel("Cost");
        backButton = new JButton("Back"); backButton.setFont(font1);
        resetSpending = new JButton("Reset Spending History");
        resetSpending.setFont(font1); resetSpending.setBackground(new Color(255, 0, 0));
        resetSpending.setForeground(new Color(0,0,0));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 50, 10, 50);

        //headers to the history
        gc.gridx = 0;
        gc.gridy = 0;
        frame.add(date, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        frame.add(description, gc);
        gc.gridx = 2;
        gc.gridy = 0;
        frame.add(cost, gc);

        dataArray = sd.read();
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
            frame.add(backButton, gc);
            gc.gridx = 1;
            gc.gridy = y;
            frame.add(resetSpending, gc);
        } else {

            //back button
            gc.gridx = 0;
            gc.gridy = 3;
            frame.add(backButton, gc);
            gc.gridx = 1;
            gc.gridy = 3;
            frame.add(resetSpending, gc);
        }
        backButton.addActionListener(new ActionListener() { //go to home page
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new FinancialAssistant();
            }

        });
        

        resetSpending.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SpendingData.write(new ArrayList<SpendingData>());
                resetPrompt();
                
            }
        });
        

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private void resetPrompt() {
        JFrame save = new JFrame("Reset Successful");
        JButton ok = new JButton("OK");
        JLabel success = new JLabel("Spending history has been reset.");

        save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.setLayout(new GridBagLayout());

        try {
            save.setIconImage(ImageIO.read(new File("logo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

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

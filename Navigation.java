/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author prime
 */
public class Navigation extends Frame implements ActionListener {

    ImageIcon image;
    JLabel l1;
    JButton b1, b2, b3;

    public Navigation() {

        setLayout(null);
        setLocation(200, 300);
        setResizable(false);

        setSize(784, 582);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        image = new ImageIcon(getClass().getResource("mainframe.jpg"));

        l1 = new JLabel(image);
        l1.setBounds(0, 101, 785, 481);
        add(l1);

        b1 = new JButton("MENU");
        b2 = new JButton("EMPLOYEE");
        b3 = new JButton("BILL");

        b1.setBounds(180, 70, 90, 30);
        b2.setBounds(300, 70, 150, 30);
        b3.setBounds(480, 70, 90, 30);

        b1.setBackground(new Color(230, 244, 255));
        b2.setBackground(new Color(230, 244, 255));
        b3.setBackground(new Color(230, 244, 255));

        add(b1);
        add(b2);
        add(b3);

        setBackground(new Color(230, 244, 255));

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setVisible(true);
        setTitle("Navigation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        if (g.equals("MENU")) {
            dispose();
            new DisplayMenu();
        } else if (g.equals("EMPLOYEE")) {
            dispose();
            new DisplayEmp();
        } else if (g.equals("BILL")) {
            dispose();
            new DMl();
        }
    }

}

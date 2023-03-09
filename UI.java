++++++++package pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI extends Frame implements ActionListener {

    static String USER;
    static String PASS;
    static String driver;
    static String DB_URL;

    static String Admin;
    static String Password;

    ImageIcon image;

    JLabel l1, l2, il;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;

    UI() {

        setSize(900, 530);
        setLocation(200, 200);

        setBackground(new Color(0, 174, 251));

        setResizable(false);

        image = new ImageIcon(getClass().getResource("loginim (2).jpg"));//getting image

        il = new JLabel(image);
        il.setBounds(0, 0, 445, 591);
        add(il);

        l1 = new JLabel("Username");
        l1.setForeground(Color.WHITE);
        t1 = new JTextField(10);
        l2 = new JLabel("Password");
        l2.setForeground(Color.WHITE);
        t2 = new JPasswordField(10);
        b1 = new JButton("Login");
        b1.setBackground(new Color(0, 205, 147));
        b1.setForeground(Color.WHITE);
        b2 = new JButton("Reset");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(0, 205, 147));

        l1.setBounds(500, 180, 90, 30);
        t1.setBounds(600, 180, 230, 30);
        l2.setBounds(500, 225, 90, 30);
        t2.setBounds(600, 225, 230, 30);
        b1.setBounds(600, 270, 75, 30);
        b2.setBounds(690, 270, 75, 30);

        t1.setText("Admin");
        t2.setText("123");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        add(l1);
        add(t1);
        add(l2);
        add(t2);

        add(b1);
        add(b2);

        setLayout(null);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setTitle("Restaurant Management");

        UI.initConnections();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        JFrame f = new JFrame();
        if (g.equals("Login")) {
            String u = t1.getText().trim();
            String p = String.valueOf(t2.getPassword());
            System.out.println(u + p);
            if (u.equals(Admin) && p.equals(Password)) {

                JOptionPane.showMessageDialog(f, "Username password validated successfully");
                dispose();
                new Navigation();

            } else {
                t1.setText(" ");
                t2.setText(" ");
                JOptionPane.showMessageDialog(f, "Invalid Username or password Try again");
            }

        }
        if (g.equals("Reset")) {

            t1.setText("");
            t2.setText("");
        }
    }

    static void initConnections() {
        USER = "root";
        PASS = "";
        driver = "com.mysql.cj.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/RESTAURANT";
        Admin = "Admin";
        Password = "123";
    }

    public static void main(String[] args) {
        UI u = new UI();
    }
}

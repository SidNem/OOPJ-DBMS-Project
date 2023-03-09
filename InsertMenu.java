package pro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class InsertMenu extends Frame implements ActionListener {

    JLabel l1, l2;
    JTextField t1, t2;
    JButton b1, b2;
    JPanel panel;
    JTable j;
    String Name;
    int price;

    InsertMenu() {

        setSize(800, 530);
        setLocation(200, 300);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        l1 = new JLabel("Name");
        l2 = new JLabel("Price");

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        b1 = new JButton("Confirm");
        b2 = new JButton("Back");

        l1.setBounds(515, 90, 90, 30);
        t1.setBounds(590, 90, 190, 30);
        l2.setBounds(515, 130, 90, 30);
        t2.setBounds(590, 130, 190, 30);
        b1.setBounds(590, 170, 100, 30);
        b2.setBounds(710, 170, 75, 30);

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
        
        setTitle("Insert Menu");
        DisplayMenuF();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        JFrame f = new JFrame();
        if (g.equals("Confirm")) {
            try {
                Name = t1.getText().toUpperCase();
                price = Integer.parseInt(t2.getText());
                //System.out.println(Name+price);
                Class.forName(UI.driver);
                System.out.println("Connecting to database...");

                Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

                Statement stmt = conn.createStatement();
                System.out.println("inserting record...");
                
                String sql = "INSERT INTO menu " + "(NAME,PRICE)" + "VALUES('" + Name + "','" + price + "')";
                
                System.out.println("after thjas");
                stmt.executeUpdate(sql);
                conn.close();
                System.out.println("Added successfully");
                t1.setText("");
                t2.setText("");
                JOptionPane.showMessageDialog(f, Name + " " + price + " Added successfully");
                conn.close();
                dispose();
                InsertMenu i = new InsertMenu();
            } catch (Exception ea) {
                System.out.println("Connection failed");

            }
            
            
            

        } else if (g.equals("Back")) {
            dispose();
            new DisplayMenu();
        }

    }

    public void DisplayMenuF() {

        try {

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });
            setLayout(null);
            setSize(800, 530);
            setLocation(200, 300);
            setResizable(false);
            Class.forName(UI.driver);
            System.out.println("Connecting to database...Display menu");

            Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

            Statement stmt = conn.createStatement();
            String sql = "select * from menu";
            ResultSet rs = stmt.executeQuery(sql);
            j = new JTable();

            j.setModel(DbUtils.resultSetToTableModel(rs));
            panel = new JPanel();
            JScrollPane js = new JScrollPane(j);
            panel.setBounds(5, 90, 500, 500);
            panel.add(js);

            add(panel);

            setVisible(true);
            conn.close();

        } catch (Exception ea) {
            System.out.println("Connetctin failde");

        }

    }

}

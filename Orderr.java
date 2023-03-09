/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author prime
 */
public class Orderr extends Frame implements ActionListener {

    JPanel panel;
    JTable j;
    JButton b1, b2, b3, b4;
    JLabel l1, l2, l3, l4, l5, l6;
    JTextField t1, t2, t3, t4, t5;
    JFrame f;

    static String CCID = null;

    int CID, MID, EID, QTY;
    int PRICE;
    static int p = 0;

    Orderr() {

        setSize(900, 530);
        setLocation(200, 300);
        setResizable(false);
        b1 = new JButton("Confirm");
        b2 = new JButton("Reset");
        b4 = new JButton("Bill");

        l1 = new JLabel("Customer ID");
        t1 = new JTextField();

        l2 = new JLabel("Menu ID");
        t2 = new JTextField();

        l3 = new JLabel("Price");
        t3 = new JTextField();

        l4 = new JLabel("EMP ID");
        t4 = new JTextField();

        l5 = new JLabel("Quantity");
        t5 = new JTextField();

        l6 = new JLabel("Menu");

        l1.setBounds(515, 90, 90, 30);
        t1.setBounds(610, 90, 190, 30);

        l2.setBounds(515, 130, 90, 30);
        t2.setBounds(610, 130, 190, 30);

        l3.setBounds(515, 170, 90, 30);
        t3.setBounds(610, 170, 190, 30);

        l4.setBounds(515, 210, 90, 30);
        t4.setBounds(610, 210, 190, 30);

        l5.setBounds(515, 250, 90, 30);
        t5.setBounds(610, 250, 190, 30);

        b1.setBounds(610, 290, 100, 30);
        b2.setBounds(730, 290, 75, 30);

        b4.setBounds(720, 330, 75, 30);

        l6.setBounds(0, 0, 90, 30);

        add(l1);
        add(t1);

        add(l2);
        add(t2);

        add(l3);
        add(t3);

        add(l4);
        add(t4);

        add(l5);
        add(t5);

        add(b1);
        add(b2);

        add(b4);

        add(l6);

        setLayout(null);
        setVisible(true);

        DisplayMenuF();

        b1.addActionListener(this);
        b4.addActionListener(this);
        GetCid();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        if (g.equals("Confirm")) {
            try {

                PRICE = Integer.parseInt(t3.getText());

                MID = Integer.parseInt(t2.getText());
                EID = Integer.parseInt(t4.getText());
                QTY = Integer.parseInt(t5.getText());

                p = p + PRICE * QTY;

                Class.forName(UI.driver);
                System.out.println("Connecting to database...");

                Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

                Statement stmt = conn.createStatement();
                System.out.println("inserting record...");
                
                String sql = "INSERT INTO orderr " + "(QTY,CID,MID,EID)" + "VALUES('" + QTY + "',"
                        + "'" + CCID + "','" + MID + "','" + EID + "')";
                
                System.out.println("after thjas");
                stmt.executeUpdate(sql);
                conn.close();
                System.out.println("Added successfully");
                t1.setText("");
                t2.setText("");
                JOptionPane.showMessageDialog(f, " Added successfully");
                conn.close();
                dispose();
                Orderr O = new Orderr();
            } catch (Exception ea) {
                System.out.println("Connection failed");

            }
        } else if (g.equals("Bill")) {
            dispose();
            Bill bil = new Bill();
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
            setSize(900, 530);
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

    public void GetCid() {
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
            String sql = "select CID from customer order by CID DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CCID = rs.getString("CID");
            }

            System.out.println(CCID);

            t1.setText(CCID);

            setVisible(true);
            conn.close();

        } catch (Exception ea) {
            System.out.println("Connetctin failde");

        }

    }

}

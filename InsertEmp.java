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
public class InsertEmp extends Frame implements ActionListener {

    String Ename;
    String Desgination;
    String Address;
    int Salary;
    JLabel l1, l2, l3, l4;
    JTextField t1, t2, t3, t4;
    JButton b1, b2;
    JTable j;
    JPanel panel;

    InsertEmp() {
        setSize(800, 530);
        setLocation(200, 300);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        l1 = new JLabel("Name");
        l2 = new JLabel("Designation");
        l3 = new JLabel("Address");
        l4 = new JLabel("Salary");

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();

        b1 = new JButton("Confirm");
        b2 = new JButton("Back");

        l1.setBounds(515, 90, 90, 30);
        t1.setBounds(600, 90, 190, 30);

        l2.setBounds(515, 130, 90, 30);
        t2.setBounds(600, 130, 190, 30);

        l3.setBounds(515, 170, 90, 30);
        t3.setBounds(600, 170, 190, 30);

        l4.setBounds(515, 210, 90, 30);
        t4.setBounds(600, 210, 190, 30);

        b1.setBounds(600, 260, 100, 30);
        b2.setBounds(720, 260, 75, 30);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(b1);
        add(b2);

        setLayout(null);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);

        
        setTitle("Insert Employee");
        DisplayEmpf();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String g = e.getActionCommand();
        JFrame f = new JFrame();
        if (g.equals("Confirm")) {
            try {
                Ename = t1.getText().toUpperCase();
                Desgination = t2.getText().toUpperCase();
                Address = t3.getText().toUpperCase();
                Salary = Integer.parseInt(t4.getText());

                Class.forName(UI.driver);
                System.out.println("Connecting to database...");

                Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

                Statement stmt = conn.createStatement();
                System.out.println("inserting record...");
                String sql = "INSERT INTO employee " + "(ENAME,DESIGNATION,ADDRESS,salary)" + "VALUES('" + Ename + "','" + Desgination + "',"
                        + "'" + Address + "','" + Salary + "')";
                System.out.println("after thjas");
                stmt.executeUpdate(sql);
                conn.close();
                System.out.println("Added successfully");
                t1.setText("");
                t2.setText("");
                JOptionPane.showMessageDialog(f, " Added successfully");
                conn.close();
                dispose();
                InsertEmp ie = new InsertEmp();
            } catch (Exception ea) {
                System.out.println("Connection failed");

            }

        } else if (g.equals("Back")) {
            dispose();
            new DisplayEmp();
        }

    }

    public void DisplayEmpf() {

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
            System.out.println("Connecting to database...Display EMp");

            Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

            Statement stmt = conn.createStatement();
            String sql = "select * from employee";
            ResultSet rs = stmt.executeQuery(sql);

            j = new JTable();

            j.setModel(DbUtils.resultSetToTableModel(rs));
            panel = new JPanel();
            JScrollPane js = new JScrollPane(j);
            panel.setBounds(9, 90, 500, 500);
            panel.add(js);

            add(panel);

            setVisible(true);
            conn.close();

        } catch (Exception ea) {

        }

    }

}

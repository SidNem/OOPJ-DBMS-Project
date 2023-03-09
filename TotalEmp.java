/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author prime
 */
public class TotalEmp extends Frame implements ActionListener {

    JTextField t1;
    JButton b;
    String count;
    JLabel l1;
    JTable j;
    JPanel frame;

    TotalEmp() {

        try {
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });

            t1 = new JTextField(10);
            b = new JButton("Back");

            setLayout(null);
            setSize(800, 530);
            setLocation(200, 300);
            setResizable(false);
            Class.forName(UI.driver);

            System.out.println("Connecting to database...Display EMp");

            Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

            Statement stmt = conn.createStatement();
            
            String sql = "select count(EID) from employee";
            
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                count = rs.getString("count(EID)");
            }

            setVisible(true);

            conn.close();
            b.setBounds(10, 40, 70, 30);
            add(b);
            b.addActionListener(this);

            l1 = new JLabel("Total Employees");
            l1.setBounds(250, 200, 180, 30);

            t1.setText(count);
            t1.setBounds(390, 200, 80, 30);

            add(l1);
            add(t1);

        } catch (Exception ea) {
            System.out.println("Connection failed");

        }
        
        setTitle("No of employees");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        if (g.equals("Back")) {
            dispose();
            new DisplayEmp();
        }

    }
}

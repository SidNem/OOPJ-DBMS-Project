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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author prime
 */
public class SecondHighestSal extends Frame implements ActionListener {

    JTable j;
    JPanel panel;
    JButton b;

    SecondHighestSal() {
        try {

            b = new JButton("Back");
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
            String sql = "select EID,ENAME,salary from employee where salary=(select max(salary) from employee where salary not in (select max(salary) from employee))";
            ResultSet rs = stmt.executeQuery(sql);

            j = new JTable();

            j.setModel(DbUtils.resultSetToTableModel(rs));
            panel = new JPanel();
            JScrollPane js = new JScrollPane(j);
            panel.setBounds(10, 90, 780, 500);
            panel.add(js);

            add(panel);

            setVisible(true);

            conn.close();
            b.setBounds(10, 40, 70, 30);
            add(b);
            b.addActionListener(this);

        } catch (Exception ea) {
            System.out.println("Connection failed");

        }
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

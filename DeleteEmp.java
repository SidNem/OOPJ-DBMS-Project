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
public class DeleteEmp extends Frame implements ActionListener {

    JLabel l1;
    JTextField t1;
    JButton b1, b2;
    int EID;
    JFrame f;
    JTable j;
    JPanel panel;

    public DeleteEmp() {

        setSize(900, 700);
        setLocation(200, 300);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        l1 = new JLabel("EMP ID");
        t1 = new JTextField(10);
        b1 = new JButton("Confirm");
        b2 = new JButton("Back");

        l1.setBounds(515, 90, 90, 30);
        t1.setBounds(590, 90, 140, 30);
        b1.setBounds(590, 130, 100, 30);
        b2.setBounds(10, 40, 70, 30);

        add(l1);
        add(t1);
        add(b1);
        add(b2);

        setLayout(null);
        setVisible(true);

        b1.addActionListener(this);
        b2.addActionListener(this);
        DisplayEmpf();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String g = e.getActionCommand();
        if (g.equals("Confirm")) {
            try {
                f = new JFrame();

                EID = Integer.parseInt(t1.getText());
                Class.forName(UI.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);
                Statement stmt = conn.createStatement();
                System.out.println("Deleting record...");
                
                String sql = "DELETE from employee where EID=" + EID;
                
                stmt.executeUpdate(sql);
                conn.close();
                System.out.println("Deleted successfully");
                t1.setText("");
                JOptionPane.showMessageDialog(f, "EID " + EID + " deleted successfully");
                dispose();
                DeleteEmp dee = new DeleteEmp();
                conn.close();
            } catch (Exception ea) {
                System.out.println("Connection failed");

            }

        } else if (g.equals("Back")) {
            dispose();
            new DisplayEmp();
        }
        
        
        setTitle("Employee Deletion");

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

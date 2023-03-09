/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro;

import java.awt.Color;
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
import javax.swing.ImageIcon;
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
public class DisplayEmp extends Frame implements ActionListener {

    JTable j;
    JPanel panel;
    JButton b1, b2, b3, b4, b5, b6;
    ImageIcon image;
    JLabel il;

    DisplayEmp() {
        try {

            b1 = new JButton("Back");
            b2 = new JButton("INSERT");
            b3 = new JButton("UPDATE");
            b4 = new JButton("DELETE");
            b5 = new JButton("TOTAL EMP");
            b6 = new JButton("2nd HIGHEST SALARY");
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });

            image = new ImageIcon(getClass().getResource("emp1.png"));

            il = new JLabel(image);
            il.setBounds(0, 200, 300, 199);
            add(il);
            setLayout(null);
            setSize(900, 530);
            setLocation(200, 300);
            setResizable(false);
            Class.forName(UI.driver);
            System.out.println("Connecting to database...Display EMp");
            setBackground(Color.WHITE);

            Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

            Statement stmt = conn.createStatement();
            
            String sql = "select * from details";
            
            ResultSet rs = stmt.executeQuery(sql);

            j = new JTable();

            j.setModel(DbUtils.resultSetToTableModel(rs));
            panel = new JPanel();
            JScrollPane js = new JScrollPane(j);
            panel.setBounds(150, 90, 780, 500);
            panel.setBackground(Color.WHITE);
            panel.add(js);

            add(panel);

            setVisible(true);

            conn.close();
            b1.setBounds(10, 40, 70, 30);

            b2.setBounds(100, 40, 100, 30);
            b3.setBounds(230, 40, 100, 30);

            b4.setBounds(360, 40, 100, 30);
            b5.setBounds(490, 40, 120, 30);
            b6.setBounds(620, 40, 230, 30);

            add(b1);
            add(b2);
            add(b3);
            add(b4);
            add(b5);
            add(b6);

            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b4.addActionListener(this);
            b5.addActionListener(this);
            b6.addActionListener(this);

        } catch (Exception ea) {
            System.out.println("Connection failed");

        }
        
        setTitle("Display Employee");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String g = e.getActionCommand();
        if (g.equals("Back")) {
            dispose();
            new Navigation();
        }
        if (g.equals("INSERT")) {
            dispose();
            new InsertEmp();
        }
        if (g.equals("DELETE")) {
            dispose();
            new DeleteEmp();
        }
        if (g.equals("UPDATE")) {
            dispose();
            new UpdateEmp();
        }
        if (g.equals("TOTAL EMP")) {
            dispose();
            new TotalEmp();
        }
        if (g.equals("2nd HIGHEST SALARY")) {
            dispose();
            new SecondHighestSal();
        }

    }

}

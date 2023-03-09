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
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class Bill extends Frame implements ActionListener {

    String QTY, OID, CNAME, PH_NO, PRICE, MID;
    JTable j;
    JPanel frame;

    String mm = "";
    String qq = "";

    JButton b;

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;

    Bill() {
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
            String sql = "select * from orderr INNER JOIN customer ON orderr.CID=customer.CID where orderr.CID=" + Orderr.CCID;

            ResultSet rs = stmt.executeQuery(sql);

            j = new JTable();

            while (rs.next()) {
                OID = rs.getString("OID");
                CNAME = rs.getString("CNAME");
                PH_NO = rs.getString("PNO");
                QTY = rs.getString("QTY");
                MID = rs.getString("MID");
                GetMenu();

            }

            l1 = new JLabel("Name:");
            l2 = new JLabel(CNAME);

            l3 = new JLabel("Phone No:");
            l4 = new JLabel(PH_NO);

            l5 = new JLabel("Items:");
            l6 = new JLabel(mm);

            l7 = new JLabel("Quantity:");
            l8 = new JLabel(qq);

            l9 = new JLabel("Total:");
            l10 = new JLabel(Integer.toString(Orderr.p));

            b = new JButton("Next");

            System.out.println(OID);
            System.out.println(CNAME);
            System.out.println(PH_NO);
            System.out.println(QTY);
            System.out.println(Orderr.p);

            l1.setBounds(300, 90, 90, 30);
            l2.setBounds(350, 90, 90, 30);

            l3.setBounds(300, 130, 90, 30);
            l4.setBounds(390, 130, 90, 30);

            l5.setBounds(300, 170, 90, 30);
            l6.setBounds(350, 170, 190, 30);

            l7.setBounds(300, 210, 90, 30);
            l8.setBounds(390, 210, 90, 30);

            l9.setBounds(300, 250, 90, 30);
            l10.setBounds(350, 250, 90, 30);
            b.setBounds(300, 290, 70, 30);

            add(l1);
            add(l2);
            add(l3);
            add(l4);
            add(l5);
            add(l6);
            add(l7);
            add(l8);
            add(l9);
            add(l10);
            add(b);

            b.addActionListener(this);

            setVisible(true);

            conn.close();

        } catch (Exception ea) {
            System.out.println("Connection failed");

        }
        
      

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        dispose();
        Orderr.p = 0;
        DMl d = new DMl();

    }

    public void GetMenu() {
        try {

            Class.forName(UI.driver);
            System.out.println("Connecting to database...Display menuu for name");

            Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

            Statement stmt = conn.createStatement();
            String sql = "select NAME,QTY from menu INNER JOIN orderr ON orderr.MID=menu.MID where orderr.OID=" + OID;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                mm = mm + " " + rs.getString("NAME");
                qq = qq + " " + rs.getString("QTY");

            }

        } catch (Exception e) {
            System.out.println("Error fething name of menu");

        }

    }

}

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
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class DeleteMenu extends Frame implements ActionListener {

    JLabel l1;
    JTextField t1;
    JButton b1, b2;
    int MID;
    JFrame f;
    JTable j;
    JPanel panel;

    DeleteMenu() {

        setSize(900, 700);
        setLocation(200, 300);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        l1 = new JLabel("Menu ID");
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
        
         setTitle("Delete Menu");
        DisplayMenuF();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        if (g.equals("Confirm")) {
            try {
                f = new JFrame();

                MID = Integer.parseInt(t1.getText());
                Class.forName(UI.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);
                Statement stmt = conn.createStatement();
                System.out.println("Deleting record...");
                
                String sql = "DELETE from menu where MID=" + MID;
                
                stmt.executeUpdate(sql);
                conn.close();
                System.out.println("Deleted successfully");
                t1.setText("");
                JOptionPane.showMessageDialog(f, "Menu ID " + MID + " deleted successfully");
                dispose();
                DeleteMenu d = new DeleteMenu();
                conn.close();
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
            System.out.println("Connection failed");

        }

    }

}

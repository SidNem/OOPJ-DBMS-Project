package pro;

import java.awt.Color;
import java.awt.Frame;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Scrollable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class DisplayMenu extends Frame implements ActionListener {

    JTable j;
    JButton b1, b2, b3, b4;
    JPanel panel;
    JLabel il;
    ImageIcon image;
    DisplayMenu() {

        try {

            b1 = new JButton("Back");
            b2 = new JButton("INSERT");
            b3 = new JButton("UPDATE");
            b4 = new JButton("DELETE");

            setBackground(new Color(255,255,255));
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });
            
             image = new ImageIcon(getClass().getResource("menu56.jpg"));

        il = new JLabel(image);
        il.setBounds(0, 170, 300, 378);
        add(il);
            setLayout(null);
            setSize(800, 650);
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
            panel.setBounds(170, 90, 780, 500);
            panel.setBackground(new Color(255,255,255));
            panel.add(js);

            add(panel);

            setVisible(true);

            conn.close();
            b1.setBounds(20, 40, 70, 30);
            b2.setBounds(120, 40, 100, 30);
            b3.setBounds(230, 40, 100, 30);
            b4.setBounds(340, 40, 100, 30);

            add(b1);
            add(b2);
            add(b3);
            add(b4);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b4.addActionListener(this);

        } catch (Exception ea) {
            System.out.println("Connection failed");

        }
        
        
        setTitle("Display Menu");

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
            new InsertMenu();
        }
        if (g.equals("DELETE")) {
            dispose();
            new DeleteMenu();
        }
        if (g.equals("UPDATE")) {
            dispose();
            new MenuUpdate();
        }
    }

}

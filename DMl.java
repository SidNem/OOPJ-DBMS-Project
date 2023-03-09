package pro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DMl extends Frame implements ActionListener {

    JTextField t1, t2, t3;
    JLabel l1, l2, l3,il;
    JButton b, back;
    ImageIcon image;
    String CName;
    String Pno;

    public DMl() {

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        
        
        
        image = new ImageIcon(getClass().getResource("foodimenu.jpg"));

        il = new JLabel(image);
        il.setBounds(0, 0, 458, 500);
        add(il);

        setBackground(new Color(243,231,255));
        l1 = new JLabel("Customer Name");
        l2 = new JLabel("Customer MNo");
        t1 = new JTextField(15);
        t2 = new JTextField();
        b = new JButton("Confirm");

       

        back = new JButton("Back");

        

        l1.setBounds(500, 180, 150, 30);
        t1.setBounds(640, 180, 230, 30);
        l2.setBounds(500, 225, 150, 30);
        t2.setBounds(640, 225, 230, 30);
        b.setBounds(640, 270, 90, 30);
        back.setBounds(750, 270, 80, 30);

        add(l1);
        add(l2);
        add(t1);


        add(t2);
        add(b);
        add(back);
        setSize(900, 520);
        setLocation(200, 300);
        setResizable(false);

        back.addActionListener(this);
        b.addActionListener(this);

        setLayout(null);
        setVisible(true);
        
        
          setTitle("Customer details");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        if (g.equals("Back")) {
            dispose();
            new Navigation();
        } else if (g.equals("Confirm")) {
            try {
                CName = t1.getText().toUpperCase();
                Pno = t2.getText();

                System.out.println(Pno);
                Class.forName(UI.driver);
                System.out.println("Connecting to database...");

                Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

                Statement stmt = conn.createStatement();
                System.out.println("inserting record...");
                
                String sql = "INSERT INTO customer " + "(CNAME,PNO)" + "VALUES('" + CName + "','" + Pno + "')";
                
                stmt.executeUpdate(sql);
                conn.close();
                t1.setText("");
                t2.setText("");
                System.out.println("Added successfully");
                conn.close();
                dispose();
                 new Orderr();
            } catch (Exception ea) {
                System.out.println("Connection failed");

            }

        }

    }
}

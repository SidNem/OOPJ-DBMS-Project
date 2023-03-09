package pro;

import java.awt.Frame;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

public class UpdateEmp extends Frame implements ActionListener {

    String UAddress;
    int USalary, EID;
    JTable j;
    JPanel panel;
    JLabel l1, l2, l3;
    JTextField t1, t2, t3;
    JButton b1, b2;

    UpdateEmp() {

        setSize(800, 530);
        setLocation(200, 300);

        setResizable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setResizable(false);

        l1 = new JLabel("EID");
        t1 = new JTextField(10);
        l2 = new JLabel("Address");
        t2 = new JTextField(10);
        l3 = new JLabel("New salary");
        t3 = new JTextField(10);

        b1 = new JButton("Confirm");
        b2 = new JButton("Back");

        l1.setBounds(515, 90, 90, 30);
        t1.setBounds(600, 90, 190, 30);

        l2.setBounds(515, 130, 90, 30);
        t2.setBounds(600, 130, 190, 30);

        l3.setBounds(515, 170, 90, 30);
        t3.setBounds(600, 170, 190, 30);

        b1.setBounds(600, 210, 100, 30);
        b2.setBounds(710, 210, 75, 30);

        add(l1);
        add(t1);
        add(l2);
        add(t2);

        add(l3);
        add(t3);
        add(b1);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setLayout(null);
        setVisible(true);
        setTitle("Updating Employee");
        DisplayEmpf();
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g = e.getActionCommand();
        JFrame f = new JFrame();
        if (g.equals("Confirm")) {

            EID = Integer.parseInt(t1.getText());
            UAddress = t2.getText();
            USalary = Integer.parseInt(t3.getText());

            try {

                Class.forName(UI.driver);
                System.out.println("Connecting to database...");

                Connection conn = DriverManager.getConnection(UI.DB_URL, UI.USER, UI.PASS);

                Statement stmt = conn.createStatement();
                System.out.println("updating record...");
                
                String sql = "UPDATE employee SET salary=" + USalary + ",ADDRESS='" + UAddress + "' where EID=" + EID;

                stmt.executeUpdate(sql);
                System.out.println("updated successfully");
                t1.setText("");
                t2.setText("");
                JOptionPane.showMessageDialog(f, " updated successfully");
                dispose();
                UpdateEmp ue = new UpdateEmp();
                conn.close();
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

package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin,repin;
    JButton change,back;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber=pinnumber;
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,800,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image =new JLabel(i3);
        image.setBounds(0,0,900,800);
        add(image);

        JLabel text=new JLabel("Change Your PIN");
        text.setForeground(Color.white);
        text.setFont(new Font("System",Font.BOLD, 15));
        text.setBounds(260,260,500,35);
        image.add(text);

        JLabel pintext=new JLabel("New PIN");
        pintext.setForeground(Color.white);
        pintext.setFont(new Font("System",Font.BOLD, 15));
        pintext.setBounds(165,300,180,25);
        image.add(pintext);

        pin=new JPasswordField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(330, 300, 180,20);
        image.add(pin);

        JLabel repintext=new JLabel("Re-Enter New PIN");
        repintext.setForeground(Color.white);
        repintext.setFont(new Font("System",Font.BOLD, 15));
        repintext.setBounds(165,335,180,25);
        image.add(repintext);

        repin=new JPasswordField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(330, 335, 180,20);
        image.add(repin);

        change = new JButton("Change");
        change.setBounds(375,430,140,25);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(375,460,140,25);
        back.addActionListener(this);
        image.add(back);




        setSize(900,800);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {
            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not Match");
                    return;
                }

                if (npin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter new PIN ");
                    return;
                }
                if (rpin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN ");
                    return;
                }

                Conn conn=new Conn();
                String query1="update bank set pin= '"+rpin+"' where pin='"+pinnumber+"'";
                String query2="update login set pin= '"+rpin+"' where pin='"+pinnumber+"'";
                String query3="update signupthree set pin= '"+rpin+"' where pin='"+pinnumber+"'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN Change Successfully");

                setVisible(false);
                new Transactions(rpin).setVisible(true);


            } catch (Exception ae) {
                System.out.println(ae);
            }
        }
            else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }

    }


    public static void main(String[] args) {
        new PinChange(" ").setVisible(true);
    }
}

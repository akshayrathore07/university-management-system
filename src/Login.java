import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login , cancel;
    JTextField tfusername ,tfpassword ;
    Login(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null); // when you dont want to try default layout

        // whenever you write something on a frame then we use jlabel
        JLabel lblUsername = new JLabel("Username");
        // set bound component are help to set user component in your frame
        lblUsername.setBounds(40, 20, 100, 20);
        add(lblUsername);

        //create input field with the help of jtext field
        tfusername = new JTextField();
        tfusername.setBounds(150, 20,150, 20);
        add(tfusername);

        // whenever you write something on a frame then we use jlabel
        JLabel lblpassword = new JLabel("Password");
        // set bound component are help to set user component in your frame
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);

        //create input field with the help of jtext field
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70,150, 20);
        add(tfpassword);

        // create button on jfrmae field
        login = new JButton("Login");
        login.setBounds(40, 140 , 120 , 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("serif", Font.BOLD, 16));
        add(login);



        // create button on jfrmae field
        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140 , 120 , 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("serif", Font.BOLD, 16));
        add(cancel);

        //upload image on frame background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));

        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon  i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350 , 0 , 200, 200);
        add(image);


        setSize(600,350);
        setLocation(400 , 120);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()== login){
            String username = tfusername.getText();
            String password = tfpassword.getText();

            String query = "select * from login where username='"+username+"' and password='"+password+"'";
            try{
                Conn c = new Conn();
                ResultSet rs =  c.s.executeQuery(query);

                if (rs.next()){  // rs.next return true or false
                    setVisible(false);
                    new Project();
                }else {  // it takes two argument 1st one is formating and another is string
                    JOptionPane.showMessageDialog(null , "Invalid username or password");  // JOption pane show a popup msg
                    setVisible(false);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

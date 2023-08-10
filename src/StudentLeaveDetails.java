import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;
public class StudentLeaveDetails extends JFrame implements ActionListener {

    Choice crollno;
    JTable table;
    JButton search , print , update , add , cancel;

    StudentLeaveDetails(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20 , 20, 150 , 30);
        add(heading);

        crollno = new Choice();
        crollno.setBounds(180 , 20 , 150, 20);
        add(crollno);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while (rs.next()){
                crollno.add(rs.getString("rollno"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();  // create table in a frame

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from studentLeave");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900 , 600);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20 , 70 , 80 ,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120 , 70 , 80 ,20);
        print.addActionListener(this);
        add(print);



        cancel = new JButton("Cancel");
        cancel.setBounds(220 , 70 , 80 ,20);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900 , 700);
        setLocation(250,10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == search){
            String query = "select * from studentLeave where rollno = '"+crollno.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (ae.getSource() == print){
            try{
                table.print();
            }catch (Exception e){
                e.printStackTrace();
            }

//            new updateStudent();
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentLeaveDetails();
    }
}


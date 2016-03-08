package chat1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.*;

import org.omg.CORBA.Request;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Register extends WindowAdapter implements ActionListener {
	JFrame frame1; 
	JTextField tf1; 
	JPasswordField pw1;
	JPasswordField CpasswordTextField = new JPasswordField(); 
    JTextField EmailTextField = new JTextField();
	JButton button;
	JButton cancel;
	JLabel heading; 
	JLabel label;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	private static String tableName = "user";  
	private static Connection conn = null;  
	private static Statement stmt = null;

	public static void main(String[] args){ 
		new Register(); 
		} 
	public Register(){ 
		  
		frame1 = new JFrame("Registeration Page"); 
		tf1=new JTextField();
		pw1=new JPasswordField();
		button=new JButton("Confirm");
		cancel =new JButton("Cancel");
		heading=new JLabel("Registeration Page");
	 heading.setFont(new Font("Impact", Font.BOLD,40));
	 label=new JLabel("Enter Username"); 
	 label.setFont(new Font("Serif", Font.PLAIN, 16)); 
	 label1=new JLabel("Enter Password"); 
	 label1.setFont(new Font("Serif", Font.PLAIN, 16));
	 label2=new JLabel("Re-Enter Password"); 
	 label2.setFont(new Font("Serif", Font.PLAIN, 16));
	 label3=new JLabel("Enter email-ID"); 
	 label3.setFont(new Font("Serif", Font.PLAIN, 16));
	 JPanel panel = new JPanel();
	 button.addActionListener(this);
	 cancel.addActionListener(this);
	 panel.add(heading);
	 panel.add(tf1);
	 panel.add(pw1);
	 panel.add(CpasswordTextField);
	 panel.add(EmailTextField);
	 panel.add(label); 
	 panel.add(label1); 
	 panel.add(label2); 
	 panel.add(label3); 
	 panel.add(button); 
	 panel.add(cancel);
	 heading.setBounds(30,20,280,80);
	 label.setBounds(20,130,250,60); 
	 label1.setBounds(20,180,250,60);
	 label2.setBounds(20,230,250,60);
	 label3.setBounds(20,280,250,60);
	 tf1.setBounds(150,150,150,30);
	 pw1.setBounds(150,200,150,30);
	 CpasswordTextField.setBounds(150,250,150,30);
	 EmailTextField.setBounds(150,300,150,30);
	 button.setBounds(70,400,90,30); 
	 cancel.setBounds(250,400,90,30);
	 frame1.add(panel); 
	 panel.setLayout(null); 
	 frame1.setSize(500, 550);
	 frame1.setVisible(true);
	 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); } // pass the user name to MyClient class 
	public void actionPerformed(ActionEvent e){ 
		try{ 
			
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
	        //gets a connection  
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","1234");  
	        stmt = conn.createStatement(); 
	        String login = new String(tf1.getText());  
	        String pwd = new String(pw1.getPassword());  
	        String cpass = new String(CpasswordTextField.getPassword());
            String email = EmailTextField.getText();
            ResultSet results = stmt.executeQuery("select username from user");
           String uname = null;
            while(results.next())  
            {  
               uname = results.getString("username"); 
               if(login.equals(uname)){
            	   JOptionPane.showMessageDialog(null,"Please enter another username","Error",JOptionPane.ERROR_MESSAGE);  
                   tf1.setText(null);  
                   pw1.setText(null);  
                   CpasswordTextField.setText(null);
                   EmailTextField.setText(null);
                   
                   tf1.requestFocusInWindow();   
               }
            }
              if(login.equals("")||pwd.equals("")||cpass.equals("")||email.equals("")){
             	JOptionPane.showMessageDialog(null,"Please fill all fields","Error",JOptionPane.ERROR_MESSAGE);
             	 tf1.setText(null);  
                 pw1.setText(null);  
                 CpasswordTextField.setText(null);
                 EmailTextField.setText(null);
             	tf1.requestFocusInWindow();
             	
             }
            else   if (pwd.equals(cpass))
    		{ 
            stmt.executeUpdate("insert into user values('"+login+"','"+pwd+"','"+cpass+"','"+email+"')"); 
            
	        System.out.println("Registration successful"); 
        JOptionPane.showMessageDialog(null,"Registeration Successful","Congratulations",JOptionPane.INFORMATION_MESSAGE);
        frame1.dispose(); 
		Login lgn=new Login();
         
    }     
            
            else if(pwd!=cpass){
            	 JOptionPane.showMessageDialog(null,"password and confirm password doesn't match","Error",JOptionPane.ERROR_MESSAGE);
            	 tf1.setText(null);  
                 pw1.setText(null);  
                 CpasswordTextField.setText(null);
                 EmailTextField.setText(null);
            	 tf1.requestFocusInWindow();
             }
             
    else {  
        JOptionPane.showMessageDialog(null,"Please enter another username","Error",JOptionPane.ERROR_MESSAGE);  
        tf1.setText(null);  
        pw1.setText(null);  
        CpasswordTextField.setText(null);
        EmailTextField.setText(null);
        
        tf1.requestFocusInWindow();  
    }      
	            	
	        	
	         
	            
	              
	          
	        stmt.close();  
	        conn.close();  
			
		
		}catch (Exception te){
			te.printStackTrace();
		} 
		cancel.addActionListener(new java.awt.event.ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		frame1.dispose();
	    		
	    	}
	    	
		} );
	} 
}

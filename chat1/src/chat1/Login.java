package chat1;

import javax.swing.*;

import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.sql.Statement;
import java.util.*; 
// Login class which takes a user name and passed it to client class 
public class Login implements ActionListener{ 
JFrame frame1; 
JTextField tf; 
JPasswordField pw;
JButton button;
JButton signup;
JLabel heading; 
JLabel label; 
JLabel label1;
private static String tableName = "user";  
private static Connection conn = null;  
private static Statement stmt = null;

public static void main(String[] args){ 
	new Login(); 
	} 
public Login(){ 
	  
	frame1 = new JFrame("Login Page"); 
	tf=new JTextField();
	pw=new JPasswordField();
	button=new JButton("Login");
	signup =new JButton("Sign Up");
	heading=new JLabel("Chat Server");
 heading.setFont(new Font("Impact", Font.BOLD,40));
 label=new JLabel("Enter Username"); 
 label.setFont(new Font("Serif", Font.PLAIN, 16)); 
 label1=new JLabel("Enter Password"); 
 label1.setFont(new Font("Serif", Font.PLAIN, 16));
 JPanel panel = new JPanel();
 button.addActionListener(this);
 signup.addActionListener(this);
 panel.add(heading);
 panel.add(tf);
 panel.add(pw);
 panel.add(label);
 panel.add(label1);
 panel.add(button); 
 panel.add(signup);
 heading.setBounds(30,20,280,80);
 label.setBounds(20,130,250,60); 
 label1.setBounds(20,180,250,60); 
 tf.setBounds(150,150,150,30);
 pw.setBounds(150,200,150,30);
 button.setBounds(70,300,90,30); 
 signup.setBounds(200,300,90,30);
 frame1.add(panel); 
 panel.setLayout(null); 
 frame1.setSize(500, 500);
 frame1.setVisible(true);
 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); } // pass the user name to MyClient class 
public void actionPerformed(ActionEvent e){ 
	String name="";
	String pass="";
	if(e.getSource()==button){
	try{ 
		
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
        //gets a connection  
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","1234");  
        stmt = conn.createStatement(); 
        name = tf.getText();
        String login = tf.getText();  
        String pwd = new String(pw.getPassword());  
        ResultSet results = stmt.executeQuery("select username, password from user where username ='"+login+"'");  
        String username = null; 
        String password = null;  
          
        while(results.next())  
        {  
           username = results.getString("username");  
          password = results.getString("password");  
        }  
        	//if(request.getParameter("login").equals(null) && request.getParameter("pwd").equals(null)  ){
        	//	JOptionPane.showMessageDialog(null,"Please enter username and password","Error",JOptionPane.ERROR_MESSAGE); 
        //	}
            //validate login and password here. validity will be done by sending login/password to the server  
         if ( login.equals(username) && pwd.equals(password)) {  
                System.out.println("login successful");     
            	frame1.dispose(); 
        		MyClient mc=new MyClient(name);
        	
         //MyServer ms=new MyServer(); 
               
            }     
            else {  
                JOptionPane.showMessageDialog(null,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);  
                tf.setText("");  
                pw.setText("");  
                tf.requestFocusInWindow();  
            }  
              
          
        results.close();  
        conn.close();  
		
	//	frame1.dispose(); 
	//	MyClient mc=new MyClient(name);
	
 //MyServer ms=new MyServer(); 
	}catch (Exception te){
		te.printStackTrace();
	} 
	}
	if (e.getSource()==signup){
		frame1.dispose();
		Register reg=new Register();
	}
	
	//signup.addActionListener(new java.awt.event.ActionListener() {
	//public void actionPerformed(ActionEvent e1) 
    //	{
    		
    //		frame1.dispose();
    //		Register reg = new Register();
    		
    //	}
	
//});

}
}
    	
	 


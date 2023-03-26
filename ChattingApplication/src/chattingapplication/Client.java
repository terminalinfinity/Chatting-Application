package chattingapplication;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client  implements ActionListener {
    JTextField text;
    static JPanel a1;
   static  Box vertical=Box.createVerticalBox();
    static JFrame f=new JFrame();
    static DataOutputStream dout;
    
    Client(){
        f.setLayout(null);
        
        JPanel p1=new JPanel();
        p1.setBackground(new Color(0, 69, 116));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);
        
       ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/3.png"));
       Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
       ImageIcon i3=new ImageIcon(i2);
       JLabel  back=new JLabel(i3);
       back.setBounds(5,20,25,25);
       p1.add(back);
       
      back.addMouseListener(new MouseAdapter(){
          public void mouseClicked(MouseEvent ae){
              System.exit(0);
          }
      });
        
       ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icon/DP4.png"));
       Image i5=i4.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
       ImageIcon i6=new ImageIcon(i5);
       JLabel  profile=new JLabel(i6);
       profile.setBounds(35,10,45,45);
       p1.add(profile);
       
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/video.png"));
       Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i9=new ImageIcon(i8);
       JLabel  video=new JLabel(i9);
       video.setBounds(300,20,30,30);
       p1.add(video);
       
       ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icon/phone.png"));
       Image i11=i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
       ImageIcon i12=new ImageIcon(i11);
       JLabel  phone=new JLabel(i12);
       phone.setBounds(360,20,35,30);
       p1.add(phone);
       
       ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icon/3icon.png"));
       Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
       ImageIcon i15=new ImageIcon(i14);
       JLabel  morevert=new JLabel(i15);
       morevert.setBounds(420,20,10,25);
       p1.add(morevert);
       
       JLabel name=new JLabel("Active Now");
       name.setBounds(110, 35, 100, 18);
       name.setForeground(Color.GREEN);
       name.setFont(new Font("Times New Roman", Font.BOLD,14));
       p1.add(name);
       
       JLabel status=new JLabel("Jannat");
       status.setBounds(100, 15, 100, 18);
       status.setForeground(Color.WHITE);
       status.setFont(new Font("Times New Roman", Font.BOLD,18));
       p1.add(status);
       
        f.setSize(450,700);
        f.setLocation(800,50);
        f.getContentPane().setBackground(Color.WHITE);
        
        a1=new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.setUndecorated(true);
       f.getContentPane().setBackground(Color.GRAY);   
       f.add(a1);
       
       text=new JTextField();
       text.setBounds(5, 655, 310, 40);
       text.setForeground(Color.BLACK);
       text.setFont(new Font("Times New Roman",Font.PLAIN,16));
       f.add(text);
       
       JButton  sent=new JButton("Sent");
       sent.setBounds(320, 655, 123, 40);
       sent.setBackground(new Color(0, 69, 116));
       sent.setForeground(Color.WHITE);
       sent.addActionListener(this);
       sent.setFont(new Font("Times New Roman",Font.BOLD,16));
       f.add(sent);
        
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
        String out=text.getText();
        
        JPanel p2=formatLabel(out);
        
        a1.setLayout(new BorderLayout());
        
        JPanel right=new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical,BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        
        text.setText("");
        
        f.repaint();
        f.invalidate();
        f.validate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static JPanel formatLabel(String out){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        JLabel output=new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
        output.setFont(new Font("Times New Roman",Font.PLAIN,20));
        output.setBackground(new Color(129, 141, 254));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    
    public static void main(String[] args) {
        new Client();
        
        try{
            Socket s=new Socket("127.0.0.1",6001);
             DataInputStream din=new DataInputStream(s.getInputStream());
                dout=new DataOutputStream(s.getOutputStream());
                
                 while(true){
                     a1.setLayout(new BorderLayout());
                     
                    String msg=din.readUTF();
                    JPanel panel=formatLabel(msg);
                    
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical,BorderLayout.PAGE_START);
                    
                    f.validate();
                }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

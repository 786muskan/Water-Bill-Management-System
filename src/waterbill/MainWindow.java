package waterbill;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import java.awt.Color;
import java.awt.Cursor;


public class MainWindow extends JFrame implements ActionListener,MouseListener{
    Dimension dObj;
    JButton btnConsumer,btnMeter,btnBill;
    JLabel lbMain,lbConsumer,lbMeter,lbBill,lbimg;
    ImageIcon img;
    Font textFont;
    Cursor hand;
    public MainWindow(){
        setTitle("Main Window");
        hand=new Cursor(Cursor.HAND_CURSOR);
        Dimension dObj=Toolkit.getDefaultToolkit().getScreenSize();
        textFont=new Font("Dubai",Font.BOLD,26);

         lbMain=new JLabel("Main Menu");
         lbMain.setFont(new Font("Ebrima",Font.BOLD,80));
         lbMain.setBounds(800, 100,550,100);
        lbMain.setForeground(Color.BLACK);
        img=new ImageIcon(ClassLoader.getSystemResource("img/Consumer_menu.png"));
         btnConsumer=new JButton(img);
      //  btnConsumer.setBackground(Color.WHITE);
        btnConsumer.setBounds(200,450,250,250);
         btnConsumer.addActionListener(this);
         btnConsumer.setOpaque(false);
         btnConsumer.setContentAreaFilled(false); 
        btnConsumer.setBorderPainted(false);
        btnConsumer.addMouseListener(this);

         img=new ImageIcon(ClassLoader.getSystemResource("img/meter_menu.png"));
         btnMeter=new JButton(img);
         //btnMeter.setBackground(Color.WHITE);
         btnMeter.setBounds(600,450,250,250);
         btnMeter.addActionListener(this);
         btnMeter.addMouseListener(this);
         btnMeter.setOpaque(false);
         btnMeter.setContentAreaFilled(false); 
         btnMeter.setBorderPainted(false);

         img=new ImageIcon(ClassLoader.getSystemResource("img/bill_menu.png"));
         btnBill=new JButton(img);
        btnBill.setBounds(1000,450,250,250);
         btnBill.addActionListener(this);
         btnBill.addMouseListener(this);
         btnBill.setOpaque(false);
         btnBill.setContentAreaFilled(false); 
         btnBill.setBorderPainted(false);

        lbConsumer=new JLabel("Consumer");
        lbConsumer.setFont(textFont);
         lbConsumer.setBounds(250,620,200,250);

        lbBill=new JLabel("Bill");
         lbBill.setFont(textFont);
         lbBill.setBounds(1050,620,100,250);

        lbMeter=new JLabel("Meter");
        lbMeter.setFont(textFont);
        lbMeter.setBounds(700,620,100,250);

        
         add(btnConsumer);
         add(btnBill);
         add(btnMeter);
         add(lbMain);
         add(lbBill);
         add(lbConsumer);
         add(lbMeter);
        // Background image
        
        img = new ImageIcon(ClassLoader.getSystemResource("img/Main_Menu.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setLayout(null);
        //getContentPane().setBackground(new Color(166,227,233));

        setResizable(false);
        setBounds(0,0,(int)dObj.getWidth(),(int)dObj.getHeight()-150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        img=new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        setIconImage(img.getImage());
        setVisible(true);
    }
    public void actionPerformed(ActionEvent aeObj){
        if(aeObj.getSource()==btnConsumer){
           dispose();
            new Consumer_menu();
        }
        else if(aeObj.getSource()==btnMeter){
            dispose();
            new Meter_Menu();
        }
        else if(aeObj.getSource()==btnBill){
            dispose();
            new Bill_Menu();
        }
    }
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==btnConsumer){
            btnConsumer.setCursor(hand);
        }
        else if(e.getSource()==btnBill){
            btnBill.setCursor(hand);
        }
        else if(e.getSource()==btnMeter){
            btnMeter.setCursor(hand);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==btnConsumer){
            btnConsumer.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnBill){
            btnBill.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnMeter){
            btnMeter.setCursor(new Cursor(1));
        }
    }
//     static JDialog  box;
//    static JLabel mg;
//     static JButton okay;
//     static boolean ans=false;
//     public static boolean dialogBox(String msg){
//         box=new JDialog();
//         mg=new JLabel(msg);
//         mg.setBounds(10, 10,550,150);
//         mg.setFont(new Font("Dubai",Font.BOLD,24));
       
        
//         okay=new JButton("OKAY");
//         okay.setBounds(250,150,100,50);
//         okay.setBackground(Color.black);
//         okay.setForeground(Color.white);
//         okay.addActionListener(new ActionListener() {     
//             public void actionPerformed(ActionEvent e){ 
//                 ans=true;
//                 box.dispose();
//             }});

//         box.add(okay);
//         box.add(mg);
//         box.setBounds(600,350,600,300);
        
//         box.setLayout(null);
//         box.setVisible(true);
//         return ans;
//     }
    
}

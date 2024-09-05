package waterbill;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Cursor;

public class Bill_Menu extends JFrame implements ActionListener,MouseListener{
    Dimension dObj;
    JButton btnPay,btnSearch,btnReceipt,btnBack;
    JLabel lbPay,lbSearch,lbReceipt,lbimg;
    ImageIcon img;
    Font textFont;
    Cursor hand;
    public Bill_Menu(){
        setTitle("Bill Window");
        hand=new Cursor(Cursor.HAND_CURSOR);
        textFont=new Font("Dubai",Font.BOLD,26);
        dObj=Toolkit.getDefaultToolkit().getScreenSize();

        
         img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\payment.png");
         btnPay=new JButton(img);
         //btnAdd.setBackground(Color.WHITE);
         btnPay.setBounds(400,400,250,250);
         btnPay.addActionListener(this);
         btnPay.addMouseListener(this);
         btnPay.setOpaque(false);
         btnPay.setContentAreaFilled(false); 
         btnPay.setBorderPainted(false);

        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\Search_bill.png");
        btnSearch=new JButton(img);
        //btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(850,400,250,250);
        btnSearch.addActionListener(this);
        btnSearch.addMouseListener(this);
        btnSearch.setOpaque(false);
        btnSearch.setContentAreaFilled(false); 
        btnSearch.setBorderPainted(false);

        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\receipt.png");
        btnReceipt=new JButton(img);
       // btnUpdate.setBackground(Color.WHITE);
       btnReceipt.setBounds(1300,400,250,250);
       btnReceipt.addActionListener(this);
       btnReceipt.addMouseListener(this);
       btnReceipt.setOpaque(false);
       btnReceipt.setContentAreaFilled(false); 
       btnReceipt.setBorderPainted(false);

        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\back.png");
        btnBack=new JButton(img);
        btnBack.setBackground(Color.WHITE);
        btnBack.setBounds(1600,100,200,200);
        btnBack.addActionListener(this);
        btnBack.addMouseListener(this);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false); 
        btnBack.setBorderPainted(false);

        lbPay=new JLabel("Make Payment");
        lbPay.setFont(textFont);
        lbPay.setBounds(450,550,200,250);

        lbReceipt=new JLabel("Print Bill Receipt");
        lbReceipt.setFont(textFont);
        lbReceipt.setBounds(1350,550,200,250);

        lbSearch=new JLabel("Search Bill");
        lbSearch.setFont(textFont);
        lbSearch.setBounds(900,550,200,250);//950,500,100,250

        
         add(btnPay);
         add(btnSearch);
         add(btnReceipt);
         add(lbPay);
         add(lbSearch);
         add(lbReceipt);
         add(btnBack);

        img = new ImageIcon(ClassLoader.getSystemResource("img/Bill_MMenu.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setLayout(null);
       // getContentPane().setBackground(new Color(166,227,233));
        setBounds(0,0,(int)dObj.getWidth(),(int)dObj.getHeight()-150);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent aeObj){
        if(aeObj.getSource()==btnPay){
           // new Consumer().searchDialog("m");;
           new Bill().searchDialog("p");
        }
        else if(aeObj.getSource()==btnSearch){
            //new Meter().searchDialog("s");
            new Bill().searchDialog("s");
        }
        else if(aeObj.getSource()==btnReceipt){
            new Bill().searchDialog("r");
        }
        else if(aeObj.getSource()==btnBack){
            dispose();
            new MainWindow();
        }
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==btnBack){
            btnBack.setCursor(hand);
        }
        else if(e.getSource()==btnPay){
            btnPay.setCursor(hand);
        }
        else if(e.getSource()==btnSearch){
            btnSearch.setCursor(hand);
        }
        else if(e.getSource()==btnReceipt){
            btnReceipt.setCursor(hand);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==btnBack){
            btnBack.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnPay){
            btnPay.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnSearch){
            btnSearch.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnReceipt){
            btnReceipt.setCursor(new Cursor(1));
        }
    }
}

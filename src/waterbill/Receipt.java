package waterbill;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;

import java.awt.Color;

import java.awt.Font;

import java.awt.Dimension;
 import java.awt.Toolkit;
import java.awt.event.ActionListener;

public class Receipt extends JFrame implements ActionListener{
    JLabel lbimg,lbPD,lbCIN,lbCN,lbTC,lbTP;
    ImageIcon img;
    JButton btnBack;
    Dimension dObj;
    Font fObj;
    public Receipt(Meter_Reading mrObj,Meter mObj,String []data,Bill bObj){
        setTitle("Bill Receipt");  
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        fObj=new Font("Dubai",Font.BOLD,30);

        lbCN=new JLabel(data[0]);
        lbCN.setFont(fObj);
        lbCN.setBounds(1100, 280,250,150);

        lbCIN=new JLabel(mObj.getcINumber());
        lbCIN.setFont(fObj);
        lbCIN.setBounds(1100, 180,250,150);
        
        
       
       long c=(mrObj.getCurrentReading()-mrObj.getPreviousReading())/1000;
       
       if(bObj.getPaymentDate().compareTo(bObj.getDueDate())>0){
         lbTP=new JLabel(String.valueOf(c+Long.parseLong(data[2])+10)+"(penalty)");
       }
       else{

           lbTP=new JLabel(String.valueOf(c+Long.parseLong(data[2])));
       }
        lbTP.setFont(fObj);
        lbTP.setBounds(1100, 600,250,150);

        lbTC=new JLabel(String.valueOf(mrObj.getCurrentReading()-mrObj.getPreviousReading()));
        lbTC.setFont(fObj);
        lbTC.setBounds(1100, 450,250,150);
        
        lbPD=new JLabel(bObj.getPaymentDate());
        lbPD.setFont(fObj);
        lbPD.setBounds(1100, 750,250,150);

        
        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\back.png");
        btnBack=new JButton(img);
        btnBack.setBackground(Color.WHITE);
        btnBack.setBounds(100,100,200,200);
        btnBack.addActionListener(this);
        //btnBack.addMouseListener(this);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false); 
        btnBack.setBorderPainted(false);
       add(btnBack);
       add(lbCIN);
       add(lbCN);
       add(lbPD);
       add(lbTC);
       add(lbTP);
        img = new ImageIcon(ClassLoader.getSystemResource("img/receipt_pay.png"));
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
        
        if(aeObj.getSource()==btnBack){
            dispose();
           // new Bill_Menu();
        }
    }

}

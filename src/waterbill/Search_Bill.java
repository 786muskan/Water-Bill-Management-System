package waterbill;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;

import java.awt.Color;

public class Search_Bill extends JFrame implements ActionListener{
    ImageIcon img;
    JLabel lbimg,lbName,lbAddress,lbMeterType,lbMonth,lbCr,lbPr,lbTC,lbBP,lbLF,lbC,lbDc,lbTotal,lbDates[],lbCIN;
    Dimension dObj;
    JButton btnBack,btnPay;
    Font fObj;
    Bill bObj;
    
    public Search_Bill(Consumer cObj,Meter mObj,Meter_Reading mrObj,String data[],int mon,Bill bObj,String op){
        this.bObj=bObj;
        fObj=new Font("Dubai",Font.BOLD,30);
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        lbDates=new JLabel[3];
        for(int i=0;i<3;i++){
            lbDates[i]=new JLabel();
            lbDates[i].setFont(fObj);
        }
        lbDates[0].setText(mrObj.getReadingDate());
        lbDates[0].setBounds(1250,280,250,150);
        lbDates[2].setText(bObj.getDueDate());
        lbDates[1].setBounds(1450,280,250,150);
        lbDates[1].setText(mrObj.getReadingDate());
        lbDates[2].setBounds(1650,280,250,150);
        
        lbName=new JLabel(cObj.getCName());
        lbName.setFont(fObj);
        lbName.setBounds(300, 280,250,150);

        
        lbAddress=new JLabel(cObj.getCAddress());
        lbAddress.setFont(fObj);
        lbAddress.setBounds(300, 400,250,150);
        
        
        lbCIN=new JLabel(mObj.getcINumber());
        lbCIN.setFont(fObj);
        lbCIN.setBounds(300, 500,250,150);
        
        
        lbMeterType=new JLabel(data[1]);
        lbMeterType.setFont(fObj);
        lbMeterType.setBounds(750, 280,250,150);

        
        lbMonth=new JLabel(String.valueOf(mon));
        lbMonth.setFont(fObj);
        lbMonth.setBounds(1100, 280,250,150);

        
        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\back.png");
        btnBack=new JButton(img);
        btnBack.setBackground(Color.WHITE);
        btnBack.setBounds(40,10,200,200);
        btnBack.addActionListener(this);
        //btnBack.addMouseListener(this);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false); 
        btnBack.setBorderPainted(false);

        

        lbCr=new JLabel(String.valueOf(mrObj.getCurrentReading()));
        lbCr.setFont(fObj);
        lbCr.setBounds(800, 550,250,150);
        
        btnPay=new JButton("Pay");
        btnPay.setBackground(Color.black);
        btnPay.setForeground(Color.white);
        btnPay.setBounds(40,500,150,150);
        btnPay.addActionListener(this);
        
        
        lbPr=new JLabel(String.valueOf(mrObj.getPreviousReading()));
        lbPr.setFont(fObj);
        lbPr.setBounds(1200, 550,250,150);
        
        
        
        lbTC=new JLabel(String.valueOf(mrObj.getCurrentReading()-mrObj.getPreviousReading()));
        lbTC.setFont(fObj);
        lbTC.setBounds(1600, 550,250,150);
        
        
        
        lbBP=new JLabel(data[2]);
        lbBP.setFont(fObj);
        lbBP.setBounds(650, 750,250,150);
        
        
        lbLF=new JLabel(String.valueOf(10));
        lbLF.setFont(fObj);
        lbLF.setBounds(950, 750,250,150);
        
        
        long c=(mrObj.getCurrentReading()-mrObj.getPreviousReading())/1000;
        lbC=new JLabel(String.valueOf(c));
        lbC.setFont(fObj);
        lbC.setBounds(1250, 750,250,150);
        
        
        lbTotal=new JLabel(String.valueOf(c+Long.parseLong(data[2])));
        lbTotal.setFont(fObj);
        lbTotal.setBounds(450, 750,250,150);
        
        lbDc=new JLabel(String.valueOf(c+Integer.parseInt(data[2])+10));
        lbDc.setFont(fObj);
        lbDc.setBounds(1550, 750,250,150);
        
        add(btnBack);
        add(lbName);
        add(lbAddress);
        add(lbMeterType);
        add(lbMonth);
        add(lbCIN);
        add(lbCr);
        add(lbPr);
        add(lbTC);
        add(lbBP);
        add(lbLF);
        add(lbC);
        add(lbDc);
        add(lbTotal);
        add(lbDates[0]);
        add(lbDates[1]);
        add(lbDates[2]);
        if(op.charAt(0)=='p'){
            add(btnPay);
        }
        img = new ImageIcon(ClassLoader.getSystemResource("img/bills.png"));    
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-100);
        add(lbimg);
    
         setLayout(null);
            setSize((int)dObj.getWidth(),(int)dObj.getHeight()-50);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setResizable(false);
            setVisible(true);
    }
    public void actionPerformed(ActionEvent aeObj){
        
        if(aeObj.getSource()==btnBack){
            dispose();
            //new Bill_Menu();
        }
        if(aeObj.getSource()==btnPay){
            bObj.payment();
            dispose();
        }
    }
}

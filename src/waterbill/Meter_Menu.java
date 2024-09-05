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

public class Meter_Menu extends JFrame implements ActionListener,MouseListener{
    Dimension dObj;
    JButton btnAdd,btnSearch,btnUpdate,btnBack;
    JLabel lbAdd,lbSearch,lbUpdate,lbimg;
    ImageIcon img;
    Font textFont;
    Cursor hand;
    public Meter_Menu(){
        setTitle("Meter Window");
        hand=new Cursor(Cursor.HAND_CURSOR);
        textFont=new Font("Dubai",Font.BOLD,26);
        dObj=Toolkit.getDefaultToolkit().getScreenSize();

        
         img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\add_meter.png");
         btnAdd=new JButton(img);
         //btnAdd.setBackground(Color.WHITE);
         btnAdd.setBounds(400,400,250,250);
         btnAdd.addActionListener(this);
         btnAdd.addMouseListener(this);
         btnAdd.setOpaque(false);
         btnAdd.setContentAreaFilled(false); 
         btnAdd.setBorderPainted(false);

        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\Search_meter.png");
        btnSearch=new JButton(img);
        //btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(850,400,250,250);
        btnSearch.addActionListener(this);
        btnSearch.addMouseListener(this);
        btnSearch.setOpaque(false);
        btnSearch.setContentAreaFilled(false); 
        btnSearch.setBorderPainted(false);

        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\update_meter.png");
        btnUpdate=new JButton(img);
       // btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBounds(1300,400,250,250);
        btnUpdate.addActionListener(this);
        btnUpdate.addMouseListener(this);
        btnUpdate.setOpaque(false);
        btnUpdate.setContentAreaFilled(false); 
        btnUpdate.setBorderPainted(false);

        img=new ImageIcon("D:\\Projects\\Java\\WaterBillManagement\\src\\img\\back.png");
        btnBack=new JButton(img);
        btnBack.setBackground(Color.WHITE);
        btnBack.setBounds(1600,100,200,200);
        btnBack.addActionListener(this);
        btnBack.addMouseListener(this);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false); 
        btnBack.setBorderPainted(false);

        lbAdd=new JLabel("Add Meter");
        lbAdd.setFont(textFont);
        lbAdd.setBounds(450,550,200,250);

        lbUpdate=new JLabel("Update Meter");
        lbUpdate.setFont(textFont);
        lbUpdate.setBounds(1350,550,200,250);

        lbSearch=new JLabel("Search Meter");
        lbSearch.setFont(textFont);
        lbSearch.setBounds(900,550,200,250);//950,500,100,250

        
         add(btnAdd);
         add(btnSearch);
         add(btnUpdate);
         add(lbAdd);
         add(lbSearch);
         add(lbUpdate);
         add(btnBack);

        img = new ImageIcon(ClassLoader.getSystemResource("img/Meter_MMenu.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setLayout(null);
       // getContentPane().setBackground(new Color(166,227,233));
        setBounds(0,0,(int)dObj.getWidth(),(int)dObj.getHeight()-150);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent aeObj){
        if(aeObj.getSource()==btnAdd){
            new Consumer().searchDialog("m");;
        }
        else if(aeObj.getSource()==btnSearch){
            new Meter().searchDialog("s");
        }
        else if(aeObj.getSource()==btnUpdate){
            new Meter().searchDialog("u");
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
        else if(e.getSource()==btnAdd){
            btnAdd.setCursor(hand);
        }
        else if(e.getSource()==btnSearch){
            btnSearch.setCursor(hand);
        }
        else if(e.getSource()==btnUpdate){
            btnUpdate.setCursor(hand);
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
        else if(e.getSource()==btnAdd){
            btnAdd.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnSearch){
            btnSearch.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnUpdate){
            btnUpdate.setCursor(new Cursor(1));
        }
    }

    
}



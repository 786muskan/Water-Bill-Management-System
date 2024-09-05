package waterbill;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Cursor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Consumer_menu extends JFrame implements ActionListener,MouseListener{
    Dimension dObj;
    JButton btnBack,btnAdd,btnSearch,btnUpdate,btnShow;
    JLabel lbAdd,lbSearch,lbBill,lbUpdate,lbShow,msg,errorMsg,lbimg;
    ImageIcon img;
    Font textFont;
    JTextField txtAddh;
    JDialog searchBox;
    Cursor hand;
    public Consumer_menu(){
        setTitle("Consumer Window");
        hand=new Cursor(Cursor.HAND_CURSOR);
        Dimension dObj=Toolkit.getDefaultToolkit().getScreenSize();
        textFont=new Font("Dubai",Font.BOLD,26);


        img=new ImageIcon(ClassLoader.getSystemResource("img//Back.png"));
        btnBack=new JButton(img);
        btnBack.setBounds(1600,100,200,200);
        btnBack.addActionListener(this);
        btnBack.addMouseListener(this);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false); 
        btnBack.setBorderPainted(false);

         img=new ImageIcon(ClassLoader.getSystemResource("img/add_Consumer.png"));
         btnAdd=new JButton(img);
         btnAdd.setBackground(Color.WHITE);
         btnAdd.setBounds(300,350,250,250);
         btnAdd.addActionListener(this);
         btnAdd.addMouseListener(this);
         btnAdd.setOpaque(false);
         btnAdd.setContentAreaFilled(false); 
         btnAdd.setBorderPainted(false);


         lbAdd=new JLabel("Add Consumer");
        lbAdd.setFont(textFont);
         lbAdd.setBounds(350,520,200,250);

        img=new ImageIcon(ClassLoader.getSystemResource("img/search_c.png"));
        btnSearch=new JButton(img);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(650,350,250,250);
        btnSearch.addActionListener(this);
        btnSearch.addMouseListener(this);
        btnSearch.setOpaque(false);
        btnSearch.setContentAreaFilled(false); 
        btnSearch.setBorderPainted(false);


         lbSearch=new JLabel("Search Consumer");
         lbSearch.setFont(textFont);
         lbSearch.setBounds(680,520,200,250);

        img=new ImageIcon(ClassLoader.getSystemResource("img/edit_c.png"));
        btnUpdate=new JButton(img);
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBounds(1050,350,250,250);
        btnUpdate.addActionListener(this);
        btnUpdate.addMouseListener(this);
        btnUpdate.setOpaque(false);
        btnUpdate.setContentAreaFilled(false); 
        btnUpdate.setBorderPainted(false);

        lbUpdate=new JLabel("Update Consumer");
        lbUpdate.setFont(textFont);
        lbUpdate.setBounds(1050,520,200,250);

        img=new ImageIcon(ClassLoader.getSystemResource("img/All_consumer.png"));
        btnShow=new JButton(img);
        btnShow.setBackground(Color.WHITE);
        btnShow.setBounds(1400,350,250,250);
        btnShow.addActionListener(this);
        btnShow.addMouseListener(this);
        btnShow.setOpaque(false);
        btnShow.setContentAreaFilled(false); 
        btnShow.setBorderPainted(false);

        lbShow=new JLabel("Show Consumer");
        lbShow.setFont(textFont);
        lbShow.setBounds(1450,520,200,250);

        add(btnBack);
        add(btnAdd);
        add(lbAdd);
        add(btnSearch);
        add(lbSearch);
        add(lbUpdate);
        add(btnUpdate);
        add(btnShow);
        add(lbShow);
        setLayout(null);

        img = new ImageIcon(ClassLoader.getSystemResource("img/Consumer_MMenu.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setSize((int)dObj.getWidth()-100,(int)dObj.getHeight()-250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        img=new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        setIconImage(img.getImage());
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        Consumer cObj=new Consumer();
        if(e.getSource()==btnBack){
            dispose();
            new MainWindow();
        }
        else if(e.getSource()==btnAdd){
            dispose();
            new Add_Consumer();
        }
        else if(e.getSource()==btnSearch){
            cObj.searchDialog("s");   
        }
        else if(e.getSource()==btnUpdate){
            cObj.searchDialog("u");
        }
       
        else if(e.getSource()==btnShow){
           dispose();
            Consumer.showAll();
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
        else if(e.getSource()==btnShow){
            btnShow.setCursor(hand);
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
        else if(e.getSource()==btnShow){
            btnShow.setCursor(new Cursor(1));
        }

    }
    
}


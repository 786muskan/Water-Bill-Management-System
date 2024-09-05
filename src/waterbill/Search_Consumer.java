package waterbill;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Search_Consumer extends JFrame implements ActionListener,MouseListener{
    Dimension dObj;
    JLabel lbMain,lbName,lbAddress,lbPhone,lbEmail,lbAadhar,lbimg;
    JTextField txt[];
    JTextArea txtAddress;
    Font textFont,btnFont; 
    Consumer cObj;
    JButton Okay;
    ImageIcon img;

    
    public Search_Consumer(Consumer cObj){
        this.cObj=cObj;
        setTitle("Search Consumer");
        textFont=new Font("Dubai",Font.BOLD,26);
        btnFont=new Font("Arial",Font.PLAIN,20);
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        
         txt=new JTextField[4];


        lbName=new JLabel("Name");
        lbName.setBounds(900,300, 200, 50);
        lbName.setFont(textFont);

        txt[0]=new JTextField();
        txt[0].setBounds(1050,300,250,50);
        txt[0].setFont(textFont);
        txt[0].setText(cObj.getCName());
        txt[0].setEditable(false);

        lbPhone=new JLabel("Phone");
        lbPhone.setBounds(900,400, 200, 50);
        lbPhone.setFont(textFont);

        txt[1]=new JTextField();
        txt[1].setBounds(1050,400,250,50);
        txt[1].setFont(textFont);
        txt[1].setText(cObj.getCPhone());
        txt[1].setEditable(false);

        lbAadhar=new JLabel("Aadhar-No");
        lbAadhar.setBounds(900,500, 200, 50);
        lbAadhar.setFont(textFont);

        txt[2]=new JTextField();
        txt[2].setBounds(1050,500,250,50);
        txt[2].setFont(textFont);
        txt[2].setText(cObj.getCAadharNo());
        txt[2].setEditable(false);

        lbEmail=new JLabel("Email");
        lbEmail.setBounds(900,600, 200, 50);
        lbEmail.setFont(textFont);

        txt[3]=new JTextField();
        txt[3].setBounds(1050,600,250,50);
        txt[3].setFont(textFont);
        txt[3].setText(cObj.getCEmail());
        txt[3].setEditable(false);

        lbAddress=new JLabel("Address");
        lbAddress.setBounds(900,700, 200, 50);
        lbAddress.setFont(textFont);

        txtAddress=new JTextArea(cObj.getCAddress());
        txtAddress.setBounds(1050,700,400,100);
        txtAddress.setFont(textFont);
        txtAddress.setText(cObj.getCAddress());
        txtAddress.setEditable(false);

        Okay=new JButton("Okay");
        Okay.setBounds(1000, 850, 200, 50);
        Okay.setFont(btnFont);
        Okay.setBackground(Color.BLACK);
        Okay.setForeground(Color.WHITE);
        Okay.addActionListener(this);
        Okay.addMouseListener(this);
        

        
        add(txt[0]);
         add(lbName);
        add(txt[1]);
        add(lbPhone);
        add(txt[2]);
        add(lbAadhar);
        add(txt[3]);
        add(lbEmail);
        add(txtAddress);
        add(lbAddress);
         add(Okay);
       
        img = new ImageIcon(ClassLoader.getSystemResource("img/search_con.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setLayout(null);
        setSize((int)dObj.getWidth()-200,(int)dObj.getHeight()-100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==Okay){
            dispose();
            new Consumer_menu();
       }
    }  
    
    //mouse events
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {  }
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==Okay){
            Okay.setCursor(new Cursor(1));
        }
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==Okay){
            Cursor hand=new Cursor(Cursor.HAND_CURSOR);
            Okay.setCursor(hand);
        }
     }
}

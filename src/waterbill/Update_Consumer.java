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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Cursor;



public class Update_Consumer extends JFrame implements ActionListener,MouseListener {
    Dimension dObj;
    JLabel lbMain,lbName,lbAddress,lbPhone,lbEmail,lbAadhar,errorMsg[],lbimg;
    JTextField txt[];
    JTextArea txtAddress;
    Font textFont,btnFont,errorFont;
    Consumer cObj;
    JButton btnUpdate;
    ImageIcon img;
    
    public Update_Consumer(Consumer cObj){
        this.cObj=cObj;
        setTitle("Update Consumer");
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        textFont=new Font("Dubai",Font.BOLD,26);
        btnFont=new Font("Arial",Font.PLAIN,20);
        errorFont=new Font("Arial",Font.PLAIN,16);

        txt=new JTextField[4];

        lbName=new JLabel("Name");
        lbName.setBounds(880,300, 200, 50);
        lbName.setFont(textFont);

        txt[0]=new JTextField();
        txt[0].setBounds(1030,300,250,50);
        txt[0].setFont(textFont);
        txt[0].setText(cObj.getCName());
        

        lbPhone=new JLabel("Phone");
        lbPhone.setBounds(880,400, 200, 50);
        lbPhone.setFont(textFont);

        txt[1]=new JTextField();
        txt[1].setBounds(1030,400,250,50);
        txt[1].setFont(textFont);
        txt[1].setText(cObj.getCPhone());
        
        lbAadhar=new JLabel("Aadhar-No");
        lbAadhar.setBounds(880,500, 200, 50);
        lbAadhar.setFont(textFont);

        txt[2]=new JTextField();
        txt[2].setBounds(1030,500,250,50);
        txt[2].setFont(textFont);
        txt[2].setText(cObj.getCAadharNo());
        txt[2].setEditable(false);

        lbEmail=new JLabel("Email");
        lbEmail.setBounds(880,600, 200, 50);
        lbEmail.setFont(textFont);

        txt[3]=new JTextField();
        txt[3].setBounds(1030,600,250,50);
        txt[3].setFont(textFont);
        txt[3].setText(cObj.getCEmail());
        

        lbAddress=new JLabel("Address");
        lbAddress.setBounds(880,700, 200, 50);
        lbAddress.setFont(textFont);

        txtAddress=new JTextArea(cObj.getCAddress());
        txtAddress.setBounds(1030,700,300,100);
        txtAddress.setFont(textFont);
        txtAddress.setText(cObj.getCAddress());

        btnUpdate=new JButton("Update");
        btnUpdate.setBounds(980, 850, 200, 50);
        btnUpdate.setFont(btnFont);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.addActionListener(this);
        
        errorMsg=new JLabel[4];
        for(int i=0;i<4;i++){
            errorMsg[i]=new JLabel();
        }
        errorMsg[0].setBounds(1300,300,200,50);
        errorMsg[1].setBounds(1280,400,280,50);
       //errorMsg[2].setBounds(1200,400,280,50);
        errorMsg[2].setBounds(1280,600,200,50);
        errorMsg[3].setBounds(1350,700,200,50);
        for(int i=0;i<4;i++){
            add(errorMsg[i]);
            errorMsg[i].setFont(errorFont);
        }


    
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
        add(btnUpdate);
       
        
        img = new ImageIcon(ClassLoader.getSystemResource("img/update_con.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);
        setLayout(null);
       
        setSize((int)dObj.getWidth()-200,(int)dObj.getHeight()-100);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent eObj) {
       
       if(eObj.getSource()==btnUpdate){
            int temp=0;
            if(txt[0].getText().trim().isEmpty()){
               errorMsg[0].setText("Name Can not be empty");
                errorMsg[0].setForeground(Color.RED);
                temp--;
            }
            else if(cObj.isValidName(txt[0].getText())){
                errorMsg[0].setText("");
                cObj.setCName(txt[0].getText());
                temp++;
            }
            else{
                errorMsg[0].setText("Invalid Name");
                errorMsg[0].setForeground(Color.RED);
                temp--;
            }
            if(txt[1].getText().trim().isEmpty()){
                errorMsg[1].setText("You must enter a phone number");
                errorMsg[1].setForeground(Color.RED);
                temp--;
            }
            else if(cObj.isValidPhone(txt[1].getText())){
                errorMsg[1].setText("");
                cObj.setCPhone(txt[1].getText());
                temp++;
            }
            else{
                errorMsg[1].setText("Phone number must be 10 digits");
                errorMsg[1].setForeground(Color.RED);
                temp--;
            }
            
            if(cObj.isValidEmail(txt[3].getText()) ){
                errorMsg[2].setText("");
                cObj.setCEmail(txt[3].getText());
                temp++;
            }
            else if(!txt[3].getText().trim().isEmpty()){
                errorMsg[2].setText("Invalid Email Format");
                errorMsg[2].setForeground(Color.RED);
                temp--;
            }
           
           if(txtAddress.getText().trim().isEmpty()){
                errorMsg[3].setText("Enter address");
                errorMsg[3].setForeground(Color.RED);  
                temp--;
            }
            else{
                errorMsg[3].setText("");
                cObj.setCAddress(txtAddress.getText());
                temp++;
            }
            if(temp==4){
                cObj.updateConsumer();
                for(int i=0;i<4;i++){   txt[i].setText("");}
                txtAddress.setText("");
                dispose();
                new Consumer_menu();
            }
            
            
        }
    }
    //mouse events
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {  }
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==btnUpdate){
            btnUpdate.setCursor(new Cursor(1));
        }
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==btnUpdate){
            Cursor hand=new Cursor(Cursor.HAND_CURSOR);
            btnUpdate.setCursor(hand);
        }
     }
 }
    


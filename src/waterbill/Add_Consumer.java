package waterbill;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Add_Consumer extends JFrame implements ActionListener,MouseListener{
    Dimension dObj;
    JLabel lbName,lbAddress,lbPhone,lbEmail,lbAadhar,errorMsg[],lbimg;
    JTextField txt[];
    JTextArea txtAddress;
    Font textFont,btnFont,errorFont;
    Cursor hand;
    Consumer cObj;
    JButton save;
    ImageIcon img;
    
    public Add_Consumer(){
        setTitle("Add Consumer");
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        hand=new Cursor(Cursor.HAND_CURSOR);
        textFont=new Font("Dubai",Font.BOLD,30);
        btnFont=new Font("Arial",Font.PLAIN,20);
        errorFont=new Font("Arial",Font.PLAIN,18);
        
        txt=new JTextField[4];

        lbName=new JLabel("Name");
        lbName.setBounds(800,200, 200, 50);
        lbName.setFont(textFont);

        txt[0]=new JTextField();
        txt[0].setBounds(950,200,250,50);
        txt[0].setFont(textFont);

        lbPhone=new JLabel("Phone");
        lbPhone.setBounds(800,300, 200, 50);
        lbPhone.setFont(textFont);

        txt[1]=new JTextField();
        txt[1].setBounds(950,300,250,50);
        txt[1].setFont(textFont);

        lbAadhar=new JLabel("Aadhar-No");
        lbAadhar.setBounds(800,400, 200, 50);
        lbAadhar.setFont(textFont);

        txt[2]=new JTextField();
        txt[2].setBounds(950,400,250,50);
        txt[2].setFont(textFont);

        lbEmail=new JLabel("Email");
        lbEmail.setBounds(800,500, 200, 50);
        lbEmail.setFont(textFont);

        txt[3]=new JTextField();
        txt[3].setBounds(950,500,250,50);
        txt[3].setFont(textFont);

        lbAddress=new JLabel("Address");
        lbAddress.setBounds(800,600, 200, 50);
        lbAddress.setFont(textFont);

        txtAddress=new JTextArea();
        txtAddress.setBounds(950,600,320,100);
        txtAddress.setFont(textFont);

        save=new JButton("Save");
        save.setBounds(1000, 750, 200, 50);
        save.setFont(btnFont);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        save.addMouseListener(this);

        errorMsg=new JLabel[5];
        for(int i=0;i<5;i++){
            errorMsg[i]=new JLabel();
        }
        errorMsg[0].setBounds(1250,200,200,50);
        errorMsg[1].setBounds(1250,300,280,50);
        errorMsg[2].setBounds(1250,400,280,50);
        errorMsg[3].setBounds(1250,500,200,50);
        errorMsg[4].setBounds(1280,600,170,100);
        for(int i=0;i<5;i++){
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
        add(save);
        img = new ImageIcon(ClassLoader.getSystemResource("img/add_c.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setLayout(null);
        setSize((int)dObj.getWidth()-200,(int)dObj.getHeight()-100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent eObj){
        cObj=new Consumer();
        if(eObj.getSource()==save){
            int temp=0;
            //name
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
            //number
            if(txt[1].getText().trim().isEmpty()){
                errorMsg[1].setText("You must enter number");
                errorMsg[1].setForeground(Color.RED);
                temp--;
            }
            else if(cObj.isValidPhone(txt[1].getText().trim())){
                errorMsg[1].setText("");
                cObj.setCPhone(txt[1].getText());
                temp++;
            }
            else{
                errorMsg[1].setText("Phone number must be 10 digits");
                errorMsg[1].setForeground(Color.RED);
                temp--;
            }
            //aadhar
            if(txt[2].getText().trim().isEmpty()){
                errorMsg[2].setText("You must enter Aadhar number");
                 errorMsg[2].setForeground(Color.RED);
                 temp--;
            }
            else if(cObj.isValidAadhar(txt[2].getText().trim())){
                errorMsg[2].setText("");
                if(cObj.isDuplicate(txt[2].getText().trim())){
                    int id=new Meter().searchMeterCID(txt[2].getText().trim());
                    if(id!=0){
                        dispose();
                        new Add_Meter(id);
                    }
                    else{
                       // MainWindow.dialogBox("NO match Found");
                        JOptionPane.showMessageDialog(null,"No Record Found");
                    }
                }
                else{
                    cObj.setcAadharNo(txt[2].getText());
                    temp++;
                }
            }
             
            else{
                errorMsg[2].setText("Aadhar number must be 12 digits");
                errorMsg[2].setForeground(Color.RED);
                temp--;
            }
            //email

            if(cObj.isValidEmail(txt[3].getText().trim()) ){
                errorMsg[3].setText("");
                cObj.setCEmail(txt[3].getText());
                temp++;
            }
            else if(!txt[3].getText().trim().isEmpty()){
                errorMsg[3].setText("Invalid Email Format");
                errorMsg[3].setForeground(Color.RED);
                temp--;
            }
            else if (txt[3].getText().trim().isEmpty()) {
                errorMsg[3].setText("");
                cObj.setCEmail(txt[3].getText());
                temp++;
 
            }
           //address
           if(txtAddress.getText().trim().isEmpty()){
                errorMsg[4].setText("You must enter value");
                errorMsg[4].setForeground(Color.RED);  
                temp--;
            }
            else{
                errorMsg[4].setText("");
                cObj.setCAddress(txtAddress.getText());
                temp++;
            }
            if(temp==5){
                cObj.addData();
                for(int i=0;i<4;i++){   txt[i].setText("");}
                txtAddress.setText("");
                dispose();
                cObj.searchOne(cObj.getCAadharNo());
                
                new Add_Meter(cObj.getCId());
            }
            
            
        }
    }
    
    //mouse events
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {  }
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==save){
            save.setCursor(new Cursor(1));
        }
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==save){
            Cursor hand=new Cursor(Cursor.HAND_CURSOR);
            save.setCursor(hand);
        }
     }

    
}

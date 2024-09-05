package waterbill;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;

public class Search_Meter extends JFrame implements ActionListener{
    Dimension dObj;
    JLabel lbName,lbType,lbCIN,lbDate,lbStatus,lbimg;
    JTextField txt[];
    Font textFont,btnFont;
    ImageIcon img;
    Meter cObj;
    JButton Okay;
    Choice chStatus;

    
    public Search_Meter(Meter cObj,String op){
        this.cObj=cObj;
        setTitle("Search Meter");
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        textFont=new Font("Dubai",Font.BOLD,26);
        btnFont=new Font("Arial",Font.PLAIN,20);
        
         txt=new JTextField[4];


        lbName=new JLabel("Consumer Name");
        lbName.setBounds(800,300, 200, 50);
        lbName.setFont(textFont);
        String []temp=cObj.getCName(cObj.getcINumber());
        txt[0]=new JTextField();
        txt[0].setBounds(1050,300,250,50);
        txt[0].setFont(textFont);
        txt[0].setText(temp[0]);
        txt[0].setEditable(false);

        lbType=new JLabel("Meter Type");
        lbType.setBounds(800,400, 200, 50);
        lbType.setFont(textFont);

        txt[1]=new JTextField();
        txt[1].setBounds(1050,400,250,50);
        txt[1].setFont(textFont);
        txt[1].setText(temp[1]);
        txt[1].setEditable(false);

        lbCIN=new JLabel("CIN");
        lbCIN.setBounds(800,500, 200, 50);
        lbCIN.setFont(textFont);

        txt[2]=new JTextField();
        txt[2].setBounds(1050,500,250,50);
        txt[2].setFont(textFont);
        txt[2].setText(cObj.getcINumber());
        txt[2].setEditable(false);

        lbDate=new JLabel("I.Date");
        lbDate.setBounds(800,600, 200, 50);
        lbDate.setFont(textFont);

        txt[3]=new JTextField();
        txt[3].setBounds(1050,600,250,50);
        txt[3].setFont(textFont);
        txt[3].setText(cObj.getInstallationDate());
        txt[3].setEditable(false);

        lbStatus=new JLabel("Status");
        lbStatus.setBounds(800,700, 200, 50);
        lbStatus.setFont(textFont);

        chStatus=new Choice();
        chStatus.add("Active");
        chStatus.add("Not Active");
        if(cObj.isActive()){
           
            chStatus.select(0);
        }
        else{
           
            chStatus.select(1);
        }
        chStatus.setBounds(1050,700,200,100);
        chStatus.setFont(textFont);
        chStatus.setEnabled(false);

        Okay=new JButton("Okay");
        Okay.setBounds(1000, 850, 200, 50);
        Okay.setFont(btnFont);
        Okay.setBackground(Color.BLACK);
        Okay.setForeground(Color.WHITE);
        Okay.addActionListener(this);
        if(op.charAt(0)=='u'){
            chStatus.setEnabled(true);
            Okay.setText("Update");
        }
        
        add(txt[0]);
        add(lbName);
        add(txt[1]);
        add(lbType);
        add(txt[2]);
        add(lbCIN);
        add(txt[3]);
        add(lbStatus);
        add(chStatus);
        add(lbDate);
        add(Okay);
       img = new ImageIcon(ClassLoader.getSystemResource("img/search_m.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setLayout(null);
        getContentPane().setBackground(new Color(166,227,233));
        setSize((int)dObj.getWidth()-200,(int)dObj.getHeight()-100);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==Okay && Okay.getText().equals("Update")){
           // dispose();
            //update
            if(chStatus.getSelectedItem().equals("Not Active")){
                //update query
                cObj.updateStatus();
                dispose();
            }
            else{
               // MainWindow.dialogBox("Update only happens when meter Status is Not Active..");
                JOptionPane.showMessageDialog(null,"Update only happens when meter Status is Not Active..");
            }
       }
       else if(e.getSource()==Okay && Okay.getText().equals("Okay")){
            dispose();

       }
    } 
}

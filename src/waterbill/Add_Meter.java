package waterbill;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class Add_Meter extends JFrame implements ActionListener,ItemListener{
    Dimension dObj;
    JLabel lbCINum,lbMType,lbIDate,lberror,lbimg;
    JTextField txtCINum,txtIDate;
    Choice cbType;
    Font textFont,btnFont;
    JButton save;
    ImageIcon img;
    
    Meter mObj;
    boolean temp=false;

    public Add_Meter(int id){
        
        setTitle("Add Meter");
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        textFont=new Font("Dubai",Font.BOLD,26);
        btnFont=new Font("Arial",Font.PLAIN,20);
        


        lbCINum=new JLabel("CI number");
        lbCINum.setBounds(950,300, 200, 50);
        lbCINum.setFont(textFont);

        mObj=new Meter();
        mObj.setcId(id);
        mObj.genCINumber();
       
        txtCINum=new JTextField(mObj.getcINumber());
        txtCINum.setBounds(1250,300,250,50);
        txtCINum.setFont(textFont);
        txtCINum.setEditable(false);

        lbMType=new JLabel("Meter Type");
        lbMType.setBounds(950,400, 200, 50);
        lbMType.setFont(textFont);

        cbType=new Choice();
        cbType.add("--None--");
        String []data=Meter.showMeterType();
        if(data!=null){
            for(int i=0;i<data.length;i++)
            cbType.add(data[i]);
        }
        cbType.setBounds(1250,400,250,50);
        cbType.setFont(textFont);
        lberror=new JLabel();
        lberror.setBounds(1500,400,280,50);
        lberror.setFont(new Font("Arial",Font.PLAIN,18));

        lbIDate=new JLabel("Installation Date");
        lbIDate.setBounds(950,500, 200, 50);
        lbIDate.setFont(textFont);

        txtIDate=new JTextField(Meter.getDate());
        txtIDate.setBounds(1250,500,250,50);
        txtIDate.setFont(textFont);
        txtIDate.setEditable(false);

        
        save=new JButton("Save");
        save.setBounds(1100, 600, 200, 50);
        save.setFont(btnFont);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        cbType.addItemListener(this);
        

        add(lbCINum);
         add(lbIDate);
        add(lbMType);
         add(txtCINum);
        add(txtIDate);
         add(cbType);
         add(save);
        add(lberror);
        
        img = new ImageIcon(ClassLoader.getSystemResource("img/add_meters.png"));
        lbimg = new JLabel(img,10);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),(int)dObj.getHeight()-150);
        add(lbimg);

        setLayout(null);
        setSize((int)dObj.getWidth(),700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
        setResizable(false);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent eObj){
        //cObj=new Consumer();
        if(eObj.getSource()==save){ 
            if(temp){
                lberror.setText("");
               mObj.setMeterTypeQ(cbType.getSelectedItem());
                mObj.setAllMeter( mObj.getcId(), mObj.getMeterType(),txtIDate.getText(),txtCINum.getText());
                mObj.addMeter();
                dispose();
                new MainWindow();
            }
            else {
                lberror.setText("Please select a value");
                lberror.setForeground(Color.RED);

            }
            
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==cbType){
            if(cbType.getSelectedItem()=="--None--"){
                temp=false;
            }
            else{
                lberror.setText("");
                temp=true;
            }
        }
    }  
}

package waterbill;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Show_Consumer extends JFrame implements ActionListener{
    Dimension dObj;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JLabel lbimg;
    Font textFont;
    JButton btnDone;
    ImageIcon img;
    public Show_Consumer(Vector<String> col,Vector<Vector<String>> consumerData){
        textFont=new Font("Dubai",Font.BOLD,20);
        setTitle("Show Consumer");
        dObj=Toolkit.getDefaultToolkit().getScreenSize();
        
        tableModel = new DefaultTableModel(consumerData,col);

        table = new JTable(tableModel);
        table.setFont(textFont);
        table.setBackground(Color.white);
        table.setForeground(Color.BLACK);
        table.setRowHeight(50);
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getPreferredSize().width, 50));
        table.getTableHeader().setFont(textFont);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);

        
        scrollPane = new JScrollPane(table);

        btnDone=new JButton("Done");
        btnDone.setBackground(Color.BLACK);
        btnDone.setForeground(Color.white);
        btnDone.setBounds(850, 850, 300, 50);
        btnDone.addActionListener(this);

       
        scrollPane.setBounds(80,400,(int)dObj.getWidth()-200,400);
        scrollPane.setBackground(new Color(166,227,233));
        add(scrollPane);
        
        add(btnDone);
        
        img = new ImageIcon(ClassLoader.getSystemResource("img/c_all.png"));
        lbimg = new JLabel(img);
        lbimg.setBounds(0,0, (int)dObj.getWidth(),1000);
        add(lbimg);
        setLayout(null);
        setSize((int)dObj.getWidth(),1000);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    public  void actionPerformed(ActionEvent aeObj){
        if(aeObj.getSource()==btnDone){
            dispose();
            new Consumer_menu();
        }
    }
   
    
}

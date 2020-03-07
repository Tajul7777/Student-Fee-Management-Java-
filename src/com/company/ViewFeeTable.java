package com.company;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ViewFeeTable extends JFrame {
    static ViewFeeTable frame;
    private JPanel contentPane;
    private JTextField textField;    //id
    private JTextField textField_1;  //Total Fee
    private JTextField textField_2;  //Due Amount
    private JTextField textField_3;  //Paid Amount
    private JTextField textField_4;  //Advance Balance
    private JScrollPane sp;

    boolean loadButtonClick=false;
    static String data[][] = new String[100][7];
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ViewFeeTable();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ViewFeeTable() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblFeeTitle = new JLabel("Student Fee Record");
        lblFeeTitle.setForeground(Color.DARK_GRAY);
        lblFeeTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblId = new JLabel("Student ID:");

        JLabel lblName = new JLabel("Total Fee:");

        JLabel lblFeeType = new JLabel("Due Amount:");

        JLabel lblCourse = new JLabel("Paid Amount:");

        JLabel lblFee = new JLabel("Advance Paid:");

        JSeparator separator = new JSeparator();
        String columnNames[]={"Rollno","Name","Fee Type","Course","Fee","Paid","Trimester Name"};
        JTable jt=new JTable(data,columnNames);
        sp=new JScrollPane(jt);

        for(int i=0;i<100;i++){
            data[i][0]="";
            data[i][1]="";
            data[i][2]="";
            data[i][3]="";
            data[i][4]="";
            data[i][5]="";
            data[i][6]="";
        }

        JButton btnLoadRecord = new JButton("Load Record");
        btnLoadRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                loadButtonClick = true;
                for(int i=0;i<100;i++){
                    data[i][0]="";
                    data[i][1]="";
                    data[i][2]="";
                    data[i][3]="";
                    data[i][4]="";
                    data[i][5]="";
                    data[i][6]="";
                }
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
                sp.setVisible(false);
                int totalFee = 0;
                int totalPaid = 0;
                int totalDue = 0;
                int totalAdvance = 0;
                BufferedReader br = null;
                BufferedReader br1 = null;
                BufferedReader br2 = null;
                String line = "";
                try{
                    int row = 0;
                    boolean flag = false;
                    String srollno=textField.getText();
                    br = new BufferedReader(new FileReader("feeNpaid.txt"));
                    br1 = new BufferedReader(new FileReader("paidFee.txt"));
                    br2 = new BufferedReader(new FileReader("fee.txt"));
                    if(srollno==null||srollno.trim().equals("")){
                        JOptionPane.showMessageDialog(ViewFeeTable.this,"Please enter rollno first!");
                    }else {
                        while ((line = br.readLine()) != null) {
                            //System.out.println(line);
                            String[] s = line.split(" ");
                            String id = s[0];
                            if (srollno.equals(id)) {
                                flag = true;
                             //   totalFee += Integer.parseInt(s[5]);
                                data[row][0]=s[0];
                                data[row][1]=s[1];
                                data[row][2]=s[2]+" "+s[3];
                                data[row][3]=s[4];
                                data[row][4]=s[5];
                                data[row][5]=s[6];
                                data[row][6]=s[7];
                                row++;
                            }
                        }
                        if(flag==false){
                            JOptionPane.showMessageDialog(ViewFeeTable.this,"No record found of this ID!");
                        }
                        else{
                            sp.setVisible(true);
                            while ((line = br2.readLine()) != null) {
                                //System.out.println(line);
                                String[] s = line.split(" ");
                                String id = s[0];
                                if (id.equals(srollno)) {
                                    totalFee += Integer.parseInt(s[5]);
                                }
                            }
                            while ((line = br1.readLine()) != null) {
                                //System.out.println(line);
                                String[] s = line.split(" ");
                                String id = s[0];
                                if (id.equals(srollno)) {
                                    totalPaid += Integer.parseInt(s[1]);
                                }
                            }
                            if(totalPaid>totalFee){
                                totalAdvance = totalPaid - totalFee;
                                totalDue = 0;
                            }
                            else {
                                totalDue = totalFee - totalPaid;
                                totalAdvance = 0;
                            }
                            textField_1.setText(totalFee+"");
                            textField_1.setEditable(false);
                            textField_2.setText(totalDue+"");
                            textField_2.setEditable(false);
                            textField_3.setText(totalPaid+"");
                            textField_3.setEditable(false);
                            textField_4.setText(totalAdvance+"");
                            textField_4.setEditable(false);
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setColumns(10);



        // add(sp);

       /* JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountantSection.main(new String[]{});
                frame.dispose();
            }
        });*/
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(142)
                                                .addComponent(lblFeeTitle))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblFeeType, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblCourse, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblId, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                                .addGap(33)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                                                .addComponent(btnLoadRecord)))))
                                .addContainerGap(124, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblFee, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(356, Short.MAX_VALUE))
                        .addComponent(sp)
//                        .addGroup(gl_contentPane.createSequentialGroup()
//                                .addGap(18)
//                                .addComponent(btnBack)
//                                .addContainerGap(44, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(27)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(46, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblFeeTitle)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblId)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLoadRecord))
                                .addGap(16)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblName)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblFeeType)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblCourse)
                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblFee)
                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(sp)
//                                .addGap(7)
//                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//                                        .addComponent(btnBack))
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }
}
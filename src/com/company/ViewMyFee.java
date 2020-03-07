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

public class ViewMyFee extends JFrame {
    static ViewMyFee frame;
    private static String myStudentId;
    private JPanel contentPane;
    private JTextField textField;    //id
    private JTextField textField_1;  //Name
    private JScrollPane sp;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ViewMyFee();
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

    public ViewMyFee(String myId){
        myStudentId=myId;
        // System.out.println(myId);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ViewMyFee();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewMyFee() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

      //  System.out.println(myStudentId);

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        textField.setText(myStudentId);
        textField_1.setText("");

        JLabel lblFeeTitle = new JLabel("My Fee Record");
        lblFeeTitle.setForeground(Color.DARK_GRAY);
        lblFeeTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblId = new JLabel("Student ID:");

        JLabel lblName = new JLabel("Student Name:");

        String data[][]=new String[100][12];
        int row=0;

        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("feeNpaid.txt"));
            while ((line = br.readLine()) != null) {
                String [] s= line.split(" ");
                if(myStudentId.equals(s[0])) {
                    textField_1.setText(s[1]);
                    data[row][0] = s[2] + " " + s[3];
                    data[row][1] = s[4];
                    data[row][2] = s[5];
                    data[row][3] = s[6];
                    data[row][4] = s[7];
                    row++;
                }
                textField.setEditable(false);
                textField_1.setEditable(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        String columnNames[]={"Fee Type","Course","Fee","Paid","Trimester Name"};
        JTable jt=new JTable(data,columnNames);
        JScrollPane sp=new JScrollPane(jt);
        add(sp);


//        JButton btnBack = new JButton("back");
//        btnBack.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                AccountantSection.main(new String[]{});
//                frame.dispose();
//            }
//        });


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 500);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(180)
                                                .addComponent(lblFeeTitle))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblId, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                                .addGap(33)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)))))
                                .addContainerGap(124, Short.MAX_VALUE))

                        .addComponent(sp)
//                        .addGroup(gl_contentPane.createSequentialGroup()
//                                .addGap(18)
//                                .addComponent(btnBack)
//                                .addContainerGap(44, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblFeeTitle)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblId)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(16)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblName)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
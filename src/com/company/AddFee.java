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

public class AddFee extends JFrame {
    static AddFee frame;
    private JPanel contentPane;
    private JTextField textField;    //id
    private JTextField textField_1;  //name
   // private JTextField textField_2;  //Fee Type
    private JTextField textField_3;  //Course
    private JTextField textField_4;  //Fee
    private JTextField textField_5;  //Trimester Name

    private static String feeType;

    boolean loadButtonClick=false;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new AddFee();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Updating Id.
     */
    public int getLastId(){

        BufferedReader br = null;
        String line = "";
        int id=0;
        try {
            br = new BufferedReader(new FileReader("studentId.txt"));
            id = Integer.parseInt(br.readLine());
            PrintWriter writer = new PrintWriter("studentId.txt", "UTF-8");
            writer.println((id+1)+"");
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return id;
    }

    /**
     * Adding A new Accountant info. into file.
     */
    public void writeFee(String s){
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("fee.txt"));
            PrintWriter writer = new PrintWriter("fee1.txt", "UTF-8");
            while ((line = br.readLine()) != null) {
                writer.println(line);
            }
            writer.close();

            br = new BufferedReader(new FileReader("fee1.txt"));
            writer = new PrintWriter("fee.txt", "UTF-8");
            while ((line = br.readLine()) != null) {
                writer.println(line);
            }
            writer.println(s);
            writer.close();


            br = new BufferedReader(new FileReader("feeNpaid.txt"));
            writer = new PrintWriter("feeNpaid1.txt", "UTF-8");
            while ((line = br.readLine()) != null) {
                writer.println(line);
            }
            writer.close();

            br = new BufferedReader(new FileReader("feeNpaid1.txt"));
            writer = new PrintWriter("feeNpaid.txt", "UTF-8");
            while ((line = br.readLine()) != null) {
                writer.println(line);
            }
            writer.println(s);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    /**
     * Create the frame.
     */
    public AddFee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblFeeTitle = new JLabel("Add New Fee");
        lblFeeTitle.setForeground(Color.DARK_GRAY);
        lblFeeTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblId = new JLabel("Id:");

        JLabel lblName = new JLabel("Name:");

        JLabel lblFeeType = new JLabel("Fee Type:");

        JLabel lblCourse = new JLabel("Course:");

        JLabel lblFee = new JLabel("Fee:");

        JLabel lblTrimester = new JLabel("Trimester:");

        JButton btnFee = new JButton("Add Fee");

        List<Fee> feeList = new ArrayList<Fee>();

        JSeparator separator = new JSeparator();

        String[] feeStrings = { "Tuition Fee", "Library Fine", "Late Fee", "Aditional Fee"};

//Create the combo box, select item at index 4.
//Indices start at 0, so 4 specifies the pig.
        JComboBox feeTypeList = new JComboBox(feeStrings);
        feeTypeList.setSelectedIndex(0);
        feeTypeList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                feeType = (String)cb.getSelectedItem();
                if(feeType.equals("Tuition Fee")){
                    textField_3.setText("");
                    textField_3.setEditable(true);
                }
                else{
                    textField_3.setText("None");
                    textField_3.setEditable(false);
                }
            }
        });
        if(feeType==null){
            feeType = "Tuition Fee";
        }

        JButton btnLoadRecord = new JButton("Load Record");
        btnLoadRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BufferedReader br = null;
                String line = "";
                try{
                    boolean flag = false;
                    String srollno=textField.getText();
                    br = new BufferedReader(new FileReader("student.txt"));
                    if(srollno==null||srollno.trim().equals("")){
                        JOptionPane.showMessageDialog(AddFee.this,"Please enter rollno first!");
                    }else {
                        while ((line = br.readLine()) != null) {
                            //System.out.println(line);
                            String[] s = line.split(" ");
                            String id = s[0];
                            if (srollno.equals(id)) {
                                flag = true;
                                textField_1.setText(s[1]);
                            }
                            textField.setEditable(false);
                            textField_1.setEditable(false);
                        }
                        if(flag==false){
                            JOptionPane.showMessageDialog(AddFee.this,"No Student Found of this ID!");
                            textField.setEditable(true);
                            textField_1.setEditable(true);
                        }
                        else if(flag==true){
                            loadButtonClick = true;
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnFee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(loadButtonClick==false){
                    JOptionPane.showMessageDialog(AddFee.this,"Load ID First!");
                }
                else {
                    JOptionPane.showMessageDialog(AddFee.this, "Fee added successfully!");
                    BufferedReader br = null;
                    String line = "";
                    String id = textField.getText();
                    String name = textField_1.getText();
                  //  String feeType = textField_2.getText();
                    String fullNameofCourse = textField_3.getText();
                    String breakName[] = fullNameofCourse.split(" ");
                    String course = breakName[0].toUpperCase();
                    int fee = Integer.parseInt(textField_4.getText());
                    String trimester = textField_5.getText();
                    Fee f = new Fee(id, name, feeType, course, fee, trimester);
                    feeList.add(f);
                    writeFee(id + " " + name + " " + feeType + " " + course + " " + fee + " " +"-"+" " + trimester);

                    textField.setText("");
                    textField.setEditable(true);
                    textField_1.setText("");
                    textField.setEditable(true);
                    feeTypeList.setSelectedIndex(0);
                    textField_3.setText("");
                    textField_4.setText("");
                    textField_5.setText("");
                }
            }
        });

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        /*textField_2 = new JTextField();
        textField_2.setColumns(10);*/

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setColumns(10);

        JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountantSection.main(new String[]{});
                frame.dispose();
            }
        });
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
                                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblFeeType, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblCourse, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblId, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(33)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                                        .addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                                        .addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                                        .addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                                        .addComponent(feeTypeList, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                                                .addComponent(btnLoadRecord)))))
                                .addContainerGap(124, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblFee)
                                .addContainerGap(356, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTrimester, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                .addGap(350))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(128)
                                .addComponent(btnFee, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnBack)
                                .addContainerGap(44, Short.MAX_VALUE))
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
                                        .addComponent(feeTypeList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblCourse)
                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblFee)
                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblTrimester)
                                        .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(7)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnFee, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBack))
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }
}

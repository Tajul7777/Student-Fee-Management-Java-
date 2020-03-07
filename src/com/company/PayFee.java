package com.company;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class PayFee extends JFrame {
    static PayFee frame;
    private JPanel contentPane;
    private JTextField textField;
    private static String myStudentId;
    private static String myName;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new PayFee();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void writePaidFee(String s, String s1){
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("paidFee.txt"));
            PrintWriter writer = new PrintWriter("paidFee1.txt", "UTF-8");
            while ((line = br.readLine()) != null) {
                writer.println(line);
            }
            writer.close();

            br = new BufferedReader(new FileReader("paidFee1.txt"));
            writer = new PrintWriter("paidFee.txt", "UTF-8");
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
            writer.println(s1);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public PayFee(String myId){
        myStudentId=myId;
        // System.out.println(myId);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new PayFee();
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
    public PayFee() {
        //System.out.println(myStudentId);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAccountantLogin = new JLabel("Pay Your Fee");
        lblAccountantLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAccountantLogin.setForeground(Color.DARK_GRAY);

        JLabel lblAmount = new JLabel("Amount:");

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnpay = new JButton("Pay");
        btnpay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BufferedReader br = null;
                String line = "";
                try {
                    br = new BufferedReader(new FileReader("student.txt"));
                    while ((line = br.readLine()) != null) {
                        String [] s= line.split(" ");
                        if(myStudentId.equals(s[0])){
                            myName = s[1];
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
                String amount = textField.getText();
                writePaidFee(myStudentId+" "+amount , myStudentId + " " + myName + " " + "-" + " " +"-" +" " + "-" + " " + "-" + " " +amount+" " + "-");
                JOptionPane.showMessageDialog(PayFee.this,"Amount Paid Successfully!");
                textField.setText("");
                StudentSection.main(new String[]{});
                frame.dispose();
            }
        });

        JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentSection.main(new String[]{});
                frame.dispose();
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(28)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)

                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblAmount)
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addGap(76)
                                                                                .addComponent(lblAccountantLogin))
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addGap(54)
                                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))))))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(158)
                                                .addComponent(btnpay, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                                .addGap(52)
                                                .addComponent(btnBack)))
                                .addContainerGap(78, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblAccountantLogin)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblAmount)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnpay, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBack))
                                .addContainerGap(96, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
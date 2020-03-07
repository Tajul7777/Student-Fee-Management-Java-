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

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class FacultyLogin extends JFrame {
    static FacultyLogin frame;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private static String facultyId;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new FacultyLogin();
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
    public FacultyLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAdminLogin = new JLabel("Faculty Login");
        lblAdminLogin.setForeground(Color.DARK_GRAY);
        lblAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JLabel lblName = new JLabel("Name:");

        JLabel lblPassword = new JLabel("Password:");

        textField = new JTextField();
        textField.setColumns(10);

        passwordField = new JPasswordField();

        JButton btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                char ch[]=passwordField.getPassword();
                String password=String.valueOf(ch);
                boolean flag = false;
                BufferedReader br = null;
                String line = "";
                try {
                    br = new BufferedReader(new FileReader("faculty.txt"));
                    while ((line = br.readLine()) != null) {
                        String [] s= line.split(" ");
                        if(name.equals(s[1])){
                            if(password.equals(s[2])){
                                flag = true;
                                facultyId = s[0];
                                // StudentSection.main(new String[]{});
                                new FacultySection(s[0]);
                                frame.dispose();
                            }
                        }
                    }
                    if(flag==false){
                        JOptionPane.showMessageDialog(null,"Sorry, username or password error!","Login error!",JOptionPane.ERROR_MESSAGE);
                        //textField.setText("");passwordField.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });

        JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FeeReport.main(new String[]{});
                frame.dispose();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(180)
                                                .addComponent(lblAdminLogin))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(25)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblName)
                                                        .addComponent(lblPassword))
                                                .addGap(58)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(passwordField)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(177)
                                                .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                .addGap(52)
                                                .addComponent(btnBack)))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblAdminLogin)
                                .addGap(29)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblName)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(27)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPassword))
                                .addGap(36)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBack))
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}

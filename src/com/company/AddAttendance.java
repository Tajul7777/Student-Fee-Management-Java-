package com.company;

/**
 * Created by Tushar on 2017-08-22.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javax.swing.*;
public class AddAttendance{
    private JFrame f;
    private JPanel pnl;
    private JButton load, back;
    private JComboBox courseList;
    private static String courseType;
    private static String date;
    public AddAttendance(String date){
        this.date = date;
        new AddAttendance();
    }


    public AddAttendance(){
        f = new JFrame("Student Attendance");
        f.setLayout(null);
        f.setSize(350, 150);
        f.setLocation(300, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnl = new JPanel();
        pnl.setLayout(null);

        String[] courses = { "---Course---", "Algorithm", "Java", "Compiler","Discrete"};
        courseList = new JComboBox(courses);
        courseList.setBounds(50,20,100,25);
        courseList.setSelectedIndex(0);
        pnl.add(courseList);
        courseList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                courseType = (String)cb.getSelectedItem();
                courseType = courseType.toUpperCase();
            }
        });

        load = new JButton("Load");
        load.setBounds(170,20,100,25);
        pnl.add(load);
        load.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (courseType == null || date == null) {
                    JOptionPane.showMessageDialog(null, "Please select a course first!");
                } else {
                    courseType = (String) courseList.getSelectedItem();
                    courseType = courseType.toUpperCase();
                    boolean flag = false;
                    BufferedReader br = null;
                    String line = "";
                    try {
                        br = new BufferedReader(new FileReader("attendance.txt"));
                        while ((line = br.readLine()) != null) {
                            String[] s = line.split(" ");
                            if (date.equals(s[0]) && courseType.equals(s[1])) {
                                flag = true;
                            }
                        }
                        if (flag == true) {
                            JOptionPane.showMessageDialog(null, "Today's attendace session for " + courseType + " is completed already!");
                            //textField.setText("");passwordField.setText("");
                        } else if (flag == false) {

                            new AttendanceSheet(courseType, date);
                            f.dispose();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }
            }
        });

        back = new JButton("Back");
        back.setBounds(100,70,100,20);
        pnl.add(back);
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String s[]={};
                FacultySection.main(s);
                f.dispose();
            }
        });

        f.setContentPane(pnl);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
        String s = ft.format(dNow);
        new AddAttendance(s);
    }








}

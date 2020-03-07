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
public class EditAttendance{
    private JFrame f;
    private JPanel pnl;
    private JButton load, back;
    private JComboBox courseList,dateList;
    private static String courseType;
    private static String date;


    public EditAttendance(){
        f = new JFrame("Edit Attendance");
        f.setLayout(null);
        f.setSize(500, 150);
        f.setLocation(300, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnl = new JPanel();
        pnl.setLayout(null);

        String[] courses = { "---Course---", "Algorithm", "Java", "Compiler","Discrete"};
        courseList = new JComboBox(courses);
        courseList.setBounds(50,20,120,25);
        courseList.setSelectedIndex(0);
        pnl.add(courseList);

        courseList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                courseType = (String)cb.getSelectedItem();
                courseType = courseType.toUpperCase();
            }
        });

        //String[] dates = { "---Dates---", "Algorithm", "Java", "Java Lab","Discrete Math"};

        String[] dates = new String[150];
        dates[0]="---Date---";

        BufferedReader br = null;
        String line = "";
        int i;
        try {
            br = new BufferedReader(new FileReader("attendance.txt"));
            i=1;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                String newDate = s[0];
                boolean flag = false;
                for(String dt:dates){
                    if(dt!=null) {
                        if (dt.equals(newDate)) {
                            flag = true;
                        }
                    }
                }
                if(flag==false) {
                    dates[i] = newDate;
                    i++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        dateList = new JComboBox(dates);
        dateList.setBounds(190,20,120,25);
        dateList.setSelectedIndex(0);
        pnl.add(dateList);

        dateList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                date = (String)cb.getSelectedItem();
            }
        });


        load = new JButton("Load");
        load.setBounds(340,20,120,25);
        pnl.add(load);
        load.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(courseType==null||date==null){
                    JOptionPane.showMessageDialog(null,"Please select course and date first!");
                }
                else {
                    courseType = (String)courseList.getSelectedItem();
                    courseType = courseType.toUpperCase();
                    date = (String)dateList.getSelectedItem();

                    new EditAttendanceSheet(courseType, date);
                    f.dispose();
                }
                //System.out.println(courseType+", "+date);
            }
        });

        back = new JButton("Back");
        back.setBounds(200,70,100,20);
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
        new EditAttendance();
    }


}

package com.company;

/**
 * Created by Tushar on 2017-08-22.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
public class AttendanceSheet implements ActionListener{
    JFrame f;
    JTextField tf;
    JPanel p1,p2;
    JLabel l1, l2, l3, l4;
    JCheckBox b1,b2,b3,b4;
    JButton save,back;
    static ArrayList<JCheckBox> jc;
    static ArrayList<JCheckBox> jc1;
    static int c=0;
    static int[] ar = new int[10];
    static int[] ar1 = new int[10];
    static int count = 0;
    static ArrayList<String> sid = new ArrayList<String>();
    static ArrayList<String> sname = new ArrayList<String>();
    static ArrayList<String> sattendance = new ArrayList<String>();

    private static String courseType;
    private static String date;

    public AttendanceSheet(String course, String date){
        courseType = course;
        this.date = date;
        new AttendanceSheet();
    }


    public AttendanceSheet(){
        int lblL=100;
        int btnL = 100;
        f = new JFrame("");
        f.setLayout(null);
        f.setSize(500, 700);
        f.setLocation(300, 10);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel editSheet = new JLabel("Add New Attendance");
        editSheet.setFont(new Font("Tahoma", Font.PLAIN, 20));
        editSheet.setForeground(Color.DARK_GRAY);
        editSheet.setBounds(150,0,300,20);
        f.add(editSheet);
        JLabel sublbl = new JLabel("Subject: "+courseType);
        sublbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sublbl.setForeground(Color.DARK_GRAY);
        sublbl.setBounds(10,30,300,20);
        f.add(sublbl);
        JLabel datelbl = new JLabel("Date: "+date);
        datelbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        datelbl.setForeground(Color.DARK_GRAY);
        datelbl.setBounds(10,60,300,20);
        f.add(datelbl);

        jc = new ArrayList<JCheckBox>();
        jc1 = new ArrayList<JCheckBox>();

        BufferedReader br = null;
        String line = "";
        int i=0;
        try {
            br = new BufferedReader(new FileReader("feeNpaid.txt"));
            i=0;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                if(courseType.equals(s[4])){
                    l1 = new JLabel("ID: "+s[0]);
                    l1.setBounds(10,lblL,50,20);
                    f.add(l1);
                    l2 = new JLabel("Name: "+s[1]);
                    l2.setBounds(50,lblL,100,20);
                    f.add(l2);

                    jc.add(new JCheckBox());
                    jc.get(i).setBounds(150, btnL, 20,20);
                    jc.get(i).addActionListener(this);

                    f.add(jc.get(i));
                    l3 = new JLabel("Absent");
                    l3.setBounds(170,lblL,50,20);
                    f.add(l3);

                    jc1.add(new JCheckBox());
                    jc1.get(i).setBounds(250, btnL, 20,20);
                    jc1.get(i).addActionListener(this);
                    f.add(jc1.get(i));

                    l4 = new JLabel("Present");
                    l4.setBounds(270,lblL,50,20);
                    f.add(l4);
                    sid.add(s[0]);
                    sname.add(s[1]);
                    lblL += 50;
                    btnL += 50;
                    i++;
                    count++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        btnL += 50;

        save=new JButton("Save");
        save.setBounds(50,btnL,150,30);
        save.addActionListener(this);
        f.add(save);

        back = new JButton("Back");
        back.setBounds(300,(btnL+10),100,20);
        f.add(back);
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new AddAttendance();
                f.dispose();
            }
        });

        f.setVisible(true);
    }
    public static void main(String[] args) {
        new AttendanceSheet();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<count;i++){
            /*System.out.print("Student ID: " + i + " Value: ");
            System.out.print(jc.get(i).isSelected());
            System.out.println();*/
            if(jc.get(i).isSelected()==false){
                ar[i]=0;
            }
            else if(jc.get(i).isSelected()==true){
                ar[i]=1;
            }
        }

        for(int i=0;i<count;i++){
            /*System.out.print("Student ID: " + i + " Value: ");
            System.out.print(jc.get(i).isSelected());
            System.out.println();*/
            if(jc1.get(i).isSelected()==false){
                ar1[i]=0;
            }
            else if(jc1.get(i).isSelected()==true){
                ar1[i]=1;
            }
        }

        Object ob = e.getSource();
        if(ob==save){
            String s=null;
            for(int i=0;i<count;i++){
                if(ar[i]==0){
                    if(ar1[i]==0){
                        s="Not Setted";
                        sattendance.add(s);
                    }
                    else if(ar1[i]==1){
                        s="Present";
                        sattendance.add(s);
                    }
                }
                else if(ar[i]==1){
                    if(ar1[i]==0){
                        s="Absent";
                        sattendance.add(s);
                    }
                    else if(ar1[i]==1){
                        s="Absent";
                        sattendance.add(s);
                    }
                }
               // System.out.println("ID- "+(i+1)+": "+s);
            }

            for(int i=0;i<count;i++){
               // System.out.println(sid.get(i)+" "+sname.get(i)+" "+sattendance.get(i));
                addAttendance(date+" "+courseType+" "+sid.get(i)+" "+sname.get(i)+" "+sattendance.get(i));
            }

            JOptionPane.showMessageDialog(null,"Attendance Added Successfully!");
            new AddAttendance();
            f.dispose();
        }
    }


    void addAttendance(String s){
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("attendance.txt"));
            PrintWriter writer = new PrintWriter("attendance1.txt", "UTF-8");
            while ((line = br.readLine()) != null) {
                writer.println(line);
            }
            writer.close();

            br = new BufferedReader(new FileReader("attendance1.txt"));
            writer = new PrintWriter("attendance.txt", "UTF-8");
            while ((line = br.readLine()) != null) {
                writer.println(line);
            }
            writer.println(s);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


}

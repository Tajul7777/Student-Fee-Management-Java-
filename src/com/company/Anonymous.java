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
public class Anonymous implements ActionListener{
    private JFrame f;
    private JTextField tf;
    private JPanel pnl;
    private JLabel l1, l2, l3, l4;
    private JLabel datelbl;
    private JButton save, back;
    private JComboBox courseList;
    private JScrollPane sp;
    static ArrayList<JCheckBox> jc;
    static ArrayList<JCheckBox> jc1;
    static int c=0;
    static int[] ar = new int[10];
    static int[] ar1 = new int[10];
    static String start = "01-06-2017";
    static String end = "30-09-2017";

    private static String courseType;
    static int lblL=10;
    static int btnL = 60;

    static int count =0;


    public Anonymous(){
        f = new JFrame("Student Attendance");
        f.setLayout(null);
        f.setSize(400, 500);
        f.setLocation(300, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnl = new JPanel();
        pnl.setLayout(null);

        String[] courses = { "---Course---", "Algorithm", "Java", "Java Lab","Discrete Math"};
        courseList = new JComboBox(courses);
        courseList.setBounds(10,lblL,100,20);
        courseList.setSelectedIndex(0);
        courseList.addActionListener(this);
        pnl.add(courseList);

       /* String [] s= start.split("-");
        int startingDate = Integer.parseInt(s[0]);
        int startingMonth = Integer.parseInt(s[1]);
        int startingYear = Integer.parseInt(s[2]);

        s= end.split("-");
        int endingDate = Integer.parseInt(s[0]);
        int endingMonth = Integer.parseInt(s[1]);
        int endingYear = Integer.parseInt(s[2]);

        String[] date;
        for(int i = startingMonth; i<=endingMonth;i++){

        }*/

        datelbl = new JLabel("Date: ");
        datelbl.setBounds(150,lblL,40,20);
        pnl.add(datelbl);

        tf = new JTextField();
        tf.setText("");
        tf.setBounds(190,lblL,100,20);
        pnl.add(tf);

        lblL += 50;



        /*for(int i=0;i<5;i++){
            c = i;
            l1 = new JLabel("ID: "+(i+1));
            l1.setBounds(10,lblL,50,20);
            pnl.add(l1);
            l2 = new JLabel("Name: ");
            l2.setBounds(50,lblL,130,20);
            pnl.add(l2);

            jc.add(new JCheckBox());
            jc.get(i).setBounds(180, btnL, 20,20);
            jc.get(i).addActionListener(this);

            pnl.add(jc.get(i));
            l3 = new JLabel("Absent");
            l3.setBounds(200,lblL,50,20);
            pnl.add(l3);

            jc1.add(new JCheckBox());
            jc1.get(i).setBounds(280, btnL, 20,20);
            jc1.get(i).addActionListener(this);
            pnl.add(jc1.get(i));

            l4 = new JLabel("Present");
            l4.setBounds(300,lblL,50,20);
            pnl.add(l4);

            lblL += 50;
            btnL += 50;

        }*/
        btnL += 50;

        save=new JButton("Save");
        save.setBounds(20,btnL,100,30);
        save.addActionListener(this);
        pnl.add(save);

        back=new JButton("Back");
        back.setBounds(200,btnL,100,30);
        back.addActionListener(this);
        pnl.add(back);

        f.setContentPane(pnl);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new Anonymous();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jc = new ArrayList<JCheckBox>();
        jc1 = new ArrayList<JCheckBox>();

        Object ob = e.getSource();
        if(ob==courseList){
            JComboBox cb = (JComboBox)e.getSource();
            courseType = (String)cb.getSelectedItem();
        }
        BufferedReader br = null;
        String line = "";
        int i=0;
        try {
            br = new BufferedReader(new FileReader("feeNpaid.txt"));
            i=0;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                if(courseType.equals(s[4])){
                    load(s[0],s[1],i);
                    lblL += 50;
                    btnL += 50;
                    i++;
                    count++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }


        for(i=0;i<count;i++){
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

        for(i=0;i<count;i++){
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


        if(ob==save){
            String s=null;
            for(i=0;i<count;i++){
                if(ar[i]==0){
                    if(ar1[i]==0){
                        s="Not Setted";
                    }
                    else if(ar1[i]==1){
                        s="Present";
                    }
                }
                else if(ar[i]==1){
                    s="Absent";
                    jc1.get(i).isSelected();

                    /*if(ar1[i]==0){
                        s="Absent";
                    }
                    else if(ar1[i]==1){
                        s="Both Setted";
                    }*/
                }
                System.out.println("ID- "+(i+1)+": "+s);
            }
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


    void load(String id, String name, int i){
        l1 = new JLabel("ID: "+id);
        l1.setBounds(10,lblL,50,20);
        pnl.add(l1);

        l2 = new JLabel("Name: "+name);
        l2.setBounds(50,lblL,130,20);
        pnl.add(l2);

        jc.add(new JCheckBox());
        jc.get(i).setBounds(180, lblL, 20,20);
        jc.get(i).addActionListener(this);
        pnl.add(jc.get(i));

        l3 = new JLabel("Absent");
        l3.setBounds(200,lblL,50,20);
        pnl.add(l3);

        jc1.add(new JCheckBox());
        jc1.get(i).setBounds(280, lblL, 20,20);
        jc1.get(i).addActionListener(this);
        pnl.add(jc1.get(i));

        l4 = new JLabel("Present");
        l4.setBounds(300,lblL,50,20);
        pnl.add(l4);
        f.setVisible(true);

    }




}

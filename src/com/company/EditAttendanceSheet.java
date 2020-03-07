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
        import javax.swing.*;

public class EditAttendanceSheet implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4;
    JButton save,back;
    static ArrayList<JCheckBox> jc;
    static ArrayList<JCheckBox> jc1;
    static int c=0;
    static int[] ar = new int[100];
    static int[] ar1 = new int[100];
    static int count = 0;
    static int lc = 0;
    static int[] la = new int[100];
    static ArrayList<String> sid = new ArrayList<String>();
    static ArrayList<String> sname = new ArrayList<String>();
    static ArrayList<String> sattendance = new ArrayList<String>();

    private static String courseType;
    private static String date;

    public EditAttendanceSheet(String courseType, String date){
        this.courseType = courseType;
        this.date = date;
        new EditAttendanceSheet();
    }


    public EditAttendanceSheet(){
        int lblL=100;
        int btnL = 100;
        f = new JFrame("");
        f.setLayout(null);
        f.setSize(500, 700);
        f.setLocation(300, 10);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        JLabel editSheet = new JLabel("Edit Attendance Sheet");
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
        int i;
        int j;
        try {
            br = new BufferedReader(new FileReader("attendance.txt"));
            i=0;
            j=0;
            count=0;
            lc=0;
            while ((line = br.readLine()) != null) {
                lc++;
                String[] s = line.split(" ");
                if(date.equals(s[0]) && courseType.equals(s[1])){
                    la[j]=lc;
                    l1 = new JLabel("ID: "+s[2]);
                    l1.setBounds(10,lblL,50,20);
                    f.add(l1);
                    l2 = new JLabel("Name: "+s[3]);
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

                    if(s[4].equals("Present")){
                        jc1.get(i).setSelected(true);
                    }
                    else if(s[4].equals("Absent")){
                        jc.get(i).setSelected(true);
                    }

                    sid.add(s[2]);
                    sname.add(s[3]);
                    lblL += 50;
                    btnL += 50;
                    i++;
                    j++;
                    count++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        btnL += 50;

        save=new JButton("Save");
        save.setBounds(50,btnL,150,30);
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
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

                BufferedReader br = null;
                String line = "";
                try{
                    int lc1=0;
                    int j=0;
                    br = new BufferedReader(new FileReader("attendance.txt"));
                    PrintWriter writer = new PrintWriter("attendance1.txt", "UTF-8");
                    while ((line = br.readLine()) != null) {
                        lc1++;
                        String [] s1= line.split(" ");
                        //	System.out.println(s[0]+" "+s[1]+" "+s[2]);
                    /*if(date.equals(s1[0]) && courseType.equals(s1[1])){
                        for(int i=0;i<count;i++){
                            if(s1[2].equals(sid.get(i))){
                              //  System.out.println("Found!");
                               // System.out.println(sid.get(i)+", "+ar[i]+" "+ar1[i]);
                                if(ar[i]==1){
                                    s1[4]="Absent";
                                }
                                else if(ar1[i]==1){
                                    s1[4]="Present";
                                }
                                break;
                            }
                        }
                    }*/
                        if(la[j]==lc1){
                         //   System.out.println("Line= "+la[j]+", File Line= "+lc1+", Absent= "+ar[j]+", Present= "+ar1[j]);
                            if(ar[j]==1 && ar1[j]==0){
                                s1[4]="Absent";
                            }
                            else if(ar1[j]==1){
                                s1[4]="Present";
                            }
                            j++;
                        }

                        writer.println(s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]);
                    }
                    writer.close();



                  //  System.out.println("Total= "+count+", File Lines= "+j);

                     /*for(int i=0;i<count;i++){
                         //System.out.print(la[i]+", ");
                          System.out.println("Line= "+la[i]+", Absent= "+ar[i]+", Present= "+ar1[i]);
                      }
                    System.out.println();*/

                    //For Update information
                    br = new BufferedReader(new FileReader("attendance1.txt"));
                    writer = new PrintWriter("attendance.txt", "UTF-8");
                    while ((line = br.readLine()) != null) {
                        //System.out.println(line);
                        // String [] s1= line.split(" ");
                        //System.out.println(s[0]+" "+s[1]+" "+s[2]);
                        // writer.println(s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]);
                        writer.println(line);
                    }
                    writer.close();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                for(int i=0;i<count;i++){
                    la[i]=0;
                }
                JOptionPane.showMessageDialog(null,"Attendance Updated Successfully!");
                new EditAttendance();
                f.dispose();
            }
        });
        f.add(save);

        back = new JButton("Back");
        back.setBounds(300,(btnL+10),100,20);
        f.add(back);
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new EditAttendance();
                f.setVisible(false);
                f.dispose();
            }
        });

        f.setVisible(true);
    }
    public static void main(String[] args) {
        new EditAttendanceSheet();
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
    }


}


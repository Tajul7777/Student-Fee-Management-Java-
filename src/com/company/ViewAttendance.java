package com.company;

        import java.awt.BorderLayout;
        import java.awt.EventQueue;
        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.PrintWriter;
        import java.util.List;

        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;
        import javax.swing.border.EmptyBorder;

public class ViewAttendance extends JFrame {
    static ViewAttendance frame;
    static int tableSize=0;
    public ViewAttendance() {
        //Code to view data in JTable
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("attendance.txt"));
            while ((line = br.readLine()) != null) {
                tableSize++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        String data[][]=new String[tableSize][5];
        int row=0;

        br = null;
        line = "";
        try {
            br = new BufferedReader(new FileReader("attendance.txt"));
            while ((line = br.readLine()) != null) {
                String [] s= line.split(" ");
                data[row][0]=s[0];
                data[row][1]=s[1];
                data[row][2]=s[2];
                data[row][3]=s[3];
                data[row][4]=s[4];
                row++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        String columnNames[]={"Date","Course","Id","Name","Status"};

        JTable jt=new JTable(data,columnNames);
        JScrollPane sp=new JScrollPane(jt);
        add(sp);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 400);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ViewAttendance();
                    frame.setTitle("Attendance Sheet");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


package com.company;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ViewStudent extends JFrame {
	static ViewStudent frame;
	public ViewStudent() {
		//Code to view data in JTable
		int size=getLastId();

		String data[][]=new String[size][12];
		int row=0;

		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader("student.txt"));
			while ((line = br.readLine()) != null) {
				String [] s= line.split(" ");
				data[row][0]=s[0];
				data[row][1]=s[1];
				data[row][2]=s[2];
				data[row][3]=s[3];
				data[row][4]=s[4];
				data[row][5]=s[5];
				data[row][6]=s[6];
				data[row][7]=s[7];
				data[row][8]=s[8];
				row++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		String columnNames[]={"Rollno","Name","Email","Password","Address","City","State","Country","Contact No"};
		
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
					frame = new ViewStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int getLastId(){

		BufferedReader br = null;
		String line = "";
		int id=0;
		try {
			br = new BufferedReader(new FileReader("studentId.txt"));
			id = Integer.parseInt(br.readLine());
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return id;
	}
}

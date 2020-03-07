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

public class ViewAccountant extends JFrame {
	static ViewAccountant frame;
	public ViewAccountant() {
		//Code to view data in JTable
		int size=getLastId();
		String data[][]=new String[size][5];
		int row=0;

		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader("accountant.txt"));
			while ((line = br.readLine()) != null) {
				String [] s= line.split(" ");
				//for(int i=0;i<size;i++){
					data[row][0]=s[0];
					data[row][1]=s[1];
					data[row][2]=s[2];
					data[row][3]=s[3];
					data[row][4]=s[4];
					row++;
				//}
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		String columnNames[]={"Id","Name","Password","Email","Contact No"};
		
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
					frame = new ViewAccountant();
					frame.setTitle("Accountant List");
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
			br = new BufferedReader(new FileReader("accountId.txt"));
			id = Integer.parseInt(br.readLine());
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return id;
	}
}

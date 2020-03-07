package com.company;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DueFee extends JFrame {
	static DueFee frame;
	public DueFee() {
		setLayout(new BorderLayout());
		//Code to view data in JTable
		int size=getLastId();
		String data[][]=new String[50][6];
		int row=0;

		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader("fee.txt"));
			while ((line = br.readLine()) != null) {
				String [] s= line.split(" ");
				data[row][0]=s[0];
				data[row][1]=s[1];
				data[row][2]=s[2];
				data[row][3]=s[3];
				data[row][4]=s[4];
				data[row][5]=s[5];
				row++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		String columnNames[]={"Rollno","Name","Fee Type","Course","Fee","Trimester Name"};
		JLabel totalFeelbl = new JLabel("Total Fee");
		JTextField tF1 = new JTextField(10);
		tF1.setText("");
		JLabel paidlbl = new JLabel("Paid Amount");
		JTextField tF2 = new JTextField(10);
		tF2.setText("");
		JLabel duelbl = new JLabel("Due Amount");
		JTextField tF3 = new JTextField(10);
		tF3.setText("");
		JLabel advancelbl = new JLabel("Advance Balance");
		JTextField tF4 = new JTextField(10);

		JLabel load = new JLabel("ID:");
		JTextField loadField = new JTextField(10);
		loadField.setText("");
		JSeparator separator = new JSeparator();
		JButton btnLoadRecord = new JButton("Load Record");

		tF4.setText("");
		JPanel p1 = new JPanel();
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		p1.add(load);
		p1.add(loadField);
		p1.add(btnLoadRecord);
		p1.add(separator);
		p1.add(totalFeelbl);
		p1.add(tF1);
		p1.add(paidlbl);
		p1.add(tF2);
		p1.add(duelbl);
		p1.add(tF3);
		//p1.add(advancelbl);
		//p1.add(tF4);
		JTable jt=new JTable(data,columnNames);
		JScrollPane sp=new JScrollPane(jt);
		setContentPane(p1);
		add(sp);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DueFee();
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

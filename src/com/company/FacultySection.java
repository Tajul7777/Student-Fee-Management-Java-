package com.company;

        import java.awt.BorderLayout;
        import java.awt.EventQueue;

        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.border.EmptyBorder;
        import javax.swing.GroupLayout;
        import javax.swing.GroupLayout.Alignment;
        import javax.swing.JLabel;
        import java.awt.Font;
        import java.awt.Color;
        import javax.swing.JButton;
        import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;

public class FacultySection extends JFrame {
    static FacultySection frame;
    private JPanel contentPane;
    private String facultyID;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new FacultySection();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FacultySection(String id) {
        facultyID = id;
        frame = new FacultySection();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public FacultySection() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setForeground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblFeeReport = new JLabel("Faculty Section");
        lblFeeReport.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JButton btnAddAttendance = new JButton("Add Attendance");
        btnAddAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AddAttendance.main(new String[]{});
                frame.dispose();
            }
        });

        JButton btnViewAttendance = new JButton("View Attendance");
        btnViewAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ViewAttendance.main(new String[]{});
            }
        });

        JButton btnEditAttendance = new JButton("Edit Attendance");
        btnEditAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                EditAttendance.main(new String[]{});
                frame.dispose();
            }
        });

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FeeReport.main(new String[]{});
                frame.dispose();
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(143)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnLogout, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnViewAttendance, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnEditAttendance, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                                .addComponent(btnAddAttendance, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                                .addComponent(lblFeeReport)))
                                .addContainerGap(210, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblFeeReport)
                                .addGap(42)
                                .addComponent(btnAddAttendance, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(btnViewAttendance, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(btnEditAttendance, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(91, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}

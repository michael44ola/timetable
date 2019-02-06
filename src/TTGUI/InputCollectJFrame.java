/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTGUI;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author MICHAEL
 */
public class InputCollectJFrame extends JFrame {
    private JList teacherJList;
    private DefaultListModel teacherArrayList;
    private ArrayList<Integer> teacherMaxClassArrayList;
    private static final Integer[] hoursPerDay = {1,2,3,4,5,6,7,8,9};
    private DefaultListModel courseArrayList;
    private ArrayList<Object> coursehpwClassArrayList;
    private JList coursehpwJList;

    /**
     * Creates new form InputCollectJFrame
     */
    public InputCollectJFrame() {
        TimeTableUtil tableUtil = new TimeTableUtil();
        JTabbedPane  jTabbedPane = new JTabbedPane();
        //Staff
        
        //Staff
                
        //Course
        
        //Course
        JPanel jPanelClassroom = new JPanel();
        

        
        jPanelClassroom.add(new JLabel("Panel Three", SwingConstants.CENTER));
        
        
        
        
        jTabbedPane.addTab("Staff", null, tableUtil.getStaffInput() ,"Staff Panel");
        jTabbedPane.addTab("Classes", null, tableUtil.getClassroomInput(),"Classroom Panel");
        jTabbedPane.addTab("Subjects", null, tableUtil.getSubjectInput(),"Subject Panel");
        jTabbedPane.addTab("Time Table", null, tableUtil.getTimeTable(),"Time Table");

        
        add(jTabbedPane);
    }

    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

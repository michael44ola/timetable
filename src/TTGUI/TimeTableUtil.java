/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTGUI;

import Basics.Classroom;
import Basics.Slot;
import Basics.Subject;
import Basics.Teacher;
import TTG.TTSheet;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author MICHAEL
 */
public class TimeTableUtil {
    private Slot[][] timeTableData = new Slot[5][9];
    private TTSheet mSheet = null;
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private JPanel editJPanel;
    
    
    private DefaultListModel teacherArrayList;
    private ArrayList<Integer> teacherMaxClassArrayList;
    private static final Integer[] hoursPerDay = {1,2,3,4,5,6,7,8,9};
    private JList teacherJList;
    
    private DefaultListModel classArrayList;
    private ArrayList<Integer> classroomArrayList;
    private JList classroomList;
    
    private DefaultListModel subjectArrayList;
    private ArrayList<Integer> subjectMaxClassArrayList;
    private ArrayList<Integer> subjectLevelClassArrayList;
    private ArrayList<Integer> subjectCapacityArrayList;
    private ArrayList<int[]> selectedTeacher;
    private JList subjectList;
    
   
    public TimeTableUtil(){
        editJPanel = new JPanel();
                
                
        teacherArrayList = new DefaultListModel();
        teacherMaxClassArrayList = new ArrayList<>();
        teacherJList = new JList(teacherArrayList);
        teacherJList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList jList = (JList)e.getSource();
                if(e.getClickCount()>1){
                    editJPanel.removeAll();
                    editJPanel.setLayout(new GridLayout(4,1,3,3));
                    editJPanel.add(new JLabel("*Staff Name"));
                    editJPanel.add(new JTextField(12){
                        @Override
                        public synchronized void addKeyListener(KeyListener l) {
                            
                            super.addKeyListener(l); //To change body of generated methods, choose Tools | Templates.
                        }

                        
                        public void keyPressed(Event evt, int key) {
                            JOptionPane.showMessageDialog(null, key, "Remove Staff",JOptionPane.ERROR_MESSAGE);
                        }
                        
                       
                    });
                    editJPanel.add(new JLabel("*Hour per day"));
                    JOptionPane.showMessageDialog(null, jList.locationToIndex(e.getPoint()), "Remove Staff",JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null,editJPanel, "Remove Staff",JOptionPane.NO_OPTION);
                }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        classArrayList = new DefaultListModel();
        classroomArrayList = new ArrayList<>();
        classroomList = new JList(classArrayList);
        
        subjectArrayList = new DefaultListModel();
        subjectMaxClassArrayList = new ArrayList<>();
        subjectLevelClassArrayList = new ArrayList<>();
        subjectCapacityArrayList = new ArrayList<>();
        subjectList = new JList(subjectArrayList);
        selectedTeacher = new ArrayList<>();
        
    }
    
    public JPanel getStaffInput(){
        JPanel jPanelStaff = new JPanel();
        jPanelStaff.setLayout(new BorderLayout());
        
        JPanel addTeacherPanel = new JPanel();        

        addTeacherPanel.setLayout(new GridLayout(7,1));
        JTextField jTextTeacherName = new JTextField( 15);
        JList jListTeacherMaxClass = new JList(hoursPerDay);
        jListTeacherMaxClass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListTeacherMaxClass.setVisibleRowCount(1);
        JButton teacherButton = new JButton("Add");
        teacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffName = jTextTeacherName.getText();
                int hpd = jListTeacherMaxClass.getSelectedIndex();
                if(staffName.length() ==  0){
                    JOptionPane.showMessageDialog(null, "Staff Name Cannot Be Empty", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextTeacherName.requestFocus();
                    return;
                }
                if(teacherArrayList.contains(staffName)){
                    JOptionPane.showMessageDialog(null, "Teacher Name Already Exist (Teacher Name is Unique)", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextTeacherName.requestFocus();
                    return;
                }
                if(hpd ==  -1){
                    JOptionPane.showMessageDialog(null, "Staff Hour per day Must be Selected", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jListTeacherMaxClass.requestFocus();
                    return;
                }
                teacherArrayList.addElement(staffName);
                jTextTeacherName.setText("");
                teacherMaxClassArrayList.add(hpd+1);
            }
        });
        
        addTeacherPanel.add(new JLabel("Staff Detail"));
        addTeacherPanel.add(new JLabel("Enter Name"));
        addTeacherPanel.add(jTextTeacherName);
        addTeacherPanel.add(new JLabel("Hour/Day"));
        addTeacherPanel.add(new JScrollPane(jListTeacherMaxClass));
        addTeacherPanel.add(teacherButton);

        jPanelStaff.add(addTeacherPanel, BorderLayout.WEST);
        jPanelStaff.add(teacherJList, BorderLayout.CENTER);
        
        
        return jPanelStaff;
    }
    
    public JPanel getClassroomInput(){
        JPanel jPanelClassroom = new JPanel();
        jPanelClassroom.setLayout(new BorderLayout());
        
        JPanel addClassroomPanel = new JPanel();        
        
     
        
        addClassroomPanel.setLayout(new GridLayout(7,1));
        JTextField jTextCourse = new JTextField( 15);
        JTextField jTextCapacity = new JTextField( 15);
        
        
        JButton courseButton = new JButton("Add");
        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classroomName = jTextCourse.getText();
                if(classroomName.length() ==  0){
                    JOptionPane.showMessageDialog(null, "Classroom Name Cannot Be Empty", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextCourse.requestFocus();
                    return;
                }
                if(classArrayList.contains(classroomName)){
                    JOptionPane.showMessageDialog(null, "Classroom Name Already Exist", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextCourse.requestFocus();
                    return;
                }
                int classroomCapacity = 0;
                if(jTextCapacity.getText().length() ==  0){
                    JOptionPane.showMessageDialog(null, "Class Capacity Must Be Not Be Empty", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextCapacity.requestFocus();
                    return;
                }
                try {
                    classroomCapacity = Integer.parseInt(jTextCapacity.getText());
                    if(classroomCapacity<1){
                        JOptionPane.showMessageDialog(null, "Class Capacity must be greater Than Zero", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                        jTextCapacity.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Class Capacity must be number", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextCapacity.requestFocus();
                    jTextCapacity.setText("0");
                    return;
                }

                
                System.out.printf("%s, %d \n",classroomName, classroomCapacity);
                classArrayList.addElement(classroomName);
                jTextCourse.setText("");
                jTextCapacity.setText("");
                classroomArrayList.add( classroomCapacity ) ;
                
            }
        });
        
        
        
        
        
        addClassroomPanel.add(new JLabel("Course Detail"));
        addClassroomPanel.add(new JLabel("Enter Name"));
        addClassroomPanel.add(jTextCourse);
        addClassroomPanel.add(new JLabel("Class Capacity"));
        addClassroomPanel.add(jTextCapacity);
        addClassroomPanel.add(courseButton);

        jPanelClassroom.add(addClassroomPanel, BorderLayout.WEST);
        jPanelClassroom.add(classroomList, BorderLayout.CENTER);
        return jPanelClassroom;
    }
    
    public JPanel getSubjectInput(){
        JPanel jPanelSubject = new JPanel();
        jPanelSubject.setLayout(new BorderLayout());
        
        JPanel addSubjectPanel = new JPanel();
        addSubjectPanel.setLayout(new GridLayout(14,1));
        JTextField jTextSubjectName = new JTextField( 15);
        JTextField jTextSubjectCapacity = new JTextField( 15);
        JList jListSubjectMaxClass = new JList(hoursPerDay);
        jListSubjectMaxClass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListSubjectMaxClass.setVisibleRowCount(1);
        JList jListSubjectYear = new JList(hoursPerDay);
        jListSubjectYear.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListSubjectYear.setVisibleRowCount(1);
        JList jListSubjectTeacher = new JList(teacherArrayList);
        jListSubjectTeacher.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jListSubjectTeacher.setVisibleRowCount(1);
        
        JButton subjectButton = new JButton("Add");
        subjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subName = jTextSubjectName.getText();
                int hpw = jListSubjectMaxClass.getSelectedIndex();
                int year = jListSubjectYear.getSelectedIndex();
                int subjectAtt = 0;
                int[] teacher = jListSubjectTeacher.getSelectedIndices();
                if(subName.length() == 0){
                    JOptionPane.showMessageDialog(null, "Subject Name Cannot Be Empty", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextSubjectName.requestFocus();
                    return;
                }
                if(subjectArrayList.contains(subName)){
                    JOptionPane.showMessageDialog(null, "Subject Name Already Exist", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextSubjectName.requestFocus();
                    return;
                }
                if(hpw == -1){
                    JOptionPane.showMessageDialog(null, "Sublect Hour per Week Must be Selected", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jListSubjectMaxClass.requestFocus();
                    return;
                }
                if(year == -1){
                    JOptionPane.showMessageDialog(null, "Sublect Year Must be Selected", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jListSubjectYear.requestFocus();
                    return;
                }
                
                if(jTextSubjectCapacity.getText().length() ==  0){
                    JOptionPane.showMessageDialog(null, "Subject Student Number Must Be Not Be Empty", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextSubjectCapacity.requestFocus();
                    return;
                }
                try {
                    subjectAtt = Integer.parseInt(jTextSubjectCapacity.getText());
                    if(subjectAtt<1){
                        JOptionPane.showMessageDialog(null, "Subject Student Number must be More than Zero", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                        jTextSubjectCapacity.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Subject Student Number must be number", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jTextSubjectCapacity.requestFocus();
                    jTextSubjectCapacity.setText("0");
                    return;
                }
                if(teacher.length < 1){
                    JOptionPane.showMessageDialog(null, "Atleast A Teacher Must be Selected", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jListSubjectTeacher.requestFocus();
                    return;
                }
                if(classArrayList.size()<1){
                    JOptionPane.showMessageDialog(null, "Atleast A Class Must be Added", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    jListSubjectTeacher.requestFocus();
                    return;
                }
                
                subjectArrayList.addElement(subName);
                jTextSubjectName.setText("");
                subjectMaxClassArrayList.add(hpw+1);
                subjectLevelClassArrayList.add( year+1 );
                subjectCapacityArrayList.add( subjectAtt );
                selectedTeacher.add(teacher);
            }
        });
        
        addSubjectPanel.add(new JLabel("Subject Detail"));
        addSubjectPanel.add(new JLabel("Enter Name"));
        addSubjectPanel.add(jTextSubjectName);
        addSubjectPanel.add(new JLabel("Hour/Week"));
        addSubjectPanel.add(new JScrollPane(jListSubjectMaxClass));
        addSubjectPanel.add(new JLabel("Year"));
        addSubjectPanel.add(new JScrollPane(jListSubjectYear));
        addSubjectPanel.add(new JLabel("No of Student taking the course"));
        addSubjectPanel.add(jTextSubjectCapacity);
        addSubjectPanel.add(new JLabel("Teachers Taking Course"));
        addSubjectPanel.add(new JScrollPane(jListSubjectTeacher));
        addSubjectPanel.add(subjectButton);

        jPanelSubject.add(addSubjectPanel, BorderLayout.WEST);
        jPanelSubject.add(subjectList, BorderLayout.CENTER);
        return jPanelSubject;
    }
    public JPanel getTimeTable(){
        Graphics graphics;
        JPanel mainJpanel = new JPanel(new BorderLayout());
        JButton mButton = new JButton("Gen TT");
        mainJpanel.add(mButton, BorderLayout.SOUTH);
        JPanel mJPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g); //To change body of generated methods, choose Tools | Templates.
                if(mSheet==null){
                    return;
                }
                double offsetX = this.getWidth()/10;
                double offsetY = this.getHeight()/6;
                for (int i = 1; i < 10; i++) {
                    g.drawLine((int)(i*offsetX), 0, (int)( i*offsetX ), this.getHeight());
                    g.drawString( ( (i+7) == 12 ? 12 :(i+7)%12 ) +" - " + ( (i+8) == 12 ? 12 :(i+8)%12 ), 
                            (int)(i*offsetX + (offsetX*0.2)), 
                            (int)(offsetY*0.5) );
                }
                for (int i = 1; i < 6; i++) {
                    g.drawLine(0, (int)(i*offsetY), this.getWidth(), (int)(i*offsetY));
                    g.drawString(days[i-1], (int)(offsetX*0.2), (int)(i*offsetY+offsetY*0.5));
                }
                Slot tmpSlot;
                ArrayList<Subject> tmpSubjects;
                for (int i = 1; i < 6; i++) {
                    for (int j = 1; j < 10; j++) {
                        tmpSlot = timeTableData[i-1][j-1];
                        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, (int)(offsetX/12) ));
                        tmpSubjects = tmpSlot.getSubjects();
                        double koff = 0.25;
                        for (int k = 0; k < tmpSubjects.size(); k++) {
                            g.drawString(tmpSubjects.get(k).getName() +" - "+
                                    tmpSubjects.get(k).teacherNameString()+"("+
                                    tmpSubjects.get(k).getClassroom().getName()+")"
                                    , (int)(j*offsetX + (offsetX*0.05)), (int)(i*offsetY+offsetY*koff) );
                            koff += 0.25;
                        }
                    }
                }
            }
            
            
        };
        mainJpanel.add(mJPanel, BorderLayout.CENTER);
        mButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(subjectArrayList.size()<1){
                    JOptionPane.showMessageDialog(null, "Atleast A Subject Must be Added", "Malformed Input",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                mSheet = new TTSheet(getTeachersMain(), 
                        getClassroomsMain(), 
                        getSubjectsMain()
                
                );
                timeTableData = mSheet.getTimeTableData();
                mJPanel.repaint();
            }
        });
        
        return mainJpanel;
    }

    public Teacher[] getTeachersMain() {
        Teacher[] arrayList = new Teacher[teacherArrayList.size()];
        for (int i = 0; i < arrayList.length; i++) {
            arrayList[i] = new Teacher(teacherArrayList.get(i).toString(), teacherMaxClassArrayList.get(i));
        }
        return arrayList;
    }

    public Classroom[] getClassroomsMain() {
        Classroom[] arrayList = new Classroom[classArrayList.size()];
        for (int i = 0; i < arrayList.length; i++) {
            arrayList[i] = new Classroom(classArrayList.get(i).toString(), classroomArrayList.get(i));
        }
        return arrayList;
    }

    public Subject[] getSubjectsMain() {
        Subject[] arrayList = new Subject[subjectArrayList.size()];
        
        for (int i = 0; i < arrayList.length; i++) {
            Teacher[] tmpTeacher = new Teacher[selectedTeacher.get(i).length];
            for (int j = 0; j < selectedTeacher.get(i).length; j++) {
                tmpTeacher[j] = new Teacher(teacherArrayList.get( selectedTeacher.get(i)[j]).toString() , teacherMaxClassArrayList.get(selectedTeacher.get(i)[j]));
            }
            arrayList[i] = new Subject(subjectArrayList.get(i).toString(),
                    subjectMaxClassArrayList.get(i), 
                    subjectCapacityArrayList.get(i), 
                    tmpTeacher, subjectLevelClassArrayList.get(i) );
        }
        return arrayList;
    }
    
    
}


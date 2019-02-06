/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;
import java.util.ArrayList;
/**
 *
 * @author MICHAEL
 */
public class Subject {
    private String subName;
    private int noStudent;
    private boolean fullyAssigned = false;
    private int period;
    private int maxNoPeriod = 0;
    private ArrayList<Integer> assignedDays;
    private Teacher[] teacher;
    private Classroom classroom;
    private int level;
    public Subject(String s, int i){
        this.subName = s;
        this.period = i;
    }
    public Subject(String s, int i, int noOfStudentTakingClass, Teacher[] ss, int level){
        this.subName = s;
        this.period = i;
        this.noStudent = noOfStudentTakingClass;
        this.assignedDays = new ArrayList<>();
        this.teacher = ss;
        this.level = level;
//        this.classroom 
//        this.period = p;
    }
    
    public String getName(){
        return subName;
    }
    
    public boolean getFullyAssigned(){
        return fullyAssigned;
    }
    public int getCurrentPeriod(){
        return period;
    }
    public void deductPeriod(){
        period--;
        maxNoPeriod++;
        for(int i = 0; i < teacher.length; i++) {
            teacher[i].incCounter();
        }
    }
    public int getNoAssigned(){
        return maxNoPeriod;
    }
    public void addDay(int i){
        assignedDays.add(i);
        maxNoPeriod = 0;
    }
    public boolean isDayAssigned(int i){
        if(assignedDays.indexOf(i) > -1){
            return true;
        }
        return false;
    }
    public Teacher[] getTeacher(){
       return teacher;
    }
    public int getLevel(){
        return level;
    }

    public void setClass(Classroom classroom) {
        this.classroom = classroom;
    }
    public Classroom getClassroom(){
        return classroom;
    }
    public int getNoOfStud(){
        return noStudent;
    }
    public String teacherNameString(){
        String tmp = "";
        if(teacher.length==1){
            return teacher[0].getName()+"";
        }
        for(int i = 0; i < teacher.length; i++) {
            tmp += teacher[i].getName() + (i==teacher.length-2 ? " & "  : i==teacher.length-1 ? "":",") ;
        }
        return tmp;
    }
}

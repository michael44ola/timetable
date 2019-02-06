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
public class Slot {
    private ArrayList<Integer> currentLevels;
    private ArrayList<Teacher> workingTeachers;
    private ArrayList<Subject> subjects;
    private ArrayList<Classroom> occupiedClassrooms;
   

    private Subject subject;
    public Slot(Subject s){
        this.currentLevels = new ArrayList<>();
        this.subject = s;
    }
    public Slot(){
        this.currentLevels = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.workingTeachers = new ArrayList<>();
        this.occupiedClassrooms = new ArrayList<>();
       

    }
    public void addLevel(int i){
        currentLevels.add(i);
    }
    public void addSubject(Subject i){
        subjects.add(i);
        currentLevels.add(i.getLevel());
        for(Teacher teacherInner : i.getTeacher()){
            workingTeachers.add(teacherInner);
        }
        
//        for(Classroom itm : totalClass){
//            addClassRoom(itm);
//        }
        
    }
    public Subject getSubject(){
        return subject;
    }
    public ArrayList<Subject> getSubjects(){
        return subjects;
    }
    public String getSubjectNames(){
        String s = "";
        for (int i = 0; i < subjects.size(); i++) {
            s += subjects.get(i).getName()+ (i<subjects.size()-1 ?", ":"");
        }
        
        return s;
    }
    public String getSubjectTeacherNames(){
        String s = "";
        for(Subject item : subjects){
            Teacher[] teachers = item.getTeacher();
            s+=" *** ";
            for(Teacher teacherInner : teachers){
                s += teacherInner.getName()+ " / ";
            }
            
        }
        
        return s;
    }
    public String getClassrooms(){
        String s = "";
        
        for(Subject item : subjects){
            s += item.getClassroom().getName()+ " / ";
        }
        return s;
    }
    public void setSub(Subject s){
        subject = s;
    }
    public boolean hasLevel(int i){
        return currentLevels.indexOf(i)== -1?false:true;
    }
    public boolean isTeacherBusy(Teacher[] tch){
        for(Teacher i : tch){
            for(Teacher t : workingTeachers){
                if(i.getName().equals(t.getName())){
                    return true;
                }
            }
        }
        
        return false;
    }
    public boolean isClassAvailable(Classroom c){
        for(Classroom occupied : occupiedClassrooms){
            if(occupied.getName().equals(c.getName())){
                return true;
            }
        }
        return false;
    }
    public void addClassRoom(Classroom c){
        occupiedClassrooms.add(c);
//        if(!isClassAvailable(c)){
//            occupiedClassrooms.add(c);
//        }
    }
}

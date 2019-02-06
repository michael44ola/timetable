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
public class Teacher {
   private String teacherName;
   private ArrayList<String> subjectList;
   private int noOfClassperDay;
   private int classCounter = 0;
   public Teacher(String s, int i){
        this.teacherName = s;
        this.subjectList = new ArrayList<>();
        this.noOfClassperDay = i;
    }
    
    public String getName(){
        return teacherName;
    }
    public void restartClassCounter(){
        classCounter = 0;
    }
    public void incCounter(){
        classCounter++;
    }

    public int getMaxClass() {
        return noOfClassperDay;
    }

    public int getCurrentClassCount() {
        return classCounter;
    }
    
}


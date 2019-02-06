/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTG;
import Basics.*;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JPanel;
/**
 *
 * @author MICHAEL
 */
public class TTSheet {
   private Slot[][] timeTableData;
   private Scanner mScanner;
   private Classroom[] classrooms;

   public TTSheet(){
    this.timeTableData = new Slot[5][9];
    this.mScanner = new Scanner(System.in);
//       sortClasses();
//       return;
    getSubjects();
   }
   public TTSheet(Teacher[] teach,  Classroom[] classrooms,Subject[] subjects ){
    
    this.timeTableData = new Slot[5][9];
    this.mScanner = new Scanner(System.in);
    this.classrooms = new Classroom[classrooms.length];
    for (int i = 0; i < classrooms.length; i++) {
        this.classrooms[i] = classrooms[i];
    }
    this.classrooms = classrooms;
    sortClasses();
    genTT(subjects);
//    drawTT();
            
            
            
   }
   public void getSubjects(){
        System.out.println("Enter No of Teachers");
        int N = Integer.parseInt(mScanner.nextLine());
        Teacher[] teachers = new Teacher[N];
        for(int i = 0; i<N;i++){
            System.out.println("Enter Teacher Name "+(i+1));
            String tmpN = mScanner.nextLine();
            System.out.println("Enter Teacher Max Class Per Day "+(i+1));
            String tmN = mScanner.nextLine();
            teachers[i] = new Teacher(tmpN, Integer.parseInt(tmN));
        }
        System.out.println("Enter No of Classes");
        N = Integer.parseInt(mScanner.nextLine());
        classrooms = new Classroom[N];
        
        
        
        for(int i = 0; i<N;i++){
            System.out.println("Enter Classroom Name "+(i+1));
            String tmpN = mScanner.nextLine();
            System.out.println("Enter Classroom Capacity "+(i+1));
            int tmpC = Integer.parseInt(mScanner.nextLine());
            classrooms[i] = new Classroom(tmpN, tmpC);
        }
        System.out.println("Enter No of Subjects");
        N = Integer.parseInt(mScanner.nextLine());
        Subject[] collectedData = new Subject[N];
        
        
        for(int i = 0; i<N;i++){
            System.out.println("Enter Subject Name "+(i+1));
            String tmpN = mScanner.nextLine();
            System.out.println("Enter Subject Name "+(i+1) + " hour per week");
            String tmpI = mScanner.nextLine();
            System.out.println("Enter Subject Name "+(i+1) + " year 1, 2, 3, 4.....");
            String tmpL = mScanner.nextLine();
            System.out.println("Enter Subject Name "+(i+1) + " No of Student taking the course");
            int tmpC = Integer.parseInt(mScanner.nextLine());
            System.out.print("Enter Subject Teacher Name "+(i+1) + " ");
            for(int t = 0; t<teachers.length;t++){
                System.out.print(" ("+(t+1)+") "+teachers[t].getName());
            }
            System.out.println();
//            int tmpT = Integer.parseInt(mScanner.nextLine());
            String[] tString;
            tString = mScanner.nextLine().trim().split(",");
            Teacher[] t1 = new Teacher[tString.length];
            for (int j = 0; j < tString.length; j++) {
                t1[j] = teachers[ Integer.parseInt(tString[j]) - 1 ];
            }
            collectedData[i] = new Subject(tmpN,Integer.parseInt(tmpI),tmpC, t1, Integer.parseInt(tmpL));
        }
//        System.out.print(collectedData.length);
        sortClasses();
        genTT(collectedData);
        
   }
   public void genTT(Subject[] s){
   s = sortSubject(s);
    for (int i = 0; i < s.length; i++) {
        System.out.println(s[i].getName());
        System.out.println(s[i].getNoOfStud());

    }   
       ArrayList<Classroom> tmpClass;
        for(int i = 0; i<5;i++){
           for (Subject item : s) {
               
               for(Teacher teacherInner : item.getTeacher()){
                    teacherInner.restartClassCounter();
                }
           }
            for(int j = 0; j<9;j++){
                
                tmpClass = new ArrayList<>();
                tmpClass.addAll(Arrays.asList(classrooms));                
                timeTableData[i][j] = new Slot();
                for(int ii = 0; ii<s.length;ii++){
                    if(tmpClass.isEmpty()){
                        break;
                    }
                    if(s[ii].getCurrentPeriod() > 0 && !s[ii].isDayAssigned(i) ){
                        Classroom mClassroom = null;
//                        for(int k = 0; k<tmpClass.size(); k++){
//                            mClassroom = tmpClass.get(k);
//                            tmpClass.remove(k);
//                            break;
//                                
//                            }
                        if(timeTableData[i][j].hasLevel(s[ii].getLevel()) ||
                                timeTableData[i][j].isTeacherBusy(s[ii].getTeacher()) ||
//                                tmpClass.isEmpty() || 
                                teacherMaxDay(s[ii])
                                
                                ){continue;}
                        boolean ass = false;
                        if(s[ii].getNoAssigned() < 1){
                            
                            for(int k = 0; k<tmpClass.size(); k++){
                                if(s[ii].getNoOfStud() <= tmpClass.get(k).getCapacity()){
                                    mClassroom = tmpClass.remove(k);
                                    ass = true;
                                }
                            }
                            if(!ass){
                                continue;
                            }
                            s[ii].deductPeriod();
                            
                            s[ii].setClass(mClassroom);
                            timeTableData[i][j].addSubject(s[ii]);
                        }else{
                            for(int k = 0; k<tmpClass.size(); k++){
                                if(s[ii].getNoOfStud() <= tmpClass.get(k).getCapacity()){
                                    mClassroom = tmpClass.remove(k);
                                    ass = true;
                                }
                            }
                            if(!ass){
                                continue;
                            }
                            s[ii].deductPeriod();
                            s[ii].addDay(i);
//                            for(int k = 0; k<tmpClass.size(); k++){
//                                if(s[ii].getNoOfStud() <= tmpClass.get(k).getCapacity()){
//                                    s[ii].setClass(tmpClass.get(k));
//                                    tmpClass.remove(k);
//                                    break;
//                                }
////                                break;
//                            }
                            s[ii].setClass(mClassroom);
                            timeTableData[i][j].addSubject(s[ii]);
                        }
                        
                    }
                }
                if(timeTableData[i][j] == null){
                    System.out.print(" N/A ");
                }
                System.out.print(timeTableData[i][j].getSubjectNames() + " ("+timeTableData[i][j].getSubjectTeacherNames()+" at>> " +timeTableData[i][j].getClassrooms() +" ) ");
                System.out.print(" || ");
            }
            System.out.println(s.length);
        }
   }
   
   public void sortClasses(){
       for(int i = 0; i< classrooms.length-1;i++){
           for(int j = i+1; j<classrooms.length;j++){
               
               if(classrooms[i].getCapacity()>classrooms[j].getCapacity()){
                   Classroom tmp = classrooms[j];
                   classrooms[j] = classrooms[i];
                   classrooms[i] = tmp;
               }
           }
       }
       
   }
   public Subject[] sortSubject( Subject[] subject){
       
       for(int i = 0; i< subject.length-1;i++){
           for(int j = i+1; j<subject.length;j++){
               
               if(subject[i].getNoOfStud()>subject[j].getNoOfStud()){
                   Subject tmp = subject[j];
                   subject[j] = subject[i];
                   subject[i] = tmp;
               }
           }
       }
       
       return  subject;
   }
   public boolean teacherMaxDay(Subject s){
       Teacher[] ts = s.getTeacher();
       for(Teacher t : ts){
        if(t.getCurrentClassCount()>= t.getMaxClass()){
            return true;
        }
            }
       return false;
   }

    public Slot[][] getTimeTableData() {
        return timeTableData;
    }
    
    
}

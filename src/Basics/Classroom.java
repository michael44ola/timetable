/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

/**
 *
 * @author MICHAEL
 */
public class Classroom {
    private String className;
    private int capacity;
    public Classroom(String s, int capacity){
        className = s;
        this.capacity = capacity;
    }
    public String getName(){
        return className;
    }
    public int getCapacity(){
        return capacity;
    }
}

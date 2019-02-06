/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;
import TTG.TTSheet;
import TTGUI.InputCollectJFrame;
import javax.swing.*;

/**
 *
 * @author MICHAEL
 */
public class TimeTable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        new TTSheet();
        InputCollectJFrame collectJFrame = new InputCollectJFrame();
        collectJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        collectJFrame.setSize(750,600);
        collectJFrame.setVisible(true);
    }
    
}

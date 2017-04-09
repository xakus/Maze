/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labgeneration;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Murad Salmanov
 */
public class LabGeneration{
    int lab=40;
    int labSize=lab*2+1;
    int cellSize=8;
    int dx=6,dy=30;
    int width=labSize*cellSize+dx;
    int height=labSize*cellSize+dy;
    int [][] matrix=new int [labSize][labSize];
    
    WindowForm wf;
    GraphicPanel graphicPanel; 
    Maze maze;
    
    public LabGeneration(){
         wf=new WindowForm(width, height, "Lberint");
         maze=new Maze(lab);
         matrix=maze.getMazeMatrix();
         graphicPanel=new GraphicPanel(labSize, cellSize,matrix);
         wf.add(graphicPanel);
         wf.setVisible(true);
         wf.addMouseListener(new MouseAdapter() {
             @Override
             public void mousePressed(MouseEvent e) {
                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        maze.mazeGeneration();
                    }
                });
                t.start();
             }
             
});
        
    }
    public static void main(String[] args) {
        new LabGeneration();
    }
   

  
}

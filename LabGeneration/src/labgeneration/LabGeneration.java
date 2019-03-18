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
    int labW=10;
    int labH=5;
    int labSizeW=labW*2+1;
     int labSizeH=labH*2+1;
    int cellSize=16;
    int dx=6,dy=30;
    int width=labSizeW*cellSize+dx;
    int height=labSizeH*cellSize+dy;
    int [][] matrix=new int [labSizeW][labSizeH];
    
    WindowForm wf;
    GraphicPanel graphicPanel; 
    Maze maze;
    
    public LabGeneration(){
         wf=new WindowForm(width, height, "Lberint");
         maze=new Maze(labSizeW,labSizeH);
         matrix=maze.getMazeMatrix();
         graphicPanel=new GraphicPanel(labSizeW,labSizeH, cellSize,matrix);
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

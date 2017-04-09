/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labgeneration;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

/**
 *
 * @author Murad Salmanov
 */
public class WindowForm extends JFrame {

    public WindowForm(int width, int height, String title) {
        setTitle(title); 
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// Forma centerde chixmag uchun
        setResizable(false);

    }

}

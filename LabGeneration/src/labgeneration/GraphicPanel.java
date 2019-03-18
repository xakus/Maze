/**
 *
 * @author Murad Salmanov
 */
package labgeneration;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class GraphicPanel extends JPanel implements Runnable {

    public int fSizeW;
    public int fSizeH;
    public int bSize;
    private int[][] matrix;
    Color bachgroundColor = new Color(0, 0, 0);
    Color emptyColor =new Color(100, 50, 25);// new Color(230, 220, 220);
    Color wallColor =new Color(200, 100, 50);//new Color(10, 170, 25); //new Color(150, 70, 25);
    Color stepColor  = new Color(150, 70, 25);//new Color(187, 255, 255);
    Color[] colorOfNumbers = new Color[]{Color.BLUE, Color.GREEN, Color.ORANGE, Color.CYAN,
        Color.RED, Color.MAGENTA, Color.BLACK, Color.PINK};

    public GraphicPanel(int fSize, int bSize, int[][] matrix) {
        this.matrix = matrix;
        this.fSizeW = fSize;
        this.fSizeH = fSize;
        this.bSize = bSize;

        t.start();
    }
 public GraphicPanel(int fSizeW,int fSizeH, int bSize, int[][] matrix) {
        this.matrix = matrix;
        this.fSizeW = fSizeW;
        this.fSizeH = fSizeH;
        this.bSize = bSize;

        t.start();
    }
    @Override
    public void paint(Graphics g) {
        int width = fSizeW * bSize;
        int height = fSizeH * bSize;
        int cell = 0;
        for (int x = 0; x < fSizeW; x++) {
            for (int y = 0; y < fSizeH; y++) {
                cell = matrix[x][y];
                //cell=0 emty cell
                //cell=1 step cell
                //cell=2 wall cell
                if (cell == 0) {
                    
                    g = gCloseFild(x, y,g, wallColor);

                }else if (cell == 1) {
                    g = gCloseFild(x, y,g, stepColor);

                }else if (cell == 2){
                    g = gEmptyFild(x, y, g);
                }
                else if (cell == 3){
                   g = gCloseFild(x, y,g,Color.RED);
                }
            }
        }
        super.setBackground(bachgroundColor);

    }

    private Graphics gBomb(int x, int y, Graphics gr, boolean isBoom) {

        gr.setColor(emptyColor);
        gr.fill3DRect(x * bSize, y * bSize, bSize, bSize, false);
        if (!isBoom) {
            gr.setColor(new Color(0, 0, 0));
        } else {
            gr.setColor(new Color(200, 0, 0));
        }
        gr.fillOval(x * bSize + 5, y * bSize + 5, bSize - 10, bSize - 10);
        gr.setColor(new Color(255, 255, 255));
        gr.fillRect(x * bSize + bSize / 3, y * bSize + bSize / 3, bSize / 10, bSize / 10);
        return gr;
    }

    private Graphics gEmptyFild(int x, int y, Graphics g) {

        g.setColor(emptyColor);
        g.fill3DRect(x * bSize, y * bSize, bSize, bSize, false);
        return g;
    }

    private Graphics gCloseFild(int x, int y, Graphics g,Color color) {

        g.setColor(color);
        g.fill3DRect(x * bSize, y * bSize, bSize, bSize, true);
        return g;
    }

    private Graphics gCountFild(int x, int y, Graphics g, int count) {

        g.setColor(emptyColor);
        g.fill3DRect(x * bSize, y * bSize, bSize, bSize, false);
        g.setColor(colorOfNumbers[count - 1]);
        g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, bSize));
        g.drawString(count + "", x * bSize + bSize / 5, y * bSize - bSize / 5 + bSize);
        return g;
    }

//    private Graphics gFlag(int x, int y, Graphics g) {
//
//        g.setColor(closeColor);
//        g.fill3DRect(x * bSize, y * bSize, bSize, bSize, true);
//        g.setColor(flagColor);
//        g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, bSize / 2));
//        g.drawString("f", x * bSize + bSize / 3, y * bSize - bSize / 3 + bSize);
//        return g;
//    }
    Thread t = new Thread(this);

    @Override
    public void run() {

        while (true) {

            repaint();

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(GraphicPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

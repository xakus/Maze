/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labgeneration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Murad Salmanov
 */
public class Maze {

    private int labSizeW = 0;
    private int labSizeH = 0;
    private int[][] mazeMatrix;
    private int sX = 0, sY = 0;//Переменные показываюшие место положение 
    int endX = 0, endY = 0;
    int lenght = 0, oldLenghgt = 0;

    public int[][] getMazeMatrix() {
        return mazeMatrix;
    }

    public void setMazeMatrix(int[][] mazeMatrix) {
        this.mazeMatrix = mazeMatrix;
    }

    public Maze(int labSizeW, int labSizeH) {
        this.labSizeW = labSizeW;
        this.labSizeH = labSizeH;
        mazeMatrix = new int[ this.labSizeW][ this.labSizeH];
        matrixClear();
    }

    private void matrixClear() {
        oldLenghgt = 0;
        lenght = 0;
        endX = 0;
        endY = 0;
        for (int i = 0; i < labSizeW; i++) {
            for (int j = 0; j < labSizeH; j++) {
                mazeMatrix[i][j] = 0;
            }
        }
    }

    public synchronized void mazeGeneration() {
        matrixClear();
        do {
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(LabGeneration.class.getName()).log(Level.SEVERE, null, ex);
            }
            int stp = stepDefinition();
            step(stp);
            mazeMatrix[0][0] = 2;
        } while (!isEndGeneration());
        System.out.println("EndGeneration endX=" + endX + " endY=" + endY);
        mazeMatrix[endX][endY] = 3;
    }

    public synchronized void matrixRandom() {
        matrixClear();
        for (int i = 0; i < labSizeH; i++) {
            for (int j = 0; j < labSizeW; j++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LabGeneration.class.getName()).log(Level.SEVERE, null, ex);
                }
                int z = (int) ((Math.random() * 10) % 3);
                mazeMatrix[i][j] = z;
            }
        }
    }

    private int isStep(int x, int y) {//метод проверяет естли следуюший шаг если есть то выдает число возможнах ходов
        int st = 0;
        if (x >= 2) {
            if (mazeMatrix[x - 2][y] == 0) {
                st++;
            }
        }
        if (x <= labSizeW - 2) {
            if (mazeMatrix[x + 2][y] == 0) {
                st++;
            }
        }
        if (y >= 2) {
            if (mazeMatrix[x][y - 2] == 0) {
                st++;
            }
        }
        if (y <= labSizeH - 2) {
            if (mazeMatrix[x][y + 2] == 0) {
                st++;
            }
        }
        return st;
    }

    private boolean isEndGeneration() {//Метод проверяет конец генерации лаберинта. Конец лаберинта достигает тогда когда в матрице кончается 1
        boolean flag = true;
        for (int i = 0; i < labSizeW; i++) {
            for (int j = 0; j < labSizeH; j++) {
                if (mazeMatrix[i][j] == 1) {
                    flag = false;

                    return flag;
                }
            }
        }
        return flag;
    }

    private void step(int stp) {//шагает на одну позицию в указаном направление 1 вверх,2 в право,3 вниз,4 влево,0 откат назад
        switch (stp) {
            case 0:
                lenght--;
                if (sX >= 2) {
                    if (mazeMatrix[sX - 1][sY] == 1) {
                        mazeMatrix[sX][sY] = 2;
                        sX--;
                        mazeMatrix[sX][sY] = 2;
                        sX--;
                        raschot();
                        return;
                    }

                }
                if (sX <= labSizeW - 2) {
                    if (mazeMatrix[sX + 1][sY] == 1) {
                        mazeMatrix[sX][sY] = 2;
                        sX++;
                        mazeMatrix[sX][sY] = 2;
                        sX++;
                        raschot();
                        return;
                    }

                }
                if (sY >= 2) {
                    if (mazeMatrix[sX][sY - 1] == 1) {
                        mazeMatrix[sX][sY] = 2;
                        sY--;
                        mazeMatrix[sX][sY] = 2;
                        sY--;
                        raschot();
                        return;
                    }

                }
                if (sY <= labSizeH - 2) {
                    if (mazeMatrix[sX][sY + 1] == 1) {
                        mazeMatrix[sX][sY] = 2;
                        sY++;
                        mazeMatrix[sX][sY] = 2;
                        sY++;
                        raschot();
                        return;
                    }

                }
                break;
            case 1:
                lenght++;
                mazeMatrix[sX][sY] = 1;
                sY--;
                mazeMatrix[sX][sY] = 1;
                sY--;
                mazeMatrix[sX][sY] = 1;
                break;
            case 2:
                lenght++;
                mazeMatrix[sX][sY] = 1;
                sX++;
                mazeMatrix[sX][sY] = 1;
                sX++;
                mazeMatrix[sX][sY] = 1;
                break;
            case 3:
                lenght++;
                mazeMatrix[sX][sY] = 1;
                sY++;
                mazeMatrix[sX][sY] = 1;
                sY++;
                mazeMatrix[sX][sY] = 1;
                break;
            case 4:
                lenght++;
                mazeMatrix[sX][sY] = 1;
                sX--;
                mazeMatrix[sX][sY] = 1;
                sX--;
                mazeMatrix[sX][sY] = 1;
                break;
        }
        raschot();
    }

    private void raschot() {
        if (lenght > oldLenghgt) {
            oldLenghgt = lenght;
            endX = sX;
            endY = sY;
            System.out.println("Lenght" + lenght);
            System.out.println("x=" + endX);
            System.out.println("y=" + endY);
        }
    }

    private int stepDefinition() {
        int stp = 0;
        int isS = isStep(sX, sY);
        int rnd = 0;
        if (isS == 0) {
            return 0;
        } else {
            if (isS > 1) {
                rnd = (int) ((Math.random() * 10) % isS);
            }
        }

        if (sX >= 2) {
            if (mazeMatrix[sX - 2][sY] == 0) {//4
                if (rnd == 0) {
                    return 4;
                }
                rnd--;
            }
        }
        if (sX <= labSizeW - 2) {
            if (mazeMatrix[sX + 2][sY] == 0) {//2
                if (rnd == 0) {
                    return 2;
                }
                rnd--;
            }
        }
        if (sY >= 2) {
            if (mazeMatrix[sX][sY - 2] == 0) {//1
                if (rnd == 0) {
                    return 1;
                }
                rnd--;
            }
        }
        if (sY <= labSizeH - 2) {
            if (mazeMatrix[sX][sY + 2] == 0) {//3
                if (rnd == 0) {
                    return 3;
                }
                rnd--;
            }
        }
        return stp;
    }
}

package com;

import java.util.Arrays;

public class Matrix {

    private static final int TIMES = 10000000;

    public static void main(String[] args) {
        Grid[] grids = new Grid[24];
        boolean matrix[][] = new boolean[5][5];
        for (int i = 0; i < 24; i++) {
            grids[i] = new Grid();
            grids[i].value = i >= 12 ? i+1 : i;
        }

        int times = 0;
        int count = 0;
        while (times < TIMES) {
            for (boolean[] row : matrix) {
                Arrays.fill(row, false);
            }
            matrix[2][2] = true;

            for (int i = 0; i < 24; i++) {
                grids[i].rnd = Math.random();
                grids[i].selected = false;
            }
            Arrays.sort(grids, (grid1, grid2) -> {
                if (grid1.rnd > grid2.rnd) {
                    return 1;
                } else {
                    return -1;
                }
            });
            for (int i = 0; i < 14; i++) {
                int m = grids[i].value/5;
                int n = grids[i].value%5;
                matrix[m][n] = true;
            }

//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 5; j++) {
//                    System.out.print((matrix[i][j]? 1:0) + ", ");
//                }
//                System.out.println();
//            }
//                System.out.println(hasLine(matrix));

            if (hasLine(matrix)) count++;
            times++;
        }
        System.out.println(1f-((double) count)/times);
    }

    public static boolean hasLine(boolean[][] matrix) {
        //diagonal
        if (matrix[0][0] && matrix[1][1]  && matrix[3][3] && matrix[4][4]) return true;
        if (matrix[0][4] && matrix[1][3]  && matrix[3][1] && matrix[4][0]) return true;

        //row & column
        for (int i = 0; i < 5; i++) {
            if (matrix[i][0] && matrix[i][1]  && matrix[i][2] && matrix[i][3] && matrix[i][4] ||
                    matrix[0][i] && matrix[1][i]  && matrix[2][i] && matrix[3][i] && matrix[4][i]) {
                return true;
            }
        }
        return false;
    }

    public static class Grid {
        public int value;
        public boolean selected = false;
        public double rnd;
    }
}



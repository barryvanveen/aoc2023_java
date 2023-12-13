package org.aoc.day3;

public class Adjacency {
    private final int cols;
    private final int rows;
    private boolean[][] grid;

    public Adjacency(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.grid = new boolean[cols][rows];

        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                this.grid[c][r] = false;
            }
        }
    }

    public void putSymbol(int col, int row) {
        for (int c=col-1; c<=col+1; c++) {
            for (int r=row-1; r<=row+1; r++) {
                this.grid[c][r] = true;
            }
        }
    }

    public boolean isAdjacent(int col, int row) {
        return this.grid[col][row];
    }

    public void debug() {
        for (int r=0; r<this.rows; r++) {
            for (int c=0; c<this.cols; c++) {
                System.out.print(this.grid[c][r] ? 1 : 0);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}

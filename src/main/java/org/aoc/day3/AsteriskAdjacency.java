package org.aoc.day3;

import java.util.HashSet;
import java.util.Set;

public class AsteriskAdjacency {
    private final int cols;
    private final int rows;
    private int[][] grid;
    private int counter = 1;

    public AsteriskAdjacency(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.grid = new int[cols][rows];

        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                this.grid[c][r] = 0;
            }
        }
    }

    public void putSymbol(int col, int row) {
        this.grid[col][row] = this.counter;
        this.counter++;
    }

    public Set<Integer> getAdjacent(int col, int row) {
        // look at surrounding cells
        // if one is occupied by an asterisk, we are adjacent
        HashSet<Integer> asterisks = new HashSet<>();

        for (int c=Math.max(col-1, 0); c<=Math.min(this.cols-1, col+1); c++) {
            for (int r=Math.max(row-1, 0); r<=Math.min(this.rows-1, row+1); r++) {
                if (this.grid[c][r] > 0) {
                    asterisks.add(this.grid[c][r]);
                }
            }
        }

        return asterisks;
    }
}

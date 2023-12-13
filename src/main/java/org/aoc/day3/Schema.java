package org.aoc.day3;

import java.util.ArrayList;

public class Schema {
    private final int cols;
    private final int rows;
    private char[][] grid;

    public Schema(int cols, int rows, ArrayList<String> input) {
        this.cols = cols;
        this.rows = rows;
        this.grid = new char[cols][rows];

        for (int r=0; r<rows; r++) {
            String row = input.get(r);
            for (int c=0; c<cols; c++) {
                char symbol = row.charAt(c);
                this.grid[c][r] = symbol;
            }
        }
    }

    public char get(int col, int row) {
        return this.grid[col][row];
    }
}

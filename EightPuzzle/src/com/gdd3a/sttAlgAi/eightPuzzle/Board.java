/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/12/20
 *	@description	...
 *
 */

package com.gdd3a.sttAlgAi.eightPuzzle;

public class Board {
    public int m_order;
    public int[] m_tiles;
    private int m_invers;
    private int m_zeRow;

    /**
     *	@desc	Board Object Constructor
     *	@param	tiles	- Two-Dimensional Array of Int values that holds the
     *                 	  default configuration of the Board.
     *	@funct	hamming     - ...
     *          manhattan   - ...
     *          equals      - ...
     *          neighbours  - ...
     *          toString    - ...
     */
    public Board(int N, int [][] tiles) {
        this.m_order = N;
        this.m_tiles = new int[tiles.length * tiles.length];
        this.m_invers = Integer.MIN_VALUE;
        this.m_zeRow = Integer.MIN_VALUE;

        int z = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                m_tiles[z++] = tiles[i][j];
                System.out.print(m_tiles[z - 1] + "\t");
            }
            System.out.println();
        }
    }

    /**
     *	@desc	...
     */
    public int hamming() {
        // YOUR CODE HERE
        return(0);
    }

    /**
     *	@desc	...
     */
    public int manhattan() {
        // YOUR CODE HERE
        return(0);
    }

    /**
     *	@desc	Compares this Board to another Board to check for equality.
     *	@param	o	- A Board to check against this Board.
     */
    public boolean equals(Board o) {
        boolean valid = true;

        for (int i = 0; i < m_tiles.length; i++) {
            if(this.m_tiles[i] != o.m_tiles[i]) {
                valid = false;
            }
        }

        return(valid);
    }

    /**
     *	@desc	...
     */
    public Iterable<Board> neighbours() {
        // YOUR CODE HERE
        return(null);
    }

    /**
     *	@desc	...
     */
    public String toString() {
        for (int i = 0; i < this.m_tiles.length;) {
            for (int j = 0; j < Math.sqrt(this.m_tiles.length); j++) {
                System.out.print(this.m_tiles[i++] + "\t");
            }
            System.out.println();
        }
        return(null);
    }

    /**
     *	@desc	...
     */
    public int inversions() {
        if(this.m_invers == Integer.MIN_VALUE) {
            this.m_invers = 0;
            for (int i = 0; i < this.m_tiles.length; i++) {
                for (int j = i + 1; j < this.m_tiles.length; j++) {
                    if (this.m_tiles[j] > this.m_tiles[i]) {
                        this.m_invers++;
                    }
                }
                //System.out.println("Inversions @ " + i + ": " + this.invers);
            }
        System.out.println("Total Inversions: " + this.m_invers);
        }
        return(this.m_invers);
    }

    /**
     *	@desc	...
     */
    public int zeroRow() {
        if(this.m_zeRow == Integer.MIN_VALUE) {
            this.m_zeRow = 0;
            for (int i = this.m_tiles.length - 1; i > 0; i--) {
                //this.m_zeRow++;
                if (this.m_tiles[i] == 0) {
                    this.m_zeRow = i % m_order;
                }
            }
        System.out.println("Zero @ row: " + this.m_zeRow + " from bottom");
        }

        return(this.m_zeRow);
    }
}
/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/12/20
 *	@description	...
 *
 */

package com.gdd3a.sttAlgAi.eightPuzzle;

public class Board {
    public int[] m_tiles;

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
    public Board(int [][] tiles) {
        m_tiles = new int[tiles.length * tiles.length];

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
        for (int i = 0; i < m_tiles.length;) {
            for (int j = 0; j < 3; j++) {
                System.out.print(m_tiles[i++] + "\t");
            }
            System.out.println();
        }
        return(null);
    }
}
/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/12/20
 *	@description	...
 *
 */

package com.gdd3a.sttAlgAi.eightPuzzle;

public class Board {
    public int[][] m_tiles;

    /**
     *	@desc	Board Object Constructor
     *	@param	tiles	- Two-Dimensional Array of Int values that holds the
     *                 	  default configuration of the Board.
     *	@funct	...	- ...
     */
    public Board(int [][] tiles) {
        m_tiles = new int[tiles.length][tiles[0].length];
    }

    /**
     *	@desc	...
     *	@param	...	- ...
     *			...	- ...
     *	@funct	...	- ...
     */
    public int hamming() {
        // YOUR CODE HERE
        return(0);
    }

    /**
     *	@desc	...
     *	@param	...	- ...
     *			...	- ...
     *	@funct	...	- ...
     */
    public int manhattan() {
        // YOUR CODE HERE
        return(0);
    }

    /**
     *	@desc	Compares this Board to another Board to check for equality.
     *	@param	o	- A Board to check against this Board.
     *	@funct	...	- ...
     */
    public boolean equals(Board o) {
        boolean valid = true;

        for (int i = 0; i < this.m_tiles.length; i++) {
            for (int j = 0; j < this.m_tiles[0].length; j++) {
                if(this.m_tiles[i][j] != o.m_tiles[i][j]) {
                    valid = false;
                }
            }
        }

        return(valid);
    }

    /**
     *	@desc	...
     *	@param	...	- ...
     *			...	- ...
     *	@funct	...	- ...
     */
    public Iterable<Board> neighbours() {
        // YOUR CODE HERE
        return(null);
    }

    /**
     *	@desc	...
     *	@param	...	- ...
     *			...	- ...
     *	@funct	...	- ...
     */
    public String toString() {
        for (int i = 0; i < this.m_tiles.length; i++) {
            for (int j = 0; j < this.m_tiles[0].length; j++) {
                System.out.print(this.m_tiles[i][j] + "\t");
            }
            System.out.println();
        }
        return(null);
    }
}
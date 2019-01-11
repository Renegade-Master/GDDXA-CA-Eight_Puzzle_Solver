/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/12/20
 *	@description	...
 *
 */

package AlgsAI.EightPuzzle;

//import

import java.util.PriorityQueue;

class Board implements Comparable<Board>{
    int m_order;

    private int[] m_tiles;
    private PriorityQueue<Board> m_neighbours;
    private int m_hamming;
    private int m_manhattan;
    private int m_invers;
    private int m_zeRow;

    /**
     *	Board Object Constructor.
     *
     *	@param	N	    - Order of the Puzzle.
     *	@param	tiles	- Two-Dimensional Array of Int values that holds the
     *                 	  default configuration of the Board.
     */
    Board(int N, int [][] tiles) {
        this.m_order = N;
        this.m_tiles = new int[tiles.length * tiles.length];
        this.m_neighbours = new PriorityQueue<Board>();
        this.m_hamming      = Integer.MIN_VALUE;
        this.m_manhattan    = Integer.MIN_VALUE;
        this.m_invers       = Integer.MIN_VALUE;
        this.m_zeRow        = Integer.MIN_VALUE;

        int z = 0;
        for (int[] i : tiles) {
            for (int j : i) {
                this.m_tiles[z++] = j;
                //System.out.print(m_tiles[z - 1] + "\t");
            }
            //System.out.println();
        }
    }

    /**
     *	Return count of out-of-place tiles.
     *
     *  @return  ...
     */
    int hamming() {
        this.m_hamming = 0;

        int comp = 0;
        for(int i : this.m_tiles) {
            comp++;
            if(i == 0) {
                continue;
            }
            if(i != comp) {
                this.m_hamming++;
                //System.out.println("Tile " + i + " is in the wrong Position.");
            }
            else {
                //System.out.println("Tile " + i + " is in the correct Position.");
            }
        }

        return(this.m_hamming);
    }

    /**
     *	Return Manhattan distance to Goal State.
     *
     * @return  ...
     */
    int manhattan() {
        this.m_manhattan = 0;

        int compare = 0;
        for(int i : this.m_tiles) {
            compare++;
            if(i == 0) {
                continue;
            }
            if(i != compare) {
                //System.out.println("Tile " + i + " is in the wrong Position.\n");
                /*System.out.println("\tAdding " + this.manhattanDist(compare,i)
                        + " to Board Manhattan score.");*/
                this.m_manhattan += this.manhattanDist(compare,i);
            }
            else {
                //System.out.println("Tile " + i + " is in the correct Position.");
            }
        }

        return(this.m_manhattan);
    }

    /**
     *	Compares this Board to another Board to check for equality.
     *
     *	@param	o	- A Board to check against this Board.
     *
     *  @return  ...
     */
    public boolean equals(Board o) {
        boolean valid = true;

        for (int i = 0; i < this.m_tiles.length; i++) {
            if(this.m_tiles[i] != o.m_tiles[i]) {
                valid = false;
            }
        }

        return(valid);
    }

    /**
     *	Compares this Board to another Board to check for Priority.
     *
     *	@param	o	- A Board to check against this Board.
     *
     *  @return  ...
     */
    @Override
    public int compareTo(Board o) {
        //return(Integer.compare(this.m_hamming,o.m_hamming));

        return(Integer.compare(this.m_manhattan,o.m_manhattan));
    }

    /**
     *	Return iterable of neighbouring Boards.
     *
     *  @return  ...
     */
    public Iterable<Board> neighbours() {
        // YOUR CODE HERE
        return(this.m_neighbours);
    }

    /**
     *	Return Board String representation.
     *
     *  @return  ...
     */
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append("Board:\n");
        for (int i = 0; i < this.m_tiles.length;) {
            for (int j = 0; j < this.m_order; j++) {
                temp.append(this.m_tiles[i++]).append("\t");
            }
            temp.append("\n");
        }
        //temp.append("\n");

        temp.append("\nArray:\n");
        for (int i : this.m_tiles) {
            temp.append("[").append(i).append("] ");
        }

        return(temp.append("\n").toString());
    }

    /***-------------***\
    |   Extra Methods   |
    \***-------------***/

    /**
     *	Return the number of Inversions of the Board.
     *
     *  @return  ...
     */
    int inversions() {
        if(this.m_invers == Integer.MIN_VALUE) {
            this.m_invers = 0;
            for (int i = 0; i < this.m_tiles.length; i++) {
                int added = 0;
                for (int j = i + 1; j < this.m_tiles.length; j++) {
                    if(this.m_tiles[j] != 0 && this.m_tiles[i] != 0) {
                        if (this.m_tiles[j] < this.m_tiles[i]) {
                            //this.m_invers++;
                            added++;
                        }
                    }
                }
                this.m_invers += added;
                //System.out.println("Inversions @ " + i + ": " + added);
            }
        //System.out.println("Total Inversions: " + this.m_invers);
        }
        return(this.m_invers);
    }

    /**
     *	Return the Board row containing a '0'.
     *
     *  @return The row of the Board containing the '0' tile.
     */
    int zeroRow() {
        if(this.m_zeRow == Integer.MIN_VALUE) {
            this.m_zeRow = 0;
            for (int i = this.m_tiles.length - 1; i > 0; i--) {
                //this.m_zeRow++;
                if (this.m_tiles[i] == 0) {
                    this.m_zeRow = Math.abs((int)Math.ceil((double)i
                                / (double)this.m_order) - this.m_order);
                }
            }
        //System.out.println("Zero @ row: " + this.m_zeRow + " from bottom");
        }

        return(this.m_zeRow + 1);
    }

    /**
     *	Returns the Manhattan distance this tile is away from where it
     *  should be.
     *
     *	@param	position    - Current tile position.
     *	@param  tile        - Value on the current tile space.
     *
     *  @return  The minimum number of moves required to reach the Goal tile.
     */
    private int manhattanDist(double position, double tile) {
        // Where is it?
        double oldCol = position % this.m_order;
        if(oldCol == 0) {
            oldCol = this.m_order;
        }
        double oldRow = Math.ceil(position / this.m_order);

        // Where should it go?
        double newCol = tile % this.m_order;
        if(newCol == 0) {
            newCol = this.m_order;
        }
        double newRow = Math.ceil(tile / this.m_order);

        // How long does it take to get there?
        double colDiff = Math.abs(newCol - oldCol);
        double rowDiff = Math.abs(newRow - oldRow);

        return((int)(colDiff + rowDiff));
    }
}
/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/12/20
 *	@description	...
 *
 */

package AlgsAI.EightPuzzle;

//import

public class Board {
    int m_order;
    private int[] m_tiles;

    private int m_hamming;
    private int m_manhattan;

    private int m_invers;
    private int m_zeRow;

    /**
     *	@desc	Board Object Constructor
     *	@param	tiles	- Two-Dimensional Array of Int values that holds the
     *                 	  default configuration of the Board.
     *	@funct	hamming     - Return count of out-of-place blocks.
     *          manhattan   - Return Manhattan distance to Goal State.
     *          equals      - Return Boolean for Board equality check.
     *          neighbours  - Return iterable of neighbouring Boards.
     *          toString    - Return Board String representation.
     *          inversions  - Return the number of Inversions of the Board.
     *          zeroRow     - Return the Board row containing a 0.
     */
    Board(int N, int [][] tiles) {
        this.m_order = N;
        this.m_tiles = new int[tiles.length * tiles.length];
        this.m_hamming = Integer.MIN_VALUE;
        this.m_manhattan = Integer.MIN_VALUE;
        this.m_invers = Integer.MIN_VALUE;
        this.m_zeRow = Integer.MIN_VALUE;

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
     *	@desc	Return count of out-of-place tiles.
     */
    public int hamming() {
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
     *	@desc	Return Manhattan distance to Goal State.
     */
    public int manhattan() {
        this.m_manhattan = 0;

        int comp = 0;
        for(int i : this.m_tiles) {
            comp++;
            if(i == 0) {
                continue;
            }
            if(i != comp) {
                //System.out.println("Tile " + i + " is in the wrong Position.\n");
                /*System.out.println("\tAdding " + this.manhattanDist(comp,i)
                        + " to Board Manhattan score.");*/
                this.m_manhattan += this.manhattanDist(comp,i);
            }
            else {
                //System.out.println("Tile " + i + " is in the correct Position.");
            }
        }

        return(this.m_manhattan);
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
     *	@desc	Return iterable of neighbouring Boards.
     */
    public Iterable<Board> neighbours() {
        // YOUR CODE HERE
        return(null);
    }

    /**
     *	@desc	Return Board String representation.
     */
    @Override
    public String toString() {StringBuffer temp = new StringBuffer();
        for (int i = 0; i < this.m_tiles.length;) {
            for (int j = 0; j < this.m_order; j++) {
                temp.append(this.m_tiles[i++]).append("\t");
            }
            temp.append("\n");
        }
        temp.append("\n");

        for (int i : this.m_tiles) {
            temp.append("[").append(i).append("] ");
        }

        return(temp.toString());
    }

    /**
     *	@desc	Return the number of Inversions of the Board.
     */
    public int inversions() {
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
     *	@desc	Return the Board row containing a '0'.
     */
    public int zeroRow() {
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
     *	@desc	Return the Manhattan distance this tile is away from where it
     *          should be.
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
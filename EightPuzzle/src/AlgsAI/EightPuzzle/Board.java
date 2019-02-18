/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/12/20
 *	@description	...
 *
 */

package AlgsAI.EightPuzzle;

import java.util.PriorityQueue;

class Board implements Comparable<Board> {
    enum SCORING { MANHATTAN, HAMMING }
    SCORING m_Scoring = SCORING.MANHATTAN;

    int m_order = Integer.MIN_VALUE;

    private final int[] m_tiles;
    private PriorityQueue<Board> m_neighbours;

    private int m_hamming = Integer.MIN_VALUE;
    private int m_manhattan = Integer.MIN_VALUE;
    private int m_invers = Integer.MIN_VALUE;
    private int m_zeRow = Integer.MIN_VALUE;
    private int m_zeroTile = Integer.MIN_VALUE;

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

        int z = 0;
        for (int[] i : tiles) {
            for (int j : i) {
                this.m_tiles[z++] = j;
            }
        }

        this.m_hamming      = this.hamming();
        this.m_manhattan    = this.manhattan();
        this.m_invers       = this.inversions();
        this.m_zeRow        = this.zeroRow();
    }

    /**
     *	Board Object Constructor.
     *
     *	@param	N	    - Order of the Puzzle.
     *	@param	tiles	- Two-Dimensional Array of Int values that holds the
     *                 	  default configuration of the Board.
     */
    Board(int N, int [] tiles) {
        this.m_order = N;
        this.m_tiles = new int[tiles.length];
        this.m_neighbours = new PriorityQueue<Board>();

        int z = 0;
        for (int i : tiles) {
            this.m_tiles[z++] = i;
        }

        this.m_hamming      = this.hamming();
        this.m_manhattan    = this.manhattan();
        this.m_invers       = this.inversions();
        this.m_zeRow        = this.zeroRow();
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
        for (int i = 0; i < this.m_tiles.length; i++) {
            if(this.m_tiles[i] != o.m_tiles[i]) {
                return(false);
            }
        }

        return(true);
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
        switch(this.m_Scoring) {
            case HAMMING:
                return (Integer.compare(this.m_hamming, o.m_hamming));
            case MANHATTAN:
                return (Integer.compare(this.m_manhattan, o.m_manhattan));
            default:
                return(Integer.MIN_VALUE);
        }
    }

    /**
     *	Return iterable of neighbouring Boards.  Use this to expand the
     *  child Boards of this Board.  VERY IMPORTANT
     *
     *  @return  ...
     */
    public Iterable<Board> neighbours() {
        this.m_neighbours.clear();

        // Set Default conditions
        int possibleBoards = 4;
        boolean leftSafe = false;
        boolean rightSafe = false;
        boolean upSafe = false;
        boolean downSafe = false;

        // Is 0 the First Tile?
        if(this.m_zeroTile == 0) {
            possibleBoards -= 2;
            rightSafe = true;
            downSafe = true;
        }
        // Is 0 the Last Tile?
        else if(this.m_zeroTile == (this.m_tiles.length - 1)) {
            possibleBoards -= 2;
            leftSafe = true;
            upSafe = true;
        }
        else {
            //System.out.println("--Zero Row: " + this.m_zeRow);
            //  Is the Blank on the Top Row?
            if(this.m_zeRow == this.m_order){
                possibleBoards--;
                downSafe = true;
            }
            //  Is the Blank on the Bottom Row?
            else if(this.m_zeRow == 1){
                possibleBoards--;
                upSafe = true;
            }
            //  Is the Blank on the Leftmost Column?
            else if((this.m_order % this.m_zeroTile) == 0) {
                possibleBoards--;
                rightSafe = true;
            }
            //  Is the Blank on the Rightmost Column?
            else if((this.m_order % this.m_zeroTile) == (this.m_order + 1)) {
                possibleBoards--;
                leftSafe = true;
            }

            //  The Blank is in a very safe position
            else {
                leftSafe = true;
                rightSafe = true;
                upSafe = true;
                downSafe = true;
            }
        }
        //System.out.println("Boards Possible:\t" + possibleBoards);

        // Look at this Board.  Expand Child Boards.
        int[][] newBoards = new int[possibleBoards][this.m_tiles.length];
        int newValueIndex = 0;

        for(int i = 0; i < possibleBoards; i++) {
            // Copy the current Tiles
            //int[] newTiles = getTiles();
            Board newBoard = makeNewBoard(this.m_tiles);

            // Decide which Tile to Switch
            if(leftSafe) {
                newValueIndex = this.m_zeroTile - 1;
                leftSafe = false;
            }
            else if(rightSafe) {
                newValueIndex = this.m_zeroTile + 1;
                rightSafe = false;
            }
            else if(downSafe) {
                newValueIndex = this.m_zeroTile + this.m_order;
                downSafe = false;
            }
            else if(upSafe) {
                newValueIndex = this.m_zeroTile - this.m_order;
                upSafe = false;
            }

            // Apply changes to the Tiles
            int oldValue = this.m_tiles[newValueIndex];
            newBoard.m_tiles[this.m_zeroTile] = oldValue;
            newBoard.m_tiles[newValueIndex] = 0;

            newBoards[i] = newBoard.m_tiles;
        }

        // Add new Boards to the Queue
        for(int[] tiles : newBoards) {
            this.m_neighbours.offer(makeNewBoard(tiles));
        }

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

            // Find the Zero Tile
            for (int i = 0; i < this.m_tiles.length; i++) {
                if (this.m_tiles[i] == 0) {
                    this.m_zeroTile = i;
                }
            }

            // Check edge cases
            // Is it on the Top Row?
            if (this.m_zeroTile < this.m_order) {
                this.m_zeRow = this.m_order;
                return(this.m_zeRow);
            }
            // Is it on the Bottom Row?
            else if (this.m_zeroTile >= (this.m_tiles.length - this.m_order)) {
                this.m_zeRow = 1;
                return(this.m_zeRow);
            }
            // Otherwise
            else {
                this.m_zeRow = (int)Math.ceil(
                        (float)this.m_zeroTile / (float)this.m_order);
                //System.out.println("Zero @ row: " + this.m_zeRow + " from bottom");
            }
        }

        return(this.m_zeRow);
    }

    /**
     * Returns a new Board
     *
     * @param   tiles       - The Tileset for the new Board.
     *
     * @return  A new Board
     */
    Board makeNewBoard(int[] tiles) {
        Board newBoard = new Board(this.m_order, tiles);

        return(newBoard);
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
        // Where is the Tile?
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

    /**
     * Retrieve a Tile from the Initial Board
     *
     * @param   i           - Index of Tile to retrieve
     *
     * @return  The requested value
     */
    private int getTile(int i) {
        if(i >= 0 && i <= this.m_tiles.length) {
            int temp = this.m_tiles[i];
            return (temp);
        }
        else {
            return(Integer.MIN_VALUE);
        }
    }

    /**
     * Retrieve a Tile from the Initial Board
     *
     * @return  The requested values
     */
    private int[] getTiles() {
        int[] temp = this.m_tiles;
        return(temp);
    }
}
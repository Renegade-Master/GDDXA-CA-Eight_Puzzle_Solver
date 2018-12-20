/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/11/24
 *	@description	...
 *
 */
package com.gdd3a.sttAlgAi.eightPuzzle;

import edu.princeton.cs.algs4.In;

public class Solver {

    /**
     *	@desc	Object Constructor
     *	@param	initial	- A Board object arranged in its initial positions.
     *	@funct	...	- ...
     */
    public Solver(Board initial) {
        // YOUR CODE HERE
    }

    /**
     *	@desc	...
     *	@funct	...	- ...
     */
    public boolean isSolvable() {
        // YOUR CODE HERE
        return(false);
    }

    /**
     *	@desc	...
     *	@funct	...	- ...
     */
    public int moves() {
        // YOUR CODE HERE
        return(0);
    }

    /**
     *	@desc	...
     *	@funct	...	- ...
     */
    public Iterable<Board> solution() {
        // YOUR CODE HERE
        return(null);
    }

    /**
     *	@desc	...
     *	@param	args	- Command Line arguments to the program.
     *	@funct	...	- ...
     */
    public static void main(String [] args) {
        /*  -- Old Program code from reading in Boards from the Keyboard.
        int N = StdIn.readInt();
        int [][] tiles = new int [N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = StdIn.readInt();
            }
            System.out.println();
        }
        */

        String filename = null;
        In in = new In();

        try {
            if (args.length != 0) {
                System.out.println("'" + args[0] + "' selected.\n");
                filename = args[0];
            } else {
                System.out.println("There are no arguments.  " +
                        "Running default Puzzle.\n");
                filename = "puzzle_01";
            }

            //String filename = args[0];
            in = new In("puzzles\\" + filename + ".txt");
        }catch(Exception e){
            System.out.println("No such file, or some other error has " +
                    "occurred.\nPlease check your input.");
            System.exit(1);
        }
        int N = in.readInt();
        int[][] tiles = new int [N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
                //System.out.print(tiles[i][j] + "\t");
            }
            //System.out.println();
        }

        Board initial = new Board(tiles);

        Solver solver = new Solver(initial);

        /*  -- Commented out for testing
        for (Board board : solver.solution()) {
            System.out.println(board);
        }
        */

        if(!solver.isSolvable()) {
            System.out.println("No solution possible");
        }
        else {
            System.out.println("Minimum number of moves = " + solver.moves());
        }
    }
}
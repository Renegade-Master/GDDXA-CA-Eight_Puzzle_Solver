/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/11/24
 *	@description	...
 *
 */
package com.gdd3a.sttAlgAi.eightPuzzle;

import java.lang.Math;
import edu.princeton.cs.algs4.In;


public class Solver {
    private Board m_Board;

    /**
     *	@desc	Object Constructor
     *	@param	initial	- A Board object arranged in its initial configuration.
     *	@funct	isSolvable  - ...
     *          moves       - ...
     *          solution    - ...
     */
    public Solver(Board initial) {
        m_Board = initial;
    }

    /**
     *	@desc   If the grid width is odd, then the number of inversions in a
     *          	solvable situation is even.
     *          If the grid width is even, and the blank is on an even row
     *              counting from the bottom (second-last, fourth-last etc),
     *              then the number of inversions in a solvable situation is
     *              odd.
     *          If the grid width is even, and the blank is on an odd row
     *              counting from the bottom (last, third-last, fifth-last etc)
     *              then the number of inversions in a solvable situation is
     *              even.
     *
     *          ((grid width odd) && (#inversions even)) || ((grid width even)
     *          && ((blank on odd row from bottom) == (#inversions even)))
     */
    public boolean isSolvable() {
        double sqroot = Math.sqrt(this.m_Board.m_tiles.length);
        if((int)sqroot * (int)sqroot == this.m_Board.m_tiles.length){
            System.out.println("Number of Inversion are EVEN");
        }
        return(false);
    }

    /**
     *	@desc	...
     */
    public int moves() {
        // YOUR CODE HERE
        return(0);
    }

    /**
     *	@desc	...
     */
    public Iterable<Board> solution() {
        // YOUR CODE HERE
        return(null);
    }

    /**
     *	@desc	...
     *	@param	args	- Command Line arguments to the program.
     */
    public static void main(String [] args) {
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

        System.out.println("Template:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
                System.out.print(tiles[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nAfter:");

        Board initial = new Board(tiles);

        System.out.println("\ntoString:");
        initial.toString();

        Solver solver = new Solver(initial);

        /*  -- Commented out for testing
        for (Board board : solver.solution()) {
            System.out.println(board);
        }
        */

        if(!solver.isSolvable()) {
            System.out.println("\nNo solution possible");
        }
        else {
            System.out.println("\nMinimum number of moves = " + solver.moves());
        }
    }
}
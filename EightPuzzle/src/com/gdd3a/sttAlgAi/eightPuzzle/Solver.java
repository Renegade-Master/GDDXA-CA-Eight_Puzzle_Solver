/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/11/24
 *	@description	...
 *
 */
package com.gdd3a.sttAlgAi.eightPuzzle;

import edu.princeton.cs.algs4.StdIn;

public class Solver {

    /**
     *	@desc	...
     *	@param	this	- ...
     *			...	- ...
     *	@funct	...	- ...
     */
    public Solver(Board initial) {
        // YOUR CODE HERE
    }

    /**
     *	@desc	...
     *	@param	this	- ...
     *			...	- ...
     *	@funct	...	- ...
     */
    public boolean isSolvable() {
        // YOUR CODE HERE
        return(false);
    }

    /**
     *	@desc	...
     *	@param	this	- ...
     *			...	- ...
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
        int N = StdIn.readInt();
        int [][] tiles = new int [N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //tiles[i][j] = StdIn.readInt();
                System.out.print(StdIn.readInt() + "\t");
            }
            System.out.println();
        }

        Board initial = new Board(tiles);

        Solver solver = new Solver(initial);

        for (Board board : solver.solution()) {
            System.out.println(board);
        }

        if(!solver.isSolvable()) {
            System.out.println("No solution possible");
        }
        else {
            System.out.println("Minimum number of moves = " + solver.moves());
        }
    }
}
/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/11/24
 *	@description	...
 *
 */

package AlgsAI.EightPuzzle;

import edu.princeton.cs.algs4.In;

import java.util.PriorityQueue;


public class Solver {
    private PriorityQueue<Board> m_BoardQueue;
    private int m_moves;

    /**
     *	Object Constructor
     *
     *	@param	initial	- A Board object arranged in its initial configuration.
     */
    private Solver(Board initial) {
        this.m_BoardQueue = new PriorityQueue<Board>();
        this.m_BoardQueue.offer(initial);
        this.m_moves = 0;
    }

    /**
     *	Checks to see if a Board is solvable from the initial state.
     *
     *  @return True if the Board is solvable from the initial state,
     *          False if not.
     */
    private boolean isSolvable() {
        if(this.m_BoardQueue.peek().m_order % 2 != 0) {
            System.out.println("Grid Width is ODD");
            if (this.m_BoardQueue.peek().inversions() % 2 == 0) {
                System.out.println("\tNumber of inversions is EVEN");
                return(true);
            } else {
                System.out.println("--> Number of inversions is ODD");
                return(false);
            }
        } else {
            System.out.println("Grid Width is EVEN");
            if (this.m_BoardQueue.peek().zeroRow() % 2 != 0) {
                System.out.println("\tBlank is on ODD bottom row");
                if (this.m_BoardQueue.peek().inversions() % 2 == 0) {
                    System.out.println("\t\tNumber of inversions is EVEN");
                    return(true);
                } else {
                    System.out.println("\t--> Number of inversions is ODD");
                    return(false);
                }
            } else {
                System.out.println("\tBlank is on EVEN bottom row");
                if (this.m_BoardQueue.peek().inversions() % 2 != 0) {
                    System.out.println("\t\tNumber of inversions is ODD");
                    return(true);
                } else {
                    System.out.println("\t--> Number of inversions is EVEN");
                    return(false);
                }
            }
        }
    }

    /**
     *	Calculate the minimum number of m_moves required to reach the Goal State.
     *  Majority of Program compute here.
     *
     *  @return The number of m_moves required to reach the Goal State.
     */
    private int moves() {
        System.out.println("\nCalculating...");
        Board nextBoard = this.m_BoardQueue.peek();

        // Stay in this function until Solution is found
        while (nextBoard.inversions() != 0) {
            // Potential Boards
            /*System.out.println("\nPotential Moves:");
            for (Board board : this.m_BoardQueue.peek().neighbours()) {
                System.out.println("---");
                System.out.println(board.toString());
                System.out.println("Manhattan:\t" + board.manhattan());
                System.out.println("Hamming:\t" + board.hamming());
            }*/
            //this.m_BoardQueue.offer(this.m_BoardQueue.peek().neighbours());
            //nextBoard = this.m_BoardQueue.peek().neighbours();

            nextBoard = nextBoard.neighbours().iterator().next();
            //System.out.println(nextBoard.toString());

            this.m_moves++;
        }

        System.out.println("\nSolution Found:\n" + nextBoard.toString());

        return(this.m_moves);
    }

    /**
     *  ...
     *
     *  @return ...
     */
    private Iterable<Board> solution() {
        /*
        Apparently simply returning a reference to the list of Boards is
        enough.
        */

        return(this.m_BoardQueue);
    }

    /**
     *  Main method called when program is run.
     *
     *	@param	args	- Command Line arguments to the program.  Should be a
     *                 	  filename from the '.\Puzzles\' directory.
     */
    public static void main(String [] args) {
        String filename;
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
            System.exit(-1);
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

        Board initial = new Board(N, tiles);
        System.out.println("Initial Board:\n" + initial.toString());

        Solver solver = new Solver(initial);

        for (Board board : solver.solution()) {
            //System.out.println(board.toString());
        }

        if(!solver.isSolvable()) {
            System.out.println("\nNo Solution possible");
        }
        else {
            System.out.println("\nSolution possible");

            initial.m_Scoring = Board.SCORING.HAMMING;
            System.out.println("Initial Hamming Score: " + initial.hamming());

            initial.m_Scoring = Board.SCORING.MANHATTAN;
            System.out.println("Initial Manhattan Score: " + initial.manhattan());

            System.out.println("Minimum number of Moves = " + solver.moves());
        }
    }
}
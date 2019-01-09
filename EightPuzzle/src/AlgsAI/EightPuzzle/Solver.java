/**
 *	@author			Ciaran Bent [K00221230]
 *	@creationDate	2018/11/24
 *	@description	...
 *
 */

package AlgsAI.EightPuzzle;

import java.util.ArrayList;
import java.util.Iterator;
import edu.princeton.cs.algs4.In;


public class Solver implements Iterable<Board> {
    private ArrayList<Board> m_BoardList;

    /**
     *	Object Constructor
     *
     *	@param	initial	- A Board object arranged in its initial configuration.
     */
    private Solver(Board initial) {
        this.m_BoardList = new ArrayList<Board>();
        this.m_BoardList.add(initial);
    }

    /**
     *	Checks to see if a Board is solvable from the initial state.
     *
     *  @return True if the Board is solvable from the initial state,
     *          False if not.
     */
    private boolean isSolvable() {
        if(this.m_BoardList.get(0).m_order % 2 != 0) {
            System.out.println("Grid Width is ODD");
            if (this.m_BoardList.get(0).inversions() % 2 == 0) {
                System.out.println("\tNumber of inversions is EVEN");
                return(true);
            } else {
                System.out.println("--> Number of inversions is ODD");
                return(false);
            }
        } else {
            System.out.println("Grid Width is EVEN");
            if (this.m_BoardList.get(0).zeroRow() % 2 != 0) {
                System.out.println("\tBlank is on ODD bottom row");
                if (this.m_BoardList.get(0).inversions() % 2 == 0) {
                    System.out.println("\t\tNumber of inversions is EVEN");
                    return(true);
                } else {
                    System.out.println("\t--> Number of inversions is ODD");
                    return(false);
                }
            } else {
                System.out.println("\tBlank is on EVEN bottom row");
                if (this.m_BoardList.get(0).inversions() % 2 != 0) {
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
     *	Calculate the minimum number of moves required to reach the Goal State.
     *
     *  @return ...
     */
    private int moves() {
        // YOUR CODE HERE
        return(Integer.MIN_VALUE);
    }

    /**
     *  ...
     *
     *  @return ...
     */
    public Iterable<Board> solution() {
        // YOUR CODE HERE
        return(null);
    }

    /**
     *  ...
     *
     *  @return ...
     */
    public Iterator<Board> iterator() {
        return m_BoardList.iterator();
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
        System.out.println("Template:\n" + initial.toString() + "\n");

        Solver solver = new Solver(initial);

        //  -- Commented out for testing
        /*for (Board board : solver.solution()) {
            System.out.println(board.toString());
        }*/
        if(!solver.isSolvable()) {
            System.out.println("\nNo solution possible");
        }
        else {
            System.out.println("\nSolution possible");
            System.out.println("Minimum number of moves = " + solver.moves());

            System.out.println("Initial Hamming Score: " + initial.hamming());
            System.out.println("Initial Manhattan Score: " + initial.manhattan());
        }
    }
}
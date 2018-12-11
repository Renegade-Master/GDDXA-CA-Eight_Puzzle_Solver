/**
*	@author			Ciaran Bent [K00221230]
*	@creationDate	2018/11/24
*	@description	...
*
*/

public class Solver {
	
	/**
	*	@desc	...
	*	@param	...	- ...
	*			...	- ...
	*	@funct	...	- ...
	*/
	public Solver(Board initial) {
		// YOUR CODE HERE
	}
	
	/**
	*	@desc	...
	*	@param	...	- ...
	*			...	- ...
	*	@funct	...	- ...
	*/
	public boolean isSolvable() {
		// YOUR CODE HERE
		return(false);
	}
	
	/**
	*	@desc	...
	*	@param	...	- ...
	*			...	- ...
	*	@funct	...	- ...
	*/
	public int moves() {
		// YOUR CODE HERE
		return(0);
	}
	
	/**
	*	@desc	...
	*	@param	...	- ...
	*			...	- ...
	*	@funct	...	- ...
	*/
	public Iterable<Board> solution() {
		// YOUR CODE HERE
		return(null);
	}
	
	/**
	*	@desc	...
	*	@param	...	- ...
	*			...	- ...
	*	@funct	...	- ...
	*/
	public static void main(String [] args) {
		int N = StdIn.readInt();
		int [][] tiles = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tiles[i][j] = StdIn.readInt();
			}
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
			System.out.println("Mininimum number of moves = " + solver.moves());
		}
	}
}
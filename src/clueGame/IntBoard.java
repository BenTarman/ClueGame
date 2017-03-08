package clueGame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard
{

	private Set<BoardCell> visited = new HashSet<BoardCell>();
	private Set<BoardCell> target = new HashSet<BoardCell>();
	private BoardCell[][] grid;

	private Map<Character, String> legend = new HashMap<Character, String>();

	private int row = 0, column = 0;


	public IntBoard(int rows, int columns) {
		super();

		
		
		grid = new BoardCell[rows][columns];

		for(int i = 0; i < rows; i++)
		{
			row++;
			grid[i] = new BoardCell[columns];
			
			for(int j = 0; j < columns; j++)
			{
				if(i == 0)
				{
					column++;
				}
				grid[i][j] = new BoardCell(i, j);
			}
		}
	}
	public Set<BoardCell> getTargets()
	{	
		return target;
	}
	
	
	public IntBoard()
	{
		// TODO Auto-generated constructor stub
	}

	public BoardCell getCell(int row, int col)
	{
		return grid[row][col];
	}
	
	
	public Set<BoardCell> getAdjList(BoardCell cell)
	{
		Set<BoardCell> adj = new HashSet<BoardCell>();

		// Left
		if(cell.getCol() > 0)
		{
			adj.add(grid[cell.getRow()][cell.getCol() - 1]);

		}

		// Right
		if(cell.getCol() < grid[0].length - 1)
		{
			adj.add(grid[cell.getRow()][cell.getCol() + 1]);
		}

		// Up
		if(cell.getRow() > 0)
		{
			adj.add(grid[cell.getRow() - 1][cell.getCol()]);
		}

		// Down
		if(cell.getRow() < grid.length - 1)
		{
			adj.add(grid[cell.getRow() + 1][cell.getCol()]);
		}



		return adj;
	}

	public void calcTargets(BoardCell startCell, int pathLength)
	{
		visited.add(startCell); //add initial location

		Set<BoardCell> adjList = getAdjList(startCell);	//access Set list direclty over adjMtx

		for (BoardCell s : adjList)
		{
			if (visited.contains(s))
				continue; //go to next in list if already visited
			else
				visited.add(s);

			if (pathLength == 1)
			{
				target.add(s);

			}
			else
				calcTargets(s, pathLength - 1);

			visited.remove(s);	//remove at end.

		}
	}
	
}

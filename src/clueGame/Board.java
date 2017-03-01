package clueGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clueGame.BoardCell;

public class Board {
	 private static Map<BoardCell, Set<BoardCell>> adjMtx;
	 private Set<BoardCell> visited;
	 private Set<BoardCell> target;
	 private BoardCell[][] grid;
	 
	 private int row, column;
	 
	public Board(int rows, int columns) {
		super();
		target = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();	 
		grid = new BoardCell[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			grid[i] = new BoardCell[columns];
			
			for(int j = 0; j < columns; j++)
			{
				grid[i][j] = new BoardCell(i, j);
			}
		}
	}
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// ctor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void setConfigFiles(String gameBoardFile, String gameCardsFile)
	{
		
	}
	
	public void initialize()
	{
	
	}
	
	public int getNumRows()
	{
		return row;
	}
	
	public int getNumColumns()
	{
		return column;
	}
	
	public void calcAdjacencies()
	{
		
	}
	 
	public BoardCell getCellAt(int r, int c)
	{
		BoardCell cell = new BoardCell(0,0);
		
		
		return cell;
	}
	
	public Set<BoardCell> getTargets()
	{	
	
		return target;
	}
	
	public Map<Character, String> getLegend()
	{
		return null;
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
		
		adjMtx.put(cell, adj);
	
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
package clueGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.Assert;

import clueGame.BoardCell;

public class Board {
	private String gameBoardFileTest, gameCardsFileTest;  //maybe termporary variables for testing
	
	private static Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> target;
	private BoardCell[][] grid;

	private Map<Character, String> legend = new HashMap<Character, String>();

	private int row = 0, column = 0;

	public Board(int rows, int columns) {
		super();

		target = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();	 
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
		//temporry setting for tests
		gameBoardFileTest = gameBoardFile;
		gameCardsFileTest = gameCardsFile;
		
		
		String line = "";
		String[] data = null;
		String delimiter = ", ";
		File file = new File("data/" + gameCardsFile);
		Scanner read = null;
		
		try
		{
			read = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (read.hasNextLine())
		{
			line = read.nextLine();
			data = line.split(delimiter);
			legend.put(data[0].charAt(0), data[1]);
		}

		line = "";
		delimiter = ",";
		data = null;
		file = new File("data/" + gameBoardFile);
		
		try
		{
			read = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		grid = new BoardCell[100][100];
		
		while(read.hasNextLine())
		{
			line = read.nextLine();
			data = line.split(delimiter);
			column = data.length;
			
			grid[row] = new BoardCell[column];
			
			for(int j = 0; j < column; j++)
			{	
				grid[row][j] = new BoardCell(row, j);
				grid[row][j].SetRoomType(data[j]);
				
			}
			row++;
		}
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
		BoardCell cell = grid[r][c];

		return cell;
	}

	public Set<BoardCell> getTargets()
	{	
		return target;
	}

	public Map<Character, String> getLegend()
	{
		return legend;
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

	public static void main(String[] args)
	{
		

	
	}
	public void loadRoomConfig() throws BadConfigFormatException
	{
		String line = "";
		String[] data = null;
		String delimiter = ", ";
		File file = new File("data/" + gameCardsFileTest);
		Scanner read = null;
		
		try
		{
			read = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (read.hasNextLine())
		{
			line = read.nextLine();
			data = line.split(delimiter);
			if (data[2] != "Other" || data[2] != "Card")
				throw new BadConfigFormatException("room type is not 'Other' or 'Card'");
		}
		
	}
	public void loadBoardConfig() throws BadConfigFormatException
	{
		String line = "";
		String[] data = null;
		String delimiter = ", ";
		File file = new File("data/" + gameBoardFileTest);
		Scanner read = null;
		
		try
		{
			read = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (read.hasNextLine())
		{
			line = read.nextLine();
			data = line.split(delimiter);
			
			if( data.length != column)
				throw new BadConfigFormatException("not consistent columsn and rows");
		}
		
	}
	
}
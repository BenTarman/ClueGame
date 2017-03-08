package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.BoardCell;
import clueGame.IntBoard;

public class IntBoardTest {

	IntBoard board;
	@Before
	public void setup()
	{
		 board = new IntBoard(4, 4);
	
	}
	
	@Test
	public void testAdjacency()
	{

		
		//top left
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		
		testList.clear();
		
		//bottom right
		BoardCell cell2 = board.getCell(3,3);
		testList = board.getAdjList(cell2);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
		
		testList.clear();
		
		//right edge
		BoardCell cell3 = board.getCell(1,3);
		testList = board.getAdjList(cell3);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertEquals(3, testList.size());
		
		testList.clear();
		
		//left corner
		BoardCell cell4 = board.getCell(3,0);
		testList = board.getAdjList(cell4);
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(2, testList.size());
		
		
	testList.clear();
		
		//second column middle of the grid
		BoardCell cell5 = board.getCell(1,1);
		testList = board.getAdjList(cell5);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertEquals(4, testList.size());
		
	testList.clear();
		
		//second from last column middlfe of the grid
		BoardCell cell6 = board.getCell(2,2);
		testList = board.getAdjList(cell6);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(4, testList.size());
		
	
		
	}
	

	@Test
	public void testTargets1()
	{


		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));

	}

	@Test
	public void testTargets2()
	{


		BoardCell cell2 = board.getCell(1, 0);
		board.calcTargets(cell2, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(3, 0)));

	}

	@Test
	public void testTargets3()
	{	
		BoardCell cell3 = board.getCell(0, 3);
		board.calcTargets(cell3, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(3, 3)));
	}





	@Test
	public void testTargets4()
	{
		BoardCell cell4 = board.getCell(0, 2);
		board.calcTargets(cell4, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));

	}		

	@Test
	public void testTargets5()
	{

		BoardCell cell5 = board.getCell(2, 1);
		board.calcTargets(cell5, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(2, 3)));

	}	
	
	@Test
	public void testTargets6()
	{
		BoardCell cell6 = board.getCell(3, 3);
		board.calcTargets(cell6, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(3, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));

	}





		
	}
	
	


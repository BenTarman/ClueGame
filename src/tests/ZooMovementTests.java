package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class ZooMovementTests {
	static Board board;

	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ZooLayout.csv", "ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	@Test
	public void testAdjacency()
	{
		// Only walkways
		BoardCell cell = board.getCell(17, 12);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(17, 13)));
		assertTrue(testList.contains(board.getCell(17, 11)));
		assertTrue(testList.contains(board.getCell(18, 12)));
		assertTrue(testList.contains(board.getCell(16, 12)));
		assertEquals(4, testList.size());

		testList.clear();
		cell = null;

		// Within room
		cell = board.getCell(19, 1);
		testList = board.getAdjList(cell);
		assertEquals(0, testList.size());

		testList.clear();
		cell = null;

		// Left edge
		cell = board.getCell(5, 0);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(6, 0)));
		assertTrue(testList.contains(board.getCell(5, 1)));
		assertEquals(2, testList.size());

		testList.clear();
		cell = null;

		// Top edge
		cell = board.getCell(0, 18);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 17)));
		assertTrue(testList.contains(board.getCell(1, 18)));
		assertEquals(2, testList.size());

		testList.clear();
		cell = null;

		// Right edge
		cell = board.getCell(12, 22);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(11, 22)));
		assertTrue(testList.contains(board.getCell(12, 21)));
		assertEquals(2, testList.size());

		testList.clear();
		cell = null;

		// Bottom edge
		cell = board.getCell(21, 13);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(21, 12)));
		assertTrue(testList.contains(board.getCell(20, 13)));
		assertEquals(2, testList.size());

		testList.clear();
		cell = null;

		// Beside a room cell that is not a doorway
		cell = board.getCell(8, 18);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(9, 18)));
		assertTrue(testList.contains(board.getCell(7, 18)));
		assertTrue(testList.contains(board.getCell(8, 17)));
		assertEquals(3, testList.size());

		testList.clear();
		cell = null;

		// Beside a room cell that is not a doorway
		cell = board.getCell(18, 4);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(17, 4)));
		assertTrue(testList.contains(board.getCell(18, 5)));
		assertEquals(2, testList.size());

		testList.clear();
		cell = null;

		// Next to door (left)
		cell = board.getCell(1, 4);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 4)));
		assertTrue(testList.contains(board.getCell(2, 4)));
		assertTrue(testList.contains(board.getCell(1, 3)));
		assertTrue(testList.contains(board.getCell(1, 5)));
		assertEquals(4, testList.size());

		testList.clear();
		cell = null;

		// Next to door (right)
		cell = board.getCell(3, 17);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 17)));
		assertTrue(testList.contains(board.getCell(4, 17)));
		assertTrue(testList.contains(board.getCell(3, 16)));
		assertTrue(testList.contains(board.getCell(3, 18)));
		assertEquals(4, testList.size());

		testList.clear();
		cell = null;

		// Next to door (up)
		cell = board.getCell(18, 7);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(18, 6)));
		assertTrue(testList.contains(board.getCell(18, 8)));
		assertTrue(testList.contains(board.getCell(17, 7)));
		assertTrue(testList.contains(board.getCell(19, 7)));
		assertEquals(4, testList.size());

		testList.clear();
		cell = null;

		// Next to door (down)
		cell = board.getCell(5, 9);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(5, 8)));
		assertTrue(testList.contains(board.getCell(5, 10)));
		assertTrue(testList.contains(board.getCell(4, 9)));
		assertTrue(testList.contains(board.getCell(6, 9)));
		assertEquals(4, testList.size());

		testList.clear();
		cell = null;

		// Room exit (door)
		cell = board.getCell(11, 6);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(11, 5)));
		assertEquals(1, testList.size());

		testList.clear();
		cell = null;

		// Room exit (door)
		cell = board.getCell(2, 13);
		testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 12)));
		assertEquals(1, testList.size());

		testList.clear();
		cell = null;
	}


	@Test
	public void testTargets()
	{
		// Walkway 1
		BoardCell cell = board.getCell(6, 2);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(5, 1)));
		assertTrue(targets.contains(board.getCell(6, 0)));
		assertTrue(targets.contains(board.getCell(5, 3)));
		assertTrue(targets.contains(board.getCell(6, 4)));

		targets.clear();
		cell = null;

		// Walkway 2 (Can enter room as well)
		cell = board.getCell(21, 17);
		board.calcTargets(cell, 5);
		targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(19, 18)));
		assertTrue(targets.contains(board.getCell(18, 17)));
		assertTrue(targets.contains(board.getCell(16, 17)));
		assertTrue(targets.contains(board.getCell(17, 18)));
		assertTrue(targets.contains(board.getCell(21, 18)));
		assertTrue(targets.contains(board.getCell(18, 19)));
		assertTrue(targets.contains(board.getCell(19, 19)));

		targets.clear();
		cell = null;

		// Walkway 3
		cell = board.getCell(17, 13);
		board.calcTargets(cell, 3);
		targets = board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCell(17, 10)));
		assertTrue(targets.contains(board.getCell(18, 11)));
		assertTrue(targets.contains(board.getCell(16, 11)));
		assertTrue(targets.contains(board.getCell(15, 12)));
		assertTrue(targets.contains(board.getCell(14, 13)));
		assertTrue(targets.contains(board.getCell(15, 14)));
		assertTrue(targets.contains(board.getCell(20, 13)));
		assertTrue(targets.contains(board.getCell(19, 12)));
		assertTrue(targets.contains(board.getCell(17, 12)));
		assertTrue(targets.contains(board.getCell(16, 13)));
		assertTrue(targets.contains(board.getCell(18, 13)));

		// Walkway 4
		cell = board.getCell(9, 12);
		board.calcTargets(cell, 2);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(7, 12)));
		assertTrue(targets.contains(board.getCell(8, 11)));
		assertTrue(targets.contains(board.getCell(10, 11)));
		assertTrue(targets.contains(board.getCell(11, 12)));

		targets.clear();
		cell = null;

		// Can enter room
		cell = board.getCell(15, 15);
		board.calcTargets(cell, 1);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(15, 14)));
		assertTrue(targets.contains(board.getCell(15, 16)));
		assertTrue(targets.contains(board.getCell(14, 15)));
		assertTrue(targets.contains(board.getCell(16, 15)));

		targets.clear();
		cell = null;

		// Leaving a room
		cell = board.getCell(13, 19);
		board.calcTargets(cell, 2);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(11, 19)));
		assertTrue(targets.contains(board.getCell(12, 20)));
		assertTrue(targets.contains(board.getCell(12, 18)));

		targets.clear();
		cell = null;

		// Leaving a room 2
		cell = board.getCell(2, 3);
		board.calcTargets(cell, 1);
		targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCell(2, 4)));

		targets.clear();
		cell = null;
	}

}

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
		Set<BoardCell> testList = board.getAdjList(17, 12);
		assertTrue(testList.contains(board.getCellAt(17, 13)));
		assertTrue(testList.contains(board.getCellAt(17, 11)));
		assertTrue(testList.contains(board.getCellAt(18, 12)));
		assertTrue(testList.contains(board.getCellAt(16, 12)));
		assertEquals(4, testList.size());
		testList.clear();
		
		// Within room
		testList = board.getAdjList(19, 1);
		assertEquals(0, testList.size());
		testList.clear();

		// Left edge
		testList = board.getAdjList(5, 0);
		assertTrue(testList.contains(board.getCellAt(6, 0)));
		assertTrue(testList.contains(board.getCellAt(5, 1)));
		assertEquals(2, testList.size());
		testList.clear();

		// Top edge
		testList = board.getAdjList(0, 18);
		assertTrue(testList.contains(board.getCellAt(0, 17)));
		assertTrue(testList.contains(board.getCellAt(1, 18)));
		assertEquals(2, testList.size());
		testList.clear();

		// Right edge
		testList = board.getAdjList(12, 22);
		assertTrue(testList.contains(board.getCellAt(11, 22)));
		assertTrue(testList.contains(board.getCellAt(12, 21)));
		assertEquals(2, testList.size());
		testList.clear();


		// Bottom edge
		testList = board.getAdjList(21, 13);
		assertTrue(testList.contains(board.getCellAt(21, 12)));
		assertTrue(testList.contains(board.getCellAt(20, 13)));
		assertEquals(2, testList.size());
		testList.clear();

		// Beside a room cell that is not a doorway
		testList = board.getAdjList(8, 18);
		assertTrue(testList.contains(board.getCellAt(9, 18)));
		assertTrue(testList.contains(board.getCellAt(7, 18)));
		assertTrue(testList.contains(board.getCellAt(8, 17)));
		assertEquals(3, testList.size());
		testList.clear();


		// Beside a room cell that is not a doorway
		testList = board.getAdjList(18, 4);
		assertTrue(testList.contains(board.getCellAt(17, 4)));
		assertTrue(testList.contains(board.getCellAt(18, 5)));
		assertEquals(2, testList.size());
		testList.clear();


		// Next to door (left)
		testList = board.getAdjList(1, 4);
		assertTrue(testList.contains(board.getCellAt(0, 4)));
		assertTrue(testList.contains(board.getCellAt(2, 4)));
		assertTrue(testList.contains(board.getCellAt(1, 3)));
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertEquals(4, testList.size());
		testList.clear();

		// Next to door (right)
		testList = board.getAdjList(3, 17);
		assertTrue(testList.contains(board.getCellAt(2, 17)));
		assertTrue(testList.contains(board.getCellAt(4, 17)));
		assertTrue(testList.contains(board.getCellAt(3, 16)));
		assertTrue(testList.contains(board.getCellAt(3, 18)));
		assertEquals(4, testList.size());
		testList.clear();
		
		// Next to door (up)
		testList = board.getAdjList(18, 7);
		assertTrue(testList.contains(board.getCellAt(18, 6)));
		assertTrue(testList.contains(board.getCellAt(18, 8)));
		assertTrue(testList.contains(board.getCellAt(17, 7)));
		assertTrue(testList.contains(board.getCellAt(19, 7)));
		assertEquals(4, testList.size());
		testList.clear();

		// Next to door (down)
		testList = board.getAdjList(5, 9);
		assertTrue(testList.contains(board.getCellAt(5, 8)));
		assertTrue(testList.contains(board.getCellAt(5, 10)));
		assertTrue(testList.contains(board.getCellAt(4, 9)));
		assertTrue(testList.contains(board.getCellAt(6, 9)));
		assertEquals(4, testList.size());
		testList.clear();


		// Room exit (door)
		testList = board.getAdjList(11, 6);
		assertTrue(testList.contains(board.getCellAt(11, 5)));
		assertEquals(1, testList.size());
		testList.clear();

		// Room exit (door)
		testList = board.getAdjList(2, 13);
		assertTrue(testList.contains(board.getCellAt(2, 12)));
		assertEquals(1, testList.size());
		testList.clear();
	}


	@Test
	public void testTargets()
	{
		// Walkway 1
		board.calcTargets(6, 2, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 1)));
		assertTrue(targets.contains(board.getCellAt(6, 0)));
		assertTrue(targets.contains(board.getCellAt(5, 3)));
		assertTrue(targets.contains(board.getCellAt(6, 4)));
		targets.clear();

		// Walkway 2 (Can enter room as well)
		board.calcTargets(21, 17, 5);
		targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCellAt(19, 18)));
		assertTrue(targets.contains(board.getCellAt(18, 17)));
		assertTrue(targets.contains(board.getCellAt(16, 17)));
		assertTrue(targets.contains(board.getCellAt(17, 18)));
		assertTrue(targets.contains(board.getCellAt(21, 18)));
		assertTrue(targets.contains(board.getCellAt(18, 19)));
		assertTrue(targets.contains(board.getCellAt(19, 19)));
		assertTrue(targets.contains(board.getCellAt(20, 17)));
		targets.clear();

		// Walkway 3
		board.calcTargets(17, 13, 3);
		targets = board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCellAt(17, 10)));
		assertTrue(targets.contains(board.getCellAt(18, 11)));
		assertTrue(targets.contains(board.getCellAt(16, 11)));
		assertTrue(targets.contains(board.getCellAt(15, 12)));
		assertTrue(targets.contains(board.getCellAt(14, 13)));
		assertTrue(targets.contains(board.getCellAt(15, 14)));
		assertTrue(targets.contains(board.getCellAt(20, 13)));
		assertTrue(targets.contains(board.getCellAt(19, 12)));
		assertTrue(targets.contains(board.getCellAt(17, 12)));
		assertTrue(targets.contains(board.getCellAt(16, 13)));
		assertTrue(targets.contains(board.getCellAt(18, 13)));
		targets.clear();

		// Walkway 4
		board.calcTargets(9, 12, 2);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 12)));
		assertTrue(targets.contains(board.getCellAt(8, 11)));
		assertTrue(targets.contains(board.getCellAt(10, 11)));
		assertTrue(targets.contains(board.getCellAt(11, 12)));
		targets.clear();

		// Can enter room
		board.calcTargets(15, 15, 1);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 14)));
		assertTrue(targets.contains(board.getCellAt(15, 16)));
		assertTrue(targets.contains(board.getCellAt(14, 15)));
		assertTrue(targets.contains(board.getCellAt(16, 15)));
		targets.clear();

		// Leaving a room
		board.calcTargets(13, 19, 2);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(11, 19)));
		assertTrue(targets.contains(board.getCellAt(12, 20)));
		assertTrue(targets.contains(board.getCellAt(12, 18)));
		targets.clear();

		// Leaving a room 2
		board.calcTargets(2, 3, 1);
		targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(2, 4)));
		targets.clear();

	}

}

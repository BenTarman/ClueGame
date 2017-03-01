package clueGame;


public class BoardCell {
	private int row, col;


	public BoardCell(int r, int c)
	{
		row = r;
		col = c;
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
	
		return col;
	}
	
	public boolean isDoorway()
	{
		return false;
	}
	
	public DoorDirection getDoorDirection()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object getInitial()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
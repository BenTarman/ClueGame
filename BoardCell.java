package clueGame;


public class BoardCell {
	private int row, col;
	private String room_type = "";

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
	
	public void SetRoomType(String type)
	{
		room_type = type;
	}
	
	public String getRoomType()
	{
		return room_type;
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
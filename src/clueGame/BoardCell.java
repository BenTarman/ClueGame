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
		
		if(room_type.length() == 2)
		{
			if (room_type.charAt(1) == 'N')
				return false;
			else
				return true;
		}
		return false;
	}
	
	public DoorDirection getDoorDirection()
	{
		if (room_type.charAt(1) == 'R')
			return  DoorDirection.RIGHT;
		if (room_type.charAt(1) == 'L')
			return  DoorDirection.LEFT;
		if (room_type.charAt(1) == 'D')
			return  DoorDirection.DOWN;
		if (room_type.charAt(1) == 'U')
			return  DoorDirection.UP;
		
		return DoorDirection.NONE;
	}

	public char getInitial()
	{
		return room_type.charAt(0);
	}

	

}
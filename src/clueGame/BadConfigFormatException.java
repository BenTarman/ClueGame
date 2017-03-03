package clueGame;

//MAKE THESE METHODS JUST NEEDS CONSTRUCTORS
public class BadConfigFormatException extends Exception
{
	String message;

	public BadConfigFormatException()
	{
		super("Not a valid file for configuration");
	}

	public BadConfigFormatException(String message)
	{
		super();
		this.message = message;
	}
	
	
}

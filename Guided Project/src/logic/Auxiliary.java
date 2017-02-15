package logic;

public abstract class Auxiliary {

	public static char identifierSwitch(int identifier)
	{
		switch(identifier)
		{
		case 0:
		{
			return ' ';
		}
		case 1:
		{
			return 'X';
		}
		case 2:
		{
			return 'H';
		}
		case 3:
		{
			return 'G';
		}
		case 4:
		{
			return 'I';
		}
		case 5:
		{
			return 'S';
		}
		case 6:
		{
			return 'I';
		}
		case 7:
		{
			return 'S';
		}
		case 8:
		{
			return 'k';
		}
		case 9:
		{
			return 'k';
		}
		case 10:
		{
			return 'O';
		}
		default:
		{
			return ' ';
		}
		}
	}
}

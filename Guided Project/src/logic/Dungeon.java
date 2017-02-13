package logic;

public class Dungeon {
	
	private int height;
	private int width;
	private static char dungeon[][];
	
	//Default Dungeon (used for early tests)
	static char[][] defaultDungeon = 
		{{'X','X','X','X','X','X','X','X','X','X'},
		 {'X','H',' ',' ','I',' ','X',' ','G','X'},
		 {'X','X','X',' ','X','X','X',' ',' ','X'},
		 {'X',' ','I',' ','I',' ','X',' ',' ','X'},
		 {'X','X','X',' ','X','X','X',' ',' ','X'},
		 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		 {'X','X','X',' ','X','X','X','X',' ','X'},
		 {'X',' ','I',' ','I',' ','X','k',' ','X'},
		 {'X','X','X','X','X','X','X','X','X','X'}};
	
	
	public Dungeon()
	{
		dungeon = defaultDungeon;
		height = defaultDungeon.length;	//generic defaultDungeon height
		width = defaultDungeon[0].length;	//length of the first line of the defaultDungeon; generic since defaultDungeon always has the same width
	}

	
	public String printDungeonString()
	{
		String dungeonString = "";
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				dungeonString += dungeon[i][j] + " ";
			}
			dungeonString += "\n";
		}
		return dungeonString;
	}
	
}

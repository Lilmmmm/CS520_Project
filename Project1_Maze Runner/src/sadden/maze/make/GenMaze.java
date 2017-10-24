package sadden.maze.make;

public class GenMaze {

	public Maze maze;
	//there should be four terms to determine the difficulty of a maze
	public int ShortestPath;
	public int TotalNode;
	public int MaxF;
	public double RunTime;
	 
	public double score;
	
	public DFS dfs;
	public BFS bfs;
	public Astar A;
	

	public GenMaze(Maze m)
	{
		maze = m;
		ShortestPath = 0;
		TotalNode = 0;
		MaxF = 0;
		RunTime = 0;
		
		dfs = new DFS(maze);
		bfs = new BFS(maze);
		A = new Astar(maze);
		
		
	}
	
	public void TestMaze()
	{
		
	}
	public void GenerateMaze()
	{
		boolean isOut;
		isOut = bfs.doBFS3();
		if(isOut)
		{
			//there is a way out 
			ShortestPath = bfs.ShortestPath;
			TotalNode = bfs.TotalNode;
			MaxF = bfs.MaxF;
			RunTime = bfs.RunTime;
			
			showHard();
			
			
			
		}
		else
		{
			//there is no way out
			//bad maze
			//System.out.println("There is no way out");
			
			return;
		}
	}
	
	public void showHard()
	{

		
		//System.out.println("ShortestPath: "+ ShortestPath);
		//System.out.println("TotalNode: "+ TotalNode);
		//System.out.println("MaxF: "+ MaxF);
		//System.out.println("RunTime: "+ RunTime);
		score = Hardness_BFS();
		
		
	}
	
	public double Hardness_BFS()
	{
		double score = 0;
		score= ShortestPath+TotalNode/100+MaxF/100+RunTime/1000;
		
		return score;
	}
	
	
	public void SimpleClimb()
	{
		int time = 30;
		while(time>0)
		{
		Maze newmaze = maze;

		time--;
		}
		
	}
	
	
}

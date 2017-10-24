package sadden.maze.make;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//System.out.println("begin the test");
		//System.out.println("----------------------------");
		int j = 0;
		int[] giRecord;
		boolean bSuccess;
		boolean bBreak = false;
		
		HardMaze cMaze = new HardMaze();
		cMaze.SearchHardMazeForDFS(1);
		
//
//		for (int i = 0; i < 5; i++)
//		{
//			Maze cMaze = new Maze(7000, 0.22);
//			DFS cDfs   = new DFS(cMaze);
//			bSuccess   = cDfs.doDFS2();
//		
//			cDfs.showRunningTime();
//		}

//		
//		giRecord = new int[12];
//		for (double p = 0.1; p <= 0.9; p += 0.1)
//		{
//			for (int i = 0; i < 10000; i++)
//			{
//				Maze maze = new Maze(100, p);
//				Astar cA = new Astar(maze);
//				bSuccess = cA.doAstarManhanttan2();
//				if (true == bSuccess)
//				{
////					dfs.showRunningTime();
//					int iTemp = (int)(p * 10 - 1);
//					giRecord[iTemp]++;
//				}
//				else
//				{
////					dfs.showRunningTime();
////					//System.out.println("Fail to find the exit");
//				}
//			}
//		}
//		
//		double it = 0.1;
//		for (int iShow: giRecord)
//		{
//			
//			//System.out.println(it + " success: " + iShow);
//			it += 0.1;
//		}
			
		
//		Astar A = new Astar(maze);
//		A.doAstarManhattan();
//		
//		
//		GenMaze gen = new GenMaze(maze);
//		gen.GenerateMaze();
			
		
		//System.out.println("------------------------------");
		//System.out.println("End the test");
		
		
	}

}

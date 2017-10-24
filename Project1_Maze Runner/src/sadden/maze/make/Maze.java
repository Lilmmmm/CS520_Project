package sadden.maze.make;

import java.util.Random;

public class Maze {

	
	public int size;
	public double p;
	public int [][] maze;
	
	public Maze(int Size, double P)
	{
		this.size = Size;
		this.p = P;
		MakeMaze();
	}
	
	public void MakeMaze()
	{
//		//System.out.println("make maze");
		maze = new int[size][size];
		for( int i = 0; i<size;i++)
		{
			for(int j =0;j<size;j++)
			{
				Random random = new Random();
				int r = Math.abs(random.nextInt()%10);
				
				
				
				if(r<=p*10)
				{
					//this is the wall
					maze[i][j] = 1;
				}
				else
				{
					//this is empty
				maze[i][j] = 0;
				}
			}
		}
		
		maze[0][0] = 0;
		maze[size-1][size-1] =0;
		
	}
	public void showMaze()
	{
		
		for( int i = 0; i<size;i++)
		{
			for(int j =0;j<size;j++)
			{
				//System.out.print(maze[i][j]+ " ");
			
			}
			//System.out.println();
		}
		//System.out.println("-----------------------");
	}
	
	
	public void setMaze(int[][] giMaze)
	{
		for (int i = 0; i < giMaze.length; i++)
		{
			for (int j = 0; j < giMaze.length; j++)
			{
				maze[i][j] = giMaze[i][j];
			}
		}
	}
	
}

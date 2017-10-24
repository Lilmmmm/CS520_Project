package sadden.maze.make;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class DFS {
	public Maze maze;
	//the true is visited and false is not
	public boolean[][] isvisited;
	public Queue<point> queue;
	public Stack<point> stack;;
	public List<point> list;
	public HashMap<point,Integer> distance;
	public boolean isFirst;
	public boolean success;
	
	
	// some factors to determine the maze;
		public int ShortestPath;
		public int TotalNode;
		public int MaxF;
		public long RunTime; 
		
	
	
	
	public int size;
	
	/*
	 * 
	 * in order to save the path from start to end
	 * we need to create a new structure to save the father point of every
	 * point in the map
	 */
	public point[][] FaMaze;
	
	public DFS(Maze maze)
	{
		this.maze = maze;
		isvisited = new boolean[maze.size][maze.size];
		FaMaze = new point[maze.size][maze.size];
		isFirst = true;
		size = maze.size;
		
		//set all factors to zero
		ShortestPath = 0;
		TotalNode = 0;
		MaxF = 0;
		RunTime = 0;
		
		
		
		
		//set all the points in maze unvisited
		initial();
		
		//initial the list and the queue
		queue = new LinkedList<point>();
		stack = new Stack<point>();
		distance = new HashMap<point,Integer>();
		list = new LinkedList<point>();
		
		
	}
	
	public void initial()
	{
		//initialize the visited mat
		for(int i=0;i<maze.size;i++)
		{
			for(int j=0;j<maze.size;j++)
			{
				isvisited[i][j] = false;
			}
		}
		isvisited[0][0] = true;
		
	}
	
	public void doDFS()
	{
		//add the start point into map
		point start = new point(0,0);
		stack.add(start);
		//save the point into famaze
		FaMaze[0][0] = start;
	
		distance.put(start, 0);
		stack.pop();
		
		//find the neighbors of that point
		ArrayList<point> npoints = new ArrayList<point>(); 
		npoints =	FindNeighbors(start);
		//put all the points into the points waiting list
		for(int i =0;i<npoints.size();i++)
		{
			point child = npoints.get(i);
			child.setfather(start);
			stack.add(child);
			
			FaMaze[child.x][child.y] = child;
			
			//System.out.println("add "+child.x+" "+child.y);
		}
		while(!stack.isEmpty()){
			
            point expand = stack.pop();
            //maze.maze[expand.x][expand.y] = 7;
            //maze.showMaze();
            // set this is visited
            isvisited[expand.x][expand.y] = true;
            
            
            // check if it is the destination
            if(expand.x == maze.size-1 && expand.y == maze.size-1)
            {
            	////System.out.println("get to the exit!!!!!!!!!");
            	
            	//show the way out!
            	ShortestPath = ShowWayOut2();

            	ShowWayOut3();
            	
            	return ;
            }
            
            ArrayList<point> nextpoint = FindNeighbors(expand);
            for(int i =0;i<nextpoint.size();i++)
            {
            	
            	point child = nextpoint.get(i);
            	child.setfather(expand);
            	
            	stack.add(child);
            	
            	FaMaze[child.x][child.y] = child;
            }

		}
		//there is no way to get out
		////System.out.println("there is no way to get out!!!");
		
	}
	
	
	public boolean doDFS2()
	{
		long begintime = System.currentTimeMillis();
		
		
		//add the start point into map
		point start = new point(0,0);
		stack.add(start);
		TotalNode++;
		//save the point into famaze
		FaMaze[0][0] = start;
	
		distance.put(start, 0);
		stack.pop();
		
		//find the neighbors of that point
		ArrayList<point> npoints = new ArrayList<point>(); 
		npoints =	FindNeighbors(start);
		//put all the points into the points waiting list
		for(int i =0;i<npoints.size();i++)
		{
			point child = npoints.get(i);
			child.setfather(start);
			stack.add(child);
			TotalNode++;
			
			FaMaze[child.x][child.y] = child;
			
//			//System.out.println("add "+child.x+" "+child.y);
		}
		while(!stack.isEmpty()){
			
            point expand = stack.pop();
            maze.maze[expand.x][expand.y] = 7;
            //maze.showMaze();
            // set this is visited
            isvisited[expand.x][expand.y] = true;
            
            
            // check if it is the destination
            if(expand.x == maze.size-1 && expand.y == maze.size-1)
            {
            	////System.out.println("get to the exit!!!!!!!!!");
            	
            	ShortestPath = ShowWayOut2();
            	ShowWayOut3();
            	success=true;
            	
            	long endtime=System.currentTimeMillis();
            	RunTime = (endtime - begintime);
            	
            	return true;
            }
            
            ArrayList<point> nextpoint = FindNeighbors(expand);
            for(int i =0;i<nextpoint.size();i++)
            {
            	
            	point child = nextpoint.get(i);
            	child.setfather(expand);
            	
            	stack.add(child);
            	TotalNode++;
            	
            	//check the max fringe
            	MaxF = Math.max(stack.size(),MaxF);
            	
            	FaMaze[child.x][child.y] = child;
            }

		}
		//there is no way to get out
		long endtime=System.currentTimeMillis();
    	RunTime = (endtime - begintime);

		
		return false;
		
	}
	
	public Maze doDFS3()
	{
		long begintime = System.currentTimeMillis();
		
		if(isFirst)
		{
			//System.out.println("First");
			point start = new point(0,0);
			stack.add(start);
			TotalNode++;
			//save the point into famaze
			FaMaze[0][0] = start;
		
			distance.put(start, 0);
			stack.pop();
			
			//find the neighbors of that point
			ArrayList<point> npoints = new ArrayList<point>(); 
			npoints =	FindNeighbors(start);
			//put all the points into the points waiting list
			for(int i =0;i<npoints.size();i++)
			{
				point child = npoints.get(i);
				child.setfather(start);
				stack.add(child);
				TotalNode++;
				
				FaMaze[child.x][child.y] = child;
				
//				//System.out.println("add "+child.x+" "+child.y);
			}
			isFirst = false;
		}
		else
		{
		
			if(stack.isEmpty())
			{
				//System.out.println("no way out");
				return maze;
			}
			
            point expand = stack.pop();
            maze.maze[expand.x][expand.y] = 7;
          maze.showMaze();
            // set this is visited
            isvisited[expand.x][expand.y] = true;
            
            
            // check if it is the destination
            if(expand.x == maze.size-1 && expand.y == maze.size-1)
            {
            	//System.out.println("get to the exit!!!!!!!!!");
            	
            	ShortestPath = ShowWayOut2();
            	
            	
            	long endtime=System.currentTimeMillis();
            	RunTime = (endtime - begintime);
            	
            	return maze;
            }
            
            ArrayList<point> nextpoint = FindNeighbors(expand);
            for(int i =0;i<nextpoint.size();i++)
            {
            	
            	point child = nextpoint.get(i);
            	child.setfather(expand);
            	
            	stack.add(child);
            	TotalNode++;
            	
            	//check the max fringe
            	MaxF = Math.max(stack.size(),MaxF);
            	
            	FaMaze[child.x][child.y] = child;
            }
		}
		
		//there is no way to get out
		long endtime=System.currentTimeMillis();
    	RunTime = RunTime + (endtime - begintime);

		
		return maze;
		
	}
	
	public Maze doDFS4(int time)
	{
		
		long begintime = System.currentTimeMillis();
		
		if(isFirst)
		{
			//System.out.println("First");
			point start = new point(0,0);
			stack.add(start);
			TotalNode++;
			//save the point into famaze
			FaMaze[0][0] = start;
		
			distance.put(start, 0);
			stack.pop();
			
			//find the neighbors of that point
			ArrayList<point> npoints = new ArrayList<point>(); 
			npoints =	FindNeighbors(start);
			//put all the points into the points waiting list
			for(int i =0;i<npoints.size();i++)
			{
				point child = npoints.get(i);
				child.setfather(start);
				stack.add(child);
				TotalNode++;
				
				FaMaze[child.x][child.y] = child;
				
//				//System.out.println("add "+child.x+" "+child.y);
			}
			isFirst = false;
			time--;
		}
		else
		{
			while(time > 0)
			{
				
			
		
			if(stack.isEmpty())
			{
				//System.out.println("no way out");
				return maze;
			}
			
            point expand = stack.pop();
            maze.maze[expand.x][expand.y] = 7;
          maze.showMaze();
            // set this is visited
            isvisited[expand.x][expand.y] = true;
            
            
            // check if it is the destination
            if(expand.x == maze.size-1 && expand.y == maze.size-1)
            {
            	//System.out.println("get to the exit!!!!!!!!!");
            	
            	ShortestPath = ShowWayOut2();
            	Maze destinaton = new Maze(4,0.3);
            	int[][] des = new int[1][1];
            	des[0][0] = -1;
            	destinaton.maze = des;
            	
            	long endtime=System.currentTimeMillis();
            	RunTime = (endtime - begintime);
            	
            	return destinaton;
            }
            
            ArrayList<point> nextpoint = FindNeighbors(expand);
            for(int i =0;i<nextpoint.size();i++)
            {
            	
            	point child = nextpoint.get(i);
            	child.setfather(expand);
            	
            	stack.add(child);
            	TotalNode++;
            	
            	//check the max fringe
            	MaxF = Math.max(stack.size(),MaxF);
            	
            	FaMaze[child.x][child.y] = child;
            }
		}
		
		time--;}
		//there is no way to get out
		long endtime=System.currentTimeMillis();
    	RunTime = RunTime + (endtime - begintime);

		
		return maze;
		
	}
	
	
	public ArrayList<point> FindNeighbors(point p)
	{
		ArrayList<point> neighbors = new ArrayList<point>();
		
		//check the 4 territory of the point p
		
		
		/**
		 * 1.the right if it is legal 
		 * 2.not visited and it is no
		 * 3.it is not wall
		 */
		if((p.y+1)<maze.size && isvisited[p.x][p.y+1]==false && maze.maze[p.x][p.y+1] == 0)
		{
			point p1 = new point(p.x,p.y +1);
			neighbors.add(p1);
		}
		//check the left 
		if((p.y - 1)>=0 && isvisited[p.x][p.y -1] == false && maze.maze[p.x][p.y-1] == 0)
		{
			point p1 = new point(p.x,p.y-1);
			neighbors.add(p1);
		}
		//check the up
		if((p.x -1)>=0 && isvisited[p.x-1][p.y] == false && maze.maze[p.x-1][p.y] == 0)
		{
			point p1 = new point(p.x-1,p.y);
			neighbors.add(p1);
		}
		//check the down
		if((p.x+1)<maze.size && isvisited[p.x+1][p.y] == false && maze.maze[p.x+1][p.y] == 0)
		{
			point p1 = new point(p.x+1,p.y);
			neighbors.add(p1);
		}
		
		
		return neighbors;
		
	}

	public void ShowWayOut()
	{
		point p = FaMaze[size-1][size-1];
		////System.out.println("Destination");

		while(true)
		{
			point f = p.father;
			if(f.x==0 && f.y==0)
			{
				// this is the begin
				////System.out.println("0,0  it is the start");
				return;
			}
			
			////System.out.println("this is"+f.x+" "+f.y);
			p =f;
			
			
		}
	}
	
	public int ShowWayOut2()
	{
		point p = FaMaze[size-1][size-1];
		////System.out.println("Destination");
		int count = 0;
		while(true)
		{
			count++;
			point f = p.father;
			if(f.x==0 && f.y==0)
			{
				// this is the begin
				////System.out.println("0,0  it is the start");
				return count;
			}
			
			////System.out.println("this is"+f.x+" "+f.y);
			p =f;
			
			
		}
	}
	public int ShowWayOut3()
	{
		point p = FaMaze[size-1][size-1];
		////System.out.println("Destination");
		int count = 0;
		while(true)
		{
			count++;
			point f = p.father;
			if(f.x==0 && f.y==0)
			{
				// this is the begin
				////System.out.println("0,0  it is the start");
				return count;
			}
			
			////System.out.println("this is"+f.x+" "+f.y);
			maze.maze[f.x][f.y]=6;
			p =f;
			
			
		}
	}
	
	public long showRunningTime()
	{
		////System.out.println("Running Time is " + RunTime + " ms");
		return RunTime;
	}
	
}

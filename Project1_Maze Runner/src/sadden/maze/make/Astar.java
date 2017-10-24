package sadden.maze.make;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class Astar {
	
	public Maze maze;
	//the true is visited and false is not
	public boolean[][] isvisited;
	public Queue<point> queue;
	public Stack<point> stack;
	public List<point> list;
	public HashMap<point,Integer> distance;
    public int size;
	// below is for A* search
    public ArrayList<point> openList; 
    public List<point> closeList;
    public boolean success;
    
    
    
    

	// some factors to determine the maze;
		public int ShortestPath;
		public int TotalNode;
		public int MaxF;
		public long RunTime; 
		
    
    
    
	/*
	 * 
	 * in order to save the path from start to end
	 * we need to create a new structure to save the father point of every
	 * point in the map
	 */
	public point[][] FaMaze;
	
	public Astar(Maze maze)
	{
		this.maze = maze;
		isvisited = new boolean[maze.size][maze.size];
		FaMaze = new point[maze.size][maze.size];
		
		
		//set all factors to zero
		ShortestPath = 0;
		TotalNode = 0;
		MaxF = 0;
		RunTime = 0;
		
		
		
		
		size = maze.size;
		
		//set all the points in maze unvisited
		initial();
		//initial the list and the queue
		queue = new LinkedList<point>();
		stack = new Stack<point>();
		distance = new HashMap<point,Integer>();
		list = new LinkedList<point>();
		//for openlist and close list
		openList = new ArrayList<point>(); 
	    closeList = new ArrayList<point>();
		
	}
	
	public void doAstarManhattan()
	{
				//add the start point into map
				point start = new point(0,0,0);
				start.G = 0;
				openList.add(start);
				//save the point into famaze
				FaMaze[0][0] = start;
			
				distance.put(start, 0);
				openList.remove(0);
				
				//find the neighbors of that point
				ArrayList<point> npoints = new ArrayList<point>(); 
				npoints =	FindNeighbors(start);
				//put all the points into the points waiting list
				for(int i =0;i<npoints.size();i++)
				{
					point child = npoints.get(i);
					child.setfather(start);
					
					child.G = 1 + child.father.G;
					child.H = distance_Manhattan(child);
					
					openList.add(child);
					
					FaMaze[child.x][child.y] = child;
					
					//System.out.println("add "+child.x+" "+child.y);
				}
				closeList.add(start);
				while(!openList.isEmpty()){
					
					int in = FindLeast(openList);
					point expand =openList.get(in);
		           
					
					//point expand = openList.get();
		            
		            
		            
		            maze.maze[expand.x][expand.y] = 7;
		            maze.showMaze();
		            // set this is visited
		            isvisited[expand.x][expand.y] = true;
		            
		           
		            // check if it is the destination
		            if(expand.x == maze.size-1 && expand.y == maze.size-1)
		            {
		            	//System.out.println("get to the exit!!!!!!!!!");
		            	
		            	//show the way out!
		            	ShowWayOut();
		            	
		            	return ;
		            }
		            
		            ArrayList<point> nextpoint = FindNeighbors(expand);
		            for(int i =0;i<nextpoint.size();i++)
		            {
		            	
		            	point child = nextpoint.get(i);
		            	child.setfather(expand);
		            	child.G = child.father.G+1;
	            		child.H = distance_Manhattan(child);
		            	if(IsInClose(child))
		            	{
		            		continue;
		            	}
		            	if(FindPointOpen(child)==null)
		            	{
		            		
		            		openList.add(child);
		            		FaMaze[child.x][child.y] = child;
		            		continue;
		            	}
		            	//then the point is in the openlist but here find a new one
		            	point old = FindPointOpen(child);
		            	if(old.G > child.G)
		            	{
		            		old.G = child.G;
		            		old.father = expand;
		            		FaMaze[child.x][child.y] = old;
		            	}
		            	
		            	
		            	
		            }
		            
		            openList.remove(in);
		            closeList.add(expand);

				}
				//there is no way to get out
				//System.out.println("there is no way to get out!!!");
	}
	public void doAstarEucilide()
	{
				//add the start point into map
				point start = new point(0,0,0);
				start.G = 0;
				openList.add(start);
				//save the point into famaze
				FaMaze[0][0] = start;
			
				distance.put(start, 0);
				openList.remove(0);
				
				//find the neighbors of that point
				ArrayList<point> npoints = new ArrayList<point>(); 
				npoints =	FindNeighbors(start);
				//put all the points into the points waiting list
				for(int i =0;i<npoints.size();i++)
				{
					point child = npoints.get(i);
					child.setfather(start);
					
					child.G = 1 + child.father.G;
					child.H = distance_Eucilide(child);
					
					openList.add(child);
					
					FaMaze[child.x][child.y] = child;
					
					//System.out.println("add "+child.x+" "+child.y);
				}
				closeList.add(start);
				while(!openList.isEmpty()){
					
					int in = FindLeast(openList);
					point expand =openList.get(in);
		           
					
					//point expand = openList.get();
		            
		            
		            
		            maze.maze[expand.x][expand.y] = 7;
		            maze.showMaze();
		            // set this is visited
		            isvisited[expand.x][expand.y] = true;
		            
		           
		            // check if it is the destination
		            if(expand.x == maze.size-1 && expand.y == maze.size-1)
		            {
		            	//System.out.println("get to the exit!!!!!!!!!");
		            	
		            	//show the way out!
		            	ShowWayOut();
		            	
		            	return ;
		            }
		            
		            ArrayList<point> nextpoint = FindNeighbors(expand);
		            for(int i =0;i<nextpoint.size();i++)
		            {
		            	
		            	point child = nextpoint.get(i);
		            	child.setfather(expand);
		            	child.G = child.father.G+1;
	            		child.H = distance_Manhattan(child);
		            	if(IsInClose(child))
		            	{
		            		continue;
		            	}
		            	if(FindPointOpen(child)==null)
		            	{
		            		
		            		openList.add(child);
		            		FaMaze[child.x][child.y] = child;
		            		continue;
		            	}
		            	//then the point is in the openlist but here find a new one
		            	point old = FindPointOpen(child);
		            	if(old.G > child.G)
		            	{
		            		old.G = child.G;
		            		old.father = expand;
		            		FaMaze[child.x][child.y] = old;
		            	}
		            	
		            	
		            	
		            }
		            
		            openList.remove(in);
		            closeList.add(expand);

				}
				//there is no way to get out
				//System.out.println("there is no way to get out!!!");
	}
	public boolean doAstarEucilide2()
	{
		long begintime = System.currentTimeMillis();

		//add the start point into map
		point start = new point(0,0,0);
		start.G = 0;
		openList.add(start);
		TotalNode++;
		//save the point into famaze
		FaMaze[0][0] = start;
	
		distance.put(start, 0);
		openList.remove(0);
		
		//find the neighbors of that point
		ArrayList<point> npoints = new ArrayList<point>(); 
		npoints =	FindNeighbors(start);
		//put all the points into the points waiting list
		for(int i =0;i<npoints.size();i++)
		{
			point child = npoints.get(i);
			child.setfather(start);
			
			child.G = 1 + child.father.G;
			child.H = distance_Eucilide(child);
			
//			//change!!!!
//			isvisited[child.x][child.y] = true;
			
			openList.add(child);
			TotalNode++;
			FaMaze[child.x][child.y] = child;
			
			//System.out.println("add "+child.x+" "+child.y);
		}
		closeList.add(start);
		while(!openList.isEmpty()){
			
			int in = FindLeast(openList);
			point expand =openList.get(in);
           
			
			//point expand = openList.get();
            
            
            
            //maze.maze[expand.x][expand.y] = 7;
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
            	child.G = child.father.G+1;
        		child.H = distance_Manhattan(child);
            	if(IsInClose(child))
            	{
            		continue;
            	}
            	if(FindPointOpen(child)==null)
            	{
            		
            		openList.add(child);
            		TotalNode++;
            		FaMaze[child.x][child.y] = child;
            	 	MaxF = Math.max(openList.size(),MaxF);
            		continue;
            	}
            	//then the point is in the openlist but here find a new one
            	point old = FindPointOpen(child);
            	if(old.G > child.G)
            	{
            		old.G = child.G;
            		old.father = expand;
            		FaMaze[child.x][child.y] = old;
            		
            		
            	}
            	
            	
            	
            }
            
            openList.remove(in);
            closeList.add(expand);

		}
		//there is no way to get out
		//System.out.println("there is no way to get out!!!");
		
		
		long endtime=System.currentTimeMillis();
    	RunTime = (endtime - begintime);

		
		return false;
		
		
	}
	
	
	public boolean doAstarManhanttan2()
	{
		long begintime = System.currentTimeMillis();

		//add the start point into map
		point start = new point(0,0,0);
		start.G = 0;
		openList.add(start);
		TotalNode++;
		//save the point into famaze
		FaMaze[0][0] = start;
	
		distance.put(start, 0);
		openList.remove(0);
		
		//find the neighbors of that point
		ArrayList<point> npoints = new ArrayList<point>(); 
		npoints =	FindNeighbors(start);
		//put all the points into the points waiting list
		for(int i =0;i<npoints.size();i++)
		{
			point child = npoints.get(i);
			child.setfather(start);
			
			child.G = 1 + child.father.G;
			child.H = distance_Manhattan(child);
			
			openList.add(child);
			TotalNode++;
			FaMaze[child.x][child.y] = child;
			
			////System.out.println("add "+child.x+" "+child.y);
		}
		closeList.add(start);
		while(!openList.isEmpty()){
			
			int in = FindLeast(openList);
			point expand =openList.get(in);
           
			
			//point expand = openList.get();
            
            
            
            //maze.maze[expand.x][expand.y] = 7;
            //maze.showMaze();
            // set this is visited
            isvisited[expand.x][expand.y] = true;
            
           
            // check if it is the destination
            if(expand.x == maze.size-1 && expand.y == maze.size-1)
            {
            	////System.out.println("get to the exit!!!!!!!!!");
            	
            	ShortestPath = ShowWayOut2();
            	ShowWayOut4();
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
            	child.G = child.father.G+1;
        		child.H = distance_Manhattan(child);
            	if(IsInClose(child))
            	{
            		continue;
            	}
            	if(FindPointOpen(child)==null)
            	{
            		
            		openList.add(child);
            		TotalNode++;
            		FaMaze[child.x][child.y] = child;
            	 	MaxF = Math.max(openList.size(),MaxF);
            		continue;
            	}
            	//then the point is in the openlist but here find a new one
            	point old = FindPointOpen(child);
            	if(old.G > child.G)
            	{
            		old.G = child.G;
            		old.father = expand;
            		FaMaze[child.x][child.y] = old;
            	}
            	
            	
            	
            }
            
            openList.remove(in);
            closeList.add(expand);

		}
		//there is no way to get out
		//System.out.println("there is no way to get out!!!");
		
		
		long endtime=System.currentTimeMillis();
    	RunTime = (endtime - begintime);

		
		return false;
		
		
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
	
	public ArrayList<point> FindNeighbors(point p)
	{
		ArrayList<point> neighbors = new ArrayList<point>();
		
		//check the 4 territory of the point p
		
		
		/**
		 * 1.the right if it is legal 
		 * 2.not visited and it is no
		 * 3.it is not wall
		 */
		if((p.y+1)<maze.size  && maze.maze[p.x][p.y+1] == 0)
		{
			point p1 = new point(p.x,p.y +1);
			neighbors.add(p1);
		}
		//check the left 
		if((p.y - 1)>=0 &&  maze.maze[p.x][p.y-1] == 0)
		{
			point p1 = new point(p.x,p.y-1);
			neighbors.add(p1);
		}
		//check the up
		if((p.x -1)>=0 &&  maze.maze[p.x-1][p.y] == 0)
		{
			point p1 = new point(p.x-1,p.y);
			neighbors.add(p1);
		}
		//check the down
		if((p.x+1)<maze.size &&  maze.maze[p.x+1][p.y] == 0)
		{
			point p1 = new point(p.x+1,p.y);
			neighbors.add(p1);
		}
		
		
		return neighbors;
		
	}

	
	public void ShowWayOut()
	{
		point p = FaMaze[size-1][size-1];
		//System.out.println("Destination");

		while(true)
		{
			point f = p.father;
			if(f.x==0 && f.y==0)
			{
				// this is the begin
				//System.out.println("0,0  it is the start");
				return;
			}
			
			//System.out.println("this is"+f.x+" "+f.y);
			p =f;
		}
	}
	
	public double distance_Manhattan(point p)
	{
		double dis = -1;
		dis = Math.abs(p.y-(size-1))+Math.abs(p.x-(size-1));
		
		return dis;
	}
	public double distance_Eucilide(point p)
	{
		double dis = -1;
		dis = Math.sqrt(Math.pow(p.y-(size-1),2)+Math.pow(p.x-(size-1),2));
		
		return dis;
	}
	/**
	 * actually to check if the point is in the close list
	 * @param p
	 * @return
	 */
	public boolean IsInClose(point p)
	{
		boolean CanAddOpen = true;
		//if the point is in the close
		if (closeList.isEmpty()) 
			return false;
        for (point ps : closeList)
        {
            if (ps.x == p.x && ps.y == p.y)
            {
                return true;
            }
        }
        return false;
		
	}
	
	
	/**
	 * find if there is a p in openlist
	 */
	public point FindPointOpen(point p)
	{
		if (p == null || openList.isEmpty()) 
			return null;
	    for (point ps : openList)
	    {
	        if (ps.x == p.x && ps.y == p.y)
	        {
	            return ps;
	        }
	    }
		
		
		return null;
	}
	
	public int FindLeast(ArrayList<point> list)
	{
		int index = -1;
		point sp = list.get(0);
		for(int i=0;i<list.size();i++)
		{
			point pn = list.get(i);
			if((pn.H+pn.G)<=(sp.H+sp.G))
			{
				sp = pn;
				index = i;
			}
		}
		return index;
	}
	
	
	public int ShowWayOut2()
	{
		point p = FaMaze[size-1][size-1];
		//System.out.println("Destination");
		int count = 0;
		while(true)
		{
			count++;
			point f = p.father;
			if(f.x==0 && f.y==0)
			{
				// this is the begin
				//System.out.println("0,0  it is the start");
				return count;
			}
			
			//System.out.println("this is"+f.x+" "+f.y);
			p =f;
			
			
		}
	}
	
	public int ShowWayOut3()
	{
		point p = FaMaze[size-1][size-1];
		//System.out.println("Destination");
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
			maze.maze[f.x][f.y]=8;
			p =f;
			
			
		}
	}
	
	public int ShowWayOut4()
	{
		point p = FaMaze[size-1][size-1];
		//System.out.println("Destination");
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
			maze.maze[f.x][f.y]=9;
			p =f;
			
			
		}
	}
	
}

package sadden.maze.make;

public class point {

	
	
	
	
	public int x;
	public int y;
	public point father;
	public double dis;
	public double G;//this is the dis to begin
	public double H;//this is the dis to end
	
	public point(int X, int Y)
	{
		x=X;
		y=Y;
		father = null;
		dis = -1;
		G=-1;
		H=-1;
	}
	public point(int X,int Y,double D)
	{
		x=X;
		y=Y;
		father = null;
		dis = D;
		G=0;
		H=0;
	}
	public void setfather(point fa)
	{
		this.father = fa;
	}
	
	
	
}

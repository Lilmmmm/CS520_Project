package sadden.maze.make;

public class cell {
	
	//this class used to record info. of Obstacle
	public int iRow;
	public int iCol;
	
	public int iOriginRow;
	public int iOriginCol;
	
	public boolean bIsWall;
	public boolean bIsMoved;
	
	public cell()
	{
		
	}
	
	public void setCell(int iRow, int iCol)
	{
		this.iRow          = iRow;
		this.iCol          = iCol;
		this.iOriginRow    = iRow;
		this.iOriginCol    = iCol;
		
		bIsMoved      = false;
		bIsWall       = false;
	}
	
	public cell(cell cCell)
	{
		this.iRow              = cCell.iRow;
		this.iCol              = cCell.iCol;
		this.iOriginRow        = cCell.iOriginRow;
		this.iOriginCol        = cCell.iOriginCol;
		this.bIsMoved          = cCell.bIsMoved;
		this.bIsWall           = cCell.bIsWall;
	}
	
	
	public void SetWall(cell cCell)
	{
		cCell.bIsWall = true;
	}
	
	
	
	

}

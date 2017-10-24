package sadden.maze.make;

import java.util.List;
import java.util.ArrayList;


public class HardMaze {
	
	//this is a macro definition
	public static final int    iRANGE         = 100;
	public static final double dPROB          = 0.22;
	public static final double dMOVINGPROB    = 1;
	public static final int    iPATHLEN       = 1;
	public static final int    iEXPANDNODE    = 2;
	public static final int    iMAXFRINGE     = 3;
	public static final int    iMIXPARA       = 4;
	public static final double dPATHLEN_W     = 0.3;
	public static final double dEXPANDNODE_W  = 0.3;
	public static final double dMAXFRINGE_W   = 0.4;
	
	
	public cell[][] gMaze;
	public List<cell> lstWall;
	//hardest maze para
	public long lDFSMaxPathLen;
	public long lDFSMaxExpandNode;
	public long lDFSMaxFringe;
	public long lDFSMaxPara;
	
	public long lBFSMaxPathLen;
	public long lBFSMaxExpandNode;
	public long lBFSMaxFringe;
	public long lBFSMaxPara;
	
	public long lEUMaxPathLen;
	public long lEUMaxExpandNode;
	public long lEUMaxFringe;
	public long lEUMaxPara;
	
	public long lMAMaxPathLen;
	public long lMAMaxExpandNode;
	public long lMAMaxFringe;
	public long lMAMaxPara;
	
	public int  iMaxIndex;
	
	public HardMaze()
	{
		lDFSMaxPathLen      = 0;
		lDFSMaxExpandNode   = 0;
		lDFSMaxFringe       = 0;
		lDFSMaxPara         = 0;
		
		lBFSMaxPathLen      = 0;
		lBFSMaxExpandNode   = 0;
		lBFSMaxFringe       = 0;
		lBFSMaxPara         = 0;
		
		lEUMaxPathLen       = 0;
		lEUMaxExpandNode    = 0;
		lEUMaxFringe        = 0;
		lEUMaxPara          = 0;
		
		lMAMaxPathLen       = 0;
		lMAMaxExpandNode    = 0;
		lMAMaxFringe        = 0;
		lMAMaxPara          = 0;
		iMaxIndex = 0;
		CreateMaze();
	}


	//To create a random maze
	public void CreateMaze()
	{
		int iRow, iCol;
		double dRandom;
		
		gMaze = new cell[iRANGE][iRANGE];
		lstWall = new ArrayList<cell>();
		for (iRow = 0; iRow < iRANGE; iRow++)
		{
			for (iCol = 0; iCol < iRANGE; iCol++)
			{
				dRandom = Math.random();
				
				gMaze[iRow][iCol] = new cell();
				gMaze[iRow][iCol].setCell(iRow, iCol);
				
				if (dPROB >= dRandom)
				{
					gMaze[iRow][iCol].bIsWall = true;
					//ATTENTION:start and goal maybe add to list!!!!
					lstWall.add(gMaze[iRow][iCol]);
				}
			}
		}
		
		//start and goal
		gMaze[0][0].bIsWall                    = false;
		gMaze[iRANGE - 1][iRANGE - 1].bIsWall  = false;
	}
	
	
	//operation of cell in maze
	public boolean MoveCellUp(cell cCell)
	{	
		//check validity
		if ((0 > (cCell.iRow - 1)) || (true == gMaze[cCell.iRow - 1][cCell.iCol].bIsWall) || ((0 == cCell.iRow - 1) && (0 == cCell.iCol )))
		{
			return false;
		}
		
		gMaze[cCell.iRow - 1][cCell.iCol].bIsWall       = true;
		gMaze[cCell.iRow - 1][cCell.iCol].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow - 1][cCell.iCol].iOriginCol	= cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	

	public boolean MoveCellDown(cell cCell)
	{	
		//check validity
		if ((iRANGE <= (cCell.iRow + 1)) || (true == gMaze[cCell.iRow + 1][cCell.iCol].bIsWall) || (((iRANGE - 1) == cCell.iRow + 1) && ((iRANGE - 1) == cCell.iCol )))
		{
			return false;
		}
		
		gMaze[cCell.iRow + 1][cCell.iCol].bIsWall       = true;
		gMaze[cCell.iRow + 1][cCell.iCol].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow + 1][cCell.iCol].iOriginCol	= cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	
	public boolean MoveCellLeft(cell cCell)
	{	
		//check validity
		if ((0 > (cCell.iCol - 1)) || (true == gMaze[cCell.iRow][cCell.iCol - 1].bIsWall) || ((0 == cCell.iRow) && (0 == cCell.iCol - 1)))
		{
			return false;
		}
		
		gMaze[cCell.iRow][cCell.iCol - 1].bIsWall       = true;
		gMaze[cCell.iRow][cCell.iCol - 1].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow][cCell.iCol - 1].iOriginCol	= cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	
	public boolean MoveCellRight(cell cCell)
	{	
		//check validity
		if ((iRANGE <= (cCell.iCol + 1)) || (true == gMaze[cCell.iRow][cCell.iCol + 1].bIsWall) || (((iRANGE - 1) == cCell.iRow) && ((iRANGE - 1) == cCell.iCol + 1)))
		{
			return false;
		}
		
		gMaze[cCell.iRow][cCell.iCol + 1].bIsWall       = true;
		gMaze[cCell.iRow][cCell.iCol + 1].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow][cCell.iCol + 1].iOriginCol	= cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	
	public boolean MoveCellUpLeft(cell cCell)
	{	
		//check validity
		if ((0 > (cCell.iCol - 1)) || (0 > (cCell.iRow - 1)) || (true == gMaze[cCell.iRow - 1][cCell.iCol - 1].bIsWall) || ((0 == cCell.iRow - 1) && (0 == cCell.iCol - 1)))
		{
			return false;
		}
		
		gMaze[cCell.iRow - 1][cCell.iCol - 1].bIsWall       = true;
		gMaze[cCell.iRow - 1][cCell.iCol - 1].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow - 1][cCell.iCol - 1].iOriginCol    = cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	
	public boolean MoveCellUpRight(cell cCell)
	{	
		//check validity
		if ((iRANGE <= (cCell.iCol + 1)) || (0 > (cCell.iRow - 1)) || (true == gMaze[cCell.iRow - 1][cCell.iCol + 1].bIsWall))
		{
			return false;
		}
		
		gMaze[cCell.iRow - 1][cCell.iCol + 1].bIsWall       = true;
		gMaze[cCell.iRow - 1][cCell.iCol + 1].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow - 1][cCell.iCol + 1].iOriginCol	= cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	
	public boolean MoveCellDownLeft(cell cCell)
	{	
		//check validity
		if ((0 > (cCell.iCol - 1)) || (iRANGE <= (cCell.iRow + 1)) || (true == gMaze[cCell.iRow + 1][cCell.iCol - 1].bIsWall))
		{
			return false;
		}
		
		gMaze[cCell.iRow + 1][cCell.iCol - 1].bIsWall       = true;
		gMaze[cCell.iRow + 1][cCell.iCol - 1].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow + 1][cCell.iCol - 1].iOriginCol    = cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	
	public boolean MoveCellDownRight(cell cCell)
	{	
		//check validity
		if ((iRANGE <= (cCell.iCol + 1)) || (iRANGE <= (cCell.iRow + 1)) || (true == gMaze[cCell.iRow + 1][cCell.iCol + 1].bIsWall) || (((iRANGE - 1) == cCell.iRow + 1) && ((iRANGE - 1) == cCell.iCol + 1)))
		{
			return false;
		}
		
		gMaze[cCell.iRow + 1][cCell.iCol + 1].bIsWall       = true;
		gMaze[cCell.iRow + 1][cCell.iCol + 1].iOriginRow    = cCell.iRow;
		gMaze[cCell.iRow + 1][cCell.iCol + 1].iOriginCol	= cCell.iCol;
		
		cCell.bIsWall  = false;
		cCell.bIsMoved = true;
				
		return true;
	}
	
	
	//set cell to origin
	public void SetCellToOrigin(cell cCell)
	{
		
		cCell.bIsWall                                      = false;
		gMaze[cCell.iOriginRow][cCell.iOriginCol].bIsWall  = true;
		
	}
	
	//Transfer HardMaze to Maze format
	public Maze HardMazeIntoMaze()
	{
		int  iRow, iCol;
		Maze cMaze;
		
		cMaze = new Maze(iRANGE, dPROB);
		
		for (iRow = 0; iRow < iRANGE; iRow++)
		{
			for (iCol = 0; iCol < iRANGE; iCol++)
			{
				if (true == gMaze[iRow][iCol].bIsWall)
				{
					cMaze.maze[iRow][iCol] = 1;
				}
				else
				{
					cMaze.maze[iRow][iCol] = 0;
				}
			}
		}
		
		return cMaze;
	}
	
	//transfer Maze to HardMaze format
	public void MazeIntoHardMaze(Maze cMaze)
	{
		int iRow, iCol;
		
		//renew the wall list
		lstWall = new ArrayList<cell>();
		
		for (iRow = 0; iRow < iRANGE; iRow++)
		{
			for (iCol = 0; iCol < iRANGE; iCol++)
			{
				if (1 == cMaze.maze[iRow][iCol])
				{
					gMaze[iRow][iCol].bIsWall = true;
					//add wall to list
					lstWall.add(gMaze[iRow][iCol]);
				}
				else if (0 == cMaze.maze[iRow][iCol])
				{
					gMaze[iRow][iCol].bIsWall = false;
				}
			}
		}
		
	}
	
	public long GetMax(long[] gRecord, cell cCell)
	{
		int      iIndex = 0;
		long     lMax   = gRecord[0]; 
		
		for (int iTemp = 1; iTemp < gRecord.length; iTemp++)
		{
			if (lMax < gRecord[iTemp])
			{
				lMax  = gRecord[iTemp];
				iIndex = iTemp;
			}
		}

		iMaxIndex = iIndex;
		
//		switch (iIndex)
//		{
//			case 1:  
//				this.MoveCellUp(cCell);
//				break;
//			case 2:
//				this.MoveCellDown(cCell);
//				break;
//			case 3:
//				this.MoveCellLeft(cCell);
//				break;
//			case 4:
//				this.MoveCellRight(cCell);
//				break;
//			case 5:
//				this.MoveCellUpLeft(cCell);
//				break;
//			case 6:
//				this.MoveCellUpRight(cCell);
//				break;
//			case 7:
//				this.MoveCellDownLeft(cCell);
//				break;
//			case 8:
//				this.MoveCellDownRight(cCell);
//				break;
//			default:
//				break;
//		}
		
		
		return lMax;	
	}
	
	public Maze SearchHardMazeForDFS(int iPara)
	{
		HardMaze   cHardMaze;
		Maze       cMaze;
		DFS        cDFS;
		long[]     glPara;
		long       lMaxValue = 0;
		int        iNo;
		long       lTemp = 0;
		cell       cCell;
		double     dMoveProb;   //probability of moving a wall

		//gdRuntime:0 for Origin, 1 for Up, 2 for Down, 3 for Left, 4 for Right, 5 for UpLe, 6 for UpRi, 7 for DownLe, 8 for DownRi;
		glPara = new long[9];
		cMaze  = new Maze(iRANGE, dPROB);
		
		//discard no path situation
		do
		{
			cHardMaze = new HardMaze();
			cMaze = cHardMaze.HardMazeIntoMaze();
			cDFS  = new DFS(cMaze);
		}while (false == cDFS.doDFS2());
		
		cCell = new cell();
		for (iNo = 0; iNo < cHardMaze.lstWall.size(); iNo++)
		{
			dMoveProb = Math.random();
			
			// do need to move
			if ((dMOVINGPROB < dMoveProb) || (true == cHardMaze.lstWall.get(iNo).bIsMoved) || (false == cHardMaze.lstWall.get(iNo).bIsWall))
			{
				continue;
			}
			
			//group reset
			for (int iIndexA = 0; iIndexA < 9; iIndexA++)
			{
				glPara[iIndexA] = 0;
			}
			
			cCell = cHardMaze.lstWall.get(iNo);
			cMaze = cHardMaze.HardMazeIntoMaze();
			cDFS  = new DFS(cMaze);
			cDFS.doDFS2();
			if (iPATHLEN == iPara)
			{
			    glPara[0] = (long)cDFS.ShortestPath;
			}
			else if (iEXPANDNODE == iPara)
			{
				glPara[0] = (long)cDFS.TotalNode;
			}
			else if (iMAXFRINGE == iPara)
			{
				glPara[0] = (long)cDFS.MaxF;
			}
			else if (iMIXPARA == iPara)
			{
				glPara[0] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
			}
			
			// move to up
			if (true == cHardMaze.MoveCellUp(cCell))
			{
				
                cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[1] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[1] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[1] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[1] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[1] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol]);
			}
			else
			{
				glPara[1] = 0;
			}
			
			// move to down
			if (true == cHardMaze.MoveCellDown(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[2] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[2] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[2] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[2] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[2] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol]);
			}
			else
			{
				glPara[2] = 0;
			}
			
			// move to left
			if (true == cHardMaze.MoveCellLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[3] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[3] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[3] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[3] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[3] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol - 1]);
			}
			else
			{
				glPara[3] = 0;
			}
			
			// move to right
			if (true == cHardMaze.MoveCellRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[4] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[4] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[4] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[4] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[4] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol + 1]);
			}
			else
			{
				glPara[4] = 0;
			}
			
			// move to upleft
			if (true == cHardMaze.MoveCellUpLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[5] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[5] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[5] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[5] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[5] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[5] = 0;
			}
			
			// move to upright
			if (true == cHardMaze.MoveCellUpRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[6] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[6] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[6] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[6] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[6] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol + 1]);
			}
			else
			{
				glPara[6] = 0;
			}
			
			// move to downleft
			if (true == cHardMaze.MoveCellDownLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[7] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[7] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[7] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[7] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[7] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[7] = 0;
			}
			
			// move to downright
			if (true == cHardMaze.MoveCellDownRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//fing path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[8] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[8] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[8] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[8] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[8] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol + 1]);				
			}
			else
			{
				glPara[8] = 0;
			}
			
			//find the max time
		    lTemp = cHardMaze.GetMax(glPara, cCell);
			if (lTemp > lMaxValue)
			{
				lMaxValue = lTemp;
				switch (cHardMaze.iMaxIndex)
				{
					case 1:  
						cHardMaze.MoveCellUp(cCell);
						cCell.bIsWall  = false;
						break;
					case 2:
						cHardMaze.MoveCellDown(cCell);
						cCell.bIsWall  = false;
						break;
					case 3:
						cHardMaze.MoveCellLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 4:
						cHardMaze.MoveCellRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 5:
						cHardMaze.MoveCellUpLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 6:
						cHardMaze.MoveCellUpRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 7:
						cHardMaze.MoveCellDownLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 8:
						cHardMaze.MoveCellDownRight(cCell);
						cCell.bIsWall  = false;
						break;
					default:
						break;
				}
			}
			
		}
		
		if (iPATHLEN == iPara)
		{
			System.out.println("Max Path Length is " + lMaxValue);
		    lDFSMaxPathLen = lMaxValue;
		}
		else if (iEXPANDNODE == iPara)
		{
			System.out.println("Max Expand Node is " + lMaxValue);
			lDFSMaxExpandNode = lMaxValue;
		}
		else if (iMAXFRINGE == iPara)
		{
			System.out.println("Max Fringe is " + lMaxValue);
			lDFSMaxFringe = lMaxValue;
		}
		else if (iMIXPARA == iPara)
		{
			System.out.println("Max MIXED Parameter is " + lMaxValue);
			lDFSMaxPara = lMaxValue;
		}

		cMaze = cHardMaze.HardMazeIntoMaze();
		cDFS  = new DFS(cMaze);
		cDFS.doDFS2();
		System.out.println("End " + cDFS.MaxF);
		cMaze = cHardMaze.HardMazeIntoMaze();
		return cMaze;	
	}
	 
	public Maze MakeMazeHarderForDFS(Maze cOriginMaze, int iPara)
	{
		HardMaze   cHardMaze;
		Maze       cMaze;
		DFS        cDFS;
		long[]     glPara;
		long       lMaxValue = 0;
		int        iNo;
		long       lTemp = 0;
		cell       cCell;
		double     dMoveProb;   //probability of moving a wall

		//gdRuntime:0 for Origin, 1 for Up, 2 for Down, 3 for Left, 4 for Right, 5 for UpLe, 6 for UpRi, 7 for DownLe, 8 for DownRi;
		glPara = new long[9];
		
		//initial the class
		cMaze  = new Maze(iRANGE, dPROB);
		cHardMaze = new HardMaze();
		
		//caller guarantee that the maze definitely has a path
		cHardMaze.MazeIntoHardMaze(cOriginMaze);
		
		cCell = new cell();
		for (iNo = 0; iNo < cHardMaze.lstWall.size(); iNo++)
		{
			dMoveProb = Math.random();
			
			// do need to move
			if ((dMOVINGPROB < dMoveProb) || (true == cHardMaze.lstWall.get(iNo).bIsMoved) || (false == cHardMaze.lstWall.get(iNo).bIsWall))
			{
				continue;
			}
			
			//group reset
			for (int iIndexA = 0; iIndexA < 9; iIndexA++)
			{
				glPara[iIndexA] = 0;
			}
			
			cCell = cHardMaze.lstWall.get(iNo);
			cMaze = cHardMaze.HardMazeIntoMaze();
			cDFS  = new DFS(cMaze);
			cDFS.doDFS2();
			if (iPATHLEN == iPara)
			{
			    glPara[0] = (long)cDFS.ShortestPath;
			}
			else if (iEXPANDNODE == iPara)
			{
				glPara[0] = (long)cDFS.TotalNode;
			}
			else if (iMAXFRINGE == iPara)
			{
				glPara[0] = (long)cDFS.MaxF;
			}
			else if (iMIXPARA == iPara)
			{
				glPara[0] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
			}
			
			// move to up
			if (true == cHardMaze.MoveCellUp(cCell))
			{
				
                cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[1] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[1] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[1] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[1] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[1] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol]);
			}
			else
			{
				glPara[1] = 0;
			}
			
			// move to down
			if (true == cHardMaze.MoveCellDown(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[2] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[2] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[2] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[2] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[2] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol]);
			}
			else
			{
				glPara[2] = 0;
			}
			
			// move to left
			if (true == cHardMaze.MoveCellLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[3] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[3] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[3] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[3] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[3] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol - 1]);
			}
			else
			{
				glPara[3] = 0;
			}
			
			// move to right
			if (true == cHardMaze.MoveCellRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[4] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[4] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[4] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[4] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[4] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol + 1]);
			}
			else
			{
				glPara[4] = 0;
			}
			
			// move to upleft
			if (true == cHardMaze.MoveCellUpLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[5] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[5] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[5] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[5] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[5] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[5] = 0;
			}
			
			// move to upright
			if (true == cHardMaze.MoveCellUpRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[6] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[6] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[6] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[6] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[6] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol + 1]);
			}
			else
			{
				glPara[6] = 0;
			}
			
			// move to downleft
			if (true == cHardMaze.MoveCellDownLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[7] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[7] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[7] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[7] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[7] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[7] = 0;
			}
			
			// move to downright
			if (true == cHardMaze.MoveCellDownRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cDFS  = new DFS(cMaze);
				
				//find path then record, or record 0
				if (true == cDFS.doDFS2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[8] = (long)cDFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[8] = (long)cDFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[8] = (long)cDFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[8] = (long)(dPATHLEN_W * cDFS.ShortestPath + dEXPANDNODE_W * cDFS.TotalNode + dMAXFRINGE_W * cDFS.MaxF);
					}
				}
				else
				{
					glPara[8] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol + 1]);				
			}
			else
			{
				glPara[8] = 0;
			}
			
			//find the max time
		    lTemp = cHardMaze.GetMax(glPara, cCell);
			if (lTemp > lMaxValue)
			{
				lMaxValue = lTemp;
				switch (cHardMaze.iMaxIndex)
				{
					case 1:  
						cHardMaze.MoveCellUp(cCell);
						cCell.bIsWall  = false;
						break;
					case 2:
						cHardMaze.MoveCellDown(cCell);
						cCell.bIsWall  = false;
						break;
					case 3:
						cHardMaze.MoveCellLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 4:
						cHardMaze.MoveCellRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 5:
						cHardMaze.MoveCellUpLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 6:
						cHardMaze.MoveCellUpRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 7:
						cHardMaze.MoveCellDownLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 8:
						cHardMaze.MoveCellDownRight(cCell);
						cCell.bIsWall  = false;
						break;
					default:
						break;
				}
			}
			
		}
		
		if (iPATHLEN == iPara)
		{
			System.out.println("Max Path Length is " + lMaxValue);
		    lDFSMaxPathLen = lMaxValue;
		}
		else if (iEXPANDNODE == iPara)
		{
			System.out.println("Max Expand Node is " + lMaxValue);
			lDFSMaxExpandNode = lMaxValue;
		}
		else if (iMAXFRINGE == iPara)
		{
			System.out.println("Max Fringe is " + lMaxValue);
			lDFSMaxFringe = lMaxValue;
		}
		else if (iMIXPARA == iPara)
		{
			System.out.println("Max MIXED Parameter is " + lMaxValue);
			lDFSMaxPara = lMaxValue;
		}

		cMaze = cHardMaze.HardMazeIntoMaze();
		cDFS  = new DFS(cMaze);
		cDFS.doDFS2();
		System.out.println("End " + cDFS.ShortestPath);
		cMaze = cHardMaze.HardMazeIntoMaze();
		return cMaze;	
	}
	
	public Maze MakeMazeHarderForBFS(Maze cOriginMaze, int iPara)
	{
		HardMaze   cHardMaze;
		Maze       cMaze;
		BFS        cBFS;
		long[]     glPara;
		long       lMaxValue = 0;
		int        iNo;
		long       lTemp = 0;
		cell       cCell;
		double     dMoveProb;   //probability of moving a wall

		//gdRuntime:0 for Origin, 1 for Up, 2 for Down, 3 for Left, 4 for Right, 5 for UpLe, 6 for UpRi, 7 for DownLe, 8 for DownRi;
		glPara = new long[9];
		
		//initial the class
		cMaze  = new Maze(iRANGE, dPROB);
		cHardMaze = new HardMaze();
		
		//caller guarantee that the maze definitely has a path
		cHardMaze.MazeIntoHardMaze(cOriginMaze);
		
		cCell = new cell();
		for (iNo = 0; iNo < cHardMaze.lstWall.size(); iNo++)
		{
			dMoveProb = Math.random();
			
			// do need to move
			if ((dMOVINGPROB < dMoveProb) || (true == cHardMaze.lstWall.get(iNo).bIsMoved) || (false == cHardMaze.lstWall.get(iNo).bIsWall))
			{
				continue;
			}
			
			//group reset
			for (int iIndexA = 0; iIndexA < 9; iIndexA++)
			{
				glPara[iIndexA] = 0;
			}
			
			cCell = cHardMaze.lstWall.get(iNo);
			cMaze = cHardMaze.HardMazeIntoMaze();
			cBFS  = new BFS(cMaze);
			cBFS.doBFS3();
			if (iPATHLEN == iPara)
			{
			    glPara[0] = (long)cBFS.ShortestPath;
			}
			else if (iEXPANDNODE == iPara)
			{
				glPara[0] = (long)cBFS.TotalNode;
			}
			else if (iMAXFRINGE == iPara)
			{
				glPara[0] = (long)cBFS.MaxF;
			}
			else if (iMIXPARA == iPara)
			{
				glPara[0] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
			}
			
			// move to up
			if (true == cHardMaze.MoveCellUp(cCell))
			{
				
                cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[1] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[1] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[1] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[1] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[1] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol]);
			}
			else
			{
				glPara[1] = 0;
			}
			
			// move to down
			if (true == cHardMaze.MoveCellDown(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[2] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[2] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[2] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[2] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[2] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol]);
			}
			else
			{
				glPara[2] = 0;
			}
			
			// move to left
			if (true == cHardMaze.MoveCellLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[3] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[3] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[3] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[3] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[3] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol - 1]);
			}
			else
			{
				glPara[3] = 0;
			}
			
			// move to right
			if (true == cHardMaze.MoveCellRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[4] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[4] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[4] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[4] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[4] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol + 1]);
			}
			else
			{
				glPara[4] = 0;
			}
			
			// move to upleft
			if (true == cHardMaze.MoveCellUpLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[5] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[5] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[5] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[5] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[5] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[5] = 0;
			}
			
			// move to upright
			if (true == cHardMaze.MoveCellUpRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[6] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[6] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[6] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[6] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[6] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol + 1]);
			}
			else
			{
				glPara[6] = 0;
			}
			
			// move to downleft
			if (true == cHardMaze.MoveCellDownLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[7] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[7] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[7] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[7] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[7] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[7] = 0;
			}
			
			// move to downright
			if (true == cHardMaze.MoveCellDownRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cBFS  = new BFS(cMaze);
				
				//find path then record, or record 0
				if (true == cBFS.doBFS3())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[8] = (long)cBFS.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[8] = (long)cBFS.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[8] = (long)cBFS.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[8] = (long)(dPATHLEN_W * cBFS.ShortestPath + dEXPANDNODE_W * cBFS.TotalNode + dMAXFRINGE_W * cBFS.MaxF);
					}
				}
				else
				{
					glPara[8] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol + 1]);				
			}
			else
			{
				glPara[8] = 0;
			}
			
			//find the max time
		    lTemp = cHardMaze.GetMax(glPara, cCell);
			if (lTemp > lMaxValue)
			{
				lMaxValue = lTemp;
				switch (cHardMaze.iMaxIndex)
				{
					case 1:  
						cHardMaze.MoveCellUp(cCell);
						cCell.bIsWall  = false;
						break;
					case 2:
						cHardMaze.MoveCellDown(cCell);
						cCell.bIsWall  = false;
						break;
					case 3:
						cHardMaze.MoveCellLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 4:
						cHardMaze.MoveCellRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 5:
						cHardMaze.MoveCellUpLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 6:
						cHardMaze.MoveCellUpRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 7:
						cHardMaze.MoveCellDownLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 8:
						cHardMaze.MoveCellDownRight(cCell);
						cCell.bIsWall  = false;
						break;
					default:
						break;
				}
			}
			
		}
		
		if (iPATHLEN == iPara)
		{
			System.out.println("Max Path Length is " + lMaxValue);
		    lBFSMaxPathLen = lMaxValue;
		}
		else if (iEXPANDNODE == iPara)
		{
			System.out.println("Max Expand Node is " + lMaxValue);
			lBFSMaxExpandNode = lMaxValue;
		}
		else if (iMAXFRINGE == iPara)
		{
			System.out.println("Max Fringe is " + lMaxValue);
			lBFSMaxFringe = lMaxValue;
		}
		else if (iMIXPARA == iPara)
		{
			System.out.println("Max MIXED Parameter is " + lMaxValue);
			lBFSMaxPara = lMaxValue;
		}

		cMaze = cHardMaze.HardMazeIntoMaze();
		cBFS  = new BFS(cMaze);
		cBFS.doBFS3();
		System.out.println("End " + cBFS.ShortestPath);
		cMaze = cHardMaze.HardMazeIntoMaze();
		return cMaze;	
	}
	
	public Maze MakeMazeHarderForEU(Maze cOriginMaze, int iPara)
	{
		HardMaze   cHardMaze;
		Maze       cMaze;
		Astar      cAStar;
		long[]     glPara;
		long       lMaxValue = 0;
		int        iNo;
		long       lTemp = 0;
		cell       cCell;
		double     dMoveProb;   //probability of moving a wall

		//gdRuntime:0 for Origin, 1 for Up, 2 for Down, 3 for Left, 4 for Right, 5 for UpLe, 6 for UpRi, 7 for DownLe, 8 for DownRi;
		glPara = new long[9];
		
		//initial the class
		cMaze  = new Maze(iRANGE, dPROB);
		cHardMaze = new HardMaze();
		
		//caller guarantee that the maze definitely has a path
		cHardMaze.MazeIntoHardMaze(cOriginMaze);
		
		cCell = new cell();
		for (iNo = 0; iNo < cHardMaze.lstWall.size(); iNo++)
		{
			dMoveProb = Math.random();
			
			// do need to move
			if ((dMOVINGPROB < dMoveProb) || (true == cHardMaze.lstWall.get(iNo).bIsMoved) || (false == cHardMaze.lstWall.get(iNo).bIsWall))
			{
				continue;
			}
			
			//group reset
			for (int iIndexA = 0; iIndexA < 9; iIndexA++)
			{
				glPara[iIndexA] = 0;
			}
			
			cCell = cHardMaze.lstWall.get(iNo);
			cMaze = cHardMaze.HardMazeIntoMaze();
			cAStar  = new Astar(cMaze);
			cAStar.doAstarEucilide2();
			if (iPATHLEN == iPara)
			{
			    glPara[0] = (long)cAStar.ShortestPath;
			}
			else if (iEXPANDNODE == iPara)
			{
				glPara[0] = (long)cAStar.TotalNode;
			}
			else if (iMAXFRINGE == iPara)
			{
				glPara[0] = (long)cAStar.MaxF;
			}
			else if (iMIXPARA == iPara)
			{
				glPara[0] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
			}
			
			// move to up
			if (true == cHardMaze.MoveCellUp(cCell))
			{
				
                cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//fing path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[1] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[1] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[1] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[1] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[1] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol]);
			}
			else
			{
				glPara[1] = 0;
			}
			
			// move to down
			if (true == cHardMaze.MoveCellDown(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[2] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[2] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[2] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[2] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[2] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol]);
			}
			else
			{
				glPara[2] = 0;
			}
			
			// move to left
			if (true == cHardMaze.MoveCellLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[3] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[3] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[3] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[3] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[3] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol - 1]);
			}
			else
			{
				glPara[3] = 0;
			}
			
			// move to right
			if (true == cHardMaze.MoveCellRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[4] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[4] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[4] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[4] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[4] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol + 1]);
			}
			else
			{
				glPara[4] = 0;
			}
			
			// move to upleft
			if (true == cHardMaze.MoveCellUpLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[5] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[5] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[5] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[5] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[5] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[5] = 0;
			}
			
			// move to upright
			if (true == cHardMaze.MoveCellUpRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[6] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[6] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[6] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[6] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[6] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol + 1]);
			}
			else
			{
				glPara[6] = 0;
			}
			
			// move to downleft
			if (true == cHardMaze.MoveCellDownLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[7] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[7] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[7] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[7] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[7] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[7] = 0;
			}
			
			// move to downright
			if (true == cHardMaze.MoveCellDownRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarEucilide2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[8] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[8] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[8] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[8] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[8] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol + 1]);				
			}
			else
			{
				glPara[8] = 0;
			}
			
			//find the max time
		    lTemp = cHardMaze.GetMax(glPara, cCell);
			if (lTemp > lMaxValue)
			{
				lMaxValue = lTemp;
				switch (cHardMaze.iMaxIndex)
				{
					case 1:  
						cHardMaze.MoveCellUp(cCell);
						cCell.bIsWall  = false;
						break;
					case 2:
						cHardMaze.MoveCellDown(cCell);
						cCell.bIsWall  = false;
						break;
					case 3:
						cHardMaze.MoveCellLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 4:
						cHardMaze.MoveCellRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 5:
						cHardMaze.MoveCellUpLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 6:
						cHardMaze.MoveCellUpRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 7:
						cHardMaze.MoveCellDownLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 8:
						cHardMaze.MoveCellDownRight(cCell);
						cCell.bIsWall  = false;
						break;
					default:
						break;
				}
			}
			
		}
		
		if (iPATHLEN == iPara)
		{
			System.out.println("Max Path Length is " + lMaxValue);
		    lEUMaxPathLen = lMaxValue;
		}
		else if (iEXPANDNODE == iPara)
		{
			System.out.println("Max Expand Node is " + lMaxValue);
			lEUMaxExpandNode = lMaxValue;
		}
		else if (iMAXFRINGE == iPara)
		{
			System.out.println("Max Fringe is " + lMaxValue);
			lEUMaxFringe = lMaxValue;
		}
		else if (iMIXPARA == iPara)
		{
			System.out.println("Max MIXED Parameter is " + lMaxValue);
			lEUMaxPara = lMaxValue;
		}

		cMaze = cHardMaze.HardMazeIntoMaze();
		cAStar  = new Astar(cMaze);
		cAStar.doAstarEucilide2();
		System.out.println("End " + cAStar.ShortestPath);
		cMaze = cHardMaze.HardMazeIntoMaze();
		return cMaze;
	}
	
	public Maze MakeMazeHarderForMA(Maze cOriginMaze, int iPara)
	{
		HardMaze   cHardMaze;
		Maze       cMaze;
		Astar      cAStar;
		long[]     glPara;
		long       lMaxValue = 0;
		int        iNo;
		long       lTemp = 0;
		cell       cCell;
		double     dMoveProb;   //probability of moving a wall

		//gdRuntime:0 for Origin, 1 for Up, 2 for Down, 3 for Left, 4 for Right, 5 for UpLe, 6 for UpRi, 7 for DownLe, 8 for DownRi;
		glPara = new long[9];
		
		//initial the class
		cMaze  = new Maze(iRANGE, dPROB);
		cHardMaze = new HardMaze();
		
		//caller guarantee that the maze definitely has a path
		cHardMaze.MazeIntoHardMaze(cOriginMaze);
		
		cCell = new cell();
		for (iNo = 0; iNo < cHardMaze.lstWall.size(); iNo++)
		{
			dMoveProb = Math.random();
			
			// do need to move
			if ((dMOVINGPROB < dMoveProb) || (true == cHardMaze.lstWall.get(iNo).bIsMoved) || (false == cHardMaze.lstWall.get(iNo).bIsWall))
			{
				continue;
			}
			
			//group reset
			for (int iIndexA = 0; iIndexA < 9; iIndexA++)
			{
				glPara[iIndexA] = 0;
			}
			
			cCell = cHardMaze.lstWall.get(iNo);
			cMaze = cHardMaze.HardMazeIntoMaze();
			cAStar  = new Astar(cMaze);
			cAStar.doAstarManhanttan2();
			if (iPATHLEN == iPara)
			{
			    glPara[0] = (long)cAStar.ShortestPath;
			}
			else if (iEXPANDNODE == iPara)
			{
				glPara[0] = (long)cAStar.TotalNode;
			}
			else if (iMAXFRINGE == iPara)
			{
				glPara[0] = (long)cAStar.MaxF;
			}
			else if (iMIXPARA == iPara)
			{
				glPara[0] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
			}
			
			// move to up
			if (true == cHardMaze.MoveCellUp(cCell))
			{
				
                cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//fing path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[1] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[1] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[1] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[1] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[1] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol]);
			}
			else
			{
				glPara[1] = 0;
			}
			
			// move to down
			if (true == cHardMaze.MoveCellDown(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[2] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[2] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[2] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[2] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[2] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol]);
			}
			else
			{
				glPara[2] = 0;
			}
			
			// move to left
			if (true == cHardMaze.MoveCellLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[3] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[3] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[3] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[3] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[3] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol - 1]);
			}
			else
			{
				glPara[3] = 0;
			}
			
			// move to right
			if (true == cHardMaze.MoveCellRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[4] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[4] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[4] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[4] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[4] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow][cCell.iCol + 1]);
			}
			else
			{
				glPara[4] = 0;
			}
			
			// move to upleft
			if (true == cHardMaze.MoveCellUpLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[5] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[5] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[5] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[5] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[5] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[5] = 0;
			}
			
			// move to upright
			if (true == cHardMaze.MoveCellUpRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[6] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[6] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[6] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[6] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[6] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow - 1][cCell.iCol + 1]);
			}
			else
			{
				glPara[6] = 0;
			}
			
			// move to downleft
			if (true == cHardMaze.MoveCellDownLeft(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[7] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[7] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[7] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[7] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[7] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol - 1]);
			}
			else
			{
				glPara[7] = 0;
			}
			
			// move to downright
			if (true == cHardMaze.MoveCellDownRight(cCell))
			{
				
				cMaze = cHardMaze.HardMazeIntoMaze();
				cAStar  = new Astar(cMaze);
				
				//find path then record, or record 0
				if (true == cAStar.doAstarManhanttan2())
				{
					if (iPATHLEN == iPara)
					{
					    glPara[8] = (long)cAStar.ShortestPath;
					}
					else if (iEXPANDNODE == iPara)
					{
						glPara[8] = (long)cAStar.TotalNode;
					}
					else if (iMAXFRINGE == iPara)
					{
						glPara[8] = (long)cAStar.MaxF;
					}
					else if (iMIXPARA == iPara)
					{
						glPara[8] = (long)(dPATHLEN_W * cAStar.ShortestPath + dEXPANDNODE_W * cAStar.TotalNode + dMAXFRINGE_W * cAStar.MaxF);
					}
				}
				else
				{
					glPara[8] = 0;
				}
				
				//recover to origin
				cHardMaze.SetCellToOrigin(cHardMaze.gMaze[cCell.iRow + 1][cCell.iCol + 1]);				
			}
			else
			{
				glPara[8] = 0;
			}
			
			//find the max time
		    lTemp = cHardMaze.GetMax(glPara, cCell);
			if (lTemp > lMaxValue)
			{
				lMaxValue = lTemp;
				switch (cHardMaze.iMaxIndex)
				{
					case 1:  
						cHardMaze.MoveCellUp(cCell);
						cCell.bIsWall  = false;
						break;
					case 2:
						cHardMaze.MoveCellDown(cCell);
						cCell.bIsWall  = false;
						break;
					case 3:
						cHardMaze.MoveCellLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 4:
						cHardMaze.MoveCellRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 5:
						cHardMaze.MoveCellUpLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 6:
						cHardMaze.MoveCellUpRight(cCell);
						cCell.bIsWall  = false;
						break;
					case 7:
						cHardMaze.MoveCellDownLeft(cCell);
						cCell.bIsWall  = false;
						break;
					case 8:
						cHardMaze.MoveCellDownRight(cCell);
						cCell.bIsWall  = false;
						break;
					default:
						break;
				}
			}
			
		}
		
		if (iPATHLEN == iPara)
		{
			System.out.println("Max Path Length is " + lMaxValue);
		    lMAMaxPathLen = lMaxValue;
		}
		else if (iEXPANDNODE == iPara)
		{
			System.out.println("Max Expand Node is " + lMaxValue);
			lMAMaxExpandNode = lMaxValue;
		}
		else if (iMAXFRINGE == iPara)
		{
			System.out.println("Max Fringe is " + lMaxValue);
			lMAMaxFringe = lMaxValue;
		}
		else if (iMIXPARA == iPara)
		{
			System.out.println("Max MIXED Parameter is " + lMaxValue);
			lMAMaxPara = lMaxValue;
		}

		cMaze = cHardMaze.HardMazeIntoMaze();
		cAStar  = new Astar(cMaze);
		cAStar.doAstarManhanttan2();
		System.out.println("End " + cAStar.ShortestPath);
		cMaze = cHardMaze.HardMazeIntoMaze();
		return cMaze;
		
	}
	
}

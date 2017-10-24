package sadden.maze.make;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UI extends JFrame {
	public String flag;
	public String flag2;
	public Draw test;
	public String RunTime;
	public String TotalNode;
	public String ShortestPath;
	public String MaxF;
	public String Difficulty;
	public Maze mazee;
	public Maze mazen;
	public int size;
	public int p;
	public long runTime;
	public int totalNodes;
	public int maxFringe;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int n; // size of each square
	public int situation;
	public static int[][] mazeMatrix;
	private JPanel contentPane;
	private JTextField textN;
	private JTextField textP;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 335, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStep = new JLabel("Step 1: Bild a maze");
		lblStep.setBounds(40, 6, 118, 16);
		contentPane.add(lblStep);

		JLabel lblSetSizeN = new JLabel("Set size N:");
		lblSetSizeN.setBounds(70, 34, 66, 16);
		contentPane.add(lblSetSizeN);

		JLabel lblSetPossibilityP = new JLabel("Set ratio of wall P:");
		lblSetPossibilityP.setBounds(70, 62, 124, 16);
		contentPane.add(lblSetPossibilityP);

		JLabel label = new JLabel("-------------------------------");
		label.setBounds(40, 119, 255, 16);
		contentPane.add(label);

		JLabel lblStep_1 = new JLabel("Step 2: Choose an algorithm");
		lblStep_1.setBounds(40, 139, 183, 16);
		contentPane.add(lblStep_1);

		JLabel label_1 = new JLabel("-------------------------------");
		label_1.setBounds(40, 207, 255, 16);
		contentPane.add(label_1);

		JLabel lblStepStill = new JLabel("Step 3: Too simple?");
		lblStepStill.setBounds(40, 226, 255, 16);
		contentPane.add(lblStepStill);

		JLabel lblmonitor = new JLabel("------------Monitor-------------");
		lblmonitor.setBounds(40, 470, 255, 16);
		contentPane.add(lblmonitor);

		textN = new JTextField(10);
		textN.setBounds(200, 29, 54, 26);
		contentPane.add(textN);
		textN.setColumns(10);

		textP = new JTextField(10);
		textP.setColumns(10);
		textP.setBounds(200, 57, 54, 26);
		contentPane.add(textP);

		JLabel lblRunningTime = new JLabel("Running time:");
		lblRunningTime.setBounds(70, 526, 88, 16);
		contentPane.add(lblRunningTime);

		JLabel lblTotalNodes = new JLabel("Total nodes:");
		lblTotalNodes.setBounds(70, 582, 88, 16);
		contentPane.add(lblTotalNodes);

		JLabel lblShortestPath = new JLabel("Shortest path:");
		lblShortestPath.setBounds(70, 554, 104, 16);
		contentPane.add(lblShortestPath);

		JLabel lblMaximumFringe = new JLabel("Maximum fringe:");
		lblMaximumFringe.setBounds(70, 610, 118, 16);
		contentPane.add(lblMaximumFringe);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(200, 521, 54, 26);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(200, 577, 54, 26);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(200, 549, 54, 26);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(200, 605, 54, 26);
		contentPane.add(textField_5);

		JLabel lblScore = new JLabel("Difficulty:");
		lblScore.setBounds(70, 638, 118, 16);
		contentPane.add(lblScore);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(200, 633, 54, 26);
		contentPane.add(textField_6);

		// Make a maze
		JButton btnMakeOne = new JButton("Make One");
		btnMakeOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textOfN = textN.getText();
				int numberOfN = Integer.parseInt(textOfN);
				String textOfP = textP.getText();
				double numberOfP = Double.parseDouble(textOfP);
				Maze maze = new Maze(numberOfN, numberOfP);
				int[][] mazeMatrix = maze.maze;
				mazen = maze;
				maze.showMaze();
				Draw test2 = new Draw(mazeMatrix);
				test2.setVisible(true);
			}
		});

		btnMakeOne.setBounds(90, 90, 150, 29);
		contentPane.add(btnMakeOne);

		JButton btnBfs = new JButton("BFS");
		btnBfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("BFS");
			}
		});
		btnBfs.setBounds(30, 165, 70, 30);
		contentPane.add(btnBfs);

		JButton btnDfs = new JButton("DFS");
		btnDfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("DFS");
			}
		});
		btnDfs.setBounds(95, 165, 70, 30);
		contentPane.add(btnDfs);

		JButton btnA = new JButton("A* M");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("A* M");
			}
		});
		btnA.setBounds(160, 165, 70, 30);
		contentPane.add(btnA);

		JButton btnAE = new JButton("A* E");
		btnAE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("A* E");
			}
		});
		btnAE.setBounds(225, 165, 70, 30);
		contentPane.add(btnAE);

		JLabel lblStatus = new JLabel("Found way out?");
		lblStatus.setBounds(70, 498, 104, 16);
		contentPane.add(lblStatus);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(200, 493, 54, 26);
		contentPane.add(textField);

		String[] x = { "Shortest Path", "Total Nodes", "Maximum Fringe", "Difficulty" };
		JComboBox<Object> comboBox_1 = new JComboBox<Object>(x);
		comboBox_1.setBounds(80, 402, 170, 27);
		contentPane.add(comboBox_1);

		// draw a harder Maze after clicking the Button"Make it harder!"
		JButton btnNewButton = new JButton("Make it harder!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag2 = (String) comboBox_1.getSelectedItem();
				drawMaze(flag);
			}
		});
		btnNewButton.setBounds(90, 429, 150, 29);
		contentPane.add(btnNewButton);

		JLabel lblMake = new JLabel("Create a simple maze with N=100, P=0.22");
		lblMake.setBounds(40, 254, 269, 16);
		contentPane.add(lblMake);

		JLabel lblThenChooseThe = new JLabel("How do you want to make it harder?");
		lblThenChooseThe.setBounds(40, 374, 255, 16);
		contentPane.add(lblThenChooseThe);

		JButton btnClickToCreat = new JButton("Click to Create");
		btnClickToCreat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Maze maze = new Maze(100, 0.22);
				int[][] mazeMatrix = maze.maze;
				mazen = maze;
				maze.showMaze();
				Draw test2 = new Draw(mazeMatrix);
				test2.setVisible(true);
			}
		});
		btnClickToCreat.setBounds(90, 282, 150, 29);
		contentPane.add(btnClickToCreat);

		JLabel lblChooseAnAlgorithm = new JLabel("Pick an algorithm to solve it");
		lblChooseAnAlgorithm.setBounds(40, 317, 269, 16);
		contentPane.add(lblChooseAnAlgorithm);

		JButton button = new JButton("BFS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("BFS");
				flag = "BFS_";
			}
		});
		button.setBounds(30, 345, 70, 30);
		contentPane.add(button);

		JButton button_1 = new JButton("DFS");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("DFS");
				flag = "DFS_";
			}
		});
		button_1.setBounds(95, 345, 70, 30);
		contentPane.add(button_1);

		JButton button_2 = new JButton("A* M");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("A* M");
				flag = "A* M_";
			}
		});
		button_2.setBounds(160, 345, 70, 30);
		contentPane.add(button_2);

		JButton button_3 = new JButton("A* E");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawMaze("A* E");
				flag = "A* E_";
			}
		});
		button_3.setBounds(225, 345, 70, 30);
		contentPane.add(button_3);
	}

	public void drawMaze(String choice) {
		int[][] mazeMatrix;
		BFS bfs = new BFS(mazen);
		DFS dfs = new DFS(mazen);
		Astar astar = new Astar(mazen);
		HardMaze hardMaze = new HardMaze();
		Maze maze = new Maze(1, 0);
		long difficulty;

		// clear the maze before drawing it
		for (int i = 0; i < mazen.maze.length; i++) {
			for (int j = 0; j < mazen.maze.length; j++) {
				if (mazen.maze[i][j] != 0 && mazen.maze[i][j] != 1) {
					mazen.maze[i][j] = 0;
				}
			}
		}

		//choose the maze to create
		switch (choice) {
		
		//use BFS to solve the maze
		case "BFS":
			bfs.doBFS3();
			mazeMatrix = bfs.maze.maze;
			test = new Draw(mazeMatrix);
			if (bfs.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(bfs.RunTime);
			TotalNode = Integer.toString(bfs.TotalNode);
			ShortestPath = Long.toString(bfs.ShortestPath);
			MaxF = Long.toString(bfs.MaxF);
			difficulty = (long) (0.3 * bfs.ShortestPath + 0.3 * bfs.TotalNode + 0.4 * bfs.MaxF);
			Difficulty = Long.toString(difficulty);
			break;

			//use DFS to solve the maze
		case "DFS":
			dfs.doDFS2();
			mazeMatrix = dfs.maze.maze;
			test = new Draw(mazeMatrix);
			if (dfs.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(dfs.RunTime);
			TotalNode = Integer.toString(dfs.TotalNode);
			ShortestPath = Long.toString(dfs.ShortestPath);
			MaxF = Long.toString(dfs.MaxF);
			difficulty = (long) (0.3 * dfs.ShortestPath + 0.3 * dfs.TotalNode + 0.4 * dfs.MaxF);
			Difficulty = Long.toString(difficulty);
			break;

			//use Astar_Mannhattan to solve the maze
		case "A* M":
			astar.doAstarManhanttan2();
			mazeMatrix = astar.maze.maze;
			astar.maze.showMaze();
			test = new Draw(mazeMatrix);
			if (astar.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(astar.RunTime);
			TotalNode = Integer.toString(astar.TotalNode);
			ShortestPath = Long.toString(astar.ShortestPath);
			MaxF = Long.toString(astar.MaxF);
			difficulty = (long) (0.3 * astar.ShortestPath + 0.3 * astar.TotalNode + 0.4 * astar.MaxF);
			Difficulty = Long.toString(difficulty);
			break;
			//use Astar_Eucilide to solve the maze
		case "A* E":
			astar.doAstarEucilide2();
			mazeMatrix = astar.maze.maze;
			test = new Draw(mazeMatrix);
			if (astar.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(astar.RunTime);
			TotalNode = Integer.toString(astar.TotalNode);
			ShortestPath = Long.toString(astar.ShortestPath);
			MaxF = Long.toString(astar.MaxF);
			difficulty = (long) (0.3 * astar.ShortestPath + 0.3 * astar.TotalNode + 0.4 * astar.MaxF);
			Difficulty = Long.toString(difficulty);
			break;
			
			//make maze with BFS harder in terms of:
			//1. make shortest path longer
			//2. make total nodes larger
			//3. make maximum fringe larger
			//4. make difficulty larger
		case "BFS_":
			switch (flag2) {
			case "Shortest Path":
				maze = hardMaze.MakeMazeHarderForBFS(mazen, 1);
				break;
			case "Total Nodes":
				maze = hardMaze.MakeMazeHarderForBFS(mazen, 2);
				break;
			case "Maximum Fringe":
				maze = hardMaze.MakeMazeHarderForBFS(mazen, 3);
				break;
			case "Difficulty":
				maze = hardMaze.MakeMazeHarderForBFS(mazen, 4);
				break;
			}
			bfs = new BFS(maze);
			bfs.doBFS3();
			mazeMatrix = bfs.maze.maze;
			test = new Draw(mazeMatrix);
			if (bfs.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(bfs.RunTime);
			TotalNode = Integer.toString(bfs.TotalNode);
			ShortestPath = Long.toString(bfs.ShortestPath);
			MaxF = Long.toString(bfs.MaxF);
			difficulty = (long) (0.3 * bfs.ShortestPath + 0.3 * bfs.TotalNode + 0.4 * bfs.MaxF);
			Difficulty = Long.toString(difficulty);
			break;
			
			//make maze with DFS harder in terms of:
			//1. make shortest path longer
			//2. make total nodes larger
			//3. make maximum fringe larger
			//4. make difficulty larger
		case "DFS_":
			switch (flag2) {
			case "Shortest Path":
				maze = hardMaze.MakeMazeHarderForDFS(mazen, 1);
				break;
			case "Total Nodes":
				maze = hardMaze.MakeMazeHarderForDFS(mazen, 2);
				break;
			case "Maximum Fringe":
				maze = hardMaze.MakeMazeHarderForDFS(mazen, 3);
				break;
			case "Difficulty":
				maze = hardMaze.MakeMazeHarderForDFS(mazen, 4);
				break;
			}
			dfs = new DFS(maze);
			dfs.doDFS2();
			mazeMatrix = dfs.maze.maze;
			test = new Draw(mazeMatrix);
			if (dfs.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(dfs.RunTime);
			TotalNode = Integer.toString(dfs.TotalNode);
			ShortestPath = Long.toString(dfs.ShortestPath);
			MaxF = Long.toString(dfs.MaxF);
			difficulty = (long) (0.3 * dfs.ShortestPath + 0.3 * dfs.TotalNode + 0.4 * dfs.MaxF);
			Difficulty = Long.toString(difficulty);
			break;
			
			//make maze with Astar_Mannhatten harder in terms of:
			//1. make shortest path longer
			//2. make total nodes larger
			//3. make maximum fringe larger
			//4. make difficulty larger
		case "A* M_":
			switch (flag2) {
			case "Shortest Path":
				maze = hardMaze.MakeMazeHarderForMA(mazen, 1);
				break;
			case "Total Nodes":
				maze = hardMaze.MakeMazeHarderForMA(mazen, 2);
				break;
			case "Maximum Fringe":
				maze = hardMaze.MakeMazeHarderForMA(mazen, 3);
				break;
			case "Difficulty":
				maze = hardMaze.MakeMazeHarderForMA(mazen, 4);
				break;
			}
			astar = new Astar(maze);
			astar.doAstarManhanttan2();
			mazeMatrix = astar.maze.maze;
			test = new Draw(mazeMatrix);
			if (astar.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(astar.RunTime);
			TotalNode = Integer.toString(astar.TotalNode);
			ShortestPath = Long.toString(astar.ShortestPath);
			MaxF = Long.toString(astar.MaxF);
			difficulty = (long) (0.3 * astar.ShortestPath + 0.3 * astar.TotalNode + 0.4 * astar.MaxF);
			Difficulty = Long.toString(difficulty);
			break;
			
			//make maze with Astar_Eucilide harder in terms of:
			//1. make shortest path longer
			//2. make total nodes larger
			//3. make maximum fringe larger
			//4. make difficulty larger
		case "A* E_":
			switch (flag2) {
			case "Shortest Path":
				maze = hardMaze.MakeMazeHarderForEU(mazen, 1);
				break;
			case "Total Nodes":
				maze = hardMaze.MakeMazeHarderForEU(mazen, 2);
				break;
			case "Maximum Fringe":
				maze = hardMaze.MakeMazeHarderForEU(mazen, 3);
				break;
			case "Difficulty":
				maze = hardMaze.MakeMazeHarderForEU(mazen, 4);
				break;
			}
			astar = new Astar(maze);
			astar.doAstarEucilide2();
			mazeMatrix = astar.maze.maze;
			test = new Draw(mazeMatrix);
			if (astar.success) {
				textField.setText("Yes");
			} else {
				textField.setText("No");
			}
			RunTime = Long.toString(astar.RunTime);
			TotalNode = Integer.toString(astar.TotalNode);
			ShortestPath = Long.toString(astar.ShortestPath);
			MaxF = Long.toString(astar.MaxF);
			difficulty = (long) (0.3 * astar.ShortestPath + 0.3 * astar.TotalNode + 0.4 * astar.MaxF);
			Difficulty = Long.toString(difficulty);
			break;
		}
		textField_2.setText(RunTime);
		textField_3.setText(TotalNode);
		textField_4.setText(ShortestPath);
		textField_5.setText(MaxF);
		textField_6.setText(Difficulty);
		test.setVisible(true);
	}

}

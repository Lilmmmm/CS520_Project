package sadden.maze.make;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class Draw extends JFrame {
	private static final long serialVersionUID = 1L;
	Container pane;
	int n;// size of each square
	int situation;
	int[][] mazeMatrix;
	private JPanel contentPane;
	double P;
	int N;

	/**
	 * Launch the application.
	 * @return 
	 */

	/**
	 * Create the frame.
	 */
	public Draw(int[][] mazeMatrix) {
		
		this.mazeMatrix=mazeMatrix;
		this.N=mazeMatrix.length;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20,100, 950, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}
	
	public void tryDraw()  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					Draw drawMaze= new Draw(mazeMatrix);				
					drawMaze.setVisible(true);
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//paint the maze
	//wall=1=black
	//road=0=empty
	//path=7=green
	public void paint(Graphics g) {
		//System.out.println("paint !");
		n = (int)(830/N);		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int x = 20 + j * n, y =  40 + i * n;
				g.setColor(Color.black);
				g.drawRect(x, y, n, n);
				if (mazeMatrix[i][j] == 1) {
					g.fillRect(x, y, n, n);
				}
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
					int x = 20 + j * n, y =  40 + i * n;
					switch(mazeMatrix[i][j]){
					case 5:
						g.setColor(Color.orange);
						g.fillRect(x + 1, y + 1, n - 2, n - 2);
						break;
					case 6:
						g.setColor(Color.red);
						g.fillRect(x + 1, y + 1, n - 2, n - 2);
						break;
					case 8:
						g.setColor(Color.blue);
						g.fillRect(x + 1, y + 1, n - 2, n - 2);
						break;	
					case 9:
						g.setColor(Color.yellow);
						g.fillRect(x + 1, y + 1, n - 2, n - 2);
						break;
					}
					
					
				
			}
		}

	}
}

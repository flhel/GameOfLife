package gameoflife;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLifeRun {

	JFrame frame;
	private int size;
	
	HashMap<Integer, Component> panels = new HashMap<Integer, Component>();
	
	/**
	 * Create the application.
	 */
	public GameOfLifeRun(int size) {
		this.size = size;
		initialize();
		start();
	}

	private void start() {
		GameMain game = new GameMain(size, 3, 2, 3, panels, frame);
		game.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(10, 10, (size * 10) + 16, (size * 10) + 39);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int panelNum = 0;
		for(int i = 0; i < size; i++) {
			for(int k = 0; k < size; k++) {
				JPanel panel = new JPanel();
				panel.setBounds(10 * k, 10 * i, 10, 10);
				panel.setName("p" + panelNum);
				panels.put(panelNum, panel); //Erstellung einer Hashmap mit den Panels für DrawGrid
				frame.getContentPane().add(panel);		
				panelNum++;
			}
		}
		frame.setVisible(true);
	}
/*	
	public void drawGrid(ArrayList<CellRow> grid) {
		for(int i = 0; i < grid.size(); i++) {
			
			CellRow c = grid.get(i);
			
			for(int k = 0; k < c.cellRow.size(); k++) {
				
				for(int g = 0; g < panels.length; g++) {
					if(panels[g].getName().equals("p" + i + k)) {
						if(c.cellRow.get(k).getAlive()) {
							panels[g].setBackground(Color.WHITE);
						} else {
							panels[g].setBackground(Color.BLACK);
						}
					}
				}				
			}
		}
	}	
*/	
}

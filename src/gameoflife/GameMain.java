package gameoflife;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameMain extends Thread {
	ArrayList<CellRow> grid;
	int neightboursToCreate;
	int neightboursToLiveMin;
	int neightboursToLiveMax;

	boolean gridAlive;
	HashMap<Integer, Component> panels;
	JFrame frame;

	public GameMain(int size, int neightboursToCreate, int neightboursToLiveMin, int neightboursToLiveMax,
			HashMap<Integer, Component> panels, JFrame frame) {
		//this.grid = generateGrid(size);
		this.grid = generateGridManual(size);

		this.neightboursToCreate = neightboursToCreate;
		this.neightboursToLiveMin = neightboursToLiveMin;
		this.neightboursToLiveMax = neightboursToLiveMax;
		this.panels = panels;
		this.frame = frame;
	}

	public void run() {

		gridAlive = true;
		HashSet<CellRow> hashGrid = new HashSet<CellRow>();

		while (gridAlive) {

			hashGrid.addAll(grid);
			DrawGrid draw = new DrawGrid(hashGrid, panels);
			draw.start();

			try {
				draw.join();
				checkGrid();
				refreshCellStatus();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//gridAlive = false; // debug automaten

			SwingUtilities.updateComponentTreeUI(frame);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// redraw the dead grid
		DrawGrid draw = new DrawGrid(hashGrid, panels);
		draw.start();

		this.interrupt();
		return;
	}

	private void checkGrid() {
		for (int i = 0; i < grid.size(); i++) {
			
			if(i < grid.size() - 1) {
				
			}
			

			CellRow rowBefore = null;
			CellRow rowMain = null;
			CellRow rowAfter = null;

			rowMain = grid.get(i);
			if (i >= 1) {
				rowBefore = grid.get(i - 1);
			}
			if (i < grid.size() - 1) {
				rowAfter = grid.get(i + 1);
			}
			
			if(i == (grid.size() - 2)) {
				//System.out.println(rowAfter.getCellRow().get(1).getPanelNum());
			}

			for (int k = 0; k < rowMain.getCellRow().size(); k++) {

				int neighboursAlive = 0;

				Cell cellBefore = null;
				Cell cellAfter = null;

				Cell cellMain = rowMain.getCellRow().get(k);

				if (k >= 1) {
					cellBefore = rowMain.getCellRow().get(k - 1);
				}
				if (k < rowMain.getCellRow().size() - 1) {
					cellAfter = rowMain.getCellRow().get(k + 1);
				}

				neighboursAlive = countAliveInBlock(cellBefore, null, cellAfter);

				if (rowBefore != null) {
					cellBefore = null;
					cellAfter = null;
					if (k >= 1) {
						cellBefore = rowBefore.getCellRow().get(k - 1);
					}
					if (k < rowMain.getCellRow().size() - 1) {
						cellAfter = rowBefore.getCellRow().get(k + 1);
					}
					neighboursAlive = neighboursAlive
							+ countAliveInBlock(cellBefore, rowBefore.getCellRow().get(k), cellAfter);
				}

				if (rowAfter != null) {
					cellBefore = null;
					cellAfter = null;
					if (k >= 1) {
						cellBefore = rowAfter.getCellRow().get(k - 1);
					}
					if (k < rowMain.getCellRow().size() - 1) {
						cellAfter = rowAfter.getCellRow().get(k + 1);
					}
					neighboursAlive = neighboursAlive
							+ countAliveInBlock(cellBefore, rowAfter.getCellRow().get(k), cellAfter);
				}

				cellMain.setNeighboursAlive(neighboursAlive);
			}
		}
	}

	private int countAliveInBlock(Cell cellBefore, Cell cellMid, Cell cellAfter) {
		int alive = 0;
		if (cellBefore != null && cellBefore.getAlive()) {
			alive++;
		}
		if (cellMid != null && cellMid.getAlive()) {
			alive++;
		}
		if (cellAfter != null && cellAfter.getAlive()) {
			alive++;
		}
		return alive;
	}

	private void refreshCellStatus() {
		gridAlive = false;
		for (CellRow c : grid) {
			for (Cell cell : c.getCellRow()) {
				if(cell.getAlive() == false) {
					if (cell.getNeighboursAlive() == neightboursToCreate) {
						cell.setAlive(true);
						gridAlive = true;
					}
				}
				if(cell.getAlive() == true) {
					if (cell.getNeighboursAlive() >= neightboursToLiveMin
					 && cell.getNeighboursAlive() <= neightboursToLiveMax ) {
						cell.setAlive(true);
						gridAlive = true;
					} else {
						cell.setAlive(false);
					}
				}
			}
		}
	}

	private static ArrayList<CellRow> generateGrid(int size) {

		ArrayList<CellRow> startGrid = new ArrayList<CellRow>();
		int panelNum = 0;
		for (int i = 0; i < size; i++) {
			CellRow row = new CellRow();
			for (int k = 0; k < size; k++) {
				Cell cell;
				if (0.5 < Math.random()) {
					cell = new Cell(panelNum, true);
				} else {
					cell = new Cell(panelNum, false);
				}
				row.getCellRow().add(cell);
				panelNum++;
			}
			startGrid.add(row);
		}

		return startGrid;
	}

	private static ArrayList<CellRow> generateGridManual(int size) {

		ArrayList<CellRow> startGrid = new ArrayList<CellRow>();
		int panelNum = 0;
		for (int i = 0; i < size; i++) {
			CellRow row = new CellRow();
			for (int k = 0; k < size; k++) {
				row.getCellRow().add(new Cell(panelNum, false));

				panelNum++;
			}
			startGrid.add(row);
		}
		
		CellRow row;
		ArrayList<Cell> cellRow;

		// ............
		row = startGrid.get(2);
		cellRow = row.getCellRow();

		cellRow.get(0).setAlive(true);
		cellRow.get(1).setAlive(true);
		cellRow.get(2).setAlive(true);

		startGrid.add(row);

		// ............
		row = startGrid.get(1);
		cellRow = row.getCellRow();

		cellRow.get(2).setAlive(true);

		startGrid.add(row);

		// ............
		row = startGrid.get(0);
		cellRow = row.getCellRow();

		cellRow.get(1).setAlive(true);

		startGrid.add(row);

		/*
		 * row = startGrid.get(5); cellRow = row.getCellRow();
		 * cellRow.get(6).setAlive(true); startGrid.add(row);
		 */

		return startGrid;
	}
}

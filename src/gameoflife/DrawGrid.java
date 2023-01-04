package gameoflife;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.HashSet;

public class DrawGrid extends Thread {
	HashMap<Integer, Component> panels;
	HashSet<CellRow> grid;
	
	public DrawGrid(HashSet<CellRow> grid, HashMap<Integer, Component> panels) {
		this.grid = grid;
		this.panels = panels;
	}
	
	public void run() {
		for(CellRow cellRow : grid) { 
			
			for(Cell cell : cellRow.getCellRow()) {
				
				Component panel = panels.get(cell.getPanelNum());
				
				if(cell.getAlive()) {
					panel.setBackground(Color.WHITE);
				} else {
					panel.setBackground(Color.BLACK);
				}
			}
		}
	}
}

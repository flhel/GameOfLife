package gameoflife;

public class Cell {
	
	private int panelNum;
	private boolean isAlive;
	private int neighboursAlive;

	public Cell(int panelNum, boolean isAlive) {
		this.setPanelNum(panelNum);
		this.setAlive(isAlive);
		setNeighboursAlive(0);
	}

	public boolean getAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getNeighboursAlive() {
		return neighboursAlive;
	}

	public void setNeighboursAlive(int neighboursAlive) {
		this.neighboursAlive = neighboursAlive;
	}

	public int getPanelNum() {
		return panelNum;
	}

	public void setPanelNum(int cellNumber) {
		this.panelNum = cellNumber;
	}
}

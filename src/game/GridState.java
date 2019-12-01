package game;

public class GridState {

	private int R,C;
	private short horizontalEdges [][];
	private short verticalEdges [][];
	private boolean isTerminal;
	private int boxCount;
	
	
	GridState(int numberOfRows, int numberOfColumns, short[] horizontals, short[] verticals){
		this.R = numberOfRows;
		this.C = numberOfColumns;
		
		this.horizontalEdges	= new short [R+1][C];  
		this.verticalEdges		= new short [R][C+1];
		
		this.initializeHorizontals(horizontals);
		this.initializeVerticals(verticals);
		this.countBoxes();
	}
	
	GridState(int numberOfRows, int numberOfColumns, short[][] horizontalEdges, short[][] verticalEdges){
		this.R = numberOfRows;
		this.C = numberOfColumns;
		
		this.horizontalEdges	= horizontalEdges;  
		this.verticalEdges		= verticalEdges;
	
		this.countBoxes();
	}
	
	void initializeHorizontals(short[] horizontals) {
		int k = 0;
		for(int i=0; i < R+1; i++) {
			for(int j=0; j < C; j++) {
				this.horizontalEdges[i][j] = horizontals[k];
				k++;
			}
		}
	}
	
	void initializeVerticals(short[] verticals) {
		int k = 0;
		for(int i=0; i < R; i++) {
			for(int j=0; j < C+1; j++) {
				this.verticalEdges[i][j] = verticals[k];
				k++;
			}
		}
	}
	
	private void terminalTest() {
		boolean terminal = true;
		
		for(int i=0; i < R+1; i++) {
			for(int j=0; j < C; j++) {
				if(this.horizontalEdges[i][j]==0) terminal = false;
			}
		}
		
		for(int i=0; i < R; i++) {
			for(int j=0; j < C+1; j++) {
				if(this.verticalEdges[i][j]==0) terminal = false;
			}
		}
		
		this.setTerminal(terminal);
	}
	
	private void countBoxes() {
		int boxCount = 0;
		short markCount;
		
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				markCount = 0;
				markCount += horizontalEdges[i][j];
				markCount += horizontalEdges[i+1][j];
				markCount += verticalEdges[i][j];
				markCount += verticalEdges[i][j+1];
				if(markCount == 4) boxCount++;
			}
		}
		
		this.boxCount = boxCount;
	}
	
	public int getR() {
		return R;
	}
	
	public int getC() {
		return C;
	}
	
	public short[][] getHorizontalEdges(){
		return horizontalEdges;
	}
	
	public short[][] getVerticalEdges(){
		return verticalEdges;
	}
	
	public boolean isTerminal() {
		return isTerminal;
	}

	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public int getBoxCount() {
		return boxCount;
	}

	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
}

package game;

public class Action {
	
	private EdgeType edgeType;
	private int row;
	private int column;
	
	public Action(EdgeType edgeType, int row, int column) {
		this.edgeType = edgeType;
		this.row = row;
		this.column = column;
	}
	public EdgeType getEdgeType() {
		return edgeType;
	}
	public void setEdgeType(EdgeType edgeType) {
		this.edgeType = edgeType;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
}

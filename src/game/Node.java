package game;

public class Node {

	private Node parentNode;
	private PlayerType player;
	private GridState gridState;
	private boolean isLeaf;
	private int utility;
	private Action action;
	private int redPlayerScore, bluePlayerScore;
	
	Node(Node parentNode, PlayerType player, GridState gridState, Action action, int redPlayerScore, int bluePlayerScore){
		this.parentNode = parentNode;
		this.player = player;
		this.gridState = gridState;
		this.action = action;
		this.redPlayerScore = redPlayerScore;
		this.bluePlayerScore = bluePlayerScore;
		
		int boxCountDifference = this.gridState.getBoxCount() - this.parentNode.getGridState().getBoxCount();
		
		if(boxCountDifference > 0) {
			if(player == PlayerType.RED) this.redPlayerScore++;
			if(player == PlayerType.BLUE) this.bluePlayerScore++;
		}
		
		if(this.gridState.isTerminal()) {
			this.setLeaf(true);
		}else {
			this.setLeaf(false);
		}
		
		if(this.isLeaf) this.utility = this.redPlayerScore - this.bluePlayerScore;
		
	}
	
	public Node getParentNode() {
		return parentNode;
	}

	public PlayerType getPlayer() {
		return player;
	}

	public GridState getGridState() {
		return gridState;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getUtility() {
		return utility;
	}

	public void setUtility(int utility) {
		this.utility = utility;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getRedPlayerScore() {
		return redPlayerScore;
	}

	public void setRedPlayerScore(int redPlayerScore) {
		this.redPlayerScore = redPlayerScore;
	}

	public int getBluePlayerScore() {
		return bluePlayerScore;
	}

	public void setBluePlayerScore(int bluePlayerScore) {
		this.bluePlayerScore = bluePlayerScore;
	}
	
}

package game;

public class Node {

	private Node parentNode;
	private PlayerType player;
	private GridState gridState;
	private boolean isLeaf;
	private int minimax;
	private Action action;
	private int redPlayerScore, bluePlayerScore;
	private boolean playerGainedPoint;
	
	Node(Node parentNode, PlayerType player, GridState gridState, Action action, int redPlayerScore, int bluePlayerScore){
		this.parentNode = parentNode;
		this.player = player; // The player that caused this node to be created
		this.gridState = gridState;
		this.action = action;
		this.redPlayerScore = redPlayerScore;
		this.bluePlayerScore = bluePlayerScore;
		
		int boxCountDifference;
		if(this.parentNode!=null) {
			boxCountDifference = this.gridState.getBoxCount() - this.parentNode.getGridState().getBoxCount();
		}else boxCountDifference = 0;
		
		
		if(boxCountDifference > 0) {
			if(player == PlayerType.RED) this.redPlayerScore += boxCountDifference;
			if(player == PlayerType.BLUE) this.bluePlayerScore += boxCountDifference;
			this.playerGainedPoint = true;
		}else this.playerGainedPoint = false;
		
		if(this.gridState.isTerminal()) {
			minimax = redPlayerScore - bluePlayerScore;
			this.setLeaf(true);
		}else {
			if((this.player == PlayerType.RED) && (playerGainedPoint==false)) minimax = gridState.getR() * gridState.getC();
			else if((this.player == PlayerType.RED) && (playerGainedPoint)) minimax = 0 - (gridState.getR() * gridState.getC());
			else if ((this.player == PlayerType.BLUE) && (playerGainedPoint==false)) minimax = 0 - (gridState.getR() * gridState.getC());
			else if ((this.player == PlayerType.BLUE) && (playerGainedPoint))  minimax = gridState.getR() * gridState.getC();
			else minimax = 0 - (gridState.getR() * gridState.getC()); 
		}
		
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

	public boolean isPlayerGainedPoint() {
		return playerGainedPoint;
	}

	public void setPlayerGainedPoint(boolean playerGainedPoint) {
		this.playerGainedPoint = playerGainedPoint;
	}

	public int getMinimax() {
		return minimax;
	}

	public void setMinimax(int minimax) {
		this.minimax = minimax;
	}
	
}

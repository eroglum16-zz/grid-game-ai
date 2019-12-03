package game;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private Node parentNode;
	private PlayerType player, nextPlayer;
	private GridState gridState;
	private boolean isLeaf;
	private Action action;
	private int redPlayerScore, bluePlayerScore;
	private List<Node> children; 
	
	Node(Node parentNode, PlayerType player, GridState gridState, Action action, int redPlayerScore, int bluePlayerScore){
		this.parentNode = parentNode;
		this.player = player; // The player that caused this node to be created
		this.gridState = gridState;
		this.action = action;
		this.redPlayerScore = redPlayerScore;
		this.bluePlayerScore = bluePlayerScore;
		this.children = new ArrayList<>();
		this.isLeaf = false;
		
		int boxCountDifference=0;
		if(this.parentNode!=null) {
			boxCountDifference = this.gridState.getBoxCount() - this.parentNode.getGridState().getBoxCount();
		}
		
		if(player == PlayerType.RED) {
			nextPlayer = PlayerType.BLUE;
		}else {
			nextPlayer = PlayerType.RED;
		}
		
		if(boxCountDifference > 0) {
			if(player == PlayerType.RED) {
				this.redPlayerScore += boxCountDifference;
				this.nextPlayer = PlayerType.RED;
			}
			if(player == PlayerType.BLUE) {
				this.bluePlayerScore += boxCountDifference;
				this.nextPlayer = PlayerType.BLUE;
			}
		}
		
		if(this.gridState.isTerminal()) {
			this.setLeaf(true);
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

	public List<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		this.children.add(child);
	}

	public PlayerType getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer(PlayerType nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	
}

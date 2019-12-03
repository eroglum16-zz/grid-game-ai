package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Agent {
	
	abstract int minimax(Node node);
	
	protected int numberOfNodesEvaluated; 
	
	private long runningTime;
	
	Agent(){
		numberOfNodesEvaluated = 0;
	}
	
	public int solve (Node initialNode) {
		
		int R = initialNode.getGridState().getR();
		int C = initialNode.getGridState().getC();
		
		List<Node> gameTree = new ArrayList<>();
		Queue<Node> fringe = new LinkedList<>();
		
		gameTree.add(initialNode);
		fringe.add(initialNode);
		
		Node node, newNode;
		GridState newGridState;
		short[][] newHorizontalEdges, newVerticalEdges;
		PlayerType currentPlayer;
		Action action;
		
		while(fringe.peek() != null) {
			node = fringe.poll();
			for(int i=0; i < R+1; i++) {
				for(int j=0; j < C; j++) {
					if(node.getGridState().getHorizontalEdges()[i][j] == 0) {
						newHorizontalEdges = new short[R+1][C];
						for(int k=0; k < R+1; k++) {
							System.arraycopy(node.getGridState().getHorizontalEdges()[k], 0, newHorizontalEdges[k], 0, node.getGridState().getHorizontalEdges()[i].length);

						}
						newHorizontalEdges[i][j] = 1;
						
						newGridState = new GridState(R, C, newHorizontalEdges, node.getGridState().getVerticalEdges());
						
						currentPlayer = node.getNextPlayer();
						
						action = new Action(EdgeType.HORIZONTAL, i, j);
						
						newNode = new Node(node, currentPlayer, newGridState, action, node.getRedPlayerScore(), node.getBluePlayerScore());
						
						node.addChild(newNode);

						gameTree.add(newNode);
						fringe.add(newNode);
					}
				}
			}
			for(int i=0; i < R; i++) {
				for(int j=0; j < C+1; j++) {
					if(node.getGridState().getVerticalEdges()[i][j] == 0) {
						newVerticalEdges = new short[R][C+1];
						for(int k=0; k < R; k++) {
							System.arraycopy(node.getGridState().getVerticalEdges()[k], 0, newVerticalEdges[k], 0, node.getGridState().getVerticalEdges()[i].length);

						}
						newVerticalEdges[i][j] = 1;
						
						newGridState = new GridState(R, C, node.getGridState().getHorizontalEdges(), newVerticalEdges);
						
						action = new Action(EdgeType.VERTICAL, i, j);
						
						currentPlayer = node.getNextPlayer();
						
						newNode = new Node(node, currentPlayer, newGridState, action, node.getRedPlayerScore(), node.getBluePlayerScore());
						
						node.addChild(newNode);
						
						gameTree.add(newNode);
						fringe.add(newNode);
					}
				}
			}	
		}
		
		long startTime = System.nanoTime();
		
		int result = this.minimax(gameTree.get(0));
		
		runningTime = System.nanoTime() - startTime;
		
		return result;
		
	}
	
	public long getRunningTime(){
		return runningTime;
	}
}

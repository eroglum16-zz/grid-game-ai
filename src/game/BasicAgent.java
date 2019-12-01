package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BasicAgent {
	
	int minimax(List<Node> gameTree) {
		
		for(int i = gameTree.size()-1; i > 0; i--) {
			if(gameTree.get(i).getPlayer() == PlayerType.RED) {
				if(gameTree.get(i).getParentNode().getMinimax() < gameTree.get(i).getMinimax()) {
					gameTree.get(i).getParentNode().setMinimax(gameTree.get(i).getMinimax());
				}
			}else if(gameTree.get(i).getPlayer() == PlayerType.BLUE) {
				if(gameTree.get(i).getParentNode().getMinimax() > gameTree.get(i).getMinimax()) {
					gameTree.get(i).getParentNode().setMinimax(gameTree.get(i).getMinimax());
				}
			}
		}
		
		return gameTree.get(0).getMinimax();
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
						
						// Playing again if gained a point
						if(node.isPlayerGainedPoint()) {
							currentPlayer = node.getPlayer();
						}else {
							if(node.getPlayer() == PlayerType.RED) currentPlayer = PlayerType.BLUE;
							else if(node.getPlayer() == PlayerType.BLUE) currentPlayer = PlayerType.RED;
							// For the initial node
							else currentPlayer = PlayerType.RED;
						}	
						
						action = new Action(EdgeType.HORIZONTAL, i, j);
						
						newNode = new Node(node, currentPlayer, newGridState, action, node.getRedPlayerScore(), node.getBluePlayerScore());
						
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

						// Playing again if gained a point	
						if(node.isPlayerGainedPoint()) {
							currentPlayer = node.getPlayer();
						}else {
							if(node.getPlayer() == PlayerType.RED) currentPlayer = PlayerType.BLUE;
							else if(node.getPlayer() == PlayerType.BLUE) currentPlayer = PlayerType.RED;
							// For the initial node
							else currentPlayer = PlayerType.RED;
						}		
						
						action = new Action(EdgeType.VERTICAL, i, j);
						
						newNode = new Node(node, currentPlayer, newGridState, action, node.getRedPlayerScore(), node.getBluePlayerScore());
						
						gameTree.add(newNode);
						fringe.add(newNode);
					}
				}
			}	
		}
		
		return this.minimax(gameTree);
		
		/*
		 for(int i=0; i < gameTree.size(); i++) {
			if(gameTree.get(i).getPlayer()!=null) System.out.println("Player: " + gameTree.get(i).getPlayer().toString());
			else System.out.println("Player: " + PlayerType.BLUE.toString());
			
			System.out.println("Red: " + gameTree.get(i).getRedPlayerScore());
			System.out.println("Blue: " + gameTree.get(i).getBluePlayerScore());
			
			for(int j=0; j<gameTree.get(i).getGridState().getHorizontalEdges().length; j++) {
				System.out.println("Horizontal (" + (j+1) + "): " + Arrays.toString(gameTree.get(i).getGridState().getHorizontalEdges()[j]));
			}
			
			for(int j=0; j<gameTree.get(i).getGridState().getVerticalEdges().length; j++) {
				System.out.println("Vertical (1): " + Arrays.toString(gameTree.get(i).getGridState().getVerticalEdges()[j]));
			}
			
			System.out.println("Box count: " + gameTree.get(i).getGridState().getBoxCount());
			for(int j=0; j<30; j++)System.out.print("-");
			System.out.println();
		}
		*/
		
	}
}

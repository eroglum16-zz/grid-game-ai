package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		
		String filename = args[0];
		
		InitialGridReader initialGridReader = new InitialGridReader(filename);
		
		int R = initialGridReader.getNumberOfRows();
		int C = initialGridReader.getNumberOfColumns();
		
		short[] horizontals = initialGridReader.getHorizontals();
		short[] verticals = initialGridReader.getVerticals();
		
		int redPlayerInitialScore = initialGridReader.getPlayerOneScore();
		int bluePlayerInitialScore = initialGridReader.getPlayerTwoScore();
		
		GridState initialGrid = new GridState(R,C, horizontals, verticals);
		
		Node initialNode = new Node(null, null, initialGrid, null, redPlayerInitialScore, bluePlayerInitialScore);
		
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
						newHorizontalEdges = node.getGridState().getHorizontalEdges();
						newHorizontalEdges[i][j] = 1;
						newVerticalEdges = node.getGridState().getVerticalEdges();
						
						newGridState = new GridState(R, C, newHorizontalEdges, newVerticalEdges);
						
						if(node.getPlayer() == PlayerType.RED) currentPlayer = PlayerType.BLUE;
						else if(node.getPlayer() == PlayerType.BLUE) currentPlayer = PlayerType.RED;
						// For the initial node
						else currentPlayer = PlayerType.RED;
						
						action = new Action(EdgeType.HORIZONTAL, i, j);
						
						newNode = new Node(node, currentPlayer, newGridState, action, node.getRedPlayerScore(), node.getBluePlayerScore());
						
						gameTree.add(newNode);
						fringe.add(newNode);
					}
				}
			}
		}
		
		

	}

}

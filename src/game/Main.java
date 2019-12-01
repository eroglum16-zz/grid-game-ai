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
		
		BasicAgent basicAgent = new BasicAgent();
		
		int minimaxValue = basicAgent.solve(initialNode);
		
		int p1Score, p2Score;
		if(minimaxValue < 0) {
			p1Score = R*C-Math.abs(minimaxValue);
			p2Score = Math.abs(minimaxValue);
		}else {
			p1Score = minimaxValue;
			p2Score = R*C-minimaxValue;
		}
		
		System.out.println(p1Score);
		System.out.println(p2Score);

	}

}

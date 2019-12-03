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
		//AdvancedAgent advancedAgent = new AdvancedAgent();
		
		int minimaxValue = basicAgent.solve(initialNode);
		
		int boxCount = R*C;
		
		System.out.println(minimaxValue);
		System.out.println(boxCount-minimaxValue);
		
		//System.out.println("Executed in " + (advancedAgent.getRunningTime()/1000) + " microseconds");
		//System.out.println(advancedAgent.numberOfNodesEvaluated);
		
	}

}

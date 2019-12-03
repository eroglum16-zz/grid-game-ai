package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BasicAgent extends Agent{
	
	@Override
	int minimax(Node node) {
		
		this.numberOfNodesEvaluated++;
		
		int minimaxValue, childMinimax;
		boolean maxPlaying;
		
		if(node.isLeaf()) return node.getRedPlayerScore();
		
		if(node.getNextPlayer() == PlayerType.RED) {
			minimaxValue = -24;
			maxPlaying = true;
		}else {
			minimaxValue = 24;
			maxPlaying = false;
		}
		
		for(int i = 0; i < node.getChildren().size(); i++) {
			childMinimax = minimax(node.getChildren().get(i));
			if(maxPlaying) {
				minimaxValue = Math.max(childMinimax, minimaxValue);
			}else{
				minimaxValue = Math.min(childMinimax, minimaxValue);
			}
		}
		return minimaxValue;
	}
}

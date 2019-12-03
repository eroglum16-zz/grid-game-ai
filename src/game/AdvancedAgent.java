package game;

import java.util.List;

public class AdvancedAgent extends Agent {
	
	@Override
	int minimax(Node node) {
		
		this.numberOfNodesEvaluated++;
		
		if(node.isLeaf()) return node.getRedPlayerScore();
		
		int minimaxValue, childMinimax;
		boolean maxPlaying;
		
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
				
				if(node.getAlpha() < minimaxValue) node.setAlpha(minimaxValue);
				try {
					if(node.getPlayer() == PlayerType.RED) {
						if(node.getParentNode().getAlpha() < minimaxValue) {
							node.getParentNode().setAlpha(minimaxValue);
						}
					}else {
						if(minimaxValue >= node.getParentNode().getBeta()) break;
					}
				}catch(NullPointerException e) {
					
				}
				
			}else{
				minimaxValue = Math.min(childMinimax, minimaxValue);
				
				if(node.getBeta() > minimaxValue) node.setBeta(minimaxValue);
				
				try {
					if(node.getPlayer() == PlayerType.RED) {
						if(minimaxValue <= node.getParentNode().getAlpha()) break;
					}else {
						if(node.getParentNode().getBeta() > minimaxValue) {
							node.getParentNode().setBeta(minimaxValue);
						}
					}
				}catch(NullPointerException e) {
					// No action
				}		
			}
		}
		try {
			if(maxPlaying) {
				node.setBeta(minimaxValue);
				if(node.getParentNode().getPlayer() != PlayerType.RED) {
					if(node.getParentNode().getBeta() > minimaxValue) {
						node.getParentNode().setBeta(minimaxValue);
					}
				}
			}else {
				node.setAlpha(minimaxValue);
				if(node.getParentNode().getPlayer() == PlayerType.RED) {
					if(node.getParentNode().getAlpha() < minimaxValue) {
						node.getParentNode().setAlpha(minimaxValue);
					}
				}
			}
		}catch(NullPointerException e) {
			//No action
		}	
		return minimaxValue;
		
	}

}

package game;

import java.io.IOException;
import java.io.RandomAccessFile;

public class InitialGridReader {
	
	private String fileName;
	private int numberOfRows, numberOfColumns, playerOneScore, playerTwoScore;
	private short[] horizontals;
	private short[] verticals;
	
	InitialGridReader (String filename){
		this.fileName = filename;
		this.readFile();
	}
	
	public int getNumberOfRows(){
		return this.numberOfRows;
	}
	
	public int getNumberOfColumns(){
		return this.numberOfColumns;
	}
	
	public int getPlayerOneScore(){
		return this.playerOneScore;
	}
	
	public int getPlayerTwoScore() {
		return this.playerTwoScore;
	}
	
	public short[] getHorizontals(){
		return this.horizontals;
	}
	
	public short[] getVerticals(){
		return this.verticals;
	}
	
	void readFile(){
		RandomAccessFile file;
		String line;
		int value;
		short shortValue, numberOfEmptyEdges=0;
		
		try {
			file = new RandomAccessFile(fileName, "r");
			
			//First Line
			line = file.readLine();
			if(line.length() != 1) {
				file.close();
				throw new IOException("Invalid input for the value R.");
			};
			try {
				value = Integer.parseInt(line);
				if((value < 1)|| (value > 3)) {
					file.close();
					throw new IOException("Constraint is not satisfied: 1 <= R <= 3");
				}
				this.numberOfRows = value;
			}catch(NumberFormatException e){
				System.out.println("The value for R should be an integer.");
			}
			
			//Second Line
			line = file.readLine();
			if(line.length() != 1) {
				file.close();
				throw new IOException("Invalid input for the value C.");
			};
			try {
				value = Integer.parseInt(line);
				if((value < 1)|| (value > 3)) {
					file.close();
					throw new IOException("Constraint is not satisfied: 1 <= C <= 3");
				}
				this.numberOfColumns = value;
			}catch(NumberFormatException e){
				System.out.println("The value for C should be an integer.");
			}
				
			//Horizontal edges
			this.horizontals = new short[numberOfColumns * (numberOfRows+1)];
			for(int i=0; i<horizontals.length; i++) {
				line = file.readLine();
				if(line.length() != 1) {
					file.close();
					throw new IOException("Invalid input for the horizontal edge value at line " + (i+3));
				};
				try {
					shortValue = Short.parseShort(line);
					if((shortValue < 0)|| (shortValue > 1)) {
						file.close();
						throw new IOException("Edge value should be 0 or 1. Invalid input at line " + (i+3));
					}
					this.horizontals[i] = shortValue;
					if(shortValue == 0) numberOfEmptyEdges++;
				}catch(NumberFormatException e) {
					System.out.println("Edge value should be 0 or 1. Invalid input at line " +  + (i+3));
				}
			}
			
			//Vertical edges
			this.verticals = new short[numberOfRows * (numberOfColumns+1)];
			for(int i=0; i<verticals.length; i++) {
				line = file.readLine();
				if(line.length() != 1) {
					file.close();
					throw new IOException("Invalid input for the vertical edge value at line " + (horizontals.length+i+3));
				};
				try {
					shortValue = Short.parseShort(line);
					if((shortValue < 0)|| (shortValue > 1)) {
						file.close();
						throw new IOException("Edge value should be 0 or 1. Invalid input at line " + (horizontals.length+i+3));
					}
					this.verticals[i] = shortValue;
					if(shortValue == 0) numberOfEmptyEdges++;
				}catch(NumberFormatException e) {
					System.out.println("Edge value should be 0 or 1. Invalid input at line " +  + (horizontals.length+i+3));
				}
			}
			
			// check numberOfEmptyEdges constraint
			if((numberOfEmptyEdges < 1)|| (numberOfEmptyEdges > 16)) {
				file.close();
				throw new IOException("Constraint is not satisfied: 1 <= #empty edges in the given grid <= 16");
			}
			
			// Initial score for player one
			line = file.readLine();
			if(line.length() != 1) {
				file.close();
				throw new IOException("Invalid input for the score of player one.");
			};
			try {
				value = Integer.parseInt(line);
				this.playerOneScore = value;
			}catch(NumberFormatException e){
				System.out.println("The value for the score of player one should be an integer.");
			}
			
			// Initial score for player two
			line = file.readLine();
			if(line.length() != 1) {
				file.close();
				throw new IOException("Invalid input for the score of player two.");
			};
			try {
				value = Integer.parseInt(line);
				this.playerTwoScore = value;
			}catch(NumberFormatException e){
				System.out.println("The value for the score of player two should be an integer.");
			}
			
			file.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class Board {
	
	//constructor for Board
	public Board() {
		
	}
	
	public Location[][] createBoard() {
		Location[][] board = new Location[5][5];
		return null;
		
	}
	
	public List shuffle(List<String> list, Random rnd) { 
		
		try {
			String filename = "src/code/words.txt";
			for (String line : Files.readAllLines(Paths.get(filename))) {
				list.add(line); 
				rnd.nextInt(list.size() - 1); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list;  
		}
	}


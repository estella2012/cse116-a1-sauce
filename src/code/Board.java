package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
	
	//constructor for Board
	public Board() {
		
	}
	
	public Location[][] createBoard() {
		Location[][] board = new Location[5][5];
		return null;
		
	}
	
	public ArrayList<String> createList() { 
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> codenameList = new ArrayList<String>();

		try {
			String filename = "src/code/words.txt";
			for (String line : Files.readAllLines(Paths.get(filename))) {
				list.add(line);
			}
			Collections.shuffle(list);
			for(int i = 0; i < 25; i++) {
				codenameList.add(list.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return codenameList;  
		}
	
}
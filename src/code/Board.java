package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
	
	private boolean redTeamTurn;
	
	private ArrayList<Location> board;

	private ArrayList<String> wordList;

	private ArrayList<String> personList;
	
	//constructor for Board
	public Board() {
		
	}
	
	public void createBoard() {
		board = new ArrayList<Location>();
	}
	
	public ArrayList<String> createListOfWords() { 
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
	
	public ArrayList<String> createListOfPersons(){
		ArrayList<String> list = new ArrayList<>();
		String red = "red";
		String blue = "blue";
		String innocent = "innocent";
		String assassin = "assassin";
		for(int i = 0; i < 9; i++) {
			list.add(red);
		}
		for(int i = 0; i < 8; i++) {
			list.add(blue);
		}
		for(int i = 0; i < 7; i++) {
			list.add(innocent);
		}
		list.add(assassin);
		Collections.shuffle(list);
		return list;	
	}
	
	public void gameStart() {
		redTeamTurn = true;
		wordList = createListOfWords();
		personList = createListOfPersons();
		for(int i = 0; i < 25; i++) {
			board.get(i).setCodename(wordList.get(i));
			board.get(i).setPerson(personList.get(i));
		}
	}
	
	public boolean checkIllegalClue(String clue) {
		boolean legalClue = true;
		for(int i = 0; i < 25; i++) {
			boolean check = board.get(i).isNotRevealed();
			if(check) {
				if(clue == board.get(i).getCodename()) {
					legalClue = false;
				}
			}
		}
		return legalClue;
	}
	
	public boolean checkGuess(int count) {
		
		return true;
	}
}
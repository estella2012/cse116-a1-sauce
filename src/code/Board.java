package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
	
	//boolean used to keep track of which team's turn it is
	private boolean redTeamTurn;
	//the 25 locations
	private ArrayList<Location> board;
	//the 25 words for each location
	private ArrayList<String> wordList;
	//the 25 assignments for each location
	private ArrayList<String> personList;
	//the number associated with each clue
	private int count;
	//the word at the location of the guess
	private String guess ;
	//an int used to keep track of how many blue locations are left on the board
	private int bluesLeft;
	//an int used to keep track of how many blue locations are left on the board
	private int redsLeft;
	
	//constructor for Board
	public Board() {
		
	}
	
	public void setCount(int x) {
		count = x;
	}
	
	public void createBoard() {
		board = new ArrayList<Location>();
		redsLeft = 9;
		bluesLeft = 8;
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
		setRedTeamTurn(true);
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
	
	public boolean checkGuess() {
		boolean isTeamAgent = false;
		setCount(count - 1);
		for(int i = 0; i < 25; i++) {
			if(guess == board.get(i).getCodename()) {
				board.get(i).setNotRevealed(false);
				if(board.get(i).getPerson() == "blue" && redTeamTurn == false) {
					isTeamAgent = true;
					bluesLeft = bluesLeft - 1;
				}
				if(board.get(i).getPerson() == "red" && redTeamTurn == true) {
					isTeamAgent = true;
					redsLeft = redsLeft - 1;
				}
			}
		}
		return isTeamAgent;
	}
	
	public boolean gameWon() {
		boolean gameOver = false;
		for (int index = 0; index < 25; index++) {
			if(redsLeft == 0 || bluesLeft == 0) {
				return true;
			}
		}
		return gameOver;
	}

	public boolean isRedTeamTurn() {
		return redTeamTurn;
	}

	public void setRedTeamTurn(boolean redTeamTurn) {
		this.redTeamTurn = redTeamTurn;
	}

}
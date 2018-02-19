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
	
	/*
	 * Initiate the the board and the number of words left for both teams
	 */
	public void createBoard() {
		board = new ArrayList<Location>();
		redsLeft = 9;
		bluesLeft = 8;
	}
	
	/*
	 * Shuffle all the words in the text file and pick the first 25 in to the game play
	 */
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
	
	/*
	 * Assign 9 red, 8 blue, 7 innocent and 1 assassin into a list, shuffle it,
	 * which will create a list that can randomly assign a person to the codename.
	 */
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
	
	/*
	 * Initiate everything to begin the game.
	 * Red team will be the first to go.
	 * Create a list of random words, a list of random persons, and assign them to the board.
	 */
	public void gameStart() {
		setRedTeamTurn(true);
		wordList = createListOfWords();
		personList = createListOfPersons();
		for(int i = 0; i < 25; i++) {
			board.get(i).setCodename(wordList.get(i));
			board.get(i).setPerson(personList.get(i));
		}
	}
	
	/*
	 * Check is the codename revealed or not, if it is not revealed, that 
	 * is not a legal clue
	 */
	public boolean checkIllegalClue(String clue) {
		boolean legalClue = true;
		for(int i = 0; i < 25; i++) {
			boolean check = board.get(i).isNotRevealed();
			if(check) {
				if(clue.equals(board.get(i).getCodename())) {
					legalClue = false;
				}
			}
		}
		return legalClue;
	}
	
	/*
	 * Check if the team's guess is right. If right, make the word revealed, 
	 * and reduce the number of word left for that team by 1.
	 */
	public boolean checkGuess() {
		boolean isTeamAgent = false;
		setCount(count - 1);
		for(int i = 0; i < 25; i++) {
			if(guess.equals(board.get(i).getCodename())) {
				board.get(i).setNotRevealed(false);
				if(board.get(i).getPerson().equals("blue") && redTeamTurn == false) {
					isTeamAgent = true;
					bluesLeft = bluesLeft - 1;
				}
				if(board.get(i).getPerson().equals("red") && redTeamTurn == true) {
					isTeamAgent = true;
					redsLeft = redsLeft - 1;
				}
			}
		}
		return isTeamAgent;
	}
	
	/*
	 * If one of the team's word left turn to zero, stop the game.
	 */
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
	
	public String whichTeamWonAssassin() {
		String winningTeam = "no one won";
		if(redTeamTurn) {
			winningTeam = "blue";
		}
		else {
			winningTeam = "red";
		}
		return winningTeam;
	}
}
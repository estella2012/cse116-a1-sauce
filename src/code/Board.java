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
	private Location[] board;
	//the 25 words for each location
	private ArrayList<String> wordList;
	//the 25 assignments for each location
	private ArrayList<String> personList;
	//the number associated with each clue
	private int count;
	//the word at the location of the guess
	private String guess;
	//an int used to keep track of how many blue locations are left on the board
	private int bluesLeft;
	//an int used to keep track of how many blue locations are left on the board
	private int redsLeft;
	
	/*
	 * Initializes wordList and personList
	 */
	public Board() {
		wordList = new ArrayList<>();
		personList = new ArrayList<>();
	}
	
	/*
	 * Sets the amount of words that the given clue pertains to,
	 * as well as the amount of guesses the guessing side can have.
	 */
	public void setCount(int x) {
		count = x;
	}
	
	/*
	 * Initiate the the board and the number of words left for both teams
	 * Creates a new location for each space on the board
	 */
	public void createBoard() {
		setBoard(new Location[25]);
		for(int index = 0; index < 25; index++) {
			getBoard()[index] = new Location();
			getBoard()[index].setNotRevealed(true);
		}
		setRedsLeft(9);
		setBluesLeft(8);
		//setRedsLeft((int) Math.ceil(Math.random() * 100));
	}
	
	/*
	 * Shuffle all the words in the text file and pick the first 25 in to the game play
	 */
	public ArrayList<String> createListOfWords(String filename) { 
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> codenameList = new ArrayList<String>();
		try {
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
	 * Puts 9 red, 8 blue, 7 innocent and 1 assassin into a list, shuffles it,
	 * which will create a list that can randomly assign a person to the codename in a later method.
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
	 */
	public void gameStart(String filename) {
		setRedTeamTurn(true);
		assignPeople(filename);
	}
	
	/*
	 * Create a list of random codenames, a list of random persons, and assign them to the board.
	 */
	public void assignPeople(String filename) {
		wordList = createListOfWords(filename);
		personList = createListOfPersons();
		createBoard();
		for(int i = 0; i < 25; i++) {
			getBoard()[i].setCodename(wordList.get(i));
			getBoard()[i].setPerson(personList.get(i));
		}
	}
	
	/*
	 * Check is the codename revealed or not, if it is not revealed, that 
	 * is not a legal clue
	 */
	public boolean checkIllegalClue(String clue) {
		boolean illegalClue = false;
		String fixedClue = clue.toUpperCase();
		for(int i = 0; i < 25; i++) {
				if(fixedClue.equals(getBoard()[i].getCodename().toUpperCase())) {
					if(getBoard()[i].isNotRevealed()) {
					return true;
					}
			}
		}
		return illegalClue;
	}
	
	/*
	 * Reveals spot on board and checks if the team's guess is right. 
	 * If right, returns that it was the team's agent. 
	 * If wrong (other team, innocent, or assassin), returns that it was not the team's agent.
	 * If the guess belongs to either team, it reduces the amount of that color by 1.
	 * If the team guesses the assassin, runs whichTeamWonAssassin. 
	 */
	public boolean checkGuess(String guess) {
		setCount(count - 1);
		setGuess(guess);
		for(int i = 0; i < 25; i++) {
			if(guess.equals(getBoard()[i].getCodename())) {
				getBoard()[i].setNotRevealed(false);
				if(getBoard()[i].getPerson().equals("blue") && redTeamTurn == false) {
					setBluesLeft(getBluesLeft() - 1);
					return true;
				}
				if(getBoard()[i].getPerson().equals("red") && redTeamTurn == false) {
					setRedsLeft(getRedsLeft() - 1);
					return false;
				}
				if(getBoard()[i].getPerson().equals("blue") && redTeamTurn == true) {
					setBluesLeft(getBluesLeft() - 1);
					return false;
				}
				if(getBoard()[i].getPerson().equals("red") && redTeamTurn == true) {
					setRedsLeft(getRedsLeft() - 1);
					return true;
				}
				if(getBoard()[i].getPerson().equals("innocent")) {
					return false;
				}
				if(getBoard()[i].getPerson().equals("assassin")) {
					//whichTeamWonAssassin(); I think this line is something we'll need to work with when we create a MainGUI but for now it can't do much
					return false;
				}
			}
		}
		return false;
	}
	
	
	/*
	 * If one of the team's word left turn to zero, stop the game.
	 */
	public boolean gameWon() {
			if(getRedsLeft() == 0 || getBluesLeft() == 0) {
				return true;
			}
		return false;
	}
	
	/*
	 * Getter and setter for whose turn it is.
	 */
	public boolean isRedTeamTurn() {
		return redTeamTurn;
	}

	public void setRedTeamTurn(boolean redTeamTurn) {
		this.redTeamTurn = redTeamTurn;
	}
	
	/*
	 * When the assassin is chosen, checks whose turn it is and returns whose team won as a result. 
	 */
	public String whichTeamWonAssassin() {
		String winningTeam;
		if(redTeamTurn) {
			winningTeam = "blue";
		}
		else {
			winningTeam = "red";
		}
		return winningTeam;
	}

	/*
	 * Generic getters and setters for variables.
	 */
	
	public Location[] getBoard() {
		return board;
	}

	public void setBoard(Location[] board) {
		this.board = board;
	}

	public int getRedsLeft() {
		return redsLeft;
	}

	public void setRedsLeft(int redsLeft) {
		this.redsLeft = redsLeft;
	}

	public int getBluesLeft() {
		return bluesLeft;
	}

	public void setBluesLeft(int bluesLeft) {
		this.bluesLeft = bluesLeft;
	}
	public String getGuess() {
		return guess;
	}
	public void setGuess(String yourGuess) {
		guess = yourGuess;
	}
}
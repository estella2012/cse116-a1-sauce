package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
	
	private int turnCount;
	//0 is red, 1 is blue, 2 is green
	private boolean twoPlayerGame;
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
	
	private int greensLeft;
	
	private int assassinsLeft;
	
	private int teamOut;
	
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
		if(twoPlayerGame) {
		setRedsLeft(9);
		setBluesLeft(8);
		}
		else {
			setRedsLeft(6);
			setBluesLeft(5);
			setGreensLeft(5);
		}
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
	 * if two-player,Puts 9 red, 8 blue, 7 innocent and 1 assassin into a list, shuffles it,
	 * if three-player, Puts 6 red, 5 blue, 5 green, 7 innocents, and 2 assassins 
	 * which will create a list that can randomly assign a person to the codename in a later method.
	 */
	public ArrayList<String> createListOfPersons(){
		ArrayList<String> list = new ArrayList<>();
		String red = "red";
		String blue = "blue";
		String innocent = "innocent";
		String assassin = "assassin";
		String green = "green";
		if(twoPlayerGame){
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
		}
		else {
			for(int i = 0; i < 6; i++) {
				list.add(red);
			}
			for(int i = 0; i < 5; i++) {
				list.add(blue);
			}
			for(int i = 0; i < 5; i++) {
				list.add(green);
			}
			for(int i = 0; i < 7; i++) {
				list.add(innocent);
			}
			list.add(assassin);
			list.add(assassin);	
		}
		Collections.shuffle(list);
		return list;
	}
	
	/*
	 * Initiate everything to begin the game.
	 * Red team will be the first to go in both 2player and 3player.
	 */
	public void gameStart(String filename) {
		if(!twoPlayerGame) {
			assassinsLeft = 2;
		}
		teamOut = 7;
		turnCount = 0;
		assignPeople(filename);
		count = 0;
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
	 * Check if the codename is revealed or not, if it is not revealed, that 
	 * is not a legal clue
	 */
	public boolean checkIllegalClue(String clue) {
		boolean illegalClue = false;
		if(clue.contains(" ") || clue.isEmpty()) {
			return true;
		}
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
		if(count != 0) {
			setCount(count - 1);
			}
		setGuess(guess);
		for(int i = 0; i < 25; i++) {
			if(guess.equals(getBoard()[i].getCodename())) {
				getBoard()[i].setNotRevealed(false);
				if(getBoard()[i].getPerson().equals("red")) {
					setRedsLeft(getRedsLeft() - 1);
					if(turnCount == 0) {
						return true;
					}
					else {
						return false;
					}
				}
				if(getBoard()[i].getPerson().equals("blue")) {
					setBluesLeft(getBluesLeft() - 1);
					if(turnCount == 1) {
						return true;
					}
					else {
						return false;
					}
				}
				if(getBoard()[i].getPerson().equals("green")) {
					setGreensLeft(getGreensLeft() - 1);
					if(turnCount == 2) {
						return true;
					}
					else {
						return false;
					}
				}
				if(getBoard()[i].getPerson().equals("innocent")) {
					count = 0;
					return false;
				}
				if(getBoard()[i].getPerson().equals("assassin")) {
					count = 0;
					return false;
				}
			}
		}
		return true;
	}
	
	
	/*
	 * If one of the team's word left turn to zero, stop the game.
	 */
	public boolean gameWon() {
			if((getRedsLeft() == 0 && teamOut != 0)|| (getBluesLeft() == 0 && teamOut != 1)|| (getGreensLeft() == 0 && teamOut != 2)) {
				return true;
			}
		return false;
	}
	/*
	 * updates the turnCount by one, if it goes over the amount for its respective gamemode it goes back to 0 (redteam)
	 * if the team is out (chose the first assassin in 3Team game) then skips their turn 
	 * uses magic ;)
	 * s/o my mans Shia
	 */
	public void updateTurn() {
		if(count == 0) {
			
		turnCount++;
		
		if(twoPlayerGame) {
			if(turnCount > 1) { 
				turnCount = 0;
			}
		}
		else { 
			if (turnCount > 2) { 
				turnCount = 0;
			}
	}
		if (turnCount == teamOut) {
			updateTurn();
		}
		}
	}
	/*
	 * Getter and setter for whose turn it is.
	 */
	
	/*
	 * When the assassin is chosen, checks whose turn it is and returns whose team won as a result. 
	 */
	public String whichTeamWonAssassin() {
		String winningTeam = "";
		if(twoPlayerGame) {
		if(turnCount == 0) {
			winningTeam = "blue";
		}
		else {
			winningTeam = "red";
		}
		}
		else {
			if(assassinsLeft == 2) {
				assassinsLeft = assassinsLeft - 1;
				teamOut = turnCount;
				return "no one" + teamOut;
			}
			if(assassinsLeft == 1) {
				if(turnCount == 0 && teamOut == 1 || turnCount == 1 && teamOut == 0) {
					return "green";
				}
				if(turnCount == 0 && teamOut == 2 || turnCount == 2 && teamOut == 0) {
					return "blue";
				}
				if(turnCount == 1 && teamOut == 2 || turnCount == 2 && teamOut == 1) {
					return "red";
				}
			}
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

	public int getCount() {
		return count;
	}

	public int getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	public int getGreensLeft() {
		return greensLeft;
	}

	public void setGreensLeft(int greensLeft) {
		this.greensLeft = greensLeft;
	}
	
	public boolean isTwoPlayerGame() {
		return twoPlayerGame;
	}
	
	public void setTwoPlayerGame(boolean game) {
		twoPlayerGame = game;
	}
	
	public int getTeamOut() {
		return teamOut;
	}
	
	public void setTeamOut(int teamOut) {
		this.teamOut = teamOut;
	}
}
package tests;


import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

import code.Board;

public class BoardTest {
	
	/*
	 * Tests that createListOfWords picks 25 random words from the list.
	 */
	@Test
	public void testShuffle() {
		//below is Krishna's attempt to create a list that didn't help anyone at all so we will leave it here as a reminder
		/*	list2.add("word"); 
		list2.add("chicken"); 
		list2.add("fried"); 
		list2.add("vegetable"); 
		list2.add("boarding"); 
		list2.add("boards"); 
		list2.add("hello"); 
		list2.add("jello"); 
		list2.add("spice"); 
		list2.add("cake"); 
		list2.add("spongebob"); 
		list2.add("bobby"); 
		list2.add("almonds"); 
		list2.add("nuts"); 
		list2.add("phone"); 
		list2.add("coffee"); 
		list2.add("black"); 
		list2.add("panther"); 
		list2.add("marvel"); 
		list2.add("yoplait"); 
		list2.add("yogurt"); 
		list2.add("smiley"); 
		list2.add("isacc"); 
		list2.add("derek"); 
		list2.add("ishmam"); */
		

		ArrayList<String> list = new ArrayList<String>();
		Board bob = new Board();
		list = bob.createListOfWords("src/GameWords.txt");
		
		String tester = "AFRICAAGENTAIRALIENALPSAMAZON";
		String testee = "";
		
		for(int i = 0; i < 5; i++) {
			testee += list.get(i);
		}
		
		assertEquals(25, list.size());
		assertFalse(tester.equals(testee));
	}
	
	/*
	 * Checks that createListOfPersons makes a randomized list of assignments.
	 */
	@Test
	public void testCreateListOfPersons() {
		Board bob = new Board();
		ArrayList<String> list = new ArrayList<String>();
		list = bob.createListOfPersons();
		//test list size
		assertEquals(25, list.size());
		//test shuffle
		
		String tester = "redredredredred";
		String testee = "";
		
		for(int i = 0; i < 5; i++) {
			testee += list.get(i);
		}
		assertFalse(tester.equals(testee));
	}
	
	/*
	 * Checks that gameStart sets the turn correctly.
	 */
	@Test
	public void testGameStart() {
		Board bob = new Board();
		bob.createBoard();
		bob.setRedTeamTurn(false);
		bob.gameStart("src/GameWords.txt");
		assertTrue(bob.isRedTeamTurn());	
	}
	
	/*
	 * Checks if checkIllegalClue can properly determine whether or not a clue is legal.
	 */
	@Test
	public void checkIllegalClueTest() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		assertFalse(br.checkIllegalClue("number"));
		br.getBoard()[5].setCodename("number");
		assertTrue(br.checkIllegalClue(br.getBoard()[0].getCodename()));
	}
	
	/*
	 * Tests that gameWon can determine if the board is in a winning state.
	 */
	@Test 
	public void gameWonTest() { 
		Board br = new Board();
		br.gameStart("src/GameWords.txt");
		assertFalse(br.gameWon());
		br.setBluesLeft(5);
		br.setRedsLeft(0);
		assertTrue(br.gameWon());
		br.setBluesLeft(0);
		br.setRedsLeft(5);
		assertTrue(br.gameWon());
	}
	
	/*
	 * Tests that the turn can change.
	 */
	@Test
	public void testSetRedTeamTurn() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		assertTrue(br.isRedTeamTurn());
		br.setRedTeamTurn(false);
		assertFalse(br.isRedTeamTurn());
		br.setRedTeamTurn(true);
		assertTrue(br.isRedTeamTurn());
	}
	
	/*
	 * Checks that whichTeamWonAssassin will return the proper team in the case of the Assassin being chosen.
	 */
	@Test
	public void testWhichTeamWonAssassin() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		assertEquals("blue", br.whichTeamWonAssassin());
		br.setRedTeamTurn(false);
		assertEquals("red", br.whichTeamWonAssassin());
	}
	
	/*
	 * Checks that gameStart properly runs the methods it uses.
	 */
	@Test
	public void gameStartTest() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		assertTrue(br.isRedTeamTurn());
		for(int index = 0; index < 25; index++) {
			assertNotNull(br.getBoard()[index].getCodename());
			assertNotNull(br.getBoard()[index].getPerson());
			assertTrue(br.getBoard()[index].isNotRevealed());
		}
	}
	
	/*
	 * Checks that createBoard makes a board of 25 locations with the right amount of agents.
	 */
	@Test
	public void createBoardTest() {
		Board br = new Board();
		br.createBoard();
		assertEquals(25, br.getBoard().length);
		assertEquals(9, br.getRedsLeft());
		assertEquals(8, br.getBluesLeft());
	}
	
	/*
	 * Checks that checkGuess returns the proper value in all potential cases. 
	 */
	@Test
	public void checkGuessTest() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		br.getBoard()[3].setCodename("");
		br.getBoard()[3].setPerson("blue");
		br.setRedTeamTurn(false);
		assertTrue(br.checkGuess(""));
		br.getBoard()[4].setCodename("food");
		br.getBoard()[4].setPerson("red");
		br.setRedTeamTurn(false);
		assertFalse(br.checkGuess("car")); 
		br.getBoard()[5].setCodename("boolean");
		br.getBoard()[5].setPerson("red");
		br.setRedTeamTurn(false);
		assertFalse(br.checkGuess("boolean"));
		br.getBoard()[3].setCodename("bigMac");
		br.getBoard()[3].setPerson("blue");
		br.setRedTeamTurn(true);
		assertFalse(br.checkGuess("bigMac"));
		br.getBoard()[3].setCodename("");
		br.getBoard()[3].setPerson("red");
		br.setRedTeamTurn(true);
		assertTrue(br.checkGuess("")); 
		br.getBoard()[3].setPerson("innocent");
		assertFalse(br.checkGuess(""));
	    br.getBoard()[2].setPerson("assasin");
	    assertFalse(br.checkGuess("")); 
		
	}
	
	// Do not need a test for assignPeople as those methods within it have already been tested elsewhere.
}
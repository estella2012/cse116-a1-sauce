package tests;


import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import code.Board;
import code.Location;

public class BoardTest {
	
	@Test
	public void testCreateBoard() {
		
	}
	
	@Test
	public void testShuffle() {
		ArrayList<String> list = new ArrayList<String>();
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
		ArrayList<String> listx = new ArrayList<String>();

		try {
			String filename = "src/GameWords.txt";
			for (String line : Files.readAllLines(Paths.get(filename))) {
				listx.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Board bob = new Board();
		list = bob.createListOfWords("src/GameWords.txt");
		
		String tester = "AFRICAAGENTAIRALIENALPSAMAZON";
		String testee = "";
		
		for(int i = 0; i < 5; i++) {
			testee += list.get(i);
		}
		
		assertEquals(25, list.size());
		assertFalse(tester == testee);
	}

	@Test
	public void testCreateListOfPersons() {
		Board bob = new Board();
		ArrayList<String> list = new ArrayList<String>();
		list = bob.createListOfPersons();
		//test list size
		assertEquals(25, list.size());
		//test shuffle
		ArrayList<String> testList = new ArrayList<String>();
		for (int i = 0; i < 9; i++) {
			testList.add("red");
		}
		for(int i = 0; i < 8; i++) {
			list.add("blue");
		}
		for(int i = 0; i < 7; i++) {
			list.add("innocent");
		}
		list.add("assassin");
		assertNotEquals(testList, list);
	}
	
	@Test
	public void testGameStart() {
		Board bob = new Board();
		bob.createBoard();
		bob.gameStart("src/GameWords.txt");
		assertTrue(bob.isRedTeamTurn());
		
	}
	
	@Test
	public void checkIllegalClueTest() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		assertFalse(br.checkIllegalClue("number"));
//		assertTrue(br.checkIllegalClue(br.getBoard()[0].getCodename()));
		//need to find a way to check when the clue should be illegal and return true
	}
	
	@Test 
	public void gameWonTest() { 
		
	}
	
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
	
	@Test
	public void testWhichTeamWonAssassin() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		assertEquals("blue", br.whichTeamWonAssassin());
		br.setRedTeamTurn(false);
		assertEquals("red", br.whichTeamWonAssassin());
	}
	
	@Test
	public void gameStartTest() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		for(int index = 0; index < 25; index++) {
			assertNotNull(br.getBoard()[index].getCodename());
			assertNotNull(br.getBoard()[index].getPerson());
			assertTrue(br.isRedTeamTurn());
			assertTrue(br.getBoard()[index].isNotRevealed());
		}
	}
	
	@Test
	public void createBoardTest() {
		Board br = new Board();
		br.createBoard();
		assertEquals(25, br.getBoard().length);
		assertEquals(9, br.getRedsLeft());
		assertEquals(8, br.getBluesLeft());
	}
	
	@Test
	public void checkGuessTest() {
		Board br = new Board();
		br.createBoard();
		br.gameStart("src/GameWords.txt");
		br.getBoard()[3].setCodename("");
		br.getBoard()[3].setPerson("blue");
		br.setRedTeamTurn(false);
		assertTrue(br.checkGuess(""));
		
		
	}
	// need to add tests for checkGuess and gameWon
	// there's also some new basic getters/setters that we can add tests for if we'd like
	// I added a lot of stuff into checkGuess, updated comment to reflect
	// also fix methods CheckIllegalClue and gameStart 
}
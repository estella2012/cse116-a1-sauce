package tests;


import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import code.Board;

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
		list = bob.createListOfWords();
		
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
		bob.gameStart();
		assertTrue(bob.isRedTeamTurn());
		
	}
	
	@Test
	public void checkIllegalClueTest() {
		Board br = new Board();
		br.createBoard();
		br.gameStart();
		assertFalse(br.checkIllegalClue("number"));
	}
	
	@Test 
	public void gameWonTest() { 
		
	}
}
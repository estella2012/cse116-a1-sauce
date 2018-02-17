package tests;


import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import code.Board;

public class BoardTest {
	
	@Test
	public void testCreateBoard() {
		
	}
	
	@Test
	public void testShuffle() {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>(); 
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
			String filename = "src/code/words.txt";
			for (String line : Files.readAllLines(Paths.get(filename))) {
				listx.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Board bob = new Board();
		list = bob.createList();
		
		String tester = "AFRICAAGENTAIRALIENALPSAMAZON";
		String testee = "";
		
		for(int i = 0; i < 5; i++) {
			testee += list.get(i);
		}
		System.out.println(testee);
		
		assertEquals(25, list.size());
		assertFalse(tester == testee);
		//assertTrue(tester == testee);
		
		
	}

}
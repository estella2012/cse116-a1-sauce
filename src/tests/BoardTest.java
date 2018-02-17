package tests;


import static org.junit.Assert.assertTrue;

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
		ArrayList<String> list2 = new ArrayList<String>(); 
		list2.add("word"); 
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
		list2.add("ishmam");
		Board bob = new Board();
		list = bob.createList();
		assertTrue(list.size() == 25); 
		assertFalse()
		
		
	}

}
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Location;

public class LocationTest {
	
	
	Location test = new Location("","");
	
	/*
	 * Creates a new location and tests that the constructor works.
	 */
	@Test
	public void testLocation() {
		test = new Location("BOB","IDK");
		assertEquals("person should be set with this class", "BOB", test.getPerson());
		assertEquals("codename should be set with this class", "IDK", test.getCodename());
		assertEquals("notRevealed should be set to true", true, test.isNotRevealed());		
	}
	
	/*
	 * Generic tests for the getters and setters.
	 */
		
	@Test
	public void testSetPerson() {
		test.setPerson("bob");
		assertEquals("person should be set with this class", "bob", test.getPerson());
	}
	
	@Test
	public void testSetCodename() {
		test.setCodename("blue");
		assertEquals("Codename should be set with this class", "blue", test.getCodename());
	}
	
	@Test
	public void testIsNotRevealed() {
		test.setNotRevealed(false);
		assertFalse(test.isNotRevealed());
	}
}
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Location;

public class LocationTest {
	
	
	Location test = new Location("","");

	@Test
	public void testLocation() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetPerson() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetPerson() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetCodename() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetCodename() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNotRevealed() {
		test.setNotRevealed(false);
		assertFalse(test.isNotRevealed());
	}
	

}

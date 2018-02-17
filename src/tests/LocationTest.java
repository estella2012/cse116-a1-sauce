package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Location;

public class LocationTest {
	
	
	Location test = new Location("","");

	@Test
	public void testLocation() {
		test = new Location("BOB","IDK");
		assertEquals("person should be set with this class", "BOB", test.getPerson());
		assertEquals("codename should be set with this class", "IDK", test.getCodename());
		assertEquals("notRevealed shou be set to true", true, test.isNotRevealed());
		
	}
	
	@Test
	public void testSetPerson() {
		test.setPerson("bob");
		assertEquals("person should be set with this class", "bob", test.getPerson());
	}
	
//	@Test
//	public void testGetPerson() {
//		fail("Not yet implemented");
//	}
	
//	@Test
//	public void testGetCodename() {
//		fail("Not yet implemented");
//	}
	//k
	@Test
	public void testSetCodename() {
		test.setCodename("blue");
		assertEquals("Codename should be set with this class", "blue", test.getCodename());
	}
	
	@Test
	public void testNotRevealed() {
		test.setNotRevealed(false);
		assertFalse(test.isNotRevealed());
	}
	

}

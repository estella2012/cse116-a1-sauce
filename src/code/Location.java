package code;

public class Location {
	
	//this is a string that will determine if the location is a red, blue, innocent, or the assassin
	private String person;
	//this is a string that stores the codename associated with the location
	private String codename;
	//this is  boolean that indicates whether or not the location has been flipped/revealed
	private boolean notRevealed;
	
	
	//constructs a new Location	
	public Location(String person, String codename) {
		this.person = person;
		this.codename = codename;
		notRevealed = true;
	}

	/*
	 * Generic getters and setters.
	 */
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public boolean isNotRevealed() {
		return notRevealed;
	}

	public void setNotRevealed(boolean notRevealed) {
		this.notRevealed = notRevealed;

	}
}
package code;

public class Location {
	
	private String locationType;

	public Location(String type) {
		locationType = type;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locType) {
		this.locationType = locType;
	}
}
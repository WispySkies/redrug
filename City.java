package model;

public class City {

	private String country;
	private String city;
	private String region;
	private int population;
	private double latitude;
	private double longitude;

	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch( Exception e ) {
			return false;
		}
	}

	public boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		}
		catch( Exception e ) {
			return false;
		}
	}

	public City(String countryIn, String cityIn, String regionIn, String populationIn, String latitudeIn, String longitudeIn) {
		country = countryIn;
		city = cityIn;
		region = regionIn;
		if(isInteger(populationIn)) {
			population = Integer.parseInt(populationIn);
		}
		if(isDouble(latitudeIn)) {
			latitude = new Double(latitudeIn);
		}
		if(isDouble(longitudeIn)) {
			longitude = new Double(longitudeIn);
		}

	}

	/**
	 * Useful accessor methods
	 */

	 public String getCountry() { return country; }
	public String getCity() { return city; }
	public String getRegion() { return region; }
	public int getPopulation() { return population; }
	public double getLatitude() { return latitude; }
	public double getLongitude() { return longitude; }

	/**
	 * Provide a sensible override for this method, so that all the contents of all the instance
	 * variables are represented in the returned String.
	 */
	@Override public String toString() {
		String ret = getCity() + ", " + getRegion() + ", " + getCountry() + " (" + getLatitude() + " lat, "  + getLongitude() + " long)"
				+ "Population: " + getPopulation();
		return ret;
	}

}


package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private static final int MAX_ZOOM = 18;
	private static final int MIN_ZOOM = 0;

	private ArrayList<City> _allCities;

	private Tile _primaryTile;
	private City _city;
	private int _zoom;

	private ArrayList<Observer> _observers;
	private ArrayList<City> _searchResults;
	private Constraints _constraints;

	/**
	 * Initialize this Model object.  Read the data from the given filename.
	 * 
	 * @param filename
	 */
	public Model(String filename) {
		_observers = new ArrayList<Observer>();
		_allCities = readDataFromCSV(filename);
	}

	/**
	 * Define this method so that it returns an ArrayList<Tile> containing
	 * the nine Tile objects to be displayed in the map panel of the GUI.
	 * 
	 * The order of the nine tiles must be, by index in the ArrayList<Tile>:
	 * 
	 *     [0] [1] [2]
	 *     [3] [4] [5]
	 *     [6] [7] [8]
	 * 
	 * Perform appropriate wrap-around for tiles at the edges.
	 * See http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames#Zoom_levels
	 * and http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames#X_and_Y
	 * for more details.
	 * 
	 * HINT: keep track of the file in [4] as the _primaryTile.
	 * HINT: update the _primaryTile to move the map N, S, E, and W.
	 * 
	 * @return the ArrayList<Tile>
	 */
	public ArrayList<Tile> tiles() {
		ArrayList<Tile> tiles = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Tile t = new Tile(0, 0, 0);
			tiles.add(t.getTile(_city, _zoom));
		}
		return tiles;
	}

	/**
	 * Call this method to shift the map North by one tile
	 */
	public void north() {
	}

	/**
	 * Call this method to shift the map South by one tile
	 */
	public void south() {
	}

	/**
	 * Call this method to shift the map East by one tile
	 */
	public void east() {
	}

	/**
	 * Call this method to shift the map West by one tile
	 */
	public void west() {
	}

	/**
	 * Call this method to increase the zoom level by 1
	 */
	public void zoomIn() {
	}

	/**
	 * Call this method to decrease the zoom level by 1
	 */
	public void zoomOut() {
	}

	/**
	 * Apply the filters as indicated by the Constraints object.
	 * The result must be stored an an ArrayList<City> in _searchResults 
	 * The Constraints object must be stored in _constraints
	 * @param c - the Constraints object
	 */
	public void performSearch(Constraints c) {
	}

	/*
	 * DO NOT MODIFY METHODS BELOW THIS COMMENT
	 * 
	 * These are methods we are providing to you.  Some of these you defined
	 * in an earlier part of the homework.
	 * 
	 * These method definitions are provided for a reason: you will need 
	 * to call them in your code.
	 */


	/**
	 * Reads the data from the indicated filename.
	 * 
	 * @param filename
	 * @return An ArrayList<City> of the city data from the file
	 * named filename.
	 */
	public ArrayList<City> readDataFromCSV(String filename){
		ArrayList<City> cities = new ArrayList<City>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(filename));
			lines.remove(0);
			for(String line : lines) {
				String [] info = line.split(",");
				City city = new City(info[0], info[1],info[3],info[4],info[5],info[6]);
				cities.add(city);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cities;
	}

	/**
	 * Used to add an observer to this model object.
	 * All observers must be notified whenever the internal state of the model
	 * object changes.
	 * 
	 * @param obs - the observer to be added to the list of observers.
	 */
	public void addObserver(Observer obs) {
		_observers.add(obs);
	}

	/**
	 * Used to notify all observers that the internal state of the model has
	 * changed.  Notification is via a call to each observer's update method.
	 */
	public void notifyObservers() {
		for (Observer obs : _observers) {
			obs.update();
		}
	}	

	/**
	 * Used to set the current City object.
	 * Setting the current City also gets the tile for the current City
	 * and remembers it in _primaryTile.
	 * 
	 * @param c - the (new) current City
	 */
	public void setCity(City c) {
		_city = c;
		_primaryTile = Tile.getTile(_city, currentZoom());
		notifyObservers();
	}

	/**
	 * Indicates whether there is a current City
	 * @return true if the current City is set, false otherwise
	 */
	public boolean hasCity() {
		return _city != null;
	}

	/**
	 * Useful accessor methods
	 */
	public int currentZoom() { return _zoom; }
	public String currentCity() { return _city.getCity(); }
	public String currentRegion() { return _city.getRegion(); }
	public String currentCountry() { return _city.getCountry(); }
	public int currentPopulation() { return _city.getPopulation(); }
	public ArrayList<City> getSearchResults() { return _searchResults; }
	public Constraints getConstraints() { return _constraints; }

}

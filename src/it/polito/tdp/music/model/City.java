package it.polito.tdp.music.model;

public class City {
	
	private int id ;
	private String country ;
	
	public City(int id, String country) {
		super();
		this.id = id;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtist() {
		return country;
	}
	public void setArtist(String artist) {
		this.country = artist;
	}

	@Override
	public String toString() {
		return "City[" + id + ", \"" + country + "\"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int compareTo(City city) {
		// TODO Auto-generated method stub
		return this.id-city.id;
	}
	
}

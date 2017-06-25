package it.polito.tdp.music.model;

public class BranoConAscolti {
	
	private Track brano;
	private int ascolti;
	
	public BranoConAscolti(Track brano, int ascolti) {
		super();
		this.brano = brano;
		this.ascolti = ascolti;
	}
	public Track getBrano() {
		return brano;
	}
	public void setBrano(Track brano) {
		this.brano = brano;
	}
	public int getAscolti() {
		return ascolti;
	}
	public void setAscolti(int ascolti) {
		this.ascolti = ascolti;
	}
	
	

}

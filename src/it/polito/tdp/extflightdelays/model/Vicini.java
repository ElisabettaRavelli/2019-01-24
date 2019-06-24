package it.polito.tdp.extflightdelays.model;

public class Vicini implements Comparable<Vicini>{
	
	private String stato;
	private Double peso;
	public Vicini(String stato, Double peso) {
		super();
		this.stato = stato;
		this.peso = peso;
	}
	public String getStato() {
		return stato;
	}
	public Double getPeso() {
		return peso;
	}
	
	
	@Override
	public String toString() {
		return String.format("Stato=%s, Peso=%s", stato, peso);
	}
	@Override
	public int compareTo(Vicini o) {
		return - (this.peso.compareTo(o.peso));
	}

}

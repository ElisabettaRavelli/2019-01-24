package it.polito.tdp.extflightdelays.model;

public class Evento implements Comparable<Evento>{

	private int turista;
	private int giorno;
	private String statoAttuale;
	
	public Evento(int turista, int giorno, String statoAttuale) {
		super();
		this.turista = turista;
		this.giorno = giorno;
		this.statoAttuale = statoAttuale;
	}

	public int getTurista() {
		return turista;
	}

	public void setTurista(int turista) {
		this.turista = turista;
	}
	public int getGiorno() {
		return giorno;
	}
	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}
	public String getStatoAttuale() {
		return statoAttuale;
	}
	public void setStatoAttuale(String statoAttuale) {
		this.statoAttuale = statoAttuale;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.giorno-o.giorno;
	}

}

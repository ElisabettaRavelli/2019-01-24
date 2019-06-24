package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	
	private Map<String, Integer> mapTuristi; //la chiave sono gli stati mentre il valore è il numero di turisti

	private PriorityQueue<Evento> queue;
	
	public Simulatore() {
		this.mapTuristi = new HashMap<>();
		this.queue = new PriorityQueue<>();
	}
	
	public void init(Integer T, Integer G, String statoAttuale) {
		
		mapTuristi.put(statoAttuale, T);
		this.queue = new PriorityQueue<Evento>();
		for(int i=1; i<T; i++) {
			queue.add(new Evento(i, 0, statoAttuale));
		}
		
	}
	public void run(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo, int G) {
		while(!queue.isEmpty()) {
			Evento ev = queue.poll();
			if(ev.getGiorno()!=G) {
				String stato = getProbabilitaStati(ev.getStatoAttuale(), grafo);
				if(stato!=null) {
					if(mapTuristi.get(stato)!=null) {
						mapTuristi.replace(stato, mapTuristi.get(stato), mapTuristi.get(stato)+1);
					}else
						mapTuristi.put(stato, 1);
					
					queue.add(new Evento(ev.getTurista(), ev.getGiorno()+1, stato));
				}
			}
		}
	}

	private String getProbabilitaStati(String statoAttuale,
			DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> grafo) {

			double pesoTot=0;
			double probStatoScelto=0;
			String stato = null;
			
			for(DefaultWeightedEdge e: grafo.outgoingEdgesOf(stato)) {
				pesoTot=pesoTot+ grafo.getEdgeWeight(e);
			}
			for(DefaultWeightedEdge e: grafo.outgoingEdgesOf(stato)) {
				if((grafo.getEdgeWeight(e)/pesoTot)>probStatoScelto){
					probStatoScelto=(grafo.getEdgeWeight(e)/pesoTot);
					stato = grafo.getEdgeTarget(e);
				}
			}
		return stato;
	}
}

package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private ExtFlightDelaysDAO dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<String> stati;
	private List<Vicini> vicini;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
		this.grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.stati = new ArrayList<>();
		this.vicini = new ArrayList<>();
	}
	
	public void creaGrafo() {
		this.stati = dao.loadAllStates();
		Graphs.addAllVertices(this.grafo, this.stati);
		
		for(String s1: this.grafo.vertexSet()) {
			for(String s2: this.grafo.vertexSet()) {
				Double peso = this.dao.nVelivoliDistinti(s1, s2);
				DefaultWeightedEdge e = this.grafo.getEdge(s1, s2);
				if(e==null && peso>0) {
					Graphs.addEdgeWithVertices(this.grafo, s1, s2, peso);
				}
			}
		}
		System.out.println("Vertici "+ this.grafo.vertexSet().size()+ " Archi: "+ this.grafo.edgeSet().size());
	}
	
	public List<String> getStati(){
		Collections.sort(this.stati);
		return this.stati;
	}
	public Integer nVertici() {
		return this.grafo.vertexSet().size();
	}
	public Integer nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Vicini> getVicini(String stato){
		List<String> successori = Graphs.successorListOf(this.grafo, stato);
		for(String s : successori) {
			vicini.add(new Vicini(s, this.grafo.getEdgeWeight(this.grafo.getEdge(stato, s))));
		}
		Collections.sort(vicini);
		return vicini;
	}
	
	
	
}

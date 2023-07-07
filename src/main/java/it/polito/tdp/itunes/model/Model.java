package it.polito.tdp.itunes.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.itunes.db.ItunesDAO;
import javafx.util.Pair;

public class Model {
	
	private ItunesDAO dao;
	private Graph<Integer, DefaultEdge> grafo;
	List<Pair<Integer, Integer>> coppieAlbum = new LinkedList<>();
	private Map<Integer,Integer> durataAlbumMap = new HashMap<>();

	
	public Model() {
		this.dao = new ItunesDAO();
		
		coppieAlbum.addAll(this.dao.getCoppieAlbumConPlaylistComune());
		durataAlbumMap.putAll(this.dao.getDurataPerAlbum());
		
		
	}
	
	public List<String> getAllNameAlbums(){
		return this.dao.getAllNameAlbums();
	} 
	
public void creaGrafo(Integer durata) {
		
	
		this.grafo = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
			
		// Aggiunta VERTICI 
		
		for(Integer aId : this.durataAlbumMap.keySet()) {
			if(this.durataAlbumMap.get(aId)>durata) {
				this.grafo.addVertex(aId);
			}
			
		}
		
		// Aggiunta ARCHI
		
		Set<Integer> vertici = this.grafo.vertexSet();
		
		Pair<Integer, Integer> coppia=new Pair<Integer, Integer>(0,0);
		
		for (Integer v1 : vertici) {
			for (Integer v2 : vertici) {
				if(!this.grafo.edgeSet().contains(this.grafo.getEdge(v1, v2))) {
				coppia = new Pair<Integer, Integer>(v1,v2);
				if(coppieAlbum.contains(coppia) && v1>v2) {
					this.grafo.addEdge(v1, v2);
				}
				}
			}
		}
		
		System.out.println(this.grafo.vertexSet().size());
		System.out.println(this.getArchi().size());
		System.out.println("----------------------");
		
		}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Set<Integer> getVertici(){
		
		Set<Integer> vertici=this.grafo.vertexSet();
		
		return vertici;
	}
	
public Set<DefaultEdge> getArchi(){
		
		Set<DefaultEdge> archi=this.grafo.edgeSet();
		
		return archi;
	}


public List<String> titleVertici() {
	
	List<String> title=new LinkedList<>();
	
	
	for(Integer i : this.grafo.vertexSet()) {
		title.add(this.dao.getTitleVertice(i));
	}
	
	Collections.sort(title);
	
	return title;
}

public Set<Integer> getComponente(Integer a1) {
	ConnectivityInspector<Integer, DefaultEdge> ci = new ConnectivityInspector<>(this.grafo) ;
	return ci.connectedSetOf(a1) ;
}

public Integer getIdVerticeDaTitle(String title) {
	return this.dao.getIdVerticeDaTitle(title);
}

public double getDurataComponente(Set<Integer> connessa) {
	
	double durata=0.0;
	
	for(int i : connessa) {
		if(this.durataAlbumMap.keySet().contains(i)) {
			durata=durata+this.durataAlbumMap.get(i);
		}
	}
	
	
	return durata/60000;
}
	
}

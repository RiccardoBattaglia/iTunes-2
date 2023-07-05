package it.polito.tdp.itunes.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	private Graph<Integer, DefaultEdge> grafo;
	
	public Model() {
		this.dao = new ItunesDAO();
	}
	
	public List<String> getAllNameAlbums(){
		return this.dao.getAllNameAlbums();
	} 
	
}

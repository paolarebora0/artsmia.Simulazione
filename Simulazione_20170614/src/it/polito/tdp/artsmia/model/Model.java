package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	private ArtsmiaDAO dao;
	private Graph<Mostre, DefaultEdge> grafo;
	private Map<Integer, Mostre> idMap;
	private List<Mostre> mostre;
	private Mostre mostraMax;
	private int max;
	
	public Model() {
		this.dao = new ArtsmiaDAO();
	}
	
	public List<Integer> getListaAnni() {
		List<Integer> anni = dao.listAnni();
		return anni;
	}

	public void creaGrafo(int anno) {
		mostraMax = null;
		max = 0;
		this.idMap = new HashMap<Integer, Mostre>();
		this.grafo = new SimpleDirectedGraph<Mostre, DefaultEdge>(DefaultEdge.class);
		mostre = dao.listExhibitions(anno);
		for(Mostre m: mostre) {
			idMap.put(m.getId(), m);
		}
		Graphs.addAllVertices(grafo, mostre);
		
		List<Connessioni> connessioni = dao.listConnessioni(anno);
		System.out.println(connessioni.size());
		for (Connessioni c: connessioni) {
			
			Mostre source = idMap.get(c.getE1());
			Mostre target = idMap.get(c.getE2());
			
			this.grafo.addEdge(source, target);
		}
		
		System.out.println("Grafo creato: "+grafo.vertexSet().size()+" vertici e "+grafo.edgeSet().size()+" archi");
	}

	public int getVertexSize() {
		return grafo.vertexSet().size();
	}

	public int getEdgesSize() {
		// TODO Auto-generated method stub
		return grafo.edgeSet().size();
	}
	
	
	
//	public boolean isStronglyConnected() {
//		
//		KosarajuStrongConnectivityInspector<Mostre, DefaultEdge> sConnected = 
//				new KosarajuStrongConnectivityInspector<>(grafo);
//		return sConnected.isStronglyConnected();
//	}
	
	public boolean isConnected() {
		
		Set<Mostre> visitati = new HashSet<Mostre>();
				
		for(Mostre m1: grafo.vertexSet()) {

			for(Mostre m2: grafo.vertexSet()) {
				BreadthFirstIterator<Mostre, DefaultEdge> it = new BreadthFirstIterator<Mostre, DefaultEdge>(grafo, m1);
				while(it.hasNext()) {
					visitati.add(it.next());
				}
				if(!visitati.contains(m2))
					return false;			
			}
		}
		
		return true;
	}
	
	public Mostre mostraConPiuOpere() {
		List<OpereMostre> opereMostre = dao.operePerMostra(idMap);
		
		for(OpereMostre m: opereMostre) {
			if(m.getNumeroOpere()> max) {
				max = m.getNumeroOpere();
				mostraMax = idMap.get(m.getIdMostra());
			}
		}

		return mostraMax;
	}

	public int getNumeroMaxOpere() {
		return max;
	}
}

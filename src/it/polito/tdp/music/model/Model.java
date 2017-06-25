package it.polito.tdp.music.model;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.music.db.MusicDAO;

public class Model {
	
	private MusicDAO dao;
	private List<City> best20;
	private SimpleWeightedGraph<City, DefaultWeightedEdge> grafo ;
	public Model(){
		dao = new MusicDAO();
	}

	public List<DayOfWeek> getDays() {
		// TODO Auto-generated method stub
		return dao.getDays();
	}
	
	public List<City> getCittaConPiuAscolti(DayOfWeek d){
		List<City> tutte = dao.getCittaConPiuAscolti(d);
		best20 = new ArrayList<>();
		for(int i = 0; i<20; i++){
			best20.add(tutte.get(i));
		}
		return best20;
	}

	public List<BranoConAscolti> getBraniConAscolti(DayOfWeek d){
		return dao.getBranoConAscolti(d);
	}

	public void creaGrafo(DayOfWeek d) {
		
		grafo = new SimpleWeightedGraph<City, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, best20);
		System.out.println(grafo);
		System.out.println(grafo.vertexSet().size()+"-"+grafo.edgeSet().size());
		for(City c1: grafo.vertexSet()){
			for(City c2: grafo.vertexSet()){
				if(!c1.equals(c2)){
					int brani = dao.getBraniComuni(c1,c2, d);
					if(brani>0){
						DefaultWeightedEdge arco = grafo.addEdge(c1, c2);
						if(arco!=null){
							grafo.setEdgeWeight(arco, brani);
						}
					}
				}
			}
		}
		System.out.println(grafo);
		System.out.println(grafo.vertexSet().size()+"-"+grafo.edgeSet().size());
		
	}

	public List<City> avviaRicorsione() {
		
		List<City> parziale = new ArrayList<>();
		List<City> finale = new ArrayList<>();
		
		ricorsione(parziale, finale);
		return finale;
	}

	private void ricorsione(List<City> parziale, List<City> finale) {
		
		if(parziale.size()==3){
			if(calcoloPunti(parziale)>calcoloPunti(finale)){
				finale.clear();
				finale.addAll(parziale);
				System.out.println(finale+"-"+calcoloPunti(finale));
			}
			return;
		}
		for(City c : best20){
			if(parziale.isEmpty() || c.compareTo(parziale.get(parziale.size()-1))>0){
				
				parziale.add(c);
				
				ricorsione(parziale, finale);
				
				parziale.remove(c);
			}
		}
		
	}

	private int calcoloPunti(List<City> parziale) {
		int tot = 0;
		for(City c : parziale){
			for(DefaultWeightedEdge arco : grafo.edgesOf(c)){
				tot += grafo.getEdgeWeight(arco);
			}
		}
		return tot;
	}
}

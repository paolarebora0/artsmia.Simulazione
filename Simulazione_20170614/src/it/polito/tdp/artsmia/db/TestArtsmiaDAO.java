package it.polito.tdp.artsmia.db;

import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Connessioni;

public class TestArtsmiaDAO {

	public static void main(String[] args) {
		
		ArtsmiaDAO dao = new ArtsmiaDAO() ;
		
		List<ArtObject> objects = dao.listObject() ;
//		System.out.println(objects.size());
		
		List<Connessioni> c = dao.listConnessioni(2014);
		System.out.println(c.size());

	}

}

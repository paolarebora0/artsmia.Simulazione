package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Connessioni;
import it.polito.tdp.artsmia.model.Mostre;
import it.polito.tdp.artsmia.model.OpereMostre;

public class ArtsmiaDAO {
	
	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Mostre> listExhibitions(int anno) {
		
		String sql = "SELECT DISTINCT * FROM exhibitions WHERE exhibitions.`begin` >= ? ";

		List<Mostre> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Mostre m = new Mostre(res.getInt("exhibition_id"), res.getString("exhibition_department"), 
							res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end"));
				
				result.add(m);	

				
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> listAnni() {
		String sql = "SELECT DISTINCT `begin` FROM exhibitions ORDER BY `begin`";

		List<Integer> anni = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				anni.add(res.getInt("begin"));
			}

			conn.close();
			return anni;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Connessioni> listConnessioni(int anno) {
		
		String sql = "SELECT e1.exhibition_id AS e1, e2.exhibition_id AS e2 " + 
				"FROM exhibitions e1, exhibitions e2 " + 
				"WHERE e2.`begin` > e1.`begin` AND e1.`end` >= e2.`begin` AND e1.`begin`>= ? " + 
				"AND e2.exhibition_id > e1.exhibition_id GROUP BY e1.exhibition_id , e2.exhibition_id ";

		List<Connessioni> connessioni = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Connessioni c = new Connessioni(res.getInt("e1"), res.getInt("e2"));
				connessioni.add(c);
			}

			conn.close();
			return connessioni;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<OpereMostre> operePerMostra(Map<Integer, Mostre> idMap){
		
		String sql = "SELECT exhibition_id as id, COUNT(*) AS cnt " + 
				"FROM exhibition_objects " + 
				"GROUP BY exhibition_id ";
		Connection conn = DBConnect.getConnection();
		List<OpereMostre> result = new ArrayList<OpereMostre>();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				if(idMap.containsKey(res.getInt("id"))) {	
					
					OpereMostre o = new OpereMostre(res.getInt("cnt"), res.getInt("id"));
					result.add(o);
				}		
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}

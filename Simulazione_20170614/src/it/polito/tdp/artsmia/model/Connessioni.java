package it.polito.tdp.artsmia.model;

public class Connessioni {

	private int e1;
	private int e2;
	
	public Connessioni(int e1, int e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public int getE1() {
		return e1;
	}
	public void setE1(int e1) {
		this.e1 = e1;
	}
	public int getE2() {
		return e2;
	}
	public void setE2(int e2) {
		this.e2 = e2;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + e1;
		result = prime * result + e2;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessioni other = (Connessioni) obj;
		if (e1 != other.e1)
			return false;
		if (e2 != other.e2)
			return false;
		return true;
	}
	
	
}

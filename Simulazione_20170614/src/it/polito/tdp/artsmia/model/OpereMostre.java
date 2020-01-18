package it.polito.tdp.artsmia.model;

public class OpereMostre {

	private int numeroOpere;
	private int idMostra;
	public OpereMostre(int numeroOpere, int idMostra) {
		super();
		this.numeroOpere = numeroOpere;
		this.idMostra = idMostra;
	}
	public int getNumeroOpere() {
		return numeroOpere;
	}
	public void setNumeroOpere(int numeroOpere) {
		this.numeroOpere = numeroOpere;
	}
	public int getIdMostra() {
		return idMostra;
	}
	public void setIdMostra(int idMostra) {
		this.idMostra = idMostra;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMostra;
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
		OpereMostre other = (OpereMostre) obj;
		if (idMostra != other.idMostra)
			return false;
		return true;
	}
	
	
}

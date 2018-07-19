package bean;

import java.io.Serializable;

public class CompetizioneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	int idComp;
	String nome;
	int numGiornate;
	int numPartecipanti;
	boolean approvata;
	
	

	public CompetizioneBean() {
		idComp=0;
		nome="";
		numGiornate=0;
		numPartecipanti=0;
		approvata=false;
		
	}



	public int getIdComp() {
		return idComp;
	}



	public void setIdComp(int idComp) {
		this.idComp = idComp;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public int getNumGiornate() {
		return numGiornate;
	}



	public void setNumGiornate(int numGiornate) {
		this.numGiornate = numGiornate;
	}



	public int getNumPartecipanti() {
		return numPartecipanti;
	}



	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}



	public boolean isApprovata() {
		return approvata;
	}



	public void setApprovata(boolean approvata) {
		this.approvata = approvata;
	}



	@Override
	public String toString() {
		return "CompetizioneBean [idComp=" + idComp + ", nome=" + nome + ", numGiornate=" + numGiornate
				+ ", numPartecipanti=" + numPartecipanti + ", approvata=" + approvata + "]";
	}
	


}
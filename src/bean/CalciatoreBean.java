package bean;

import java.io.Serializable;

public class CalciatoreBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String squadra;
	String nome;
	String cognome;
	int numero;
	int prezzo;
	String ruolo;
	int id;
	
	


	public CalciatoreBean() {
		squadra = "";
		nome = "";
		cognome = "";
		numero = 0;
		prezzo = 0;
		ruolo = "";
		id = 0;
	}
	
	public String getSquadra() {
		return squadra;
	}
	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CalciatoreBean [squadra=" + squadra + ", nome=" + nome + ", cognome=" + cognome + ", numero=" + numero
				+ ", prezzo=" + prezzo + ", ruolo=" + ruolo + ", id=" + id + "]";
	}





}

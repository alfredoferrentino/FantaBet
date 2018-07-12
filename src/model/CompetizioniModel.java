package model;

import java.sql.SQLException;
import java.util.Collection;

public interface CompetizioniModel {
	
	public Collection<String> doRetrieveByUtente(String utente) throws SQLException;
	public void doSave(String nome, int numGiornate, int numPartecipanti) throws SQLException;
	
}

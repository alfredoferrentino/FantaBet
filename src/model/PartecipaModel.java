package model;

import java.sql.SQLException;


public interface PartecipaModel {
	
	public void doSave(String utente) throws SQLException;
	public void aggiungi(String utente, String competizione) throws SQLException;
	
}

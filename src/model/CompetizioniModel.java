package model;

import java.sql.SQLException;
import java.util.Collection;

import bean.CompetizioneBean;

public interface CompetizioniModel {
	
	public Collection<String> doRetrieveByUtente(String utente) throws SQLException;
	public void doSave(String nome, int numGiornate, int numPartecipanti) throws SQLException;
	public Collection<CompetizioneBean> doRetrieveAll() throws SQLException;
	public boolean doDelete(int idComp) throws SQLException;
	public Collection<CompetizioneBean> doSearch (String nome) throws SQLException;
	public Collection<CompetizioneBean> doRetrieveApproved() throws SQLException;
	public void Approva(int id) throws SQLException;
	public void doPartecipa(String utente, int comp) throws SQLException;
	public int doRetrieveByNome(String nome) throws SQLException;
}

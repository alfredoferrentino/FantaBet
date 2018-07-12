package model;

import java.sql.SQLException;
import java.util.Collection;
import bean.CalciatoreBean;


public interface FormazioneModel {
	
	public void doSaveFormazione(String utente, int giornata) throws SQLException;
	public int doRetrieveGiornata() throws SQLException;
	public boolean checkFormazione(String utente, int giornata) throws SQLException;
	public void doSaveComposizione(int formazione, String[] calciatori) throws SQLException;
	public int doRetrieveId(String utente, int giornata) throws SQLException;
	public Collection<CalciatoreBean> doRetrieveFormazione(String utente, int giornata) throws SQLException;
}

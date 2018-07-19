package model;

import java.sql.SQLException;
import java.util.Collection;

import bean.CalciatoreBean;

public interface CalciatoreModel { 
	
	public Collection<CalciatoreBean> doRetrieveByRuolo(String ruolo) throws SQLException;
	public Collection<CalciatoreBean> doRetrieveAll() throws SQLException; 
	public void doSaveVoto(int idCalc, int giornata, double votazione, int gol, int assist,boolean ammonizione, boolean espulsione) throws SQLException;
}

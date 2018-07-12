package model;

import java.sql.SQLException;
import java.util.Collection;

import bean.CalciatoreBean;

public interface CalciatoreModel {
	
	public Collection<CalciatoreBean> doRetrieveByRuolo(String ruolo) throws SQLException; 
}

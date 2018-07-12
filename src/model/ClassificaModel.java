package model;

import java.sql.SQLException;
import java.util.Collection;

import bean.ClassificaBean;

public interface ClassificaModel {
	
	public Collection<ClassificaBean> doRetrieveAll() throws SQLException;
	public Collection<ClassificaBean> doRetriveByComp(String competizione) throws SQLException;
	
}

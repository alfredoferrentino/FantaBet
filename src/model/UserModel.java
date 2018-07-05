package model;

import java.sql.SQLException;
import java.util.Collection;

import bean.UserBean;



public interface UserModel {
	public void doSave(UserBean user) throws SQLException;

	public void doChangeRole(String user, String role) throws SQLException;
	
	public Collection<UserBean> doRetrieveAll() throws SQLException;
	
	public String doRetrieveRole(String username) throws SQLException;
}
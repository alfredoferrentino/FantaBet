package model;

import java.sql.SQLException;
import java.util.Collection;

import bean.CompetizioneBean;
import bean.UserBean;



public interface UserModel {
	public void doSave(UserBean user) throws SQLException;

	public void doChangeRole(String user, String role) throws SQLException;
	
	public Collection<UserBean> doRetrieveAll() throws SQLException;
	
	public String doRetrieveRole(String username) throws SQLException;
	
	public void doUpdate(String nickname, String password, String email,String username) throws SQLException;
	
	public Collection<UserBean> doSearch (String utente) throws SQLException;
	
	public boolean checkExist(String username) throws SQLException;
}
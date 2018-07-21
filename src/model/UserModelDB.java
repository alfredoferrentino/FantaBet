package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CompetizioneBean;
import bean.UserBean;
import connection.DriverManagerConnectionPool;


public class UserModelDB implements UserModel{
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/fantabet");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	@Override
	public synchronized void doSave(UserBean user) throws SQLException {


		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL ="INSERT INTO utente (email,nickname,username,pass,ruolo) VALUES (?,?,?,?,?)";



		try {
			connection = DriverManagerConnectionPool.getConnection();
			System.out.println("Entro nella query..." + user.toString());
			

			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getNick());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRuolo());
			System.out.println("Ho preso tutti i valori");
			preparedStatement.executeUpdate();
			
			connection.commit();

		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	@Override
	public synchronized void doChangeRole(String user, String role) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE  utente SET ruolo = ? WHERE username = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, role);
			preparedStatement.setString(2, user);
			

			preparedStatement.executeUpdate();

			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	@Override
	public synchronized Collection<UserBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UserBean> utenti = new LinkedList<UserBean>();

		String selectSQL = "SELECT * FROM utente";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UserBean bean = new UserBean();

				bean.setEmail(rs.getString("email"));
				bean.setNick(rs.getString("nickname"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("pass"));
				bean.setRuolo(rs.getString("ruolo"));
				
				utenti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return utenti;
	}
	@Override
	public String doRetrieveRole(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String ruolo="";
		String selectSQL = "SELECT * FROM  utente WHERE username = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			 
			if(rs.next()) {
				ruolo=rs.getString("ruolo");
			
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return ruolo;
	}
	
		public synchronized void doUpdate(String nickname, String password, String email,String username) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String updateSQL = "UPDATE  utente SET nickname = ?,pass = ?, email = ? WHERE username = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, nickname);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, email);
				preparedStatement.setString(4, username);
				

				preparedStatement.executeUpdate();

				
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		}
		@Override
		public Collection<UserBean> doSearch(String utente) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			Collection<UserBean> utenti = new LinkedList<UserBean>();
			
			String selectSQL = "SELECT * FROM  utente WHERE nickname LIKE ? ";

			try {
				connection = ds.getConnection();
				utente = "%" + utente +"%";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, utente);
				
				

				ResultSet rs = preparedStatement.executeQuery();
				

				while (rs.next()) {
					UserBean bean = new UserBean();
					bean.setUsername(rs.getString("username"));
					bean.setNick(rs.getString("nickname"));
					bean.setPassword(rs.getString("pass"));
					bean.setEmail(rs.getString("email"));
					bean.setRuolo(rs.getString("ruolo"));
					utenti.add(bean);
					System.out.println(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return utenti;
		}
		@Override
		public boolean checkExist(String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String selectSQL = "SELECT * FROM  utente WHERE username = ? ";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, username);
				
				

				ResultSet rs = preparedStatement.executeQuery();
				

				if (rs.next()) {
					return true;
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return false;
		}
	
}
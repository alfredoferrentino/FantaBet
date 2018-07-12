package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import control.DriverManagerConnectionPool;



public class PartecipaModelDS implements PartecipaModel {

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
	public void doSave(String utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL ="INSERT INTO partecipazione (utente,competizione) VALUES (?,?)";



		try {
			connection = DriverManagerConnectionPool.getConnection();
	
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente);
			preparedStatement.setInt(2, 1);
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
	public void aggiungi(String utente, String competizione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		int id=0;
		String insertSQL ="INSERT INTO partecipazione (utente,competizione) VALUES (?,?)";
		String selectSQL ="SELECT idComp FROM competizione WHERE nome=?";



		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement2 = connection.prepareStatement(selectSQL);
			preparedStatement2.setString(1, competizione);
			
			ResultSet rs = preparedStatement2.executeQuery();
			
			if (rs.next()) {
				id=rs.getInt("idComp");
			}
	
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente);
			preparedStatement.setInt(2, id);
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

		
}



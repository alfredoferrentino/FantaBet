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

import control.DriverManagerConnectionPool;



public class CompetizioniModelDS implements CompetizioniModel {

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
	public Collection<String> doRetrieveByUtente(String utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<String> competizioni = new LinkedList<String>();
		String selectSQL = "SELECT c.nome FROM competizione AS c WHERE c.idComp IN (SELECT p.competizione FROM partecipazione AS p WHERE p.utente = ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				competizioni.add(rs.getString("nome"));
			}
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}	finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
		return competizioni;
	}

	@Override
	public void doSave(String nome, int numGiornate, int numPartecipanti) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL ="INSERT INTO competizione (nome,numGiornate,numPartecipanti,approvata) VALUES (?,?,?,?)";



		try {
			connection = DriverManagerConnectionPool.getConnection();
	
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setInt(2, numGiornate);
			preparedStatement.setInt(3, numPartecipanti);
			preparedStatement.setBoolean(4, false);
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

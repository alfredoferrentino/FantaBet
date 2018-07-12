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

import bean.CalciatoreBean;
import bean.ClassificaBean;
import control.DriverManagerConnectionPool;



public class FormazioneModelDS implements FormazioneModel {

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
	public void doSaveFormazione(String utente, int giornata) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL ="INSERT INTO formazione (utente,giornata) VALUES (?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();

			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente);
			preparedStatement.setInt(2, giornata);
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
	public int doRetrieveGiornata() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int giornata=0;
		String selectSQL ="SELECT MAX(idGiornata) FROM giornata";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				giornata = (rs.getInt("MAX(idGiornata)"));

			}
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return giornata;
	}

	@Override
	public boolean checkFormazione(String utente, int giornata) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int formazione=0;
		String selectForm ="SELECT * FROM formazione AS f WHERE f.utente=? AND f.giornata=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();

			preparedStatement = connection.prepareStatement(selectForm);
			preparedStatement.setString(1, utente);
			preparedStatement.setInt(2, giornata);

			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {
				System.out.println("C'è già una formazione per questa giornata" + res.getInt("idForm"));
				return false;
			}

		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return true;
	}

	@Override
	public void doSaveComposizione(int formazione, String[] calciatori) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL ="INSERT INTO composizione (formazione,calciatore) VALUES (?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			for (String e : calciatori) {

				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, formazione);
				preparedStatement.setInt(2, Integer.parseInt(e));

				preparedStatement.executeUpdate();
			}

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
	public int doRetrieveId(String utente, int giornata) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int formazione=0;
		String selectForm ="SELECT * FROM formazione AS f WHERE f.utente=? AND f.giornata=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();

			preparedStatement = connection.prepareStatement(selectForm);
			preparedStatement.setString(1, utente);
			preparedStatement.setInt(2, giornata);

			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {
				formazione= res.getInt("idForm");
			}

		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return formazione;
	}

	@Override
	public Collection<CalciatoreBean> doRetrieveFormazione(String utente, int giornata) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		Collection<CalciatoreBean>  formazione = new LinkedList<CalciatoreBean>();
		String selectSQL = "SELECT * FROM composizione  WHERE formazione = (SELECT idForm FROM formazione WHERE utente = ? AND giornata = ?)";
		String selectCalciatore = "SELECT * FROM calciatore WHERE idCalc= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);
			preparedStatement.setInt(2, giornata);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				preparedStatement2 = connection.prepareStatement(selectCalciatore);
				preparedStatement2.setInt(1, rs.getInt("calciatore"));
				ResultSet res = preparedStatement2.executeQuery();

				while(res.next()) {
					CalciatoreBean bean = new CalciatoreBean();
					bean.setNome(res.getString("nome"));
					bean.setCognome(res.getString("cognome"));
					bean.setRuolo(res.getString("ruolo"));

					formazione.add(bean);
				}
			}
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}	finally {
				if(connection != null) {
					connection.close();
				}
			}
		}
		return formazione;
	}



}
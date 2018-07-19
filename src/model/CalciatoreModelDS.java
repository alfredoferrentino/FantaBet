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
import control.DriverManagerConnectionPool;

public class CalciatoreModelDS implements CalciatoreModel {
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
	public Collection<CalciatoreBean> doRetrieveByRuolo(String ruolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<CalciatoreBean> calciatori = new LinkedList<CalciatoreBean>();

		String selectSQL = "SELECT * FROM calciatore WHERE ruolo=?";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ruolo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CalciatoreBean bean = new CalciatoreBean();

				bean.setSquadra(rs.getString("squadra"));
				bean.setNumero(rs.getInt("numero"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setRuolo(rs.getString("ruolo"));
				bean.setId(rs.getInt("idCalc"));

				calciatori.add(bean);
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
		return calciatori;
	}

	@Override
	public Collection<CalciatoreBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		Collection<CalciatoreBean> calciatori = new LinkedList<CalciatoreBean>();

		String selectSQL = "SELECT * FROM calciatore WHERE idCalc NOT IN (SELECT calciatore FROM voto)";



		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				CalciatoreBean bean = new CalciatoreBean();
				bean.setSquadra(rs.getString("squadra"));
				bean.setNumero(rs.getInt("numero"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setRuolo(rs.getString("ruolo"));
				bean.setId(rs.getInt("idCalc"));

				calciatori.add(bean);
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
		return calciatori;
	}


	@Override
	public void doSaveVoto(int idCalc, int giornata, double votazione, int gol, int assist, boolean ammonizione,
			boolean espulsione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL ="INSERT INTO voto (calciatore,giornata,votazione,gol,assist,Ammonizione,Espulsione) VALUES (?,?,?,?,?,?,?)";



		try {
			connection = DriverManagerConnectionPool.getConnection();

			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, idCalc);
			preparedStatement.setInt(2, giornata);
			preparedStatement.setDouble(3, votazione);
			preparedStatement.setInt(4, gol);
			preparedStatement.setInt(5, assist);
			preparedStatement.setBoolean(6, ammonizione);
			preparedStatement.setBoolean(7, espulsione);
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

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

import bean.ClassificaBean;



public class ClassificaModelDS implements ClassificaModel {

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
	public Collection<ClassificaBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ClassificaBean> classifica_globale = new LinkedList<ClassificaBean>();
		int posizione=0;
		String selectSQL = "SELECT DISTINCT  f.utente,  SUM(f.punteggio) OVER ( PARTITION BY f.utente)  FROM formazione AS f  ORDER BY f.utente DESC";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ClassificaBean bean = new ClassificaBean();
				bean.setNickname(rs.getString("utente"));
				bean.setPosizione(posizione+=1);
				bean.setPunteggio(rs.getInt("SUM(f.punteggio) OVER ( PARTITION BY f.utente)"));
				
				classifica_globale.add(bean);
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
		return classifica_globale;
	}

	@Override
	public Collection<ClassificaBean> doRetriveByComp(String competizione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ClassificaBean> classifica_competizione = new LinkedList<ClassificaBean>();
		int posizione=0;
		String selectSQL = "SELECT DISTINCT  f.utente,  SUM(f.punteggio) OVER ( PARTITION BY f.utente)  FROM formazione AS f  WHERE utente IN (SELECT p.utente FROM partecipazione AS p WHERE p.competizione = (SELECT c.idComp FROM competizione AS c WHERE c.nome = ?)) ORDER BY f.utente DESC";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, competizione);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ClassificaBean bean = new ClassificaBean();
				bean.setNickname(rs.getString("utente"));
				bean.setPosizione(posizione+=1);
				bean.setPunteggio(rs.getInt("SUM(f.punteggio) OVER ( PARTITION BY f.utente)"));
				classifica_competizione.add(bean);
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
		return classifica_competizione;
	}

}

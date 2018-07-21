package competizione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import model.PartecipaModel;
import model.PartecipaModelDS;
import model.UserModel;
import model.UserModelDB;



@WebServlet("/AggiungiPartecipante")
public class AggiungiPartecipante extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static PartecipaModel modello_partecipa = new PartecipaModelDS();
	static UserModel modello_utente = new UserModelDB();
	public AggiungiPartecipante() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("aggiungi-part")) {
					String giocatore = (String) request.getParameter("nome_giocatore");
					String competizione = (String) request.getParameter("nome_competizione");
					System.out.println("Aggiungo il giocatore : " + giocatore + " alla competizione " + competizione);
					if (modello_utente.checkExist(giocatore)) {
						modello_partecipa.aggiungi(giocatore, competizione);
					}
					else  { 
						response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					}

				}
			}


		}
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/competizioni.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

package competizione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import model.CompetizioniModel;
import model.CompetizioniModelDS;



@WebServlet("/RichiestaServlet")
public class RichiestaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CompetizioniModel modello_competizioni = new CompetizioniModelDS();
	public RichiestaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("inserisci-comp")) {
					String nome_competizione = (String) request.getParameter("nome_competizione");
					System.out.println("Inserisco la competizione : " + nome_competizione);
					int num_giornate = Integer.parseInt(request.getParameter("num_giornate"));
					int num_partecipanti = Integer.parseInt(request.getParameter("num_partecipanti"));
					if (!modello_competizioni.checkCompetizione(nome_competizione)) {
					modello_competizioni.doSave(nome_competizione, num_giornate, num_partecipanti);
					String utente = (String) request.getSession().getAttribute("utente");
					modello_competizioni.doPartecipa(utente, modello_competizioni.doRetrieveByNome(nome_competizione));
					}
					else response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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

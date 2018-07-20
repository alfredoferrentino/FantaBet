package competizione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import model.FormazioneModel;
import model.FormazioneModelDS;




@WebServlet("/InserimentoFormazioneServlet")
public class InserimentoFormazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static FormazioneModel modello_formazione = new FormazioneModelDS();
	public InserimentoFormazioneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("insert-form")) {
					System.out.println("Stai inserendo la formazione con i seguenti calciatori : " + request.getParameter("calciatori"));
					String utente = (String) request.getSession().getAttribute("utente");
					int giornata = (int) request.getSession().getAttribute("giornata");
					String parametro = (String) request.getParameter("calciatori");
					String[] calciatori = parametro.split(",");
					if (modello_formazione.checkFormazione(utente, giornata) == false)
						response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					else { 
						modello_formazione.doSaveFormazione(utente, giornata);
						modello_formazione.doSaveComposizione(modello_formazione.doRetrieveId(utente, giornata), calciatori);
						request.getSession().setAttribute("formazione", modello_formazione.doRetrieveFormazione(utente, giornata));
							
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

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




@WebServlet("/CompetizioniUtenteServlet")
public class CompetizioniUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CompetizioniModel modello_competizioni = new CompetizioniModelDS();
	public CompetizioniUtenteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("competizioni")) {
					String utente = (String) request.getSession().getAttribute("utente");
					System.out.println("Ti do le competizioni relative all'utente : " + utente );
					request.getSession().setAttribute("competizioni", modello_competizioni.doRetrieveByUtente(utente));
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

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




@WebServlet("/ApprovazioniServlet")
public class ApprovazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CompetizioniModel modello_competizioni = new CompetizioniModelDS();
	public ApprovazioniServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("approva-comp")) {
					request.getSession().setAttribute("approva-competizioni", modello_competizioni.doRetrieveApproved());
				}
				if (action.equalsIgnoreCase("approvate")) {
					int idComp = Integer.parseInt(request.getParameter("idComp"));
					modello_competizioni.Approva(idComp);
				}
			}


		}
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_competizioni.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

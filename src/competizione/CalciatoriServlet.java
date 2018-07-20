package competizione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import model.CalciatoreModel;
import model.CalciatoreModelDS;




@WebServlet("/CalciatoriServlet")
public class CalciatoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CalciatoreModel modello_calciatore = new CalciatoreModelDS();
	public CalciatoriServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("carica-calciatori")) {
					request.getSession().setAttribute("lista-calciatori", modello_calciatore.doRetrieveAll());
					
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

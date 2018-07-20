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




@WebServlet("/ScegliFormazioneServlet")
public class ScegliFormazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CalciatoreModel modello_calciatore = new CalciatoreModelDS();
	public ScegliFormazioneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("load")) {
					String ruolo = (String) request.getParameter("ruolo");
					System.out.println("Ti do i calciatori con il ruolo di : " + ruolo);
					request.getSession().removeAttribute("calciatori");
					request.getSession().setAttribute("calciatori", modello_calciatore.doRetrieveByRuolo(ruolo));
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

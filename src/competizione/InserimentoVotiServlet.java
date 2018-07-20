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




@WebServlet("/InserimentoVotiServlet")
public class InserimentoVotiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CalciatoreModel modello_calciatore = new CalciatoreModelDS();
	public InserimentoVotiServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("insert-voto")) {
					int idCalc = Integer.parseInt(request.getParameter("idCalc"));
					int giornata = (int) request.getSession().getAttribute("giornata");
					double votazione = Double.parseDouble(request.getParameter("votazione"));
					int gol = Integer.parseInt(request.getParameter("gol"));
					int assist = Integer.parseInt(request.getParameter("assist"));
					boolean ammonizione =  Boolean.parseBoolean(request.getParameter("ammonizione"));
					boolean espulsione = Boolean.parseBoolean(request.getParameter("espulsione"));
					System.out.println("ID Calciatore : " + idCalc + "Giornata : " + giornata + "Votazione : " + votazione +
							"Gol : " + gol + "Assist : " + assist + "Ammonizione : "+ ammonizione + "Espulsione : " + espulsione);
					modello_calciatore.doSaveVoto(idCalc, giornata, votazione, gol, assist, ammonizione, espulsione);
					
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

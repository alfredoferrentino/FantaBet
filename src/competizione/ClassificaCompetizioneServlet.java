package competizione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import model.ClassificaModel;
import model.ClassificaModelDS;




@WebServlet("/ClassificaCompetizioneServlet")
public class ClassificaCompetizioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ClassificaModel modello_classifica = new ClassificaModelDS();
	public ClassificaCompetizioneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("classifica-comp")) {
					String nome_competizione = (String) request.getParameter("nome_competizione");
					System.out.println("Ti do la classifica relativa alla competizione : " + nome_competizione );
					request.getSession().removeAttribute("classifica-competizione");
					request.getSession().setAttribute("classifica-competizione", modello_classifica.doRetriveByComp(nome_competizione));
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

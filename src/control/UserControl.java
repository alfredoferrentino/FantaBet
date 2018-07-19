package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import model.UserModel;
import model.UserModelDB;


@WebServlet("/User")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static UserModel modello_utente = new UserModelDB();

	public UserControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("modifica")) {
					String username = (String) request.getParameter("username");
					String password = (String) request.getParameter("password");
					String nickname = (String) request.getParameter("nickname");
					String email = (String) request.getParameter("email");
					modello_utente.doUpdate(nickname,password,email,username);

				}
			}


		}
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home_utente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

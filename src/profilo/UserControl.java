package profilo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

		try {

			
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String nickname = (String) request.getParameter("nickname");
			String email = (String) request.getParameter("email");
			modello_utente.doUpdate(nickname,password,email,username);



	}
	catch (SQLException e) {
		System.out.println("Error:" + e.getMessage());
	}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("alert('I dati sono stati correttamente modificati. Effettua il logout per visualizzare i cambiamenti');");
		out.println("location='/FantaBet/profilo.jsp';");
		out.println("</script>");
		
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doGet(request, response);
}

}

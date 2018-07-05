package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import model.UserModel;
import model.UserModelDB;

@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
static UserModel model= new UserModelDB();
public Registrazione() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		String email= request.getParameter("email");
		String nick= request.getParameter("nick");
		String ruolo="u";
		
		
		UserBean bean = new UserBean();
		
		bean.setUsername(username);
		bean.setPassword(password);
		bean.setEmail(email);
		bean.setNick(nick);
		bean.setRuolo(ruolo);
		
		
		System.out.println(bean.toString());
		
		model.doSave(bean);
		
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/index.jsp");
		rs.forward(request, response);
	
		
	}
		catch (Exception e) {
			System.out.println("errore");
		}
	}

}

package control;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Login() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sono entrato nella chiamata post");
		
		response.setContentType("tex/html");
		
		String username= request.getParameter("username");
		String password= request.getParameter("psw");
		
		System.out.println("Ho preso i dati dalla form : username = "+ username + " e password = " +password);
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL ="select * from  utente where username=? and pass=?";
		try {
			System.out.println("Sto entrando...");
			connection = DriverManagerConnectionPool.getConnection();
			System.out.println("Ho effettuato la connection" + connection);
		
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet re = preparedStatement.executeQuery();
		if(re.next()) {
			
			System.out.println("Ho fatto il match");
			
			UserBean bean= new UserBean();
			System.out.println("Stu cazz e bean");
			bean.setUsername(username);
			bean.setPassword(password);
			bean.setEmail(re.getString("email"));
			bean.setNick(re.getString("nickname"));
			bean.setRuolo(re.getString("ruolo"));
			
			String ruolo= re.getString("ruolo");
			String email=re.getString("email");
			System.out.println("bean : " + bean.toString());
			
			
			
			HttpSession session= request.getSession();
			session.setAttribute("bean", bean);
			session.setAttribute("isLogged", ruolo);
			session.setAttribute("checkmail", email);
			session.setAttribute("utente", username);
			
			
			
			if (bean.getRuolo().equals("a")) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/admin.jsp");
				rs.forward(request, response);
			}
			
			else if(bean.getRuolo().equals("u")){ 
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/Data");
				rs.forward(request, response);
			}
		}
		
		else {
			System.out.println("username or pass incorrect!");
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/index.jsp");
			rs.forward(request,response);
		}
		
		}
		catch(SQLException e){
			System.out.println("Eccezione");
		}
		
		}
	
	
}


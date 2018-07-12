package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import model.ClassificaModel;
import model.ClassificaModelDS;
import model.FormazioneModel;
import model.FormazioneModelDS;


@WebServlet("/Data")
public class Data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ClassificaModel modello = new ClassificaModelDS();
	static FormazioneModel modello_formazione = new FormazioneModelDS();
   
    public Data() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		try {
			System.out.println("Ti do la classifica");
			session.setAttribute("classifica", modello.doRetrieveAll());
			session.setAttribute("giornata", modello_formazione.doRetrieveGiornata());
			session.setAttribute("formazione", modello_formazione.doRetrieveFormazione((String) session.getAttribute("utente"), (int) session.getAttribute("giornata")));
			
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

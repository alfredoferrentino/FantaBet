package control;

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
import model.CompetizioniModel;
import model.CompetizioniModelDS;
import model.UserModel;
import model.UserModelDB;



@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static CompetizioniModel modello = new CompetizioniModelDS();
	static CalciatoreModel modello_calciatore = new CalciatoreModelDS();
	static UserModel modello_user = new UserModelDB();
    public AdminControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if (action != null) {
				
				if (action.equalsIgnoreCase("remove-comp")) {
					int id = Integer.parseInt(request.getParameter("idComp"));
					modello.doDelete(id);
					
				}
				if (action.equalsIgnoreCase("retrieve-comp")) {
					request.getSession().setAttribute("lista_competizioni", modello.doRetrieveAll());
					
				}
				if (action.equalsIgnoreCase("search-comp")) {
					String nome = request.getParameter("nome");
					request.getSession().setAttribute("lista_competizioni", modello.doSearch(nome));
					
				}
				
				if (action.equalsIgnoreCase("carica-calciatori")) {
					request.getSession().setAttribute("lista-calciatori", modello_calciatore.doRetrieveAll());
				}
				
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
				if (action.equalsIgnoreCase("approva-comp")) {
					request.getSession().setAttribute("approva-competizioni", modello.doRetrieveApproved());
				}
				if (action.equalsIgnoreCase("approvate")) {
					int idComp = Integer.parseInt(request.getParameter("idComp"));
					modello.Approva(idComp);
				}
				if (action.equalsIgnoreCase("utenti")) {
					request.getSession().setAttribute("lista_utenti", modello_user.doRetrieveAll());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_utenti.jsp");
					dispatcher.forward(request, response);
				}
				if (action.equalsIgnoreCase("modifica-utente")) {
					String username = (String) request.getParameter("username");
					String password = (String) request.getParameter("password");
					String nickname = (String) request.getParameter("nickname");
					String email = (String) request.getParameter("email");
					modello_user.doUpdate(nickname, password, email, username);
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

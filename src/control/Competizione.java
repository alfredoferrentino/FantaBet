package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import model.CalciatoreModel;
import model.CalciatoreModelDS;
import model.ClassificaModel;
import model.ClassificaModelDS;
import model.CompetizioniModel;
import model.CompetizioniModelDS;
import model.FormazioneModel;
import model.FormazioneModelDS;
import model.PartecipaModel;
import model.PartecipaModelDS;


@WebServlet("/Competizione")
public class Competizione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static CompetizioniModel modello = new CompetizioniModelDS();
	static ClassificaModel modello_classifica = new ClassificaModelDS();
	static CalciatoreModel modello_calciatore = new CalciatoreModelDS();
	static FormazioneModel modello_formazione = new FormazioneModelDS();
	static PartecipaModel modello_partecipazione = new PartecipaModelDS();
    public Competizione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if (action != null) {
				if (action.equalsIgnoreCase("competizioni")) {
					String utente = (String) request.getSession().getAttribute("utente");
					System.out.println("Ti do le competizioni relative all'utente : " + utente );
					request.getSession().setAttribute("competizioni", modello.doRetrieveByUtente(utente));
				}
				if (action.equalsIgnoreCase("classifica-comp")) {
					String nome_competizione = (String) request.getParameter("nome_competizione");
					System.out.println("Ti do la classifica relativa alla competizione : " + nome_competizione );
					request.getSession().removeAttribute("classifica-competizione");
					request.getSession().setAttribute("classifica-competizione", modello_classifica.doRetriveByComp(nome_competizione));
				}
				if (action.equalsIgnoreCase("inserisci-comp")) {
					String nome_competizione = (String) request.getParameter("nome_competizione");
					System.out.println("Inserisco la competizione : " + nome_competizione);
					int num_giornate = Integer.parseInt(request.getParameter("num_giornate"));
					int num_partecipanti = Integer.parseInt(request.getParameter("num_partecipanti"));
					modello.doSave(nome_competizione, num_giornate, num_partecipanti);
				}
				if (action.equalsIgnoreCase("load")) {
					String ruolo = (String) request.getParameter("ruolo");
					System.out.println("Ti do i calciatori con il ruolo di : " + ruolo);
					request.getSession().removeAttribute("calciatori");
					request.getSession().setAttribute("calciatori", modello_calciatore.doRetrieveByRuolo(ruolo));
				}
				if (action.equalsIgnoreCase("insert-form")) {
					System.out.println("Stai inserendo la formazione con i seguenti calciatori : " + request.getParameter("calciatori"));
					String utente = (String) request.getSession().getAttribute("utente");
					int giornata = (int) request.getSession().getAttribute("giornata");
					String parametro = (String) request.getParameter("calciatori");
					String[] calciatori = parametro.split(",");
					if (modello_formazione.checkFormazione(utente, giornata) == false)
						response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					else { 
						modello_formazione.doSaveFormazione(utente, giornata);
						modello_formazione.doSaveComposizione(modello_formazione.doRetrieveId(utente, giornata), calciatori);
						request.getSession().setAttribute("formazione", modello_formazione.doRetrieveFormazione(utente, giornata));
							
					}
				}
				if (action.equalsIgnoreCase("aggiungi-part")) {
					String giocatore = (String) request.getParameter("nome_giocatore");
					String competizione = (String) request.getParameter("nome_competizione");
					System.out.println("Aggiungo il giocatore : " + giocatore + " alla competizione " + competizione);
					modello_partecipazione.aggiungi(giocatore, competizione);
					
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

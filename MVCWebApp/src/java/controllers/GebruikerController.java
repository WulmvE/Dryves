package controllers;

import java.io.*;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;

public class GebruikerController extends HttpServlet {

    /* HTTP GET request */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession sessie = request.getSession(); //Haalt gegevens uit de sessie.
        LinkedList<User> gebruikersLijst = new LinkedList(); //Maak een nieuwe LinkedList aan voor Gebruikers in op te slaan

        //Als gebruikers al in de sessie staan opgeslagen, worden deze opgehaald en in een LinkedList opgeslagen
        if (sessie.getAttribute("gebruikers") != null) {

            gebruikersLijst = (LinkedList) sessie.getAttribute("gebruikers");
        } else {//Anders wordt een nieuwe LinkedList aangemaakt
            gebruikersLijst = new LinkedList();
        }

        request.setAttribute("gebruikersUitSessie", gebruikersLijst); //De gebruikerslijst wordt opgeslagen in de sessie
        request.setAttribute("aantalGebruikers", gebruikersLijst.size()); // Het aantal gebruikers wordt opgeslagen in de sessie.

        String address = "/gebruikers.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response); //Stuurt de browser door naar het adres.
    }
}

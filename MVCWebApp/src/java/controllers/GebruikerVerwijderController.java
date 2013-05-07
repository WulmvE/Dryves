package controllers;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;

public class GebruikerVerwijderController extends HttpServlet {
    /* HTTP GET request */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));

            HttpSession sessie = request.getSession(); // Haalt gegevens uit de sessie op.
            LinkedList<User> gebruikers = (LinkedList) sessie.getAttribute("gebruikers"); //Haalt de gebruikers uit de sessie op.
            LinkedList<User> tempGebruikers = new LinkedList(); //Maakt een nieuwe LinkedList met gebruikers aan.

            for (int i = 0; i < gebruikers.size(); i++) {
                User tempGebruiker = (User) gebruikers.get(i);

                //Voegt alle gebruikers toe aan de nieuwe lijst met gebruikers, behalve de gebruiker die wordt verwijderd.
                if (tempGebruiker.getCustomerNumber() != id) {
                    tempGebruikers.add(tempGebruiker);
                }
            }

            sessie.setAttribute("gebruikers", tempGebruikers); //Nieuwe gebruikerslijst wordt opgeslagen in de sessie.
            response.sendRedirect("../gebruikers"); //Stuurt door naar de Gebruikers index
        }
    }
}

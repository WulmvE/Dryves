package controllers;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;
import models.Role;

public class RolVerwijderController extends HttpServlet {
    /* HTTP GET request */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));

            HttpSession sessie = request.getSession(); // Haalt gegevens uit de sessie op.
            LinkedList<Role> rollen = (LinkedList) sessie.getAttribute("rollen"); //Haalt de gebruikers uit de sessie op.
            LinkedList<Role> tempRollen = new LinkedList(); //Maakt een nieuwe LinkedList met gebruikers aan.

            for (int i = 0; i < rollen.size(); i++) {
                Role tempRol = (Role) rollen.get(i);

                //Voegt alle gebruikers toe aan de nieuwe lijst met gebruikers, behalve de gebruiker die wordt verwijderd.
                if (tempRol.getRoleNumber() != id) {
                    tempRollen.add(tempRol);
                }
            }

            sessie.setAttribute("rollen", tempRollen); //Nieuwe gebruikerslijst wordt opgeslagen in de sessie.
            response.sendRedirect("../rollen"); //Stuurt door naar de Gebruikers index
        }
    }
}

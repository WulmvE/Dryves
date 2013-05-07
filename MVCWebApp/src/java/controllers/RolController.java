/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;

/**
 *
 * @author Bjorn
 */
public class RolController extends HttpServlet {

      public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession sessie = request.getSession(); //Haalt gegevens uit de sessie.
        LinkedList<Role> rollenLijst = new LinkedList(); //Maak een nieuwe LinkedList aan voor Gebruikers in op te slaan

        //Als gebruikers al in de sessie staan opgeslagen, worden deze opgehaald en in een LinkedList opgeslagen
        if (sessie.getAttribute("rollen") != null) {

            rollenLijst = (LinkedList) sessie.getAttribute("rollen");
        } else {//Anders wordt een nieuwe LinkedList aangemaakt
            rollenLijst = new LinkedList();
        }

        request.setAttribute("rollenUitSessie", rollenLijst); //De gebruikerslijst wordt opgeslagen in de sessie
        request.setAttribute("aantalRollen", rollenLijst.size()); // Het aantal gebruikers wordt opgeslagen in de sessie.

        String address = "/rollen.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response); //Stuurt de browser door naar het adres.
    }
}
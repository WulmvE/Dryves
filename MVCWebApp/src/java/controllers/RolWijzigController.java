package controllers;

import java.io.*;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;
import models.Role;

public class RolWijzigController extends HttpServlet {

    private static String titelNieuw = "Nieuwe rol"; //Titel voor de Nieuwe gebruiker pagina
    private static String titelWijzig = "Wijzigen rol"; //Titel voor de Wijzig gebruiker pagina

    /* HTTP GET request */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            //Als er een id is meegegeven, worden de gegevens van de gebruiker opgehaald.
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id);

            HttpSession sessie = request.getSession(); //Haalt gegevens uit de sessie op.
            LinkedList rollen = (LinkedList) sessie.getAttribute("rollen"); //Haalt de lijst met gebruikers op en slaat deze op in een LinkedList

            for (int i = 0; i < rollen.size(); i++) {
                Role tempRole = (Role) rollen.get(i);

              //Als de rol overeenkomt met het gegeven id, worden de gegevens ingevuld in het formulier.
              if (tempRole.getRoleNumber() == id) {
                    request.setAttribute("naam", tempRole.getName());
                 
                   
                }
            }
            doorsturen(request, response, titelWijzig); //Stuurt door naar de Wijzig gebruiker pagina.
        } else {
            doorsturen(request, response, titelNieuw); //Stuurt door naar de Nieuwe gebruiker pagina.
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        long id = 0;
        String titel = new String();
        String naam = new String();
       
        String redirect = new String();
        

        if (request.getParameter("id") != null) {
            
       
            id = Long.parseLong(request.getParameter("id"));
            redirect = titelWijzig;
        } else {
            redirect = titelNieuw;
        }
        
        if (request.getParameter("naam").equals("")) {
            request.setAttribute("foutMelding", "u heeft de rol naam niet ingevuld");
            doorsturen(request, response , redirect);
        }else{
            naam = request.getParameter("naam");
        }
        
        
        
        
        //Als de boolean nog steeds true is, kan er verder worden gegaan met het opslaan van de gebruiker
        HttpSession sessie = request.getSession(); //Haalt de gegevens uit de sessie op.
        LinkedList<Role> rollen = new LinkedList(); //Maakt nieuwe LinkedList aan voor het opslaan van de gebruikers.

        if (sessie.getAttribute("rollen") != null) {
            rollen = (LinkedList) sessie.getAttribute("rollen"); //Haalt de huidige lijst met gebruikers op uit de sessie.
        }
        
        //Als er een id is meegestuurd, wordt de gebruiker geupdate.
        if (id != 0) {
            LinkedList<Role> tempRollen = new LinkedList();

            for (int i = 0; i < rollen.size(); i++) {
                Role tempRol = (Role) rollen.get(i);
                
                //Als de gebruiker overeenkomt met het gegeven id, wordt de gebruiker geupdate.
                if (tempRol.getRoleNumber() == id) {
                    tempRol.setName(naam);
               
                }
                tempRollen.add(tempRol);
            }
            rollen = tempRollen;
        } 
        else {//Anders wordt de rol aangemaakt.
            long uniekId = System.nanoTime();
            Role rol = new Role(uniekId, naam);
            rollen.add(rol);
        }

        sessie.setAttribute("rollen", rollen);
        
        response.sendRedirect("../rollen"); //Stuurt door naar de Rollen index
        
    } 

    private void doorsturen(HttpServletRequest request, HttpServletResponse response, String titel)
            throws ServletException, IOException {

        request.setAttribute("paginaTitel", titel);
        

        String address = "/rol_wijzigen.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

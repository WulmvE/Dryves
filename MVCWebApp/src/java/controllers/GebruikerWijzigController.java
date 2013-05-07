package controllers;

import java.io.*;
import java.util.LinkedList;
import javax.servlet.*;
import javax.servlet.http.*;
import models.Role;
import models.User;

public class GebruikerWijzigController extends HttpServlet {

    private static String titelNieuw = "Nieuwe gebruiker"; //Titel voor de Nieuwe gebruiker pagina
    private static String titelWijzig = "Wijzigen gebruiker"; //Titel voor de Wijzig gebruiker pagina

    /* HTTP GET request */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            //Als er een id is meegegeven, worden de gegevens van de gebruiker opgehaald.
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id);

            HttpSession sessie = request.getSession(); //Haalt gegevens uit de sessie op.
            LinkedList gebruikers = (LinkedList) sessie.getAttribute("gebruikers"); //Haalt de lijst met gebruikers op en slaat deze op in een LinkedList

            for (int i = 0; i < gebruikers.size(); i++) {
                User tempGebruiker = (User) gebruikers.get(i);

              //Als de gebruiker overeenkomt met het gegeven id, worden de gegevens ingevuld in het formulier.
              if (tempGebruiker.getCustomerNumber() == id) {
                    request.setAttribute("naam", tempGebruiker.getName());
                    request.setAttribute("adres", tempGebruiker.getStreetAddress());
                    request.setAttribute("huisnummer", tempGebruiker.getStreetNumber());
                    request.setAttribute("plaats", tempGebruiker.getCity());
                   
                }
            }
             
            LinkedList<User> rollenLijst = new LinkedList();
            rollenLijst = (LinkedList) sessie.getAttribute("rollen");
            request.setAttribute("rollenUitSessie", rollenLijst);
            doorsturen(request, response, titelWijzig); //Stuurt door naar de Wijzig gebruiker pagina.
        } else {
             HttpSession sessie = request.getSession(); //Haalt gegevens uit de sessie.
            LinkedList<User> rollenLijst = new LinkedList();
            rollenLijst = (LinkedList) sessie.getAttribute("rollen");
            sessie.setAttribute("rollenUitSessie", rollenLijst);
            doorsturen(request, response, titelNieuw); //Stuurt door naar de Nieuwe gebruiker pagina.
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        long id = 0;
       
        String naam = new String();
        String adres = new String();
        String plaats = new String();
        String huisnummer = new String();
        String redirect = new String();
        String rol = new String();
      
        

        if (request.getParameter("id") != null) {
            
       
            id = Long.parseLong(request.getParameter("id"));
            redirect = titelWijzig;
        } else {
            redirect = titelNieuw;
        }
        
        if (request.getParameter("naam").equals("")) {
            request.setAttribute("foutMelding", "u heeft uw naam niet ingevuld");
            doorsturen(request, response , redirect);
            return;
        }else{
            naam = request.getParameter("naam");
        }
        
        if (request.getParameter("straatnaam").equals("")) {
            request.setAttribute("foutMelding", "u heeft straatnaam niet ingevuld");
            doorsturen(request, response , redirect);
            return;
            
        } else {
            adres = request.getParameter("straatnaam");
        }
        
        if (request.getParameter("plaats").equals("")) {
            request.setAttribute("foutMelding", "u heeft plaats niet ingevuld");
            doorsturen(request, response , redirect);
            return;
        } else {
            plaats = request.getParameter("plaats");
        }
        
        if (request.getParameter("huisnummer").equals("")){
            request.setAttribute("foutMelding", "u heeft huisnummer niet ingevuld");
            doorsturen(request, response , redirect);
            return;
            
        } else {
            huisnummer = request.getParameter("huisnummer");
        }
        
        if(request.getParameter("rollen") != ""){
            rol = request.getParameter("rollen");
            
        }
        
        //Als de boolean nog steeds true is, kan er verder worden gegaan met het opslaan van de gebruiker
        HttpSession sessie = request.getSession(); //Haalt de gegevens uit de sessie op.
        LinkedList<User> gebruikers = new LinkedList(); //Maakt nieuwe LinkedList aan voor het opslaan van de gebruikers.

        if (sessie.getAttribute("gebruikers") != null) {
            gebruikers = (LinkedList) sessie.getAttribute("gebruikers"); //Haalt de huidige lijst met gebruikers op uit de sessie.
        }
        
        //Als er een id is meegestuurd, wordt de gebruiker geupdate.
        if (id != 0) {
            LinkedList<User> tempGebruikers = new LinkedList();

            for (int i = 0; i < gebruikers.size(); i++) {
                User tempGebruiker = (User) gebruikers.get(i);
                
                //Als de gebruiker overeenkomt met het gegeven id, wordt de gebruiker geupdate.
                if (tempGebruiker.getCustomerNumber() == id) {
                    tempGebruiker.setName(naam);
                    tempGebruiker.setStreetAddress(adres);
                    tempGebruiker.setCity(plaats);
                    tempGebruiker.setStreetNumber(huisnummer);
                    tempGebruiker.setRole(rol);
                }
                tempGebruikers.add(tempGebruiker);
            }
            gebruikers = tempGebruikers;
        } 
        else {//Anders wordt de gebruiker aangemaakt.
            long uniekId = System.nanoTime();
            User gebruiker = new User(uniekId, naam, adres, plaats , huisnummer , rol);
            gebruikers.add(gebruiker);
        }

        sessie.setAttribute("gebruikers", gebruikers);
        
        response.sendRedirect("../gebruikers"); //Stuurt door naar de Gebruikers index
        
    } 

    private void doorsturen(HttpServletRequest request, HttpServletResponse response, String titel)
            throws ServletException, IOException {

        request.setAttribute("paginaTitel", titel);
        

        String address = "/gebruiker_wijzigen.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
    
}

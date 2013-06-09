/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Dryver;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CarFacade;
import session.DryverFacade;
import session.MessageFacade;

/**
 *
 * @author Willem
 */
@WebServlet(name = "RegisterServlet",
        loadOnStartup = 1,
        urlPatterns = {"/register", "/registerConfirmed", "/contact"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private DryverFacade DryverFacade;
    @EJB
    private CarFacade CarFacade;
    
    
    private String alias;
    private String tempAlias;

    @Override
    public void init() throws ServletException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GET");
        String userPath = request.getServletPath();
        if (userPath.equals("/register")) {
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();


        if (userPath.equals("/register")) {
            tempAlias = request.getParameter("j_username");
            getServletContext().setAttribute("alias", tempAlias);
        }

        if (userPath.equals("/registerConfirmed")) {
            
      
            
           String gebruiker = request.getParameter("alias");
            if (gebruiker.equals("")) {
                gebruiker = tempAlias;
            }

            String firstName = request.getParameter("firstName");
            String adjective = request.getParameter("adjective");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String city = request.getParameter("city");
            String password = request.getParameter("password");
            
            System.out.println("alias" + gebruiker);
            System.out.println("firstname" + firstName);
            System.out.println("adjective" + adjective);
            System.out.println("lastname" + lastName);
            System.out.println("email" + email);
            System.out.println("city" + city);
            System.out.println("password" + password);
            

            int dryverId = DryverFacade.createDryver(gebruiker, firstName, adjective, lastName, email, city, password, "m", "15-06-1983");
            
            String brand = request.getParameter("carBrand");
            int numSeats = Integer.parseInt(request.getParameter("numSeats"));
            Dryver dryver = DryverFacade.find(dryverId);
            
            
            int carId = CarFacade.createCar(brand, numSeats, dryver);
        }




        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
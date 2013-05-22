/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.lang.Exception;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.DryverFacade;
import session.RideFacade;

/**
 *
 * @author hctung
 */
@WebServlet(name = "SearchRideServlet",
        loadOnStartup = 1,
        urlPatterns = {"/test", "/searchRide", "/searchRideDetails", "/searchRideList", "/searchresults"})
public class SearchRideServlet extends HttpServlet {

    @EJB
    private DryverFacade dryverFacade;
    
    @EJB
    private RideFacade rideFacade;

    @Override
    public void init() throws ServletException {

        // store category list in servlet context
        getServletContext().setAttribute("dryvers", dryverFacade.findAll());
        
       
    }

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

        // if searchRideDetails page is requested
        if (userPath.equals("/searchRideDetails")) {
            // TODO: Implement searchRideDetails request
            // if searchRideList page is requested
        } else if (userPath.equals("/searchRideList")) {
            // TODO: test, of de controller request forward naar view
        } else if (userPath.equals("/test")) {
            //dit is een test
        } else if (userPath.equals("/searchresults")){
            
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
        //getServletContext().setAttribute("rides", rideFacade.findAll());
        String van = request.getParameter("search_start");
        String naar = request.getParameter("search_destination");
        String op = request.getParameter("search_date");
        getServletContext().setAttribute("rides", rideFacade.searchRideByStart(van));
        int aantalrides = rideFacade.searchRideByStart(van).size();
        getServletContext().setAttribute("aantalrides", aantalrides);
//        String att =   (String) request.getAttribute("search_start");
//        System.out.println("TEST");
//        System.out.println("att: " + att);
//        System.out.println("uri path" + request.getRequestURI());
//        System.out.println("van : "+ van);
//        System.out.println(naar);
//        System.out.println(op);
//        System.out.println("POST");
//        rideFacade.searchedRides(van, naar, op);
        String userPath = request.getServletPath();

        // if searchRide action is called
        if (userPath.equals("/searchRide")) {
            // TODO: Implement search ride action
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

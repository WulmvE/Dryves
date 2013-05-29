/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Car;
import entity.Dryver;
import entity.Ride;
import java.io.IOException;
import java.lang.Exception;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CarFacade;
import session.DryverFacade;
import session.RideFacade;
import session.RideManager;

/**
 *
 * @author hctung
 */
@WebServlet(name = "SearchRideServlet",
        loadOnStartup = 1,
        urlPatterns = {"/test", "/searchRide", "/searchRideDetails", "/rideDetails", "/searchRideList", "/searchresults", "/createRide", "/createRideDetails", "/createRideConfirmed"})
public class SearchRideServlet extends HttpServlet {

    @EJB
    private DryverFacade dryverFacade;
    @EJB
    private RideFacade rideFacade;
    @EJB
    private RideManager rideManager;
    @EJB
    private CarFacade carFacade;
    private String tempStartLocation;
    private String tempEndLocation;
    private String tempDate;

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

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        Ride selectedRide;
//        System.out.println("GET");

        // if searchRideDetails page is requested
        if (userPath.equals("/searchresults")) {
//            String van = request.getParameter("search_start");
//            String naar = request.getParameter("search_destination");
//            String op = request.getParameter("search_date");
//            session.setAttribute("rides", rideFacade.searchRideByStart(van));
//            int aantalrides = rideFacade.searchRideByStart(van).size();
//
//
//            session.setAttribute("aantalrides", aantalrides);
        } else if (userPath.equals("/rideDetails")) {

            String idRide = request.getQueryString();

            if (idRide != null) {

                // get selected ride
                selectedRide = rideFacade.find(Integer.parseInt(idRide));

                // place selected category in session scope
                session.setAttribute("selectedRide", selectedRide);

            }

        } else if (userPath.equals("/searchRideList")) {
            // TODO: test, of de controller request forward naar view
        } else if (userPath.equals("/test")) {
            //dit is een test
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
        HttpSession session = request.getSession();

        // if searchRide action is called
        if (userPath.equals("/searchresults")) {
            String van = request.getParameter("search_start");
            String naar = request.getParameter("search_destination");
            String op = request.getParameter("search_date");
            getServletContext().setAttribute("rides", rideFacade.searchRideByStart(van));
            int aantalrides = rideFacade.searchRideByStart(van).size();
            getServletContext().setAttribute("aantalrides", aantalrides);
        }

        // if createRide action is called
        if (userPath.equals("/createRide")) {

            // pass parameters to createRideDetails
            tempStartLocation = request.getParameter("create_start");
            tempEndLocation = request.getParameter("create_destination");
            tempDate = request.getParameter("create_date");

            getServletContext().setAttribute("create_start", tempStartLocation);
            getServletContext().setAttribute("create_end", tempEndLocation);
            getServletContext().setAttribute("create_date", tempDate);

        }


        // if createRideConfirmed action is called
        if (userPath.equals("/createRideConfirmed")) {

            String startLocation = request.getParameter("create_start");
            if (startLocation.equals("")) {
                startLocation = tempStartLocation;
            }
            String endLocation = request.getParameter("create_destination");
            if (endLocation.equals("")) {
                endLocation = tempEndLocation;
            }
            String date = request.getParameter("create_date");
            if (date.equals("")) {
                date = tempDate;
            }
            System.out.println("Test2000");
            System.out.println(startLocation);
            System.out.println("Test2000");
            System.out.println(endLocation);
            System.out.println(date);
            Dryver dryver = new Dryver(100);
            Car car = new Car(100);



            int rideId = rideManager.placeRide(startLocation, endLocation, dryver, car);

        }


        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

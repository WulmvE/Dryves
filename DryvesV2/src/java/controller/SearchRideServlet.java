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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        urlPatterns = {"/test", "/searchRide", "/searchRideDetails", "/searchRideList", "/searchresults", "/createRide"})
public class SearchRideServlet extends HttpServlet {

    @EJB
    private DryverFacade dryverFacade;
    @EJB
    private RideFacade rideFacade;
     @EJB
    private RideManager rideManager;
      @EJB
    private CarFacade carFacade;

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
        } else if (userPath.equals("/searchresults")) {
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
            String startLocation = request.getParameter("create_start");
            String endLocation = request.getParameter("create_destination");
            Dryver dryver = dryverFacade.find(100);
            Car car = carFacade.find(100);
 
            int rideId = rideManager.placeRide(startLocation, endLocation, dryver, car);

            /*Date departureDate = new Date();
            Date departureTime = new Date();
            

            // to be asked
            double askingPrice = 10.0;
            String seatsAvailable = "2";
            boolean status = false;

            Ride ride = new Ride();
            ride.setStartLocation(startLocation);
            ride.setEndLocation(endLocation);
            ride.setDepartureDate(departureDate);
            ride.setDepartureTime(departureTime);
            ride.setAskingPrice(askingPrice);
            ride.setSeatsAvailable(seatsAvailable);
            ride.setStatus(status);*/

            
        }

        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

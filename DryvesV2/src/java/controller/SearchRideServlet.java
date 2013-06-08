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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author hctung
 */
@WebServlet(name = "SearchRideServlet",
        loadOnStartup = 1,
        urlPatterns = {"/test", "/searchRide", "/searchRideDetails", "/rideDetails", "/searchRideList", "/searchresults", "/createRideDetails"})
public class SearchRideServlet extends HttpServlet {

    @EJB
    private DryverFacade dryverFacade;
    @EJB
    private RideFacade rideFacade;
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

        
        // if myDryves2 page is requested
        if (userPath.equals("/myDryves2")) {
            //TODO: myDryves2 logic
            
            
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
            if  (tempStartLocation.equals("")){
                tempStartLocation = "van";
            }
            tempEndLocation = request.getParameter("create_destination");
            if  (tempEndLocation.equals("")){
                tempEndLocation = "naar";
            }
            tempDate = request.getParameter("create_date");
            if  (tempDate.equals("")){
                tempDate = "datum";
            }
            
            Dryver dryver = dryverFacade.find(100); 
            List<Car> carList = dryver.getCarList();
            String tempCar = carList.iterator().next().getBrand();

            getServletContext().setAttribute("create_start", tempStartLocation);
            getServletContext().setAttribute("create_end", tempEndLocation);
            getServletContext().setAttribute("create_date", tempDate);
            getServletContext().setAttribute("create_car", tempCar);

        }

        // if createRideConfirmed action is called
        if (userPath.equals("/createRideConfirmed")) {
            //start
            String startLocation = request.getParameter("create_start");
            if (startLocation.equals("")) {
                startLocation = tempStartLocation;
            }
            //end
            String endLocation = request.getParameter("create_destination");
            if (endLocation.equals("")) {
                endLocation = tempEndLocation;
            }

            //date
            String date = request.getParameter("create_date");
            if (date.equals("")) {
                date = tempDate;
            }
            DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
            Date dateObj = new Date();
            try {
                dateObj = df.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //car
            //TODO

            //number of seats
            int numSeats = Integer.parseInt(request.getParameter("create_num_seats"));

            //price
            String price = request.getParameter("create_price");
            double distance = 60;
            Dryver dryver = dryverFacade.find(100); 
            List<Car> carList = dryver.getCarList();
            Car car = carList.get(0);
            
            int rideId = rideFacade.placeRide(startLocation, endLocation, dryver, car, dateObj, numSeats, price, distance);

        }






        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Car;
import entity.Dryver;
import entity.Ride;
import java.io.IOException;
import java.io.*;
import java.lang.Exception;
import java.lang.reflect.Member;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import session.CarFacade;
import session.DryverFacade;
import session.RideFacade;

/**
 *
 * @author Maartje
 */
@WebServlet(name = "UserServlet",
        loadOnStartup = 1,
        urlPatterns = {"/myDryves",
    "/changeprofile",
    "/createRideConfirmed",
    "/createRide",
    "/logout"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"DryvesUser"}))
public class UserServlet extends HttpServlet {

    private String userPath;
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
        getServletContext().setAttribute("cars", carFacade.findAll());
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        userPath = request.getServletPath();
        // if myDryves is requested
        if (userPath.equals("/myDryves")) {
        }
        // if changeProfile is requested
        if (userPath.equals("/changeprofile")) {
//            orderList = customerOrderFacade.findAll();
//            request.setAttribute("orderList", orderList);
        }


        // use RequestDispatcher to forward request internally
        userPath = "index.jsp";
        try {
            request.getRequestDispatcher(userPath).forward(request, response);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
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
//                  ALIAS IS j_username IN HET INLOGSCHERM
        String alias = request.getUserPrincipal().getName();
        getServletContext().setAttribute("alias", alias);



        String testHttp;
        testHttp = request.getLocalAddr();

        String referer = request.getHeader("Referer");
        if (referer == null) {
            referer = "none";
        }

        String terug = response.getContentType();

        request.setAttribute("en", terug);

        request.setAttribute("httpistest", testHttp);
        request.setAttribute("mijnreferer", referer);
//          request.setAttribute("mijn_first_name", mijnFirstName);
//          request.setAttribute("mijn_first_name", mijnAdjective);
//          request.setAttribute("mijn_first_name", mijnLastName);
//          request.setAttribute("mijn_first_name", mijnAvgRating);
//          request.setAttribute("mijn_first_name", mijnCity);
//          request.setAttribute("mijn_first_name", mijnEmail);         




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
            }
            catch (ParseException ex) {
                Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //car
            //TODO
            //number of seats
            int numSeats = Integer.parseInt(request.getParameter("create_num_seats"));
            //price
            String price = request.getParameter("create_price");
            Dryver dryver = dryverFacade.find(100);
            List<Car> carList = dryver.getCarList();
            Car car = carList.get(0);

            int rideId = rideFacade.placeRide(startLocation, endLocation, dryver, car, dateObj, numSeats, price);
        }


        if (userPath.equals("/logout")) {
            HttpSession session = request.getSession();
            alias = "";



            session.invalidate();   // terminate session
            try {
                getServletContext().setAttribute("alias", alias);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch (Exception ex) {
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
        if (userPath.equals("/myDryves")) {
            // TODO: Implement search ride action
        }

        // if createRide action is called
        if (userPath.equals("/createRide")) {
            // pass parameters to createRideDetails
            tempStartLocation = request.getParameter("create_start");
            if (tempStartLocation.equals("")) {
                tempStartLocation = "van";
            }
            tempEndLocation = request.getParameter("create_destination");
            if (tempEndLocation.equals("")) {
                tempEndLocation = "naar";
            }
            tempDate = request.getParameter("create_date");
            if (tempDate.equals("")) {
                tempDate = "datum";
            }

            Dryver dryver = dryverFacade.find(100);
            List<Car> carList = dryver.getCarList();
            String tempCar = carList.iterator().next().getBrand();
            request.setAttribute("create_start", tempStartLocation);
            request.setAttribute("create_end", tempEndLocation);
            request.setAttribute("create_date", tempDate);
            request.setAttribute("create_car", tempCar);
        }


        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
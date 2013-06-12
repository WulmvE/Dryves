/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Car;
import entity.Dryver;
import entity.Negotiation;
import entity.Ride;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javax.servlet.http.HttpSession;
import session.CarFacade;
import session.DryverFacade;
import session.FriendFacade;
import session.MessageFacade;
import session.NegotiationFacade;
import session.RideFacade;

/**
 *
 * @author Maartje
 */
@WebServlet(name = "UserServlet",
        loadOnStartup = 1,
        urlPatterns = {"/myDryves",
    "/changeProfile",
    "/changeProfileConfirmed",
    "/createRideConfirmed",
    "/createRide",
    "/logout",
    "/rideDetails"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"DryvesUser"}))
public class UserServlet extends HttpServlet {

    private String userPath;
    @EJB
    private DryverFacade dryverFacade;
    @EJB
    private RideFacade rideFacade;
    @EJB
    private FriendFacade friendFacade;
    @EJB
    private CarFacade carFacade;
    @EJB
    private MessageFacade messageFacade;
    @EJB
    private NegotiationFacade negotiationFacade;
    private String tempStartLocation;
    private String tempEndLocation;
    private String tempDate;

    @Override
    public void init() throws ServletException {
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
        // ALIAS IS value van j_username form field IN HET INLOGSCHERM
        String alias = request.getUserPrincipal().getName();
        session.setAttribute("alias", alias);

        int idMember = dryverFacade.findByAlias(alias).getIdMember();
        session.setAttribute("idMember", idMember);

        String referer = request.getHeader("Referer");
        if (referer == null) {
            referer = "none";
        }

        String terug = response.getContentType();

        request.setAttribute("en", terug);

        request.setAttribute("mijnreferer", referer);
        Ride selectedRide;

        if (userPath.equals("/rideDetails")) {

            String idRide = request.getQueryString();

            if (idRide != null) {

                // get selected ride
                selectedRide = rideFacade.find(Integer.parseInt(idRide));

                // place selected category in session scope
                session.setAttribute("selectedRide", selectedRide);
            }
        }

        if (userPath.equals("/myDryves")) {

            String loggedInUser = request.getUserPrincipal().getName();
            int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();

            Dryver dryver = dryverFacade.find(loggedInUserId);

            request.setAttribute("rides", rideFacade.findByDryver(dryver));
            request.setAttribute("rides_passenger", rideFacade.findByNegotiationIdMember(dryver));
            request.setAttribute("friends", friendFacade.findByDryver(dryver));
            request.setAttribute("profileDryver", dryver);
        }

        if (userPath.equals("/changeProfile")) {
            String loggedInUser = request.getUserPrincipal().getName();
            int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();

            Dryver dryver = dryverFacade.find(loggedInUserId);

            request.setAttribute("dryver", dryver);
            List<Car> carList = dryver.getCarList();
            Car carProfileDryver = carList.iterator().next();

            request.setAttribute("carProfileDryver", carProfileDryver);
        }


        if (userPath.equals("/logout")) {
            //HttpSession session = request.getSession();
            alias = "";

            session.invalidate();   // terminate session
            try {
                request.setAttribute("alias", alias);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
        HttpSession session = request.getSession();

        //get alias of user and store in session
        String alias = request.getUserPrincipal().getName();
        session.setAttribute("alias", alias);

        int idMember = dryverFacade.findByAlias(alias).getIdMember();
        session.setAttribute("idMember", idMember);

        //nieuw
        Ride selectedRide;

        if (userPath.equals("/rideDetails")) {

            int requestRide = Integer.parseInt(request.getParameter("requestRide"));
            int requestDryver = dryverFacade.findByAlias(alias).getIdMember();


            selectedRide = rideFacade.find(requestRide);

            session.setAttribute("selectedRide", selectedRide);

            if (request.getParameter("requestNegotiation").equals("1")) {

                Negotiation negotiation = new Negotiation(requestRide, requestDryver);
                negotiation.setAcceptedDriver(0);
                negotiation.setAcceptedPassenger(1);
                negotiationFacade.create(negotiation);
            }
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

            int dryverId = dryverFacade.findByAlias(alias).getIdMember();
            Dryver dryver = dryverFacade.find(dryverId);
            List<Car> carList = dryver.getCarList();
            String tempCar = carList.get(0).getBrand();
            request.setAttribute("create_start", tempStartLocation);
            request.setAttribute("create_end", tempEndLocation);
            request.setAttribute("create_date", tempDate);
            request.setAttribute("create_car", tempCar);
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

            int dryverId = dryverFacade.findByAlias(alias).getIdMember();
            Dryver dryver = dryverFacade.find(dryverId);

            List<Car> carList = dryver.getCarList();
            Car car = carList.get(0);
            double distance = 60;

            rideFacade.placeRide(startLocation, endLocation, dryver, car, dateObj, numSeats, price, distance);
        }


        //if changeProfileConfirmed action is called
        if (userPath.equals("/changeProfileConfirmed")) {

            String userName = request.getParameter("alias");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            String firstName = request.getParameter("firstName");
            String adjective = request.getParameter("adjective");
            String lastName = request.getParameter("lastName");

            String carBrand = request.getParameter("carBrand");
            int numSeats = Integer.parseInt(request.getParameter("numSeats"));

            String city = request.getParameter("city");


            int dryverId = dryverFacade.findByAlias(alias).getIdMember();
            Dryver dryver = dryverFacade.find(dryverId);

            dryver.setAlias(userName);
            dryver.setPassword(password);
            dryver.setEmail(email);

            dryver.setFirstName(firstName);
            dryver.setAdjective(adjective);
            dryver.setLastName(lastName);

            dryver.setCity(city);

            dryverFacade.edit(dryver);
           
            Car car = carFacade.find(idMember);
            
            car.setBrand(carBrand);
            car.setNumSeats(numSeats);

            carFacade.edit(car);

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

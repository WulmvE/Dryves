/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utils.EmailPdf;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.NegotiationFacade;
import session.RideFacade;

/**
 *
 * @author hctung
 */
@WebServlet(name = "SearchRideServlet",
        loadOnStartup = 1,
        urlPatterns = {"/searchRide", "/searchRideDetails", "/rideDetails2", "/searchresults"})
public class SearchRideServlet extends HttpServlet {

    @EJB
    private RideFacade rideFacade;
    @EJB
    private NegotiationFacade negotiationFacade;

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
        Ride selectedRide;
        String loggedInUser = request.getUserPrincipal().getName();

        if (userPath.equals("/rideDetails2")) {

            String idRide = request.getQueryString();

            if (idRide != null) {

                // get selected ride
                selectedRide = rideFacade.find(Integer.parseInt(idRide));
                if (!loggedInUser.equalsIgnoreCase(selectedRide.getIdMember().getAlias())) {
                    response.sendRedirect("/DryvesV2/rideDetails?" + idRide);
                }
                List<Negotiation> negotiations = selectedRide.getNegotiationList();
                // place selected category in session scope
                session.setAttribute("selectedRide", selectedRide);
                session.setAttribute("negotiations", negotiations);
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
        HttpSession session = request.getSession();

        Ride selectedRide;

        if (userPath.equals("/rideDetails2")) {

            int confirmDryver = Integer.parseInt(request.getParameter("confirmDryver"));
            int confirmRide = Integer.parseInt(request.getParameter("confirmRide"));

            // get selected ride
            selectedRide = rideFacade.find(confirmRide);
            List<Negotiation> negotiations = selectedRide.getNegotiationList();

            // place selected category in session scope
            request.setAttribute("selectedRide", selectedRide);
            request.setAttribute("negotiations", negotiations);

            if (request.getParameter("confirmDryver") != null) {

                confirmDryver = Integer.parseInt(request.getParameter("confirmDryver"));
            }

            if (request.getParameter("confirmRide") != null) {
                confirmRide = Integer.parseInt(request.getParameter("confirmRide"));
            }

            if (request.getParameter("confirmNegotiation") != null
                    && request.getParameter("confirmNegotiation").equals("1")) {
                Negotiation negotiation = negotiationFacade.findByIdMemberAndIdRide(confirmDryver, confirmRide);
                negotiation.setAcceptedDriver(1);
                negotiationFacade.edit(negotiation);

                // after accept from driver, email pdf to passenger
                EmailPdf emailer = new EmailPdf();
                emailer.setDatum(new Date().toString());
                emailer.setPrijs(selectedRide.getAskingPrice());
                emailer.setRitId(confirmRide);
                emailer.setRecipient(negotiation.getDryver().getEmail());
                emailer.email();
            }
        }

        // if searchRide action is called
        if (userPath.equals("/searchresults")) {
            String van = rideFacade.trimSearchString(request.getParameter("search_start"));
            String naar = rideFacade.trimSearchString(request.getParameter("search_destination"));
            String op = request.getParameter("search_date");

            if (naar.equals("") && op.toString().equals("")) {
                request.setAttribute("rides", rideFacade.searchRideByStart(van));
                int aantalrides = rideFacade.searchRideByStart(van).size();
                request.setAttribute("aantalrides", aantalrides);
            } else if (van.equals("") && op.toString().equals("")) {
                request.setAttribute("rides", rideFacade.searchByEnd(naar));
                int aantalrides = rideFacade.searchByEnd(naar).size();
                request.setAttribute("aantalrides", aantalrides);
            } else if (van.equals("") && naar.equals("")) {
                try {
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(op);

                    request.setAttribute("rides", rideFacade.searchByDate(date));
                    int aantalrides = rideFacade.searchByDate(date).size();
                    request.setAttribute("aantalrides", aantalrides);
                } catch (ParseException ex) {
                    Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (op.toString().equals("")) {
                request.setAttribute("rides", rideFacade.searchRideByStartEnd(van, naar));
                int aantalrides = rideFacade.searchRideByStartEnd(van, naar).size();
                request.setAttribute("aantalrides", aantalrides);
            } else if (naar.equals("")) {
                try {
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(op);

                    request.setAttribute("rides", rideFacade.searchRideByStartDate(van, date));
                    int aantalrides = rideFacade.searchRideByStartDate(van, date).size();
                        request.setAttribute("aantalrides", aantalrides);
                } catch (ParseException ex) {
                    Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (van.equals("")) {
                try {
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(op);

                    request.setAttribute("rides", rideFacade.searchRideByEndDate(naar, date));
                    int aantalrides = rideFacade.searchRideByEndDate(naar, date).size();
                        request.setAttribute("aantalrides", aantalrides);
                } catch (ParseException ex) {
                    Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = formatter.parse(op);

                    request.setAttribute("rides", rideFacade.searchRideByAll(van, naar, date));
                    int aantalrides = rideFacade.searchRideByAll(van, naar, date).size();
                        request.setAttribute("aantalrides", aantalrides);
                } catch (ParseException ex) {
                    Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

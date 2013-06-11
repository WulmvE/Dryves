/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Negotiation;
import entity.Ride;
import java.io.IOException;
import java.util.List;
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

        if (userPath.equals("/rideDetails2")) {

            String idRide = request.getQueryString();

            if (idRide != null) {

                // get selected ride
                selectedRide = rideFacade.find(Integer.parseInt(idRide));
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
            }
        }

        // if searchRide action is called
        if (userPath.equals("/searchresults")) {
            String van = rideFacade.trimSearchString(request.getParameter("search_start"));
            request.setAttribute("rides", rideFacade.searchRideByStart(van));
            int aantalrides = rideFacade.searchRideByStart(van).size();
            request.setAttribute("aantalrides", aantalrides);
        }
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

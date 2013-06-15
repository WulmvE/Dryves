/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Dryver;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.DryverFacade;

/**
 *
 * @author hctung
 */
@WebServlet(name = "SearchFriendServlet", urlPatterns = {"/searchFriend", "/searchFriendResults"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"DryvesUser"}))
public class SearchFriendServlet extends HttpServlet {

    private String userPath;
    @EJB
    private DryverFacade dryverFacade;

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

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        // ALIAS IS value van j_username form field IN HET INLOGSCHERM
        String alias = request.getUserPrincipal().getName();
        session.setAttribute("alias", alias);

        int idMember = dryverFacade.findByAlias(alias).getIdMember();
        session.setAttribute("idMember", idMember);


//        String referer = request.getHeader("Referer");
//        if (referer == null) {
//            referer = "none";
//        }
//
//        String terug = response.getContentType();
//
//        request.setAttribute("en", terug);
//
//        request.setAttribute("mijnreferer", referer);
//        Ride selectedRide;


        if (userPath.equals("/searchFriend")) {
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
//        Ride selectedRide;

//

        if (userPath.equals("/searchFriend")) {
            if (request.getParameter("search_friend_first_name") != "" && request.getParameter("search_friend_last_name") == "" && request.getParameter("search_friend_last_name") == "") {
                request.setAttribute("friends", dryverFacade.findByFirstName(request.getParameter("search_friend_first_name")));
            }
            if (request.getParameter("search_friend_first_name") == "" && request.getParameter("search_friend_last_name") != "" && request.getParameter("search_friend_email") == "") {
                request.setAttribute("friends", dryverFacade.findByLastName(request.getParameter("search_friend_last_name")));
            }
            if (request.getParameter("search_friend_first_name") == "" && request.getParameter("search_friend_last_name") == "" && request.getParameter("search_friend_email") != "") {
                request.setAttribute("friends", dryverFacade.findByEmail(request.getParameter("search_friend_email")));
            }
            if (request.getParameter("search_friend_first_name") != "" && request.getParameter("search_friend_last_name") != "" && request.getParameter("search_friend_email") == "") {
                request.setAttribute("friends", dryverFacade.findByFirstNameLastName(request.getParameter("search_friend_first_name"), request.getParameter("search_friend_last_name")));
            }
            if (request.getParameter("search_friend_first_name") != "" && request.getParameter("search_friend_last_name") == "" && request.getParameter("search_friend_email") != "") {
                request.setAttribute("friends", dryverFacade.findByFirstNameEmail(request.getParameter("search_friend_first_name"), request.getParameter("search_friend_email")));
            }
            if (request.getParameter("search_friend_first_name") == "" && request.getParameter("search_friend_last_name") != "" && request.getParameter("search_friend_email") != "") {
                request.setAttribute("friends", dryverFacade.findByLastNameEmail(request.getParameter("search_friend_last_name"), request.getParameter("search_friend_email")));
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

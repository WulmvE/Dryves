/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Dryver;
import entity.Friend;
import java.io.IOException;
import java.util.List;
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
import session.FriendFacade;

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
    @EJB
    private FriendFacade friendFacade;

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

            String loggedInUser = request.getUserPrincipal().getName();
            int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();

            Dryver dryver = dryverFacade.find(loggedInUserId);

            String firstName = request.getParameter("search_friend_first_name");
            String lastName = request.getParameter("search_friend_last_name");
            String email = request.getParameter("search_friend_email");

            List<Dryver> dryverList = null;
            int status;

            if (firstName != "" && lastName == "" && email == "") {
                dryverList = dryverFacade.findByFirstName(firstName);
                request.setAttribute("dryvers", dryverList);
            }
            if (firstName == "" && lastName != "" && email == "") {
                dryverList = dryverFacade.findByLastName(lastName);
                request.setAttribute("dryvers", dryverList);
            }
            if (firstName == "" && lastName == "" && email != "") {
                request.setAttribute("dryvers", dryverFacade.findByEmail(email));
                dryverList = dryverFacade.findByEmail(email);
                request.setAttribute("dryvers", dryverList);
            }
            if (firstName != "" && lastName != "" && email == "") {
                dryverList = dryverFacade.findByFirstNameLastName(firstName, lastName);
                request.setAttribute("dryvers", dryverList);
            }
            if (firstName != "" && lastName == "" && email != "") {
                dryverList = dryverFacade.findByFirstNameEmail(firstName, email);
                request.setAttribute("dryvers", dryverList);
            }
            if (firstName == "" && lastName != "" && email != "") {
                dryverList = dryverFacade.findByLastNameEmail(lastName, email);
                request.setAttribute("dryvers", dryverList);
            }

            for (Dryver queryDryver : dryverList) {
                request.setAttribute("queryDryver", queryDryver.getIdMember());

                List<Friend> friendList = queryDryver.getFriendList();

                for (int i = 0; i < friendList.size(); i++) {
                    if (friendList.get(i).getIdFriend().getIdMember() == idMember) {
                        if (friendList.get(i).getStatus() == true) {
                            status = 1;
                            request.setAttribute("status", status);
                        }
                        if (friendList.get(i).getStatus() == false) {
                            status = 0;
                            request.setAttribute("status", status);

                        }
                    }
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

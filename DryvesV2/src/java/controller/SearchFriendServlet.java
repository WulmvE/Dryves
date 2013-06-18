/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Dryver;
import entity.Friend;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "SearchFriendServlet", urlPatterns = {"/searchFriend", "/searchFriendResults", "/requestFriend", "/acceptFriend"})
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

        if (userPath.equals("/acceptFriend")){
            int idFriend = Integer.parseInt(request.getParameter("idFriend"));
           Dryver ingelogd =  dryverFacade.findByAlias(alias);
           Dryver friend = dryverFacade.find(idFriend);
        List<Friend> ingelogd_lijst = ingelogd.getFriendList();
        List<Friend> friend_lijst = friend.getFriendList();
        
        for (int i = 0; i < ingelogd_lijst.size(); i++){
            if (ingelogd_lijst.get(i).getIdMember().getIdMember() == ingelogd.getIdMember() && ingelogd_lijst.get(i).getIdFriend().getIdMember() == friend.getIdMember()){
                ingelogd_lijst.get(i).setStatus(true);
            }
        }
        
                for (int i = 0; i < friend_lijst.size(); i++){
            if (friend_lijst.get(i).getIdMember().getIdMember() == friend.getIdMember() && friend_lijst.get(i).getIdFriend().getIdMember() == ingelogd.getIdMember()){
                friend_lijst.get(i).setStatus(true);
            }
        }
        
                ingelogd.setFriendList(ingelogd_lijst);
                friend.setFriendList(friend_lijst);
                
        dryverFacade.edit(ingelogd);
        dryverFacade.edit(friend);
        
        
        }
        
        if (userPath.equals("/requestFriend")){
            
            // haal Dryverid uit request (dit is de vriend)
            int dryverId = Integer.parseInt(request.getParameter("requestDryver"));
            // maak er een Dryver van
            Dryver queryDryver = dryverFacade.find(dryverId);
            // maak nieuwe Friend data aan
            Friend f = new Friend();
            f.setIdMember(queryDryver);
            f.setIdFriend(dryverFacade.findByAlias(alias));
            f.setIdRequester(dryverFacade.findByAlias(alias).getIdMember());
            f.setStatus(false);
            List<Friend> friendList = friendFacade.findByDryver(queryDryver);
            // zet Friend data in database (persistent)
            friendFacade.create(f);
            // voeg Friend toe aan vriendenlijst
            friendList.add(f);
            queryDryver.setFriendList(friendList);
            // zet Dryver in database (persistent)
            dryverFacade.edit(queryDryver);
            
            // doe dit nu ook, maar dan voor de ingelogde gebruiker
            Dryver ingelogd = dryverFacade.findByAlias(alias);
            f.setIdMember(ingelogd);
            f.setIdFriend(queryDryver);
            f.setIdRequester(ingelogd.getIdMember());
            f.setStatus(false);
            // haal vriendenlijst van ingelogde gebruiker op
            friendList = friendFacade.findByDryver(ingelogd);
            friendFacade.create(f);
            friendList.add(f);
            ingelogd.setFriendList(friendList);
            dryverFacade.edit(ingelogd);
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
            
            // een tweede dryverList om de friends uit kan halen (anders ConcurrentModificationException)
            List<Dryver> dryverListCopy = null;
            List<Dryver> alreadyFriends = new ArrayList();
            int status;

            if (firstName != "" && lastName == "" && email == "") {
                dryverList = dryverFacade.findByFirstName(firstName);
                dryverListCopy = dryverFacade.findByFirstName(firstName);
                for (Dryver dryver1 : dryverList) {
                    for (int i = 0; i < dryver1.getFriendList().size(); i++){
                        if (dryver1.getFriendList().get(i).getIdFriend().getIdMember() == idMember){
                            alreadyFriends.add(dryver1);
                            dryverListCopy.remove(dryver1);
                        }
                    }
                }
                request.setAttribute("dryvers", dryverListCopy);
                request.setAttribute("alreadyFriends", alreadyFriends);
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
        
        if (userPath.equals("/requestFriend")){
            Dryver queryDryver = dryverFacade.find(request.getParameter("queryDryver"));
            List<Friend> friendList = friendFacade.findByDryver(queryDryver);
            Friend f = new Friend();
            friendFacade.create(f);
            f.setIdMember(queryDryver);
            f.setIdFriend(dryverFacade.findByAlias(alias));
            f.setStatus(false);
            friendList.add(f);
            friendFacade.edit(f);
            dryverFacade.edit(queryDryver);
        }

        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

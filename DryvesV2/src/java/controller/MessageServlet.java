/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Dryver;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.DryverFacade;
import session.MessageFacade;

/**
 *
 * @author Willem
 */
@WebServlet(name = "MessageServlet",
        loadOnStartup = 1,
        urlPatterns = {"/inbox", "/outbox", "/write"})
public class MessageServlet extends HttpServlet {

    @EJB
    private MessageFacade messageFacade;
    
    @EJB
    private DryverFacade dryverFacade;

    @Override
    public void init() throws ServletException {
    }

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
        System.out.println("GET");
        String userPath = request.getServletPath();
        if (userPath.equals("/inbox")) {
            // TODO: get all messages of the idMember that is logged in for inbox
            Dryver idMember = new Dryver(107);
            getServletContext().setAttribute("messages", messageFacade.searchMessageByIdReciever(idMember));
        } if (userPath.equals("/outbox")) {
            Dryver idMember = new Dryver(107);
            getServletContext().setAttribute("messages", messageFacade.searchMessageByidSender(idMember));
        } if (userPath.equals("/write")) {
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
        Dryver idReciever = new Dryver(107);
        int idMessage = Integer.parseInt(request.getParameter("idMessage"));
        Dryver idSender = new Dryver(Integer.parseInt(request.getParameter("idSender").replaceAll("\\D", "")));
        String dateTime = request.getParameter("dateTime");
        // Select a single message.
        getServletContext().setAttribute("singleMessage", messageFacade.getSingleMessage(idMessage, idSender, dateTime));

        String userPath = request.getServletPath();
        if (userPath.equals("/inbox")) {
        }
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

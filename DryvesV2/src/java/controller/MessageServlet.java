///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import entity.Dryver;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.EJB;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import session.DryverFacade;
//import session.FriendFacade;
//import session.MessageFacade;
//
///**
// *
// * @author Willem
// */
//@WebServlet(name = "MessageServlet",
//        loadOnStartup = 1,
//        urlPatterns = {"/inbox", "/outbox", "/write"})
//public class MessageServlet extends HttpServlet {
//
//    @EJB
//    private MessageFacade messageFacade;
//    
//    @EJB
//    private DryverFacade dryverFacade;
//
//    @EJB
//    private FriendFacade friendFacade;
//    
//    @Override
//    public void init() throws ServletException {
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP
//     * <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        System.out.println("GET");
//        String userPath = request.getServletPath();
//        
//        String loggedInUser = request.getUserPrincipal().getName();
//        int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();
//        Dryver idMember = dryverFacade.find(loggedInUserId);
//        
//        if (userPath.equals("/inbox")) {
//            // TODO: get all messages of the idMember that is logged in for inbox
//            getServletContext().setAttribute("messages", messageFacade.searchMessageByIdReciever(idMember));
//        } if (userPath.equals("/outbox")) {            
//            getServletContext().setAttribute("messages", messageFacade.searchMessageByidSender(idMember));
//        } if (userPath.equals("/write")) {
//            Dryver idReciever = dryverFacade.find(loggedInUserId);
//
//            getServletContext().setAttribute("friends", friendFacade.findByIdMember(idReciever));
//
//            int idMemberSender = Integer.parseInt(request.getParameter("idMemberSender"));
//            int idMemberReciever = Integer.parseInt(request.getParameter("idMemberReciever"));
//            String text = request.getParameter("msg");
//            String dateTime = request.getParameter("date");
//            
//            DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
//            Date dateObj = new Date();
//            try {
//                dateObj = df.parse(dateTime);
//            } catch (ParseException ex) {
//                Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            int idMessage = messageFacade.createMessage(idMemberSender, idMemberReciever, text, dateTime);
//        }
//        // use RequestDispatcher to forward request internally
//        String url = "/WEB-INF/view" + userPath + ".jsp";
//
//        try {
//            request.getRequestDispatcher(url).forward(request, response);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * Handles the HTTP
//     * <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String userPath = request.getServletPath();
//
//        String loggedInUser = request.getUserPrincipal().getName();
//        int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();
//        Dryver idReciever = dryverFacade.find(loggedInUserId);
//        
//        int messageId = Integer.parseInt(request.getParameter("idMessage"));
//        Dryver idSender = new Dryver(Integer.parseInt(request.getParameter("idSender").replaceAll("\\D", "")));
//        String dateTime = request.getParameter("dateTime");
//        
//        if (userPath.equals("/inbox")) {
//            // TODO: get all messages of the idMember that is logged in for inbox.
//            getServletContext().setAttribute("messages", messageFacade.searchMessageByIdReciever(idReciever));
//            getServletContext().setAttribute("singleMessage", messageFacade.getSingleMessage(messageId, idSender, dateTime));
//        }
//            // TODO: get all messages sent from the logged in idMember.
//        if (userPath.equals("/outbox")) {
//            getServletContext().setAttribute("messages", messageFacade.searchMessageByidSender(idReciever));
//            getServletContext().setAttribute("singleMessage", messageFacade.getSingleMessage(messageId, idSender, dateTime));
//        }
//            // TODO: get Friendslist from the logged in user.
//        if (userPath.equals("/write")) {
//            getServletContext().setAttribute("friends", friendFacade.findByIdMember(idReciever));
//
//            int idMemberSender = Integer.parseInt(request.getParameter("idMemberSender"));
//            int idMemberReciever = Integer.parseInt(request.getParameter("idMemberReciever"));
//            String text = request.getParameter("msg");
//            
//            DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
//            Date dateObj = new Date();
//            try {
//                dateObj = df.parse(dateTime);
//            } catch (ParseException ex) {
//                Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            int idMessage = messageFacade.createMessage(idMemberSender, idMemberReciever, text, dateTime);
//        }
//    }
//}

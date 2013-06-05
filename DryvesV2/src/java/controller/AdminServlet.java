///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
import Utils.Stat;
//import java.io.IOException;
//import java.io.Writer;
//import javax.servlet.ServletException;
import java.util.List;
import javax.ejb.EJB;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
import session.AdminFacade;
// */
//@WebServlet(name = "Admin", urlPatterns = {"/Admin"})
//public class AdminServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP
//     * <code>GET</code> and
//     * <code>POST</code> methods.
    @EJB
    private AdminFacade adminFacade;

//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        
////            String s = new String("A Stringiesggsf");
//            String[] strings = {"fsgfsg","hahshash","sfgsfg"};
//         //   String json = new Gson().toJson(strings);
//        
//         json = "[{\"place\":\"Amsterdam\",\"value\":\"750\"},{\"place\":\"Rotterdam\",\"value\":\"520\"},"
//                 + "{\"place\":\"Den Haag\",\"value\":\"450\"},{\"place\":\"Utrecht\",\"value\":\"320\"},{\"place\":\"Eindhoven\",\"value\":\"261\"}]";

        if (request.getParameterMap().containsKey("type")) {

            String type = request.getParameter("type");
            String json;

            if (!(type == null)) {
                json = new Gson().toJson(adminFacade.getStats(type));
            }
            else {
                json = "";
            }

//        }
//
//        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//        /**
//         * Handles the HTTP
//         * <code>GET</code> method.
//         *
//         * @param request servlet request
//         * @param response servlet response
//         * @throws ServletException if a servlet-specific error occurs
//         * @throws IOException if an I/O error occurs
//         */
//        @Override
//        protected void doGet

        }
        else {
            // use RequestDispatcher to forward request internally
            String url = "/WEB-INF/admin/adminpanel.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
//         * Handles the HTTP
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
        processRequest(request, response);
    }
//         *
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
        processRequest(request, response);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
    }// </editor-fold>
}

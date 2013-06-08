package controller;

import Utils.Stat;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AdminFacade;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "Admin", urlPatterns = {"/Admin"})
public class AdminServlet extends HttpServlet {

    @EJB
    private AdminFacade adminFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        if (request.getParameterMap().containsKey("type")) {

            String type = request.getParameter("type");
            String json;

            if (!(type == null)) {
                json = new Gson().toJson(adminFacade.getStats(type));
            }
            else {
                json = "";
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Writer writer = null;
            try {
                writer = response.getWriter();
                writer.write(json);
            }
            finally {
                try {
                    writer.close();
                }
                catch (IOException ex) {
                }
            }

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
        return "Short description";
    }// </editor-fold>
}
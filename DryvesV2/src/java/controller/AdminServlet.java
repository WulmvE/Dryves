package controller;

import Utils.RdwTool;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Writer;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AdminFacade;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "Admin", urlPatterns = {"/adminpanel",})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"Admin"}))
public class AdminServlet extends HttpServlet {

    @EJB
    private AdminFacade adminFacade;

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

        //check if a type of admin statistic has been requested.
        //normally these type of requests stem from AJAX calls coming from the clientside.
        if (request.getParameterMap().containsKey("type")) {

            String type = request.getParameter("type");
            String json;

            if (!(type == null)) {
                //the 'type' from the request determines which statiscal query is called in the getStats method from adminFacade.
                //The method returns a List of 'Object' arrays. Each object containing a 'key' and a 'value' object.
                //Gson converts the returned List to JSON. 
                json = new Gson().toJson(adminFacade.getStats(type));
            }            
            //if type is empty return an empty json string.
            else {
                json = "";
            }

            //return json to client
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
        //if no 'type' parameter is present return the full jsp to the client
        else {
            // use RequestDispatcher to forward request internally
            String url = "/WEB-INF/admin/adminpanel.jsp";
            request.getRequestDispatcher(url).forward(request, response);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "AdminServlet: manages several administrator tools for the Dryves application";
    }
}

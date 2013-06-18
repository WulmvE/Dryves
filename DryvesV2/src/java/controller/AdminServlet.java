package controller;

import com.google.gson.Gson;
import entity.Admin;
import entity.Dryver;
import entity.Percentage;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.AdminFacade;
import session.DryverFacade;
import session.PercentageFacade;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin", "/statistics", "/settings", "/membercontrol", "/adminDo"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"Admin"}))
public class AdminServlet extends HttpServlet {

    @EJB
    private AdminFacade adminFacade;
    @EJB
    private DryverFacade dryverFacade;
    @EJB
    private PercentageFacade percentageFacade;

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

        //check if a type of admin statistic has been requested.
        //normally these type of requests stem from AJAX calls coming from the clientside.
        if (request.getParameterMap().containsKey("type")) {

            String type = request.getParameter("type");
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String by = request.getParameter("by");
            String json;

            if (!(type == null)) {
                //the 'type' from the request determines which statiscal query is called in the getStats method from adminFacade.
                //The method returns a List of 'Object' arrays. Each object containing a 'key' and a 'value' object.
                //Gson converts the returned List to JSON. 
                json = new Gson().toJson(adminFacade.getStats(type, from, to, by));
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
        //determine if client should be forwarded to the statistics page
        else if (userPath.equals("/admin") || userPath.equals("/statistics")) {
            // use RequestDispatcher to forward request internally
            String url = "/WEB-INF/admin/admin_statistics.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
        else if (userPath.equals("/settings")) {
            request.setAttribute("percentages", percentageFacade.findAll());
            request.setAttribute("current", percentageFacade.findByDateToIsNull().getSize());
            
            String url = "/WEB-INF/admin/admin_settings.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
        else if (userPath.equals("/membercontrol")) {

            List<Admin> admins = adminFacade.findAll();
            request.setAttribute("admins", admins);

            List<Dryver> blocked_members = dryverFacade.findByIsBlocked();
            request.setAttribute("blocked_members", blocked_members);

            // use RequestDispatcher to forward request internally
            String url = "/WEB-INF/admin/admin_membercontrol.jsp";
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
        System.out.println("blockMem is exe");
        String userPath = request.getServletPath();

        if (userPath.equals("/adminDo")) {

            String task = request.getParameter("task");

            if (task.equals("addAdmin")) {
                try {
                    Dryver dryver = dryverFacade.findByAlias(request.getParameter("alias"));
                    Admin admin = new Admin(dryver.getIdMember());
                    adminFacade.manageAdmin(admin);
                }
                catch (Exception ex) {
                    System.out.println("entity not found");
                }
                finally {
                    response.sendRedirect("/DryvesV2/membercontrol");
                }
            }
            else if (task.equals("removeAdmin")) {
                try {

                    Dryver dryver = dryverFacade.findByAlias(request.getParameter("alias"));
                    Admin admin = adminFacade.find(dryver.getIdMember());
                    adminFacade.remove(admin);
                }
                catch (Exception ex) {
                    System.out.println("entity not found");
                }
                finally {
                    response.sendRedirect("/DryvesV2/membercontrol");
                }
            }
            else if (task.equals("blockMem")) {
                try {

                    Dryver dryver = dryverFacade.findByAlias(request.getParameter("alias"));
                    dryver.setBlocked(true);
                    dryverFacade.edit(dryver);
                }
                catch (Exception ex) {
                    System.out.println("entity not found");
                }
                finally {
                    response.sendRedirect("/DryvesV2/membercontrol");
                }
            }
            else if (task.equals("unblockMem")) {
                try {

                    Dryver dryver = dryverFacade.findByAlias(request.getParameter("alias"));
                    dryver.setBlocked(false);
                    dryverFacade.edit(dryver);
                }
                catch (Exception ex) {
                    System.out.println("entity not found");
                }
                finally {
                    response.sendRedirect("/DryvesV2/membercontrol");
                }
            }
            else if (task.equals("setPerc")) {
                try {
                    Date date = new Date();
                    Double formPerc = Double.parseDouble(request.getParameter("perc"));
                    if(formPerc>100.0){
                        formPerc=100.0;
                    }
                    Percentage perc  = new Percentage(formPerc, date);
                    Percentage old = percentageFacade.findByDateToIsNull();
                    old.setDateTo(date);
                    percentageFacade.edit(old);
                    percentageFacade.create(perc);
                }
                catch (Exception ex) {
                    System.out.println("entity not found");
                }
                finally {
                    response.sendRedirect("/DryvesV2/settings");
                }
            }
        };
        


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

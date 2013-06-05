/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Car;
import entity.Dryver;
import entity.Ride;
import java.io.IOException;
import java.io.*;
import java.lang.Exception;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import session.CarFacade;
import session.DryverFacade;
import session.RideFacade;

/**
 *
 * @author Maartje
 */
@WebServlet(name = "UserServlet",
        loadOnStartup = 1,
        urlPatterns = { "/myDryves",
                        "/changeprofile",
                        "/logout"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"DryvesUser"}))
public class UserServlet extends HttpServlet {
    
    private String userPath;
    @EJB
    private DryverFacade dryverFacade;
    @EJB
    private RideFacade rideFacade;
    @EJB
    private CarFacade carFacade;

     @Override
    public void init() throws ServletException {

        // store category list in servlet context
        getServletContext().setAttribute("dryvers", dryverFacade.findAll());
        getServletContext().setAttribute("cars", carFacade.findAll());

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        userPath = request.getServletPath();

        // if myDryves is requested
        if (userPath.equals("/myDryves")) {
             
        }

        // if changeProfile is requested
        if (userPath.equals("/changeprofile")) {
//            orderList = customerOrderFacade.findAll();
//            request.setAttribute("orderList", orderList);
        }


     

        // use RequestDispatcher to forward request internally
        userPath = "index.jsp";
        try {
            request.getRequestDispatcher(userPath).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        System.out.println("login");
        System.out.println("login");
        System.out.println("login");
        System.out.println("login");
        System.out.println("login");
        System.out.println("login");
        System.out.println("login");
        String userPath = request.getServletPath();

//                  ALIAS IS j_username IN HET INLOGSCHERM
          String mijnAlias = "vincent test";
          String alias = "jandeman";
         // List<Dryver> members = dryverFacade.("jandeman");

          getServletContext().setAttribute("cars", carFacade.findAll());
//          List<Car> cars = carFacade.searchCars();
          
       //   getServletContext().setAttribute("members", members);
//          getServletContext().setAttribute("cars", cars);
          
          
          String testHttp;
          testHttp = request.getLocalAddr();
          
          String referer = request.getHeader("Referer");
          if (referer == null) 
          {referer = "none";}
          
          String terug = response.getContentType();
          
          request.setAttribute("en", terug);
          request.setAttribute("mijn_alias", mijnAlias);
          request.setAttribute("httpistest", testHttp);
          request.setAttribute("mijnreferer", referer);
//          request.setAttribute("mijn_first_name", mijnFirstName);
//          request.setAttribute("mijn_first_name", mijnAdjective);
//          request.setAttribute("mijn_first_name", mijnLastName);
//          request.setAttribute("mijn_first_name", mijnAvgRating);
//          request.setAttribute("mijn_first_name", mijnCity);
//          request.setAttribute("mijn_first_name", mijnEmail);         
     if (userPath.equals("/logout")) {

            HttpSession session = request.getSession();
            
            session.invalidate();   // terminate session
            try {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }    }
       
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
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        System.out.println("WEEEEE");
        String userPath = request.getServletPath();

        // if searchRide action is called
        if (userPath.equals("/myDryves")) {
            // TODO: Implement search ride action
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

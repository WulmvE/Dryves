/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Message;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Willem van Ess
 */

@WebServlet(name = "MessageServlet",
        loadOnStartup = 1,
        urlPatterns = {"/messageInbox",
                       "/messageConv"})

public class MessageServlet extends HttpServlet {

    /* HTTP GET request */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String userPath = request.getServletPath();

        // if addToCart action is called
        if (userPath.equals("/messageInbox")) {
            // TODO: Implement inbox action
        }
        
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }}
}
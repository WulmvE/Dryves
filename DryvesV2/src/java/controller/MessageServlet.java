package controller;

import entity.Dryver;
import entity.Message;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.DryverFacade;
import session.FriendFacade;
import session.MessageFacade;

/**
 *
 * @author Willem
 */
@WebServlet(name = "MessageServlet",
        loadOnStartup = 1,
        urlPatterns = {"/inbox", "/outbox", "/write", "/send"})
public class MessageServlet extends HttpServlet {

    @EJB
    private MessageFacade messageFacade;
    @EJB
    private DryverFacade dryverFacade;
    @EJB
    private FriendFacade friendFacade;

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
        String userPath = request.getServletPath();

        String loggedInUser = request.getUserPrincipal().getName();
        int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();
        Dryver idMember = dryverFacade.find(loggedInUserId);

        if (userPath.equals("/inbox")) {
            getServletContext().setAttribute("user", idMember);
            getServletContext().setAttribute("messages", messageFacade.searchMessageByIdReciever(idMember));
        }
        if (userPath.equals("/outbox")) {
            getServletContext().setAttribute("messages", messageFacade.searchMessageByidSender(idMember));
        }
        if (userPath.equals("/write")) {
            getServletContext().setAttribute("friends", friendFacade.findByDryver(idMember));
        }
        
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

        String loggedInUser = request.getUserPrincipal().getName();
        int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();
        Dryver idMember = dryverFacade.find(loggedInUserId);

        if (userPath.equals("/inbox")) {
            int messageId = Integer.parseInt(request.getParameter("idMessage"));
            String dateTime = request.getParameter("dateTime");
            Dryver idSender = dryverFacade.find(Integer.parseInt(request.getParameter("idSender")));

            getServletContext().setAttribute("messages", messageFacade.searchMessageByIdReciever(idMember));
            getServletContext().setAttribute("singleMessage", messageFacade.getSingleMessage(messageId, idSender, dateTime));
        }
        if (userPath.equals("/outbox")) {
            int messageId = Integer.parseInt(request.getParameter("idMessage"));
            String dateTime = request.getParameter("dateTime");

            getServletContext().setAttribute("messages", messageFacade.searchMessageByidSender(idMember));
            getServletContext().setAttribute("singleMessage", messageFacade.getSingleMessage(messageId, idMember, dateTime));
        }
        if (userPath.equals("/write")) {
            Dryver idReciever = dryverFacade.find(Integer.parseInt(request.getParameter("idReciever")));

            getServletContext().setAttribute("idReciever", idReciever);
            getServletContext().setAttribute("friends", friendFacade.findByDryver(idMember));
        }
        if (userPath.equals("/send")) {
            String dateTime = request.getParameter("dateTime");
            Dryver idReciever = dryverFacade.find(Integer.parseInt(request.getParameter("idMemberReciever")));
            String text = request.getParameter("msg");

            getServletContext().setAttribute("friends", friendFacade.findByDryver(idMember));

            DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
            Date dateObj = new Date();
            try {
                dateObj = df.parse(dateTime);
            } catch (ParseException ex) {
                Logger.getLogger(SearchRideServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Message idMessage = messageFacade.createMessage(idMember, idReciever, text, dateTime);
            getServletContext().setAttribute("message", idMessage);
        }
        
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

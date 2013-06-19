/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Dryver;
import session.DryverFacade;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Willem van Ess
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet", "/geslaagd"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"DryvesUser", "Admin"}))
public class UploadServlet extends HttpServlet {

    @EJB
    private DryverFacade dryverFacade;

    private static final String TMP_DIR_PATH = "C:\\ava";
    private File tmpDir;
    private static final String DESTINATION_DIR_PATH = "C:\\ava";

    private File destinationDir;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        tmpDir = new File(TMP_DIR_PATH);
        if (!tmpDir.isDirectory()) {
            throw new ServletException(TMP_DIR_PATH + " is not a directory");
        }
        //String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
        destinationDir = new File(DESTINATION_DIR_PATH);
        if (!destinationDir.isDirectory()) {
            throw new ServletException(DESTINATION_DIR_PATH
                    + " is not a directory");
        }
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String userPath = request.getServletPath();
        String loggedInUser = request.getUserPrincipal().getName();
        int loggedInUserId = dryverFacade.findByAlias(loggedInUser).getIdMember();

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out
                .println("<h1>Servlet File Upload Example using Commons File Upload</h1>");
        out.println();

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        /*
         * Set the size threshold, above which content will be stored on disk.
         */
        fileItemFactory.setSizeThreshold(1 * 1024 * 1024); // 1 MB
		/*
         * Set the temporary directory to store the uploaded files of size above
         * threshold.
         */
        fileItemFactory.setRepository(tmpDir);

        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            /*
             * Parse the request
             */
            List items = uploadHandler.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                /*
                 * Handle Form Fields.
                 */
                if (item.isFormField()) {
                    out.println("File Name = " + item.getFieldName()
                            + ", Value = " + item.getString());
                } else {
                    // Handle Uploaded files.
                    String url = "/WEB-INF/view/" + userPath + ".jsp";
                    try {
                        response.sendRedirect("/DryvesV2/changeProfile");
                        //request.getRequestDispatcher(url).forward(request, response);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    /*
                     * Write file to the ultimate location.
                     */
                    //Gewoon gebruiken wat ze uploaden:
                    //File file = new File(destinationDir, item.getName());
                    //helemaal onze naamgeving.
                    File file = new File(destinationDir, ("avatar" + loggedInUserId + ".jpg"));
                    item.write(file);
                }
                out.close();
            }
        } catch (FileUploadException ex) {
            log("Error encountered while parsing the request", ex);
        } catch (Exception ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
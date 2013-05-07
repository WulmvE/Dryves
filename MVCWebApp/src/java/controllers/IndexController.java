package controllers;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class IndexController extends HttpServlet {

	/* HTTP GET Request */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String address = "/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
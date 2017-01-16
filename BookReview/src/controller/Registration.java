package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.MyConnection;
import constants.BookReviewConstants;
import dao.RegistrationDAO;
import mode.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
	Boolean isInserted = false;
	PrintWriter out;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		
	}
	public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		out = response.getWriter();
		try {
			isInserted = new RegistrationDAO(new User
													(request.getParameter("userName"),
													request.getParameter("password"),
													request.getParameter("name"), 
													/*BookReviewConstants.USER_TYPE)*/2)).register();
		} catch (ClassNotFoundException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(isInserted);
		
		if(isInserted){
			response.sendRedirect("index.html");
		}else{
			out.println("Registration Unsuccessfull");
			
		}
			
		
	}

}

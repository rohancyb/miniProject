package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BookDao;
import model.Books;

import java.util.*;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import connection.MyConnection;

import java.sql.*;
/**
 * Servlet implementation class UpdateBook
 */
@WebServlet("/UpdateBook")
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(UpdateBook.class);
		BasicConfigurator.configure();
		ArrayList<Books> history=new ArrayList<>();
		BookDao bookDao=new BookDao();
		Connection connection=null;
		MyConnection con=new MyConnection();
		try {
			connection=con.connect();
		} catch (ClassNotFoundException e) {
			logger.error("Exception:",e);
		}
		
		HttpSession session = request.getSession(true); 
		
		
		String genre=(String)request.getParameter("genre");
		history=bookDao.getBooksByGenre(genre,connection);


		session.setAttribute("history", history);
		session.setAttribute("selectedGenre", genre);
        response.sendRedirect("AdminUpdate.jsp");	
        
		doGet(request, response);
	}

}

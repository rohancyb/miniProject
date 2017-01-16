package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import connection.MyConnection;
import dao.BookDao;
import model.Books;
/**
 * Servlet implementation class EditBook
 */
@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
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
		Logger logger=Logger.getLogger(EditBook.class);
		BasicConfigurator.configure();
		if(request.getParameter("btn-submit")!=null) {
			String title = request.getParameter("title");
			String author =  request.getParameter("author");
			String description = request.getParameter("description");
			
			HttpSession session = request.getSession(true);
			Books processBook = (Books)session.getAttribute("processBook");
			processBook.setTitle(title);
			processBook.setAuthor(author);
			processBook.setDescription(description);
			MyConnection con=new MyConnection();
			Connection connection = null;
			try {
				connection=con.connect();
			} catch (ClassNotFoundException e) {
				logger.error("Exception:",e);
			}
			if((new BookDao()).updateBook(processBook, connection))
			{
				logger.info("Book UPDATED!!!!!!");
			}
			else
			{
				logger.info("Could not update book");
			}
		}
		response.sendRedirect("AdminUpdate.jsp");
		doGet(request, response);
	}

}

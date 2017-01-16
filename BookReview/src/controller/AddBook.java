package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.MyConnection;
import dao.BookDao;
import model.Books;
import model.User;

/**
 * Servlet implementation class AddBook
 */
//@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	MyConnection con;
	Connection connection;
	Books books;
	User user;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String username=(String) session.getAttribute("username");
		System.out.println(username);
		String book=request.getParameter("book");
		String description=request.getParameter("description");
		String genre=request.getParameter("genre");
		
		String author=request.getParameter("author");
	
		con=new MyConnection();
		try {
			connection=con.connect();
		} catch (ClassNotFoundException e) {
			
			System.out.print(e);
			
		}
		books=new Books();
		books.setTitle(book);
		books.setDescription(description);
		books.setAuthor(author);
		//books.setGenre(genre);
		BookDao bookDao=new BookDao();
		bookDao.getBook(genre,username,books, connection);
		response.sendRedirect("./admin.jsp");
		doGet(request, response);
	}

}

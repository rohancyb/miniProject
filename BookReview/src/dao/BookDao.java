package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import constants.BookReviewConstants;

import javax.servlet.http.HttpSession;

import connection.MyConnection;
import model.Books;

public class BookDao {
	private Books book;
	private Connection connection;
	private PreparedStatement statement;
	private PreparedStatement statement1;
	private PreparedStatement statement3;
	private ResultSet resultSet;
	private ResultSet resultSet2;
	
	
	public BookDao(){
		
	}
	
	public Books getBook(String genre,String username,Books book,Connection connection){
		this.book=book;
		this.connection=connection;
		try {
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date today = Calendar.getInstance().getTime();
			String reportDate = df.format(today);
			
			statement3=connection.prepareStatement("select userId from def_users where userName=?");
			statement3.setString(1, username);
			resultSet2=statement3.executeQuery();
			resultSet2.next();
				
			statement1=connection.prepareStatement("select masterid from def_master where name=?");
			statement1.setString(1,genre);
			resultSet=statement1.executeQuery();
			resultSet.next();
			book.setGenre(resultSet.getInt(1));
			System.out.println(book.getGenre());
			
			statement=connection.prepareStatement("insert into def_books(title,author,masterid,description,status,createdBy,creationDate,modifiedBy,modificationDate) values(?,?,?,?,?,?,?,?,?)");
			
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getGenre());
			statement.setString(4, book.getDescription());
			statement.setString(5, BookReviewConstants.STATUS_ACTIVE);
			statement.setInt(6, resultSet2.getInt(1));
			statement.setString(7, reportDate);
			statement.setInt(8, resultSet2.getInt(1));
			statement.setString(9, reportDate);
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return book;
	}

}

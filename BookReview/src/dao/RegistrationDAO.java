package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import connection.MyConnection;
import constants.BookReviewConstants;
import mode.User;

public class RegistrationDAO {
		User user;
		//Connection connection;
		PreparedStatement preparedStatement;

		public RegistrationDAO(User user) throws ClassNotFoundException{
			
			this.user = user;
			
		}

		public boolean register() throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/miniproject","root","root");
			
			preparedStatement = connection.prepareStatement("Insert into def_users ("+BookReviewConstants.MASTER_ID+","
			+BookReviewConstants.USER_NAME+","
			+BookReviewConstants.PASSWORD+","
			+BookReviewConstants.NAME+",status,"
			+BookReviewConstants.CREATION_DATE+") values(?,?,?,?,?,?) ");
			
			preparedStatement.setInt(1, BookReviewConstants.USER_TYPE);
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setString(5, BookReviewConstants.STATUS_ACTIVE );
			
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
			
			preparedStatement.setString(6, df.format(dateobj));
			
			int row = preparedStatement.executeUpdate();
			System.out.println(row);
			return row>0;
			
		}
}

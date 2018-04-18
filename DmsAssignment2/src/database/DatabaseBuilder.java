package database;

import java.sql.*;

public class DatabaseBuilder 
{
	public static void main(String[] args)
	{
		//createDB();
		//createTables();
		//populateTables();
		testTables();
	}
	
	private static void createDB()
	{
		String driverURL = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:1528/";
		String sqlStatement;
		int statementResult;
		
		try
		{
			Class.forName(driverURL);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
			return;
		}
		
		try
		{
			Connection connection = DriverManager.getConnection(dbURL, "root", "darkshines");
		
			Statement statement = connection.createStatement();
		
			sqlStatement = "CREATE DATABASE movieDataBase";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Database created");
			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	private static void createTables()
	{
		String driverURL = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:1528/movieDataBase";
		String sqlStatement;
		int statementResult;
		
		try
		{
			Class.forName(driverURL);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
			return;
		}
		
		try
		{
			Connection connection = DriverManager.getConnection(dbURL, "root", "darkshines");
		
			Statement statement = connection.createStatement();
		
			sqlStatement = "CREATE TABLE Studio (" 
				+ "studioName VARCHAR(100) NOT NULL, " 
				+ "studioEst INT NOT NULL, " 
				+ "studioCountry VARCHAR(100) NOT NULL, " 
				+ "PRIMARY KEY (studioName)" 
				+ ")";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Studio table created.");
			}
		
			sqlStatement = "CREATE TABLE Director (" 
				+ "directorID INT NOT NULL, " 
				+ "firstName VARCHAR(50) NOT NULL, " 
				+ "lastName VARCHAR(50) NOT NULL, " 
				+ "birthday DATE NOT NULL, " 
				+ "PRIMARY KEY (directorID)" 
				+ ")";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Director table created");
			}
		
			sqlStatement = "CREATE TABLE Movie (" 
				+ "title VARCHAR(100) NOT NULL, " 
				+ "releaseYear INT NOT NULL, " 
				+ "mpaaRating VARCHAR(5) NOT NULL, " 
				+ "genre VARCHAR(20) NOT NULL, " 
				+ "studioName VARCHAR(100), " 
				+ "directorID INT, " 
				+ "CONSTRAINT PK_Movie PRIMARY KEY (title,releaseYear), " 
				+ "FOREIGN KEY (studioName) REFERENCES Studio(studioName), " 
				+ "FOREIGN KEY (directorID) REFERENCES Director(directorID)" 
				+ ")";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Movie table created");
			}
		
			sqlStatement = "CREATE TABLE Review(" 
				+ "reviewID INT NOT NULL, " 
				+ "reviewerFirstName VARCHAR(50) NOT NULL, " 
				+ "reviewerLastName VARCHAR(50) NOT NULL, " 
				+ "score INT NOT NULL, " 
				+ "title VARCHAR(100), " 
				+ "releaseYear INT, " 
				+ "PRIMARY KEY (reviewID), " 
				+ "CONSTRAINT FK_Movie FOREIGN KEY (title,releaseYear) REFERENCES Movie(title,releaseYear)" 
				+ ")";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Review table created");
			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return;
		}
	}
	
	private static void populateTables()
	{
		String driverURL = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:1528/movieDataBase";
		String sqlStatement;
		int statementResult;
		
		try
		{
			Class.forName(driverURL);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
			return;
		}
		
		try
		{
			Connection connection = DriverManager.getConnection(dbURL, "root", "darkshines");
		
			Statement statement = connection.createStatement();
			
			sqlStatement = "INSERT INTO Studio VALUES" 
				+ "('Walt Disney Pictures', 1923, 'USA')," 
				+ "('Universal Pictures', 1912, 'USA')," 
				+ "('WingNut Films', 2003, 'New Zealand'),"
				+ "('New Line Cinema', 1967, 'USA'),"
				+ "('20th Century Fox', 1935, 'USA')";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Studio table populated.");
			}
				
			sqlStatement = "INSERT INTO Director VALUES" 
				+ "(1, 'David', 'Fincher', '1962-08-28')," 
				+ "(2, 'Steven', 'Spielberg', '1946-12-18')," 
				+ "(3, 'Peter', 'Jackson', '1961-10-31')";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Director table populated.");
			}
			
			sqlStatement = "INSERT INTO Movie VALUES" 
				+ "('The Lord of the Rings: The Fellowship of the Ring', 2001, 'PG-13', 'Fantasy', 'WingNut Films', 3)," 
				+ "('Jurassic Park', 1993, 'PG-13', 'Adventure', 'Universal Pictures', 2)," 
				+ "('Se7en', 1995, 'R', 'Crime', 'New Line Cinema', 1)";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Movie table populated.");
			}
			
			sqlStatement = "INSERT INTO Review VALUES" 
				+ "(1, 'Craig', 'Fraser', 9, 'The Lord of the Rings: The Fellowship of the Ring', 2001)," 
				+ "(2, 'Craig', 'Fraser', 7, 'Jurassic Park', 1993)," 
				+ "(3, 'Craig', 'Fraser', 8, 'Se7en', 1995)";
			statementResult = statement.executeUpdate(sqlStatement);
			if(statementResult == 0)
			{
				System.out.println("Review table populated.");
			}
		}
		catch(SQLException e)
		{
			System.err.println("Nope: " + e.getMessage());
		}
	}
	
	public static void testTables()
	{
		String driverURL = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:1528/movieDataBase";
		String sqlStatement;
		int statementResult;
		ResultSet resultSet;
		
		try
		{
			Class.forName(driverURL);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
			return;
		}
		
		try
		{
			Connection connection = DriverManager.getConnection(dbURL, "root", "darkshines");
		
			Statement statement = connection.createStatement();
			
			sqlStatement = "SELECT * FROM Movie";
			resultSet = statement.executeQuery(sqlStatement);
			while(resultSet.next())
			{
				System.out.println(resultSet.getString(1) + "\t" 
					+ resultSet.getString(2) + "\t" 
					+ resultSet.getString(3) + "\t" 
					+ resultSet.getString(4) + "\t" 
					+ resultSet.getString(5) + "\t" 
					+ resultSet.getString(6));
			}
			System.out.println("\n");
		
			sqlStatement = "SELECT * FROM Director";
			resultSet = statement.executeQuery(sqlStatement);
			while(resultSet.next())
			{
				System.out.println(resultSet.getString(1) + "\t" 
					+ resultSet.getString(2) + "\t" 
					+ resultSet.getString(3) + "\t" 
					+ resultSet.getString(4));
			}
			System.out.println("\n");
			
			sqlStatement = "SELECT * FROM Studio";
			resultSet = statement.executeQuery(sqlStatement);
			while(resultSet.next())
			{
				System.out.println(resultSet.getString(1) + "\t" 
					+ resultSet.getString(2) + "\t" 
					+ resultSet.getString(3));
			}
			System.out.println("\n");
			
			sqlStatement = "SELECT * FROM Review";
			resultSet = statement.executeQuery(sqlStatement);
			while(resultSet.next())
			{
				System.out.println(resultSet.getString(1) + "\t" 
					+ resultSet.getString(2) + "\t" 
					+ resultSet.getString(3) + "\t" 
					+ resultSet.getString(4) + "\t" 
					+ resultSet.getString(5) + "\t" 
					+ resultSet.getString(6));
			}
			System.out.println("\n");
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}
}

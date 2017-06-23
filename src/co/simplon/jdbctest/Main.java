package co.simplon.jdbctest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class Main {

	public static void main(String[] args) {
		String prenoms[] = {"Albert", "Jonathan", "Cedric", "Michel", "Georges-Henry"};
		for (String prenom : prenoms) {
			saveInDB(prenom);
		}
//				saveInDB("Tommy");
		readDb();
//		renameCol("prenom");

	}
	public static void saveInDB(String prenom){
		String url = "jdbc:mysql://localhost/simplon";
		String login = "root";
		String pass = "heavenly";
		 Connection connect = null;
		Statement statement = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, pass);
			statement = connect.createStatement();
			String sql = "INSERT INTO `JDBC` (`prenom`) "
					+ "VALUES ('"+prenom+"')";
			statement.executeUpdate(sql);
		}catch (SQLException |ClassNotFoundException e){
//			e.printStackTrace();
			System.err.println("erreur :"+e);
		}finally{
			try{
				connect.close();
				statement.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void readDb(){
		String url = "jdbc:mysql://localhost/simplon";
		String login = "root";
		String pass = "heavenly";
		 Connection connect = null;
		Statement statement = null;
		ResultSet result = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, pass);
			statement = connect.createStatement();
			String sql = "SELECT * FROM JDBC";
			result = statement.executeQuery(sql);
			while(result.next()){
				System.err.println(result.getString("prenom"));
			}
		}catch (SQLException |ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				connect.close();
				statement.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	public static void renameCol(String col){
		String url = "jdbc:mysql://localhost/simplon";
		String login = "root";
		String pass = "heavenly";
		 Connection connect = null;
		Statement statement = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, pass);
			statement = connect.createStatement();
			String sql = "ALTER TABLE `JDBC` DROP COLUMN '"+col+"'";
			statement.executeUpdate(sql);
		}catch (SQLException |ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				connect.close();
				statement.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

}

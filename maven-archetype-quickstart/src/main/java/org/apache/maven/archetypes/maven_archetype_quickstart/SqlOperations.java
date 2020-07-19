package org.apache.maven.archetypes.maven_archetype_quickstart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@SuppressWarnings("resource")
public class SqlOperations {


	public static void insert(Connection conn,PreparedStatement stmt,Scanner sc) throws FileNotFoundException, SQLException
	{
		sc=new Scanner(System.in);
		String sql = "INSERT INTO person (Name,Email,Age,Insurance,Photo) values (?, ?, ?, ?, ?)";
		try {

			conn =
					DriverManager.getConnection("jdbc:mysql://localhost/database1","root","root");
			System.out.println("Connection created");
			// Do something with the Connection
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			System.out.println("Enter your Name: ");
			String name=sc.nextLine();
			System.out.println("Enter your Mail-Id: ");
			String mail=sc.nextLine();
			System.out.println("Enter your Age: ");
			int age=sc.nextInt();
			System.out.println("Does the user have insurance? 1:yes 0:No");
			int insurance=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the full file path to your photo");
			String filepath=sc.nextLine();

			stmt.setString(1, name);
			stmt.setString(2, mail);
			stmt.setInt(3, age);
			stmt.setInt(4, insurance);

			InputStream inputStream = new FileInputStream(new File(filepath));

			stmt.setBlob(5, inputStream);
			int row = stmt.executeUpdate();
			if (row > 0) {
				System.out.println("A contact was inserted with photo image.");
			}


			conn.commit();

		}
		catch (SQLException ex ) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch(SQLException excep) {
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			conn.setAutoCommit(true);
		}

	}


	public static void display(Connection conn,Statement stmt) throws SQLException {
		String sql = "SELECT * FROM person";
		try {

			conn =
					DriverManager.getConnection("jdbc:mysql://localhost/database1","root","root");
			System.out.println("Connection created");
			// Do something with the Connection
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			int count = 0;

			while (result.next()){
				int id=result.getInt(1);
				String name = result.getString(2);
				String mail = result.getString(3);
				int age = result.getInt(4);
				String time = result.getString(5);
				int insurance=result.getInt(6);

				String output = "User #%d: %d - %s - %s -%d - %s -%d ";
				System.out.println(String.format(output, ++count, id, name, mail, age ,time,insurance));
			}

			conn.commit();

		}
		catch (SQLException ex ) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch(SQLException excep) {
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			conn.setAutoCommit(true);
		}

	}
	public static void update(Connection conn,PreparedStatement stmt,Scanner sc) throws SQLException {
		String sql="UPDATE person SET Email=?,Age=? WHERE Name=?";
		sc=new Scanner(System.in);
		try {

			conn =
					DriverManager.getConnection("jdbc:mysql://localhost/database1","root","root");
			System.out.println("Connection created");
			// Do something with the Connection
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			System.out.println("Enter user Name you want to update: ");
			String name=sc.nextLine();
			System.out.println("Enter your new Mail-Id: ");
			String mail=sc.nextLine();
			System.out.println("Enter your new Age: ");
			int age=sc.nextInt();
			sc.nextLine();


			stmt.setString(1, mail);
			stmt.setInt(2, age);
			stmt.setString(3, name);

			int row = stmt.executeUpdate();
			if (row > 0) {
				System.out.println("A contact was updated with new mail and age.");
			}


			conn.commit();

		}
		catch (SQLException ex ) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch(SQLException excep) {
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			conn.setAutoCommit(true);
		}

	}
	public static void delete(Connection conn,PreparedStatement stmt,Scanner sc) throws SQLException
	{
		String sql="DELETE FROM person WHERE Name=?";
		sc=new Scanner(System.in);
		try {

			conn =
					DriverManager.getConnection("jdbc:mysql://localhost/database1","root","root");
			System.out.println("Connection created");
			// Do something with the Connection
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			System.out.println("Enter user Name you want to delete the record for: ");
			String name=sc.nextLine();

			stmt.setString(1, name);

			int row = stmt.executeUpdate();
			if (row > 0) {
				System.out.println("A contact was deleted.");
			}


			conn.commit();

		}
		catch (SQLException ex ) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch(SQLException excep) {
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			conn.setAutoCommit(true);
		}

	}

}


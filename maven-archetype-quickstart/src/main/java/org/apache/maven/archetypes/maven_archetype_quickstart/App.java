package org.apache.maven.archetypes.maven_archetype_quickstart;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;  

class App{  
	public static void main(String args[]) throws FileNotFoundException, ClassNotFoundException, SQLException {

		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection conn = null;
		PreparedStatement stmt =null;
		Statement smt=null;
		new SqlOperations();
		do {

			String input;
			System.out.println("Press 1 to insert data \nPress 2 to display datas \nPress 3 to update mail and age \nPress 4 to delete a record \nAny other key to exit");	
			input = sc.nextLine();

			if(input.equals("1"))
				SqlOperations.insert(conn, stmt,sc);
			else if(input.equals("2"))
				SqlOperations.display(conn, smt);
			else if(input.equals("3"))
				SqlOperations.update(conn, stmt,sc);
			else if(input.equals("4"))
				SqlOperations.delete(conn, stmt,sc);
			else 
				break;
		}while(true);

		sc.close();


	}
}



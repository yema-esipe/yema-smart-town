package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import common.Crud;

public class Test {

	public static void main(String[] args) {
		//JDBCConnectionPool jdbc = new JDBCConnectionPool(); not necessary
		
		Crud crud = new Crud();
		
		//select test 
		System.out.println(crud.executeQuery("Select prenom from users"));
		
		//update test 
		crud.executeUpdate("Update users set nom = 'KOONE' Where prenom = 'Yaya'");		
		
		//insert test 
		crud.executeUpdate("Insert into users(nom, prenom, mdp, age) values ('De Lima', 'Mathieu', 'mama', '20')");
		
		//delete test
		crud.executeUpdate("Delete from users where nom = 'De Lima'");
		
		
	}
}

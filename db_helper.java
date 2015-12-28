package pkg_main;
import java.sql.*;
import javax.swing.*;
public class db_helper {
	
	
		Connection conn=null;
		public static Connection dbConnector(){
			try{
			Class.forName("org.sqlite.JDBC");
				Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\db\\JAM_MASTER.sqlite");
			//	JOptionPane.showMessageDialog(null,"Connection Successful ! ");
				return conn;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
				return null;
			}
			
		}
	
}

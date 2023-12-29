package util;

import java.sql.Connection;

public class DBUtil {

	public static Connection connection=null;
	
	public static Connection getConnection() throws Exception {
		try {
	       String dbConnectionString = DBPropertyUtil.getConnectionString("src/util/DB.properties");
	       connection = DBConnUtil.getDBConn(dbConnectionString);
	       return connection;
		}catch(Exception e){
			System.out.println("Exception Occured : "+e.getMessage());
		}
		return null;
	}
	
}

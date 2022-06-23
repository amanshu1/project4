package in.com.rays.ORSprojectt4.utility;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {
	private static JDBCDataSource datasource;
	private ComboPooledDataSource cpds = null;
	ResourceBundle rb = ResourceBundle.getBundle("in.com.rays.ORSprojectt4.bundle.system");
	String url = rb.getString("url");
	String driver = rb.getString("driver");
	String username = rb.getString("username");
	String password = rb.getString("password");
	//String database = rb.getString("database");
	String service = rb.getString("service");
	Integer acquireIncrement = Integer.parseInt(rb.getString("acquireIncrement"));
	Integer initialPoolSize = Integer.parseInt(rb.getString("initialPoolSize"));
	Integer maxPoolSize = Integer.parseInt(rb.getString("maxPoolSize"));
	Integer minPoolSize = Integer.parseInt(rb.getString("minPoolSize"));
	Integer timeout = Integer.parseInt(rb.getString("timeout"));
	
	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();
			System.out.println(url);
			cpds.setJdbcUrl(url);
			cpds.setDriverClass(driver);
			cpds.setUser(username);
			cpds.setPassword(password);
			cpds.setInitialPoolSize(initialPoolSize);
			cpds.setMaxPoolSize(maxPoolSize);
			cpds.setMinPoolSize(minPoolSize);
			cpds.setMaxIdleTime(timeout);
			cpds.setAcquireIncrement(acquireIncrement);
		
		
			
		} catch (PropertyVetoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	public static JDBCDataSource getInstance() {
		if (datasource == null) {

			datasource = new JDBCDataSource();
	}
		return datasource;

	}
	public static Connection getConnection() {
	try {
		return getInstance().cpds.getConnection();
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
		
	}
	public static void closeConnection(Connection conn ) {
		try {
			
			if(conn!=null)conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
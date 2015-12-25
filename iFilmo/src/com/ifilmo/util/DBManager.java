package com.ifilmo.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * @author wang
 *懒汉单态模式
 */
public class DBManager {
	private static Connection con = null;

	/**
	 * 构造方法私有，外部不能创建对象
	 */
	private DBManager() {
		
	}
	//多了个同步
	public synchronized static Connection getConnection() 
		throws ClassNotFoundException, SQLException{
		
		if (con == null) {
			Class.forName(Config.DRIVER);
			con = DriverManager.getConnection(Config.URL, Config.UNAME, Config.PWD);
		} 
		return con;
	}
}

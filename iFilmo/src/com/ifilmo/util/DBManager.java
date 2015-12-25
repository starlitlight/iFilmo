package com.ifilmo.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * @author wang
 *������̬ģʽ
 */
public class DBManager {
	private static Connection con = null;

	/**
	 * ���췽��˽�У��ⲿ���ܴ�������
	 */
	private DBManager() {
		
	}
	//���˸�ͬ��
	public synchronized static Connection getConnection() 
		throws ClassNotFoundException, SQLException{
		
		if (con == null) {
			Class.forName(Config.DRIVER);
			con = DriverManager.getConnection(Config.URL, Config.UNAME, Config.PWD);
		} 
		return con;
	}
}

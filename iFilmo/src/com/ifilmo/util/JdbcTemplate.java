package com.ifilmo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

//代替了原来DBManager的功能
public class JdbcTemplate {
	
	/**
	 * 用来执行insert update delete语句
	 * @param sql 是带占位符的sql语句
	 * @param values sql占位符具体的值
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int update(String sql, Object... values) throws ClassNotFoundException, SQLException {
		
		PreparedStatement psta = null;
		int row = 0;
		try {
			Connection con = DBManager.getConnection();
			psta = con.prepareStatement(sql);
			//给占位符赋值
			for (int i = 0; i < values.length; i++) {
				psta.setObject(i + 1, values[i]);
			}
			System.out.println(psta);
			row = psta.executeUpdate();
		} finally {
			if (psta != null) {
				psta.close();
				psta = null;
			}
		}
		return row;
	}
	/**
	 * 用来执行select语句
	 * @param sql 
	 * @param mapping 转换的实体对象
	 * @param values
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List query(String sql, EntityMapping mapping, Object... values) throws ClassNotFoundException, SQLException {
		PreparedStatement psta = null;
		ResultSet rs = null;
		List<Object> list = new Vector<Object>();
		try {
			Connection con = DBManager.getConnection();
			psta = con.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				psta.setObject(i + 1, values[i]);
			}
			rs = psta.executeQuery();
			while (rs.next()) {
				Object entity = mapping.mapping(rs); 
				list.add(entity);
			}
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (psta != null) {
				psta.close();
				psta = null;
			}
		}
		return list;
	}
}

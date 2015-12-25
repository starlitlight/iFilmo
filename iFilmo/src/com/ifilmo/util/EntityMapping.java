package com.ifilmo.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapping {
	/**
	 * 把rs当前行数据转换为一个实体对象
	 * @param rs
	 * @return 具体实体类
	 * @throws SQLException
	 */
	public Object mapping(ResultSet rs) throws SQLException ;
}

package cn.domain.cm.dao;

import java.sql.Connection;
import cn.domain.cm.config.Config;

public abstract class BaseDao {
	
	public Connection conn = null;

	public Connection openDB() throws Exception {
		try {
			if (conn == null) {
				Class.forName("org.postgresql.Driver");
				conn = java.sql.DriverManager.getConnection("jdbc:postgresql://"
						+ Config.dbServerIp + ":"+Config.dbPort + "/" + Config.dbName
						+ "?useUnicode=true&characterEncoding="+Config.dbEncoding,
						Config.dbUser, Config.dbPwd);
			} else if (conn.isClosed() == true) {
				Class.forName("org.postgresql.Driver");
				conn = java.sql.DriverManager.getConnection("jdbc:postgresql://"
						+ Config.dbServerIp + ":"+Config.dbPort + "/" + Config.dbName
						+ "?useUnicode=true&characterEncoding="+Config.dbEncoding,
						Config.dbUser, Config.dbPwd);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void closeDB() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}

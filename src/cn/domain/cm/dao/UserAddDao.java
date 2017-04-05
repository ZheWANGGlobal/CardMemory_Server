package cn.domain.cm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cn.domain.cm.bean.UserBean;


public class UserAddDao extends BaseDao{
	public PreparedStatement prepStmt = null;
	public ResultSet rs = null;
	public UserBean getUserByName(String src){
		UserBean userBean = null;
		try {
			conn = super.openDB();
			if(conn!=null){
				// 插入注册信息的SQL语句(使用?占位符)  
                String sql = "insert into user(id,pw)"  
                        + "values(?,?)";
                // 创建prepareStatement对象
				prepStmt = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值
				prepStmt.setString(1,src);
				// 执行更新操作
				prepStmt.executeUpdate();
				if(rs.next()){
					userBean = new UserBean(rs.getString(1),rs.getString(2),true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(prepStmt!=null)
					prepStmt.close();
				if(conn!=null)
					conn.close();
				super.closeDB();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userBean;
	}
}
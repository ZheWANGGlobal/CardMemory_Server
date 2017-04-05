package cn.domain.cm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cn.domain.cm.bean.UserBean;


public class UserDao extends BaseDao{
	public PreparedStatement prepStmt = null;
	public PreparedStatement prepStmt1 = null;
	public ResultSet rs = null;
	public UserBean addUser(String user_id,String user_pw){
		UserBean userBean = null;
		try{
			conn = super.openDB();
			if(conn!=null){
				
				// 看用户id是否已经存在
				String sql1 = "select * from cmusers where id = ?";
				
				prepStmt1 = conn.prepareStatement(sql1);
				// 对SQL语句中的参数动态赋值 
				prepStmt1.setString(1,user_id);
				 // 执行查询操作 
				rs = prepStmt1.executeQuery();
				// 将查询到的结果用javabean对象返回
				if(rs.next()){ 
					userBean = new UserBean(rs.getString(1),rs.getString(2),true);
					if(userBean.getId().equals(user_id)){  // 查询到有用户名和注册名一致的
						// 查到用户id重复，将javabean的single属性设置为false
						userBean.setSingle(false);
						// 直接return，不再执行下面的语句
						return userBean;
					}
				}
				

				// sql语句
				String sql = "insert into cmusers(id,pw)"  
                      + "values(?,?)";
				
				// 创建PreparedStatement对象  
				prepStmt = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,user_pw);
				
				// 执行更新操作  
				prepStmt.executeUpdate();
				// 将插入到数据表中的结果用javabean对象返回 
				userBean = new UserBean(user_id,user_pw,true);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				// 关闭相关连接
				if(rs!=null)
					rs.close();
				if(prepStmt!=null)
					prepStmt.close();
				if(prepStmt1!=null)
					prepStmt1.close();
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
	public UserBean getUserByName(String src){
		UserBean userBean = null;
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "select * from cmusers where id = ?";
				
				prepStmt = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,src);
				 // 执行查询操作 
				rs = prepStmt.executeQuery();
				// 将查询到的结果用javabean对象返回
				if(rs.next()){ 
					userBean = new UserBean(rs.getString(1),rs.getString(2),true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				// 关闭相关连接
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
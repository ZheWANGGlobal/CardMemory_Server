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
				// ����ע����Ϣ��SQL���(ʹ��?ռλ��)  
                String sql = "insert into user(id,pw)"  
                        + "values(?,?)";
                // ����prepareStatement����
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ
				prepStmt.setString(1,src);
				// ִ�и��²���
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
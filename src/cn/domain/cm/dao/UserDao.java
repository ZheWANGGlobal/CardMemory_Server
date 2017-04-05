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
				
				// ���û�id�Ƿ��Ѿ�����
				String sql1 = "select * from cmusers where id = ?";
				
				prepStmt1 = conn.prepareStatement(sql1);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt1.setString(1,user_id);
				 // ִ�в�ѯ���� 
				rs = prepStmt1.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				if(rs.next()){ 
					userBean = new UserBean(rs.getString(1),rs.getString(2),true);
					if(userBean.getId().equals(user_id)){  // ��ѯ�����û�����ע����һ�µ�
						// �鵽�û�id�ظ�����javabean��single��������Ϊfalse
						userBean.setSingle(false);
						// ֱ��return������ִ����������
						return userBean;
					}
				}
				

				// sql���
				String sql = "insert into cmusers(id,pw)"  
                      + "values(?,?)";
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,user_pw);
				
				// ִ�и��²���  
				prepStmt.executeUpdate();
				// �����뵽���ݱ��еĽ����javabean���󷵻� 
				userBean = new UserBean(user_id,user_pw,true);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				// �ر��������
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
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,src);
				 // ִ�в�ѯ���� 
				rs = prepStmt.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				if(rs.next()){ 
					userBean = new UserBean(rs.getString(1),rs.getString(2),true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				// �ر��������
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
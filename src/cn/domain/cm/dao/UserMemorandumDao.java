package cn.domain.cm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.domain.cm.bean.UserArticleBean;
import cn.domain.cm.bean.UserBean;
import cn.domain.cm.bean.UserMemorandumBean;
public class UserMemorandumDao extends BaseDao {
	public PreparedStatement prepStmt = null;
	public PreparedStatement prepStmt1 = null;
	public ResultSet rs = null;
	
	/**
	 * // ����һƪ����¼
	 * @param user_id
	 * @param _date
	 * @param _text
	 * @return
	 */
	public UserMemorandumBean addMemorandum(String user_id,String _date,String _text){  
		UserMemorandumBean userMemorandumBean = null;
		
		try{
			conn = super.openDB();
			if(conn!=null){
				
				// ���û�id�ڵĸ����������Ƿ��Ѿ�����
				String sql1 = "select * from memorandum where id = ? and date = ?";
				
				prepStmt1 = conn.prepareStatement(sql1);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt1.setString(1,user_id);
				prepStmt1.setString(2,_date);
				 // ִ�в�ѯ���� 
				rs = prepStmt1.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				if(rs.next()){ 
					userMemorandumBean = new UserMemorandumBean(rs.getString(1),rs.getString(2),rs.getString(3),true);
					if(userMemorandumBean.getId().equals(user_id)&&userMemorandumBean.getDate().equals(_date)){  // ��ѯ�����û���һ��������һ��
						// �鵽ͬһ�û�һʱ�������ظ�����javabean��single��������Ϊfalse
						userMemorandumBean.setSingle(false);
						// ֱ��return������ִ����������
						return userMemorandumBean;
					}
				}
				

				// sql���
				String sql = "insert into memorandum(id,date,text)"  
                      + "values(?,?,?)";
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);
				prepStmt.setString(4,_text);
				
				// ִ�и��²���  
				prepStmt.executeUpdate();
				// �����뵽���ݱ��еĽ����javabean���󷵻� 
				userMemorandumBean = new UserMemorandumBean(user_id,_date,_text,true);
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
		return userMemorandumBean;
	}
	
	/**
	 *  ɾ��һƪ����¼
	 * @param user_id
	 * @param _date
	 * @return
	 * ע�⣺������صĶ���userMemorandumBean��useridΪ�գ�˵��ɾ�������²�����
	 */
	public UserMemorandumBean deleteMemorandum(String user_id,String _date){  
		UserMemorandumBean userMemorandumBean = null;
		
		try{
			conn = super.openDB();
			if(conn!=null){		
				
				// ��Ҫɾ���������Ƿ��Ѿ�����
				String sql1 = "select * from memorandum where id = ? and date = ?";
				
				prepStmt1 = conn.prepareStatement(sql1);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt1.setString(1,user_id);
				prepStmt1.setString(2,_date);
				 // ִ�в�ѯ���� 
				rs = prepStmt1.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				if(rs.next()){ 
					if(!((rs.getString(1)==user_id)&&(rs.getString(2)==_date))){// û�в�ѯ����ƪ����
						// ������javabean��id����Ϊ��
						userMemorandumBean = new UserMemorandumBean("","","",true);
						// �������ֱ�ӷ���
						return userMemorandumBean;
					}
				}						
				
				// sql���
				String sql = "delete from memorandum where id = ? and date = ?";
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);			
				// ִ�и��²���  
				prepStmt.executeUpdate();
				// ��ɾ�����ݱ��еĽ����javabean���󷵻� 
				userMemorandumBean = new UserMemorandumBean(user_id,_date,"",true);
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
		return userMemorandumBean;
	}
	
	/**
	 * // ��ѯĳ�û����б���¼
	 * @param user_id
	 * @param _date
	 * @return
	 */
	public ArrayList<UserMemorandumBean> getMemorandumByName(String user_id){
		 List<UserMemorandumBean> list=new ArrayList<UserMemorandumBean>();
		 UserMemorandumBean userMemorandumBean = new UserMemorandumBean();
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "select * from cmdiary where id = ?";
				
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				 // ִ�в�ѯ���� 
				rs = prepStmt.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				while(rs.next()){  // �������ص����ж���
					userMemorandumBean = new UserMemorandumBean(rs.getString(1),rs.getString(2),rs.getString(3),true);
					list.add(userMemorandumBean);
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
		return (ArrayList<UserMemorandumBean>) list;
	}
	
	/**
	 * // ��ѯĳ�û����б���¼������
	 * @param user_id
	 * @param _date
	 * @return
	 */
	public ArrayList<String> getMemorandumDateByName(String user_id){
		 List<String> list=new ArrayList<String>();
		 UserMemorandumBean userMemorandumBean = new UserMemorandumBean();
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "select * from cmdiary where id = ?";
				
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				 // ִ�в�ѯ���� 
				rs = prepStmt.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				if(rs.next()){  // �������ص����ж���
					userMemorandumBean = new UserMemorandumBean(rs.getString(1),rs.getString(2),rs.getString(3),true);
					list.add(userMemorandumBean.getDate());
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
		return (ArrayList<String>) list;
	}
	
	/**
	 * // ��ѯһƪ����¼
	 * @param user_id
	 * @param _date
	 * @return
	 */
	public UserMemorandumBean getMemorandumByNameAndDate(String user_id,String _date){
		UserMemorandumBean userMemorandumBean = null;
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "select * from memorandum where id = ? and date = ?";
				
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);
				 // ִ�в�ѯ���� 
				rs = prepStmt.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				if(rs.next()){ 
					userMemorandumBean = new UserMemorandumBean(rs.getString(1),rs.getString(2),rs.getString(3),true);
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
		return userMemorandumBean;
	}
	
	/**
	 * // �޸�һƪ����¼
	 * @param user_id
	 * @param _date
	 * @param _tag
	 * @param _text
	 * @return
	 */
	public UserMemorandumBean alterMemorandum(String user_id,String _date,String _text){  
		UserMemorandumBean userMemorandumBean = null;
		
		// ��ɾ��ԭ����
		try{
			conn = super.openDB();
			if(conn!=null){		
				// sql���
				String sql = "delete from memorandum where id = ? and date = ?";
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);			
				// ִ�и��²���  
				prepStmt.executeUpdate();
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
		
		// ������н���д��
		try{
			conn = super.openDB();
			if(conn!=null){	
				// sql���
				String sql = "insert into memorandum(id,date,text)"  
                      + "values(?,?,?)";
				// д��ʱ�����
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,df.format(new Date()));
				prepStmt.setString(4,_text);
				
				// ִ�и��²���  
				prepStmt.executeUpdate();
				// �����뵽���ݱ��еĽ����javabean���󷵻� 
				// // ע���������д���д���ʱ����и���
				//String sdate;   
				//sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(df.format(new Date())); ;// new Date()Ϊ��ȡ��ǰϵͳʱ��
				userMemorandumBean = new UserMemorandumBean(user_id,df.format(new Date()),_text,true);
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
		return userMemorandumBean;
	}
	
}


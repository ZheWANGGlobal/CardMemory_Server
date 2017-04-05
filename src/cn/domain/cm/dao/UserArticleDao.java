package cn.domain.cm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.domain.cm.bean.UserArticleBean;
import cn.domain.cm.bean.UserBean;

public class UserArticleDao extends BaseDao {
	public PreparedStatement prepStmt = null;
	public PreparedStatement prepStmt1 = null;
	public ResultSet rs = null;
	
	/**
	 * // ����һƪ����
	 * @param user_id
	 * @param _date
	 * @param _tag
	 * @param _text
	 * @return
	 */
	public UserArticleBean addAritcle(String user_id,String _date,String _tag,String _text){  
		UserArticleBean userArticleBean = null;
		
		try{
			conn = super.openDB();
			if(conn!=null){
				
				// ���û�id�ڵĸ����������Ƿ��Ѿ�����
				String sql1 = "select * from cmdiary where id = ? and date = ?";
				
				prepStmt1 = conn.prepareStatement(sql1);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt1.setString(1,user_id);
				prepStmt1.setString(2,_date);
				 // ִ�в�ѯ���� 
				rs = prepStmt1.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				if(rs.next()){ 
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
					if(userArticleBean.getId().equals(user_id)&&userArticleBean.getDate().equals(_date)){  // ��ѯ�����û���һ��������һ��
						// �鵽ͬһ�û�һʱ�������ظ�����javabean��single��������Ϊfalse
						userArticleBean.setSingle(false);
						// ֱ��return������ִ����������
						return userArticleBean;
					}
				}
				

				// sql���
				String sql = "insert into cmdiary(id,date,tag,text)"  
                      + "values(?,?,?,?)";
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);
				prepStmt.setString(3,_tag);
				prepStmt.setString(4,_text);
				
				// ִ�и��²���  
				prepStmt.executeUpdate();
				// �����뵽���ݱ��еĽ����javabean���󷵻� 
				userArticleBean = new UserArticleBean(user_id,_date,_tag,_text,true);
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
		return userArticleBean;
	}
	
	/**
	 *  ɾ��һƪ����
	 * @param user_id
	 * @param _date
	 * @return
	 * ע�⣺������صĶ���userArticleBean��useridΪ�գ�˵��ɾ�������²�����
	 */
	public UserArticleBean deleteAritcle(String user_id,String _date){  
		UserArticleBean userArticleBean = null;
		
		try{
			conn = super.openDB();
			if(conn!=null){		
				
				// ��Ҫɾ���������Ƿ��Ѿ�����
				String sql1 = "select * from cmdiary where id = ? and date = ?";
				
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
						userArticleBean = new UserArticleBean("","","","",true);
						// �������ֱ�ӷ���
						return userArticleBean;
					}
				}						
				
				// sql���
				String sql = "delete from cmdiary where id = ? and date = ?";
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);			
				// ִ�и��²���  
				prepStmt.executeUpdate();
				// ��ɾ�����ݱ��еĽ����javabean���󷵻� 
				userArticleBean = new UserArticleBean(user_id,_date,"","",true);
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
		return userArticleBean;
	}
	
	
	/**
	 * // ��ѯһƪ����
	 * @param user_id
	 * @param _date
	 * @return
	 */
	public UserArticleBean getArticleByNameAndDate(String user_id,String _date){
		UserArticleBean userArticleBean = null;
		try {
			conn = super.openDB();
			if(conn!=null){
				String sql = "select * from cmdiary where id = ? and date = ?";
				
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);
				 // ִ�в�ѯ���� 
				rs = prepStmt.executeQuery();
				// ����ѯ���Ľ����javabean���󷵻�
				while(rs.next()){ 
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
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
		return userArticleBean;
	}
	
	/**
	 * // ��ѯĳ�û���������
	 * @param user_id
	 * @param _date
	 * @return
	 */
	public ArrayList<UserArticleBean> getArticlesByName(String user_id){
		 List<UserArticleBean> list=new ArrayList<UserArticleBean>();
		 UserArticleBean userArticleBean = new UserArticleBean();
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
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
					list.add(userArticleBean);
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
		return (ArrayList<UserArticleBean>) list;
	}
	
	
	/**
	 * // ��ѯĳ�û��������µ�����
	 * @param user_id
	 * @param _date
	 * @return
	 */
	public ArrayList<String> getArticlesDateByName(String user_id){
		 List<String> list=new ArrayList<String>();
		 UserArticleBean userArticleBean = new UserArticleBean();
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
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
					list.add(userArticleBean.getDate());
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
	 * // �޸�һƪ����
	 * @param user_id
	 * @param _date
	 * @param _tag
	 * @param _text
	 * @return
	 */
	public UserArticleBean alterAritcle(String user_id,String _date,String _tag,String _text){  
		UserArticleBean userArticleBean = null;
		
		// ��ɾ��ԭ����
		try{
			conn = super.openDB();
			if(conn!=null){		
				// sql���
				String sql = "delete from cmdiary where id = ? and date = ?";
				
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
				String sql = "insert into cmdiary(id,date,tag,text)"  
                      + "values(?,?,?,?)";
				// д��ʱ�����
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
				
				// ����PreparedStatement����  
				prepStmt = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,df.format(new Date()));
				prepStmt.setString(3,_tag);
				prepStmt.setString(4,_text);
				
				// ִ�и��²���  
				prepStmt.executeUpdate();
				// �����뵽���ݱ��еĽ����javabean���󷵻� 
				// // ע���������д���д���ʱ����и���
				//String sdate;   
				//sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(df.format(new Date())); ;// new Date()Ϊ��ȡ��ǰϵͳʱ��
				userArticleBean = new UserArticleBean(user_id,df.format(new Date()),_tag,_text,true);
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
		return userArticleBean;
	}
	
}

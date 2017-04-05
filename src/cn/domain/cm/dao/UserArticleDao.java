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
	 * // 增加一篇文章
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
				
				// 看用户id内的该日期文章是否已经存在
				String sql1 = "select * from cmdiary where id = ? and date = ?";
				
				prepStmt1 = conn.prepareStatement(sql1);
				// 对SQL语句中的参数动态赋值 
				prepStmt1.setString(1,user_id);
				prepStmt1.setString(2,_date);
				 // 执行查询操作 
				rs = prepStmt1.executeQuery();
				// 将查询到的结果用javabean对象返回
				if(rs.next()){ 
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
					if(userArticleBean.getId().equals(user_id)&&userArticleBean.getDate().equals(_date)){  // 查询到有用户名一致且日期一致
						// 查到同一用户一时刻文章重复，将javabean的single属性设置为false
						userArticleBean.setSingle(false);
						// 直接return，不再执行下面的语句
						return userArticleBean;
					}
				}
				

				// sql语句
				String sql = "insert into cmdiary(id,date,tag,text)"  
                      + "values(?,?,?,?)";
				
				// 创建PreparedStatement对象  
				prepStmt = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);
				prepStmt.setString(3,_tag);
				prepStmt.setString(4,_text);
				
				// 执行更新操作  
				prepStmt.executeUpdate();
				// 将插入到数据表中的结果用javabean对象返回 
				userArticleBean = new UserArticleBean(user_id,_date,_tag,_text,true);
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
		return userArticleBean;
	}
	
	/**
	 *  删除一篇文章
	 * @param user_id
	 * @param _date
	 * @return
	 * 注意：如果返回的对象userArticleBean中userid为空，说明删除的文章不存在
	 */
	public UserArticleBean deleteAritcle(String user_id,String _date){  
		UserArticleBean userArticleBean = null;
		
		try{
			conn = super.openDB();
			if(conn!=null){		
				
				// 看要删除的文章是否已经存在
				String sql1 = "select * from cmdiary where id = ? and date = ?";
				
				prepStmt1 = conn.prepareStatement(sql1);
				// 对SQL语句中的参数动态赋值 
				prepStmt1.setString(1,user_id);
				prepStmt1.setString(2,_date);
				 // 执行查询操作 
				rs = prepStmt1.executeQuery();
				// 将查询到的结果用javabean对象返回
				if(rs.next()){ 
					if(!((rs.getString(1)==user_id)&&(rs.getString(2)==_date))){// 没有查询到该篇文章
						// 将返回javabean的id设置为空
						userArticleBean = new UserArticleBean("","","","",true);
						// 在这里就直接返回
						return userArticleBean;
					}
				}						
				
				// sql语句
				String sql = "delete from cmdiary where id = ? and date = ?";
				
				// 创建PreparedStatement对象  
				prepStmt = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);			
				// 执行更新操作  
				prepStmt.executeUpdate();
				// 将删除数据表中的结果用javabean对象返回 
				userArticleBean = new UserArticleBean(user_id,_date,"","",true);
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
		return userArticleBean;
	}
	
	
	/**
	 * // 查询一篇文章
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
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);
				 // 执行查询操作 
				rs = prepStmt.executeQuery();
				// 将查询到的结果用javabean对象返回
				while(rs.next()){ 
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
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
		return userArticleBean;
	}
	
	/**
	 * // 查询某用户所有文章
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
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				 // 执行查询操作 
				rs = prepStmt.executeQuery();
				// 将查询到的结果用javabean对象返回
				while(rs.next()){  // 遍历返回的所有对象
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
					list.add(userArticleBean);
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
		return (ArrayList<UserArticleBean>) list;
	}
	
	
	/**
	 * // 查询某用户所有文章的日期
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
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				 // 执行查询操作 
				rs = prepStmt.executeQuery();
				// 将查询到的结果用javabean对象返回
				if(rs.next()){  // 遍历返回的所有对象
					userArticleBean = new UserArticleBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),true);
					list.add(userArticleBean.getDate());
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
		return (ArrayList<String>) list;
	}
	
	/**
	 * // 修改一篇文章
	 * @param user_id
	 * @param _date
	 * @param _tag
	 * @param _text
	 * @return
	 */
	public UserArticleBean alterAritcle(String user_id,String _date,String _tag,String _text){  
		UserArticleBean userArticleBean = null;
		
		// 先删除原表项
		try{
			conn = super.openDB();
			if(conn!=null){		
				// sql语句
				String sql = "delete from cmdiary where id = ? and date = ?";
				
				// 创建PreparedStatement对象  
				prepStmt = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,_date);			
				// 执行更新操作  
				prepStmt.executeUpdate();
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
		
		// 再向表中进行写入
		try{
			conn = super.openDB();
			if(conn!=null){	
				// sql语句
				String sql = "insert into cmdiary(id,date,tag,text)"  
                      + "values(?,?,?,?)";
				// 写入时间更新
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				
				// 创建PreparedStatement对象  
				prepStmt = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值 
				prepStmt.setString(1,user_id);
				prepStmt.setString(2,df.format(new Date()));
				prepStmt.setString(3,_tag);
				prepStmt.setString(4,_text);
				
				// 执行更新操作  
				prepStmt.executeUpdate();
				// 将插入到数据表中的结果用javabean对象返回 
				// // 注意这里完成写入后将写入的时间进行更新
				//String sdate;   
				//sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(df.format(new Date())); ;// new Date()为获取当前系统时间
				userArticleBean = new UserArticleBean(user_id,df.format(new Date()),_tag,_text,true);
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
		return userArticleBean;
	}
	
}

package cn.domain.cm.servlet;
import cn.domain.cm.config.Config;
import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.json.JSONArray;  
import org.json.JSONObject;  
import cn.domain.cm.bean.UserBean;
import cn.domain.cm.dao.UserDao;  
import java.sql.PreparedStatement; 
  
public class LoginServlet extends HttpServlet {  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
  
    /** 
     * The doGet method of the servlet. <br> 
     *  
     * This method is called when a form has its tag value method equals to get. 
     *  
     * @param request 
     *            the request send by the client to the server 
     * @param response 
     *            the response send by the server to the client 
     * @throws ServletException 
     *             if an error occurred 
     * @throws IOException 
     *             if an error occurred 
     */  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doPost(request, response);  
    }  
  
    /** 
     * The doPost method of the servlet. <br> 
     *  
     * This method is called when a form has its tag value method equals to 
     * post. 
     *  
     * @param request 
     *            the request send by the client to the server 
     * @param response 
     *            the response send by the server to the client 
     * @throws ServletException 
     *             if an error occurred 
     * @throws IOException 
     *             if an error occurred 
     */  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	// 设置request与response的编码  
        request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/json;charset=UTF-8");  
        String reqMessage, respMessage;  
        // 返回json类型对象
        //JSONArray respObject = null;
        JSONObject respObject = null;
        
        // 获取表单中属性值 
        // 注意将getparameter获取的参数转换成整数类型
        String  comman = request.getParameter("cmd");
        int  command = Integer.parseInt(comman);
        String userID = request.getParameter("userid");
        String passWord = request.getParameter("password");
        
        if(command == 2){  // 如果是登录
		    try {  
		        reqMessage = command+" "+ userID +" "+passWord;  
		        System.out.println("请求报文:" + reqMessage);  
		        //reqObject = new JSONArray(reqMessage);  
		        UserDao userDao = new UserDao();  
		        UserBean ub = userDao.getUserByName(userID);  
		        if (ub.getPassword() != null  
		                && ub.getPassword().equals(  
		                		passWord)) {  
//		            respObject = new JSONArray().put(new JSONObject().put("state",  
//		                    1)); 
		        	respObject = new JSONObject().put("state",  
		                    1);
		        }  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    } finally {  
		        respMessage = respObject == null ? "{\"state\":0}": respObject.toString();  
		        System.out.println("返回报文:" + respMessage);  
		        PrintWriter pw = response.getWriter();  
		        //pw.write(respMessage); 
		        pw.write(respMessage); 
		        pw.flush();  
		        pw.close();  
		    } 
        }
        else if (command == 1){// 如果是注册
        	try {  
		        reqMessage = command+" "+ userID +" "+passWord;  
		        System.out.println("请求报文:" + reqMessage);  
		        //reqObject = new JSONArray(reqMessage);  
		        UserDao userDao = new UserDao();  
		        UserBean ub = userDao.addUser(userID, passWord);  
		        if(ub.isSingle()){ // 如果用户注册的id没有发生重复
			        if (ub.getPassword() != null  
			                && ub.getId()!= null) {  
			//		            respObject = new JSONArray().put(new JSONObject().put("state",  
			//		                    1)); 
			        	respObject = new JSONObject().put("state",  
			                    1);
			        } 
		        }else{
		        	respObject = new JSONObject().put("state",  
		                    3);
		        }
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    } finally {  
		        respMessage = respObject == null ? "{\"state\":0}": respObject.toString();  
		        System.out.println("返回报文:" + respMessage);  
		        PrintWriter pw = response.getWriter();  
		        //pw.write(respMessage); 
		        pw.write(respMessage); 
		        pw.flush();  
		        pw.close();  
		    } 
        }
//        else if(command == 1) {  // 如果是注册
//        	Connection conn = null;  
//        	try {  
//                // 加载驱动  
//                Class.forName("org.postgresql.Driver");  
//                // 数据库连接url  
//                conn = java.sql.DriverManager.getConnection("jdbc:postgresql://"
//						+ Config.dbServerIp + ":"+Config.dbPort + "/" + Config.dbName
//						+ "?useUnicode=true&characterEncoding="+Config.dbEncoding,
//						Config.dbUser, Config.dbPwd); 
//                // 获取数据库连接  
//                System.out.println("数据库连接成功!");  
//            } catch (Exception e) {  
//                e.printStackTrace();  
//            }  
//            // 判断数据库中否连接成功  
//        	PreparedStatement prepStmt = null;
//        	try{
//			if(conn!=null){
//				// 插入注册信息的SQL语句(使用?占位符)  
//                String sql = "insert into user(id,pw)"  
//                        + "values(?,?)";
//                // 创建prepareStatement对象
//				prepStmt = conn.prepareStatement(sql);
//				// 对SQL语句中的参数动态赋值
//				prepStmt.setString(1,"username");
//				prepStmt.setString(1,"password");
//				// 执行更新操作
//				prepStmt.executeUpdate();
//			}
//			else 
//			{  
//              // 发送数据库连接错误提示信息  
//              response.sendError(500, "数据库连接错误！");  
//          }  
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				if(prepStmt!=null)
//					prepStmt.close();
//				if(prepStmt!=null)
//					prepStmt.close();
//				if(conn!=null)
//					conn.close();
//			
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//    }  
  }
} 

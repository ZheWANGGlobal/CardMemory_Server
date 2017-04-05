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
    	// ����request��response�ı���  
        request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/json;charset=UTF-8");  
        String reqMessage, respMessage;  
        // ����json���Ͷ���
        //JSONArray respObject = null;
        JSONObject respObject = null;
        
        // ��ȡ��������ֵ 
        // ע�⽫getparameter��ȡ�Ĳ���ת������������
        String  comman = request.getParameter("cmd");
        int  command = Integer.parseInt(comman);
        String userID = request.getParameter("userid");
        String passWord = request.getParameter("password");
        
        if(command == 2){  // ����ǵ�¼
		    try {  
		        reqMessage = command+" "+ userID +" "+passWord;  
		        System.out.println("������:" + reqMessage);  
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
		        System.out.println("���ر���:" + respMessage);  
		        PrintWriter pw = response.getWriter();  
		        //pw.write(respMessage); 
		        pw.write(respMessage); 
		        pw.flush();  
		        pw.close();  
		    } 
        }
        else if (command == 1){// �����ע��
        	try {  
		        reqMessage = command+" "+ userID +" "+passWord;  
		        System.out.println("������:" + reqMessage);  
		        //reqObject = new JSONArray(reqMessage);  
		        UserDao userDao = new UserDao();  
		        UserBean ub = userDao.addUser(userID, passWord);  
		        if(ub.isSingle()){ // ����û�ע���idû�з����ظ�
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
		        System.out.println("���ر���:" + respMessage);  
		        PrintWriter pw = response.getWriter();  
		        //pw.write(respMessage); 
		        pw.write(respMessage); 
		        pw.flush();  
		        pw.close();  
		    } 
        }
//        else if(command == 1) {  // �����ע��
//        	Connection conn = null;  
//        	try {  
//                // ��������  
//                Class.forName("org.postgresql.Driver");  
//                // ���ݿ�����url  
//                conn = java.sql.DriverManager.getConnection("jdbc:postgresql://"
//						+ Config.dbServerIp + ":"+Config.dbPort + "/" + Config.dbName
//						+ "?useUnicode=true&characterEncoding="+Config.dbEncoding,
//						Config.dbUser, Config.dbPwd); 
//                // ��ȡ���ݿ�����  
//                System.out.println("���ݿ����ӳɹ�!");  
//            } catch (Exception e) {  
//                e.printStackTrace();  
//            }  
//            // �ж����ݿ��з����ӳɹ�  
//        	PreparedStatement prepStmt = null;
//        	try{
//			if(conn!=null){
//				// ����ע����Ϣ��SQL���(ʹ��?ռλ��)  
//                String sql = "insert into user(id,pw)"  
//                        + "values(?,?)";
//                // ����prepareStatement����
//				prepStmt = conn.prepareStatement(sql);
//				// ��SQL����еĲ�����̬��ֵ
//				prepStmt.setString(1,"username");
//				prepStmt.setString(1,"password");
//				// ִ�и��²���
//				prepStmt.executeUpdate();
//			}
//			else 
//			{  
//              // �������ݿ����Ӵ�����ʾ��Ϣ  
//              response.sendError(500, "���ݿ����Ӵ���");  
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

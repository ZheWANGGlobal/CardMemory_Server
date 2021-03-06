package cn.domain.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.domain.cm.bean.UserArticleBean;
import cn.domain.cm.dao.UserArticleDao;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置request与response的编码  
        request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/json;charset=UTF-8");  
        String reqMessage, respMessage;  
        // 返回json类型对象
        JSONArray respArray = null;
        JSONObject respObject = null;
        
        // 获取表单中属性值 
        // 注意将getparameter获取的参数转换成整数类型
        String  comman = request.getParameter("cmd");
        int  command = Integer.parseInt(comman);
        String userID = request.getParameter("userid");
        String date = "";
        String newestDate="";
        String tag="";
        String text="";
        
        if(command == 2){  // 如果是查询
		    try {  
		    	date = request.getParameter("date");
		    	reqMessage = command+" "+ userID +" "+newestDate +" "
		        		+ tag+" "+text+" "+date;  
		        System.out.println("请求报文:" + reqMessage);  
		        //reqObject = new JSONArray(reqMessage);  
		        UserArticleDao userArticleDao = new UserArticleDao();  
		        UserArticleBean ub = userArticleDao.getArticleByNameAndDate(userID, date); 
		        if (ub.getText() != null  
		                && ub.getId()!= null && ub.getTag() != null && ub.getDate() != null) {  
		//		            respObject = new JSONArray().put(new JSONObject().put("state",  
		//		                    1)); 
		        	respObject = new JSONObject().put("state",  
		                    2);
		        	respObject = new JSONObject().put("userid",  
		                    ub.getId());
		        	respObject = new JSONObject().put("tag",  
		                    ub.getTag());
		        	respObject = new JSONObject().put("text",  
		                    ub.getText());
		        	respObject = new JSONObject().put("date",  
		                    ub.getDate());
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
        else if (command == 1){// 如果是写入
        	try {  
        		//newestDate = request.getParameter("newestDate");
        		date = request.getParameter("date");
        		tag = request.getParameter("tag");
        		text = request.getParameter("text");
		        reqMessage = command+" "+ userID +" "+newestDate +" "
		        		+ tag+" "+text+" "+date;  
		        System.out.println("请求报文:" + reqMessage);  
		        //reqObject = new JSONArray(reqMessage);  
		        UserArticleDao userArticleDao = new UserArticleDao();  
		        UserArticleBean ub = userArticleDao.addAritcle(userID, date,tag,text);  
			        if (ub.getText() != null  
			                && ub.getId()!= null && ub.getTag() != null && ub.getDate() != null) {  
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
        } else if(command == 3){
        	try {  
        		date = request.getParameter("date");
		    	reqMessage = command+" "+ userID +" "+newestDate +" "
		        		+ tag+" "+text+" "+date;  
		        System.out.println("请求报文:" + reqMessage);  
		        //reqObject = new JSONArray(reqMessage);  
		        UserArticleDao userArticleDao = new UserArticleDao();  
		        UserArticleBean ub = userArticleDao.deleteAritcle(userID, date); 
		        	respObject = new JSONObject().put("state",  
		                    3);
		        //} 
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
        } else if(command == 4){ // 如果是修改
        	try {  
        		date = request.getParameter("date");
        		//newestDate = request.getParameter("newestDate");
        		tag = request.getParameter("tag");
        		text = request.getParameter("text");
		    	reqMessage = command+" "+ userID +" "+newestDate +" "
		        		+ tag+" "+text+" "+date;  
		        System.out.println("请求报文:" + reqMessage);  
		        //reqObject = new JSONArray(reqMessage);  
		        UserArticleDao userArticleDao = new UserArticleDao();  
		        UserArticleBean ub = userArticleDao.alterAritcle(userID, date,tag,text); 
		        	respObject = new JSONObject().put("state",  
		                    4);
		        //} 
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
        }else if(command == 5){ // 如果是请求同步
        	try {  
        		
        		newestDate = request.getParameter("newestDate");
        		reqMessage = command+" "+ userID +" "+newestDate +" "
		        		+ tag+" "+text+" "+date;  
		        System.out.println("请求报文:" + reqMessage); 
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		        Date _date = sdf.parse(newestDate);
		        List<String> list=new ArrayList<String>();
		        int flag = 0 ; // 出现服务器端比客户端新，flag=1
		        // 检查是否需要想客户端输出所有数据
		        //reqObject = new JSONArray(reqMessage);  
		        UserArticleDao userArticleDao = new UserArticleDao();  
		        list = userArticleDao.getArticlesDateByName(userID);
		        Iterator<String> it1 = list.iterator();
		        while(it1.hasNext()){
		        	Date webdate = sdf.parse(it1.next());
			        
		            if(webdate.compareTo(_date)>0){  // 如果服务器端数据比客户端新
		            	flag = 1;
		            }
		        }
		        if(1 == flag){ // 服务器端确实比客户端新，获取该用户所有的文章组成json数组返回
		        	respArray = new JSONArray();
    
		        	//List<UserArticleBean> list2=new ArrayList<UserArticleBean>();
		        	List<UserArticleBean> list1=new ArrayList<UserArticleBean>();
		        	list1 = userArticleDao.getArticlesByName(userID);
		        	Iterator<UserArticleBean> it = list1.iterator();
		        	it = list1.iterator();
		        	while(it.hasNext()){
			        	UserArticleBean ub =it.next();
			        	JSONObject respOb = null;
			        	respOb = new JSONObject();
			        	respOb.put("state",  
			                    6);
			        	respOb.put("userid",  
			                    ub.getId());
			        	respOb.put("tag",  
			                    ub.getTag());
			        	respOb.put("text",  
			                    ub.getText());
			        	respOb.put("date",  
			                    ub.getDate());
			        	respArray.put(respOb); 
		        	}
		        }
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    } finally {  
		        try {
					//respMessage = respArray == null ? (respArray = new JSONArray().put(new JSONObject().put("state",  0)) ).toString(): respArray.toString();
					if(respArray == null){
						respArray = new JSONArray();
						JSONObject respOb = null;
			        	respOb = new JSONObject();
			        	respOb.put("state",  
			                    0);
			        	respOb.put("userid",  
			                    "");
			        	respOb.put("tag",  
			                   "");
			        	respOb.put("text",  
			                    "");
			        	respOb.put("date",  
			                    "");
						respArray.put(respOb);
						respMessage = respArray.toString();
						
						System.out.println("返回报文:" + respMessage);  
				        PrintWriter pw = response.getWriter();  
				        //pw.write(respMessage); 
				        pw.write(respMessage); 
				        pw.flush();  
				        pw.close(); 
					}else{
						respMessage = respArray.toString();
						
						System.out.println("返回报文:" + respMessage);  
				        PrintWriter pw = response.getWriter();  
				        //pw.write(respMessage); 
				        pw.write(respMessage); 
				        pw.flush();  
				        pw.close(); 
					}
		        	
		        } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		         
		    } 
        }
	}

}

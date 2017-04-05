package cn.domain.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.domain.cm.bean.UserArticleBean;
import cn.domain.cm.bean.UserMemorandumBean;
import cn.domain.cm.dao.UserArticleDao;
import cn.domain.cm.dao.UserMemorandumDao;

/**
 * Servlet implementation class MemorandumServlet
 */
@WebServlet("/MemorandumServlet")
public class MemorandumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemorandumServlet() {
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
				// ����request��response�ı���  
		        request.setCharacterEncoding("UTF-8");  
		        response.setContentType("text/json;charset=UTF-8");  
		        String reqMessage, respMessage;  
		        // ����json���Ͷ���
		        JSONArray respArray = null;
		        JSONObject respObject = null;
		        
		        // ��ȡ��������ֵ 
		        // ע�⽫getparameter��ȡ�Ĳ���ת������������
		        String  comman = request.getParameter("cmd");
		        int  command = Integer.parseInt(comman);
		        String userID = request.getParameter("userid");
		        String date = "";
		        String newestDate="";
		        String text="";
		        
		        if(command == 2){  // ����ǲ�ѯ
				    try {  
				    	date = request.getParameter("date");
				    	reqMessage = command+" "+ userID +" "+newestDate+" "
				        		+ " "+text+" "+date;  
				        System.out.println("������:" + reqMessage);  
				        UserMemorandumDao userMemorandumDao = new UserMemorandumDao();  
				        UserMemorandumBean ub = userMemorandumDao.getMemorandumByNameAndDate(userID, date); 
				        if (ub.getText() != null  
				                && ub.getId()!= null && ub.getDate() != null) {  
				        	respObject = new JSONObject().put("state",  
				                    2);
				        	respObject = new JSONObject().put("userid",  
				                    ub.getId());
				        	respObject = new JSONObject().put("text",  
				                    ub.getText());
				        	respObject = new JSONObject().put("date",  
				                    ub.getDate());
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
		        else if (command == 1){// �����д��
		        	try {  
		        		//newestDate = request.getParameter("newestDate");
		        		date = request.getParameter("date");
		        		text = request.getParameter("text");
				    	reqMessage = command+" "+ userID +" "+newestDate+" "
				        		+ " "+text+" "+date;  
				        System.out.println("������:" + reqMessage);  
				        UserMemorandumDao userMemorandumDao = new UserMemorandumDao();  
				        UserMemorandumBean ub = userMemorandumDao.addMemorandum(userID, date,text);  
					        if (ub.getText() != null  
					                && ub.getId()!= null  && ub.getDate() != null) {  
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
		        } else if(command == 3){
		        	try {  
		        		date = request.getParameter("date");
				    	reqMessage = command+" "+ userID +" "+newestDate+" "
				        		+ " "+text+" "+date;  
				        System.out.println("������:" + reqMessage);  
				        UserMemorandumDao userMemorandumDao = new UserMemorandumDao();    
				        UserMemorandumBean ub = userMemorandumDao.deleteMemorandum(userID, date); 
				        	respObject = new JSONObject().put("state",  
				                    3);
				        //} 
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
		        } else if(command == 4){ // ������޸�
		        	try {  
		        		date = request.getParameter("date");
		        		text = request.getParameter("text");
				    	reqMessage = command+" "+ userID +" "+newestDate+" "
				        		+ " "+text+" "+date;  
				        System.out.println("������:" + reqMessage);  
				        UserMemorandumDao userMemorandumDao = new UserMemorandumDao(); 
				        UserMemorandumBean ub = userMemorandumDao.alterMemorandum(userID, date,text); 
				        	respObject = new JSONObject().put("state",  
				                    4);
				        //} 
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
		        }else if(command == 5){ // ���������ͬ��
		        	try {  
		        		
		        		newestDate = request.getParameter("newestDate");
		        		reqMessage = command+" "+ userID +" "+newestDate+" "
				        		+ " "+text+" "+date;  
				        System.out.println("������:" + reqMessage);  

				        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				        Date _date = sdf.parse(newestDate);
				        List<String> list=new ArrayList<String>();
				        int flag = 0 ; // ���ַ������˱ȿͻ����£�flag=1
				        // ����Ƿ���Ҫ��ͻ�������������� 
				        UserMemorandumDao userMemorandumDao = new UserMemorandumDao(); 
				        list = userMemorandumDao.getMemorandumDateByName(userID);
				        Iterator<String> it1 = list.iterator();
				        while(it1.hasNext()){
				        	Date webdate = sdf.parse(it1.next()); 
				            if(webdate.compareTo(_date)>0){  // ��������������ݱȿͻ�����
				            	flag = 1;
				            }
				        }
				        if(1 == flag){ // ��������ȷʵ�ȿͻ����£���ȡ���û����е��������json���鷵��
				        	respArray = new JSONArray();
				        	List<UserMemorandumBean> list1=new ArrayList<UserMemorandumBean>();
				        	list1 = userMemorandumDao.getMemorandumByName(userID);
				        	Iterator<UserMemorandumBean> it = list1.iterator();
				        	it = list1.iterator();
				        	while(it.hasNext()){
					        	UserMemorandumBean ub =it.next();
					        	JSONObject respOb = null;
					        	respOb = new JSONObject();
					        	respOb.put("state",  
					                    6);
					        	respOb.put("userid",  
					                    ub.getId());
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
					        	respOb.put("text",  
					                    "");
					        	respOb.put("date",  
					                    "");
								respArray.put(respOb);
								respMessage = respArray.toString();
								
								System.out.println("���ر���:" + respMessage);  
						        PrintWriter pw = response.getWriter();  
						        //pw.write(respMessage); 
						        pw.write(respMessage); 
						        pw.flush();  
						        pw.close(); 
							}else{
								respMessage = respArray.toString();
								
								System.out.println("���ر���:" + respMessage);  
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

package cn.domain.cm.bean;

public class UserBean {
	private String id;
	private String password;
	private boolean single;  // 如果用户唯一，则为true，否则为false
	public UserBean() {
		
	}
	public UserBean(String id,  String password,boolean single) {
		super();
		this.id = id;
		this.password = password;
		this.single = single;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSingle() {
		return single;
	}
	public void setSingle(boolean single) {
		this.single = single;
	}
}

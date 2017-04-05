package cn.domain.cm.bean;

public class UserMemorandumBean {
	private String id;
	private String date;
	private String text;
	private boolean Single; // 以防出现同一秒上传两份备忘录的bug
	public UserMemorandumBean(){
		
	}
	public UserMemorandumBean(String id,  String _date,String _text,boolean single) {
		super();
		this.id = id;
		this.date = _date;
		this.text=_text;
		this.Single = single;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isSingle() {
		return Single;
	}
	public void setSingle(boolean single) {
		Single = single;
	}

	
}

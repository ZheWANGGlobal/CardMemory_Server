package cn.domain.cm.bean;

public class UserArticleBean {
	private String id;
	private String date;
	private String tag;
	private String text;
	private boolean Single; // 以防出现同一秒上传两份文章的bug
	public UserArticleBean(){
		
	}
	public UserArticleBean(String id,  String _date,String _tag,String _text,boolean single) {
		super();
		this.id = id;
		this.date = _date;
		this.tag = _tag;
		this.text=_text;
		this.Single = single;
	}
//	public UserArticleBean(String userID, String newestDate) {
//		// TODO Auto-generated constructor stub
//		this.id = userI
//	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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

package entity;

public class Contact {
	private int cid;
	private String fullname;
	private String email;
	private String content;
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	public Contact(int cid, String fullname, String email, String content) {
		super();
		this.cid = cid;
		this.fullname = fullname;
		this.email = email;
		this.content = content;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

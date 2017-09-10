package entity;

import java.sql.Timestamp;

public class Lands {
	private int lid;
	private String lname;
	private String description;
	private Timestamp date_create;
	private int cid;
	private String picture;
	private int area;
	private int gia;
	private String address;
	private String tenlh;
	private String dclienhe;
	private String sdt;
	private int count_views;
	private String cname;
	public Lands() {
		// TODO Auto-generated constructor stub
	}
	public Lands(int lid, String lname, String description, Timestamp date_create, int cid, String picture, int area,
			int gia, String address, String tenlh, String dclienhe, String sdt, int count_views, String cname) {
		super();
		this.lid = lid;
		this.lname = lname;
		this.description = description;
		this.date_create = date_create;
		this.cid = cid;
		this.picture = picture;
		this.area = area;
		this.gia = gia;
		this.address = address;
		this.tenlh = tenlh;
		this.dclienhe = dclienhe;
		this.sdt = sdt;
		this.count_views = count_views;
		this.cname = cname;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTenlh() {
		return tenlh;
	}
	public void setTenlh(String tenlh) {
		this.tenlh = tenlh;
	}
	public String getDclienhe() {
		return dclienhe;
	}
	public void setDclienhe(String dclienhe) {
		this.dclienhe = dclienhe;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public int getCount_views() {
		return count_views;
	}
	public void setCount_views(int count_views) {
		this.count_views = count_views;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}

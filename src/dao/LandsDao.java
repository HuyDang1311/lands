package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import constant.Defines;
import entity.Cat;
import entity.Lands;
import entity.User;

@Repository
public class LandsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Lands> getlistLandsAdmin(int offset){
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid ORDER BY l.lid DESC LIMIT ?,?";
		return jdbcTemplate.query(sql,new Object[] {offset,Defines.ROW_COUNT}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public List<Lands> getListSearch(String name) {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.lname LIKE '%"+name+"%' OR l.count_views LIKE '%"+name+"%'";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public List<Lands> getListSearchPuclic(String name) {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.lname LIKE '%"+name+"%' OR l.gia LIKE '%"+name+"%' OR l.address LIKE '%"+name+"%' OR l.area LIKE '%"+name+"%'";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public List<Lands> getlistLands(int offset){
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.active = 1 ORDER BY l.lid DESC LIMIT ?,?";
		return jdbcTemplate.query(sql,new Object[] {offset,Defines.ROW_COUNT}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public int getAddLands(Lands objLands, String filename, Timestamp date, int cid, String lname, String address) {
		String sql = "INSERT INTO lands(lname, description, date_create, cid, picture, area, gia, address, tenlh, dclienhe, sdt, count_views, care, statuscare, active, lat, lng) "
				+ "VALUE (?,?,?,?,?,?,?,?,?,?,?,0,0,0,1,?,?)";
		return jdbcTemplate.update(sql, new Object[] {lname, objLands.getDescription(), date, cid, filename, objLands.getArea(), objLands.getGia(),address,objLands.getTenlh(),objLands.getDclienhe(),objLands.getSdt(), objLands.getLat(), objLands.getLng()});
	}
	
	public int getEditLands(int id, Lands objLands, Timestamp date,int cid,String lname,String address,String filename) {
		String sql = "UPDATE lands SET lname = ?, description=?, date_create=?, cid=?, picture=?, area=?, gia=?, address=?,tenlh=?,dclienhe=?, sdt=? WHERE lid = ?";
		return jdbcTemplate.update(sql, new Object[] {lname, objLands.getDescription(), date, cid, filename, objLands.getArea(), objLands.getGia(),address,objLands.getTenlh(),objLands.getDclienhe(),objLands.getSdt(),id});
	}
	
	public int getDelUser(int id) {
		String sql = "DELETE FROM lands WHERE lid = ?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	
	public int View(int id) {
		String sql = "UPDATE lands SET count_views = count_views + 1 WHERE lid = ?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	
	public int countItem(){
		String sql = "SELECT COUNT(*) AS countLands FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int countItem(int id){
		String sql = "SELECT COUNT(*) AS countLands FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.cid ="+id;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<Lands> checkID(int cid){
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.lid = ?";
		return jdbcTemplate.query(sql,new Object[] {cid}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public Lands getObjLand(int id) {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE  lid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public List<Lands> getlistLQ(int id){
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE lid != ? ORDER BY l.lid DESC LIMIT 3 ";
		return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public int getActive(int id, int number) {
		String sql = "UPDATE lands SET active = ? WHERE lid = ?";
		return jdbcTemplate.update(sql, new Object[] {number,id});
	}
	
	public List<Lands> getLandCat(int id, int offset) {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.cid = ? AND l.active = 1 ORDER BY l.lid DESC LIMIT ?,?";
		return jdbcTemplate.query(sql,new Object[] {id,offset,Defines.ROW_COUNT}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public List<Lands> listView() {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.active = 1 ORDER BY l.count_views DESC LIMIT 5";
		return jdbcTemplate.query(sql,new Object[] {}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public int getQuanTam0(int id, int care) {
		String sql = "UPDATE lands SET care = ? WHERE lid = ?";
		return jdbcTemplate.update(sql, new Object[] {care,id});
	}
	
	public int getQuanTam1(int id, int care) {
		String sql = "UPDATE lands SET care = ? WHERE lid = ?";
		return jdbcTemplate.update(sql, new Object[] {care,id});
	}
	
	public List<Lands> getslidefirst() {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid ORDER BY L.lid DESC LIMIT 0,1";
		return jdbcTemplate.query(sql,new Object[] {}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public List<Lands> getslidesecond() {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid ORDER BY L.lid DESC LIMIT 1,2";
		return jdbcTemplate.query(sql,new Object[] {}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public List<Lands> listCare() {
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.active = 1 ORDER BY l.care DESC LIMIT 5";
		return jdbcTemplate.query(sql,new Object[] {}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
}

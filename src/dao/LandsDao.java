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
	
	public List<Lands> getlistLands(int offset){
		String sql = "SELECT l.*, c.cname AS cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid ORDER BY l.lid DESC LIMIT ?,?";
		return jdbcTemplate.query(sql,new Object[] {offset,Defines.ROW_COUNT}, new BeanPropertyRowMapper<Lands>(Lands.class));
	}
	
	public int getAddLands(Lands objLands, String filename, Timestamp date, int cid, String lname, String address) {
		String sql = "INSERT INTO lands(lname, description, date_create, cid, picture, area, gia, address, tenlh, dclienhe, sdt, count_views) "
				+ "VALUE (?,?,?,?,?,?,?,?,?,?,?,1)";
		return jdbcTemplate.update(sql, new Object[] {lname, objLands.getDescription(), date, cid, filename, objLands.getArea(), objLands.getGia(),address,objLands.getTenlh(),objLands.getDclienhe(),objLands.getSdt()});
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
}

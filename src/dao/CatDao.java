package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.PreparedStatement;

import constant.Defines;
import entity.Cat;

@Repository
public class CatDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Cat> getListCat(int offset) {
		String sql = "SELECT * FROM categories ORDER BY cid DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, Defines.ROW_COUNT },
				new BeanPropertyRowMapper<Cat>(Cat.class));
	}
	public List<Cat> getListSearch(String name) {
		String sql = "SELECT * FROM categories WHERE cname LIKE '%"+name+"%'";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Cat>(Cat.class));
	}

	public List<String> getFrameWork(String term) {
		String sql = "SELECT * FROM categories WHERE cname LIKE '%"+term+"%'";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<String>(String.class) );
	}
	
	public List<Cat> getListCat() {
		String sql = "SELECT * FROM categories";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Cat>(Cat.class));
	}

	public int getAddCat(String name) {
		String sql = "INSERT INTO categories(cname) VALUE(?)";
		return jdbcTemplate.update(sql, new Object[] { name });
	}

	public int getEditCat(Cat objCat) {
		String sql = "UPDATE categories SET cname = ? WHERE cid = ?";
		return jdbcTemplate.update(sql, new Object[] { objCat.getCname(), objCat.getCid() });
	}

	public int getDelCat(int cid) {
		String sql = "DELETE FROM categories WHERE cid = ? ";
		return jdbcTemplate.update(sql, new Object[] { cid });
	}

	public List<Cat> checkTrungTen(String name) {
		String sql = "SELECT * FROM categories WHERE cname = ?";
		return jdbcTemplate.query(sql, new Object[] { name }, new BeanPropertyRowMapper<Cat>(Cat.class));
	}

	public List<Cat> checkTrungTen(int cid, String name) {
		String sql = "SELECT * FROM categories WHERE cname = ? AND cid != ?";
		return jdbcTemplate.query(sql, new Object[] { name, cid }, new BeanPropertyRowMapper<Cat>(Cat.class));
	}

	public List<Cat> checkID(int cid) {
		String sql = "SELECT * FROM categories WHERE cid = ?";
		return jdbcTemplate.query(sql, new Object[] { cid }, new BeanPropertyRowMapper<Cat>(Cat.class));
	}

	public Cat getObjCat(int cid) {
		String sql = "SELECT * FROM categories WHERE cid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { cid }, new BeanPropertyRowMapper<Cat>(Cat.class));
	}

	public int countCat() {
		String sql = "SELECT COUNT(*) FROM categories";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public int countObjCat(int cid) {
		String sql = "SELECT COUNT(*) FROM lands WHERE cid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { cid } , Integer.class);
	}
}

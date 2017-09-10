package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import constant.Defines;
import entity.Cat;
import entity.Rank;
import entity.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<User> getListUser(int offset){
		String sql = "SELECT u.*, r.rankname FROM users AS u INNER JOIN rank AS r ON u.rank_id = r.id ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[]{offset, Defines.ROW_COUNT }, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public int getAddUser(User objUser, String fullname, String password, int id_rank) {
		String sql = "INSERT INTO users(username, fullname, password, rank_id) VALUE(?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{objUser.getUsername(),fullname,password, id_rank});
	}
	
	public int getEditUser(int id, String fullname, String password, int rid) {
		String sql = "UPDATE users SET fullname = ?, password = ?, rank_id = ? WHERE id = ?";
		return jdbcTemplate.update(sql, new Object[]{fullname, password, rid, id});
	}
	
	public int getDelUser(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	
	public List<User> checkName(String name){
		String sql = "SELECT * FROM users WHERE username = ?";
		return jdbcTemplate.query(sql,new Object[]{name}, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public List<User> checkID(int id){
		String sql = "SELECT * FROM users WHERE id = ?";
		return jdbcTemplate.query(sql,new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public User getObjUser(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public List<User> checkOldPassword(String name){
		String sql = "SELECT * FROM users WHERE password = ?";
		return jdbcTemplate.query(sql,new Object[]{name}, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public int countUser() {
		String sql = "SELECT COUNT(*) FROM users AS u INNER JOIN rank AS r ON u.rank_id = r.id";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<Rank> getListRank() {
		String sql = "SELECT * FROM rank";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Rank>(Rank.class));
	}
	
	public User getcheckOldPass(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<User>(User.class));
	}
}

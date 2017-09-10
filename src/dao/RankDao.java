package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Rank;
import entity.User;
@Repository
public class RankDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Rank> checkID(int id){
		String sql = "SELECT * FROM rank WHERE id = ?";
		return jdbcTemplate.query(sql,new Object[]{id}, new BeanPropertyRowMapper<Rank>(Rank.class));
	}
}

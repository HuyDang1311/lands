package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import constant.Defines;
import entity.Cat;
import entity.Contact;
import entity.User;
@Repository
public class ContactDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int countContact() {
		String sql = "SELECT COUNT(*) FROM vnecontact";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<Contact> getListContact(int offset) {
		String sql = "SELECT * FROM vnecontact ORDER BY cid DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, Defines.ROW_COUNT },
				new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	public List<Contact> getListSearch(String name) {
		String sql = "SELECT * FROM vnecontact WHERE fullname LIKE '%"+name+"%'";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	public List<Contact> getCheckID(int id) {
		String sql = "SELECT * FROM vnecontact WHERE cid = ?";
		return jdbcTemplate.query(sql, new Object[] {id},
				new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	public int getDelContact(int id) {
		String sql = "DELETE FROM vnecontact WHERE cid = ?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	
	public int getAddContact(Contact objContact, String fullname) {
		String sql = "INSERT INTO vnecontact(fullname, email, content) VALUE(?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{fullname,objContact.getEmail(),objContact.getContent()});
	}
}

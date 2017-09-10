package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestPassWord {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("create_password")
	@ResponseBody
	public String index() {
		return passwordEncoder.encode("123");
	}
}
